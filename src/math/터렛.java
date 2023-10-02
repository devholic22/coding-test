package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 터렛 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        init();
        int test = Integer.parseInt(br.readLine());
        for (int i = 0; i < test; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + (Math.pow(y2 - y1, 2)));
            if (distance == 0) {
                if (r1 == r2) {
                    bw.write("-1\n");
                } else {{
                    bw.write("0\n");
                }}
            } else {
                if (Math.abs(r1 - r2) < distance && distance < r1 + r2) {
                    bw.write("2\n");
                } else if (distance == r1 + r2 || Math.abs(r1 - r2) == distance) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }
            }
        }
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
