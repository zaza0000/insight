import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class DataManager {
    private static DataManager _instance = null;
    private static String pattern = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";        // comma outside quotes
    private static Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static Map<String, Map<Integer, ProductYear>> productYearMap = null;
    private static Map<String, Integer> fieldToIndex = null;
    private static Set<Long> complaintsID = null;
    private static String fieldName = null;

    private DataManager() {
        productYearMap = new HashMap<>();
        complaintsID = new HashSet<>();
    }

    public void setFieldName (String fieldName) {
        if (this.fieldName == null) {
            this.fieldName = fieldName;
            fieldToIndex = new HashMap<>();
            String[] fieldNameArray = this.fieldName.split(pattern);
            for (int i = 0; i < fieldNameArray.length; i++) {
                fieldToIndex.put(fieldNameArray[i], i);
            }
        } else {
            System.out.println("can not set twice");
        }
    }

    public void insert(String newComplaints) {      // insert a line
        if (_instance == null) {
            System.out.println("need to create a Data Manager");
            return;
        }
        if (fieldName == null) {
            System.out.println("need to set up field name first");
            return;
        }
        String[] arr = newComplaints.split(pattern);
        if (arr.length != fieldToIndex.size()) {
            return;
        }
        if (!numPattern.matcher(arr[fieldToIndex.get("Complaint ID")]).matches()) {
            return;
        }
        long id = Integer.valueOf(arr[fieldToIndex.get("Complaint ID")]);
        if (complaintsID.contains(id)) {        // avoid adding a duplicate record
            return;
        }
        complaintsID.add(id);
        String product = arr[fieldToIndex.get("Product")].toLowerCase();
        String company = arr[fieldToIndex.get("Company")];
        int year = 0;
        if (arr[fieldToIndex.get("Date received")].indexOf("-") != -1) {
            year = Integer.valueOf(arr[fieldToIndex.get("Date received")].split("-")[0]);
        } else {
            year = 2000+ Integer.valueOf(arr[fieldToIndex.get("Date received")].split("/")[2]);
        }
        if (!productYearMap.containsKey(product)) {
            productYearMap.put(product, new HashMap<>());
        }
        Map<Integer, ProductYear> py = productYearMap.get(product);
        if (!py.containsKey(year)) {
            py.put(year, new ProductYear(year));
        }
        py.get(year).insert(company);
    }

    public static DataManager getInstance() {
        if (_instance == null) {
            _instance = new DataManager();
        }
        return _instance;
    }

    public static void clear() {        // reset the DataManager
        for (Map<Integer, ProductYear> m : productYearMap.values()) {
            for (ProductYear py : m.values()) {
                py.clear();
            }
            m.clear();
        }
        productYearMap.clear();
        productYearMap = null;
        fieldToIndex.clear();
        fieldToIndex = null;
        complaintsID.clear();
        complaintsID = null;
        fieldName = null;
        _instance = null;
    }

    public class ProductYear {
        private int year;
        private int totalComplaints;
        private int maxComplaint;
        private Map<String, Integer> companyToTimes;

        public ProductYear(int year) {
            companyToTimes = new HashMap<>();
            this.year = year;
            totalComplaints = 0;
            maxComplaint = 0;
        }

        public void insert(String company) {
            int times = 0;
            if (companyToTimes.containsKey(company)) {
                times = companyToTimes.get(company);
            }
            times++;
            companyToTimes.put(company, times);
            totalComplaints++;
            maxComplaint = Math.max(maxComplaint, times);
        }

        public int getYear() {
            return year;
        }

        public int getTotalComplaints() {
            return totalComplaints;
        }

        public int getNumOfCompany() {
            return companyToTimes.size();
        }

        public int getHighestPercentage() {
            if (totalComplaints == 0) {
                return 0;
            }
            return Math.toIntExact(Math.round(100.0*(double)maxComplaint / (double)totalComplaints));
        }

        public void clear() {
            companyToTimes.clear();
            companyToTimes = null;
        }
    }

    public void printInfo(String outputFile) throws IOException {
        if (_instance == null) {
            System.out.println("need to create a Data Manager");
            return;
        }
        File f = new File(outputFile);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        List<String> productList = new ArrayList<>(productYearMap.keySet());
        Collections.sort(productList);
        for (String product : productList) {
            Map<Integer, ProductYear> py = productYearMap.get(product);
            List<Integer> yearList = new ArrayList<>(py.keySet());
            Collections.sort(yearList);
            for (int year : yearList) {
                ProductYear ins = py.get(year);
                bw.write(product + "," + year + "," + ins.getTotalComplaints() + ","
                        + ins.getNumOfCompany() + "," + ins.getHighestPercentage());
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
