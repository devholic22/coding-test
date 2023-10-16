package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class a_plus_b_4 {

    public static void main(String args[]) throws Exception {
        BufferedWriter reader = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader writer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            String line = writer.readLine();
            if (line == null || line.equals("")) {
                break;
            }
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            reader.write(a + b + "\n");
        }

        reader.flush();
        reader.close();
        writer.close();
    }
}
