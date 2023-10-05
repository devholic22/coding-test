package basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class 셀프_넘버 {

    private static BufferedWriter bw;
    private static boolean[] numbers;

    public static void main(String[] args) throws IOException {
        init();
        initArray();
        storeSelfNumbers();
        print();
        close();
    }

    private static void init() {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void initArray() {
        numbers = new boolean[10001];
    }

    private static void storeSelfNumbers() {
        for (int i = 1; i <= 10000; i++) {
            makeNumber(String.valueOf(i));
        }
    }

    private static void makeNumber(String number) {
        if (number.equals("10000")) {
            return;
        }

        int temp = 0;
        if (number.length() < 2) {
            temp = 2 * Integer.parseInt(number);
        } else {
            temp += Integer.parseInt(number);
            for (int i = 0; i < number.length(); i++) {
                temp += Integer.parseInt(number.split("")[i]);
            }
        }
        if (temp >= 10001) {
            return;
        }
        numbers[temp] = true;
    }

    private static void print() throws IOException {
        for (int i = 1; i <= 10000; i++) {
            if (!numbers[i]) {
                bw.write(i + "\n");
            }
        }
    }

    private static void close() throws IOException {
        bw.close();
    }
}
