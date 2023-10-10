package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 체스판_다시_칠하기 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static String[] board;
    private static int MAX_X;
    private static int MAX_Y;

    public static void main(String[] args) throws IOException {
        init();
        initSize();
        drawBoard();
        int sol = Integer.MAX_VALUE;
        for (int y = 0; y <= MAX_Y - 8; y++) {
            for (int x = 0; x <= MAX_X - 8; x++) {
                int curSol = getSolution(y, x, board);
                if (sol > curSol) {
                    sol = curSol;
                }
            }
        }
        bw.write(sol + "");
        close();
    }

    private static void drawBoard() throws IOException {
        board = new String[MAX_Y];
        for (int y = 0; y < MAX_Y; y++) {
            board[y] = br.readLine();
        }
    }

    private static void initSize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        MAX_Y = Integer.parseInt(st.nextToken());
        MAX_X = Integer.parseInt(st.nextToken());
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static int getSolution(int startY, int startX, String[] board) {
        String[] orgBoard = {"WBWBWBWB", "BWBWBWBW"};
        int whiteSol = 0;
        for (int y = 0; y < 8; y++) {
            int row = startY + y;
            for (int x = 0; x < 8; x++) {
                int col = startX + x;
                if (board[row].charAt(col) != orgBoard[row % 2].charAt(x)) {
                    whiteSol++;
                }
            }
        }
        return Math.min(whiteSol, 64- whiteSol);
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
