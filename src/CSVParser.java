import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static DataManager parser(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        String fieldName = null;
        DataManager dm = DataManager.getInstance();
        while ((line = br.readLine()) != null) {        // read all lines
            if (fieldName == null) {        // field name
                fieldName = line;
                dm.setFieldName(fieldName);
                continue;
            }
            dm.insert(line);
        }

        return dm;
    }
}
