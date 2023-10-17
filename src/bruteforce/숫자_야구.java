package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 숫자_야구 {

    private static BufferedReader reader;
    private static BufferedWriter writer;

    public static void main(String[] args) throws IOException {
        init();
        HashSet<Integer> count = new HashSet<>();
        int N = Integer.parseInt(reader.readLine());
        int[] numbers = new int[N];
        int[] strikes = new int[N];
        int[] balls = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
            strikes[i] = Integer.parseInt(st.nextToken());
            balls[i] = Integer.parseInt(st.nextToken());
        }
        for (int x = 0; x < N; x++) {
            int number = numbers[x];
            int strike = strikes[x];
            int ball = balls[x];
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    for (int k = 1; k <= 9; k++) {
                        int index = makeNumber(i, j, k);

                        if (i == j || j == k || i == k) {
                            count.add(index);
                            continue;
                        }
                        String userNumber = String.valueOf(number);
                        String testNumber = String.valueOf(index);
                        if (countStrike(userNumber, testNumber) != strike) {
                            count.add(Integer.parseInt(testNumber));
                            continue;
                        }
                        if (countBall(userNumber, testNumber) != ball) {
                            count.add(Integer.parseInt(testNumber));
                        }
                    }
                }
            }
        }

        int temp = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    if (count.contains(makeNumber(i, j, k))) {
                        continue;
                    }
                    temp++;
                }
            }
        }
        writer.write(temp + "");
        close();
    }

    private static void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static int makeNumber(final int hundred, final int ten, final int one) {
        return hundred * 100 + ten * 10 + one;
    }

    private static int countStrike(String originNumber, String testNumber) {
        int count = 0;
        String[] originTokens = originNumber.split("");
        String[] testTokens = testNumber.split("");
        for (int i = 0; i < 3; i++) {
            if (originTokens[i].equals(testTokens[i])) {
                count++;
            }
        }
        return count;
    }

    private static int countBall(String originNumber, String testNumber) {
        char[] originTokens = originNumber.toCharArray();
        char[] testTokens = testNumber.toCharArray();

        boolean[] onlyOrigin = new boolean[3];
        boolean[] onlyTest = new boolean[3];
        Arrays.fill(onlyOrigin, true);
        Arrays.fill(onlyTest, true);

        for (int i = 0; i < 3; i++) {
            if (originTokens[i] == testTokens[i]) {
                onlyOrigin[i] = false;
                onlyTest[i] = false;
            }
        }

        HashSet<Character> originSet = new HashSet<>();
        HashSet<Character> testSet = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            if (onlyOrigin[i]) {
                originSet.add(originTokens[i]);
            }
            if (onlyTest[i]) {
                testSet.add(testTokens[i]);
            }
        }

        originSet.retainAll(testSet);

        return originSet.size();
    }


    private static void close() throws IOException {
        writer.close();
        reader.close();
    }
}
