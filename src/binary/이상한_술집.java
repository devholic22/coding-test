package binary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 이상한_술집 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static long[] kettles;
    private static int kettle;
    private static int person;
    private static long maxKettle;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        kettle = Integer.parseInt(tokenizer.nextToken());
        maxKettle = Integer.MIN_VALUE;
        kettles = new long[(int) kettle];
        person = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < kettle; i++) {
            kettles[i] = Long.parseLong(reader.readLine());
            maxKettle = Math.max(kettles[i], maxKettle);
        }
        writer.write(binary() + "");
        close();
    }

    private static void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static long binary() {
        long start = 0;
        long end = maxKettle;
        if (maxKettle == 1) {
            return 1;
        }
        while (start <= end) {
            long mid = (start + end) / 2;
            long canPerson = calculatePersonAllKettle(mid);
            if (canPerson >= person) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start < 1 ? 0 : start - 1;
    }

    private static long calculatePersonAllKettle(long alchol) {
        long sum = 0;
        for (long kettle : kettles) {
            sum += calculateMaxPersonEachAlchol(kettle, alchol);
        }
        return sum;
    }

    private static long calculateMaxPersonEachAlchol(long kettle, long alchol) {
        if (alchol == 0) {
            return 0;
        }
        return kettle / alchol;
    }

    private static void close() throws IOException {
        writer.close();
        reader.close();
    }
}
