package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class 암호_만들기 {

    private static final String VOWELS = "aeiou";
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int SELECT;
    private static boolean[] used;
    private static char[] temp;
    private static char[] alphabets;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        SELECT = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        alphabets = new char[N];
        temp = new char[SELECT];
        used = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alphabets);
        backtracking(0);
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void backtracking(int depth) {
        if (depth == SELECT && isAnyVowel(String.valueOf(temp)) && isHaveConsonantMinimum(String.valueOf(temp))) {
            print();
            return;
        } else if (depth == SELECT) {
            return;
        }
        for (int i = 0; i < N; i++) {
            if (used[i]) {
                continue;
            }
            if (depth > 0 && ((int) temp[depth - 1] > (int) alphabets[i])) {
                continue;
            }
            used[i] = true;
            temp[depth] = alphabets[i];
            backtracking(depth + 1);
            temp[depth] = ' ';
            used[i] = false;
        }
    }

    private static boolean isAnyVowel(String temp) {
        return Arrays.stream(temp.split(""))
                .anyMatch(VOWELS::contains);
    }

    private static boolean isHaveConsonantMinimum(String temp) {
        int count = 0;
        String[] list = temp.split("");
        for (int i = 0; i < SELECT; i++) {
            if (!VOWELS.contains(list[i])) {
                count++;
            }
        }
        return count >= 2;
    }

    private static void print() {
        for (int i = 0; i < SELECT; i++) {
            System.out.print(temp[i]);
        }
        System.out.println();
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
