package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class 검증수 {

    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();
        String[] number = br.readLine().split(" ");
        int result = 0;
        for (String each : number) {
            result += (int) Math.pow(Integer.parseInt(each), 2);
        }
        System.out.println(result % 10);
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private static void close() throws IOException {
        br.close();
    }
}
