import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteCSV {
    String fileName;
    PrintWriter pw;
    public WriteCSV(String fileName) throws IOException {
        this.fileName = fileName;
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        this.pw = new PrintWriter(bw);
    }


    public void saveData(ArrayList<String> a, boolean build) {
        if (build) {
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<a.size();i++) {
                sb.append(a.get(i));
                if (i!=a.size()-1) sb.append(",");
            }
            this.pw.println(sb.toString());
        }
        else {
            for(String s:a) {
                this.pw.println(s);
            }
        }

    }

    public void endCurrentFile() {
        this.pw.close();
    }
}
