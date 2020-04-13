import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputDir = "./input";
        String outputDir = "./output";
        // get all csv files from the input folder
        File FileList = new File(inputDir);
        List<File> CSVList = new ArrayList<>();
        for(File f: FileList.listFiles()) {
            if (f.getPath().indexOf(".csv") == -1) {
                continue;
            }
            CSVList.add(f);
        }
        // create output file folder
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (File csv : CSVList) {
            System.out.println("Processing " + csv.getName() + "...");
            DataManager dm = CSVParser.parser(csv);
            dm.printInfo(outputDir+"/Result_"+csv.getName());
            System.out.println("Output file: " + outputDir+"/Result_"+csv.getName() + " created!");
            DataManager.clear();
        }

    }
}
