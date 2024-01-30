package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_11501 {

    /*
    SOLVED: 24.01.30 (화)
    주식 - 실버2
    https://sorryday.tistory.com/120 참고
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine());
/*
        시도했던 방법
        모든 반례를 다 맞췄는데 왜 4%에서 계속 틀렸는지 도저히 모르겠다..
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine()); // 2 <= N <= 1,000,000
            int[] days = new int[N];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                days[j] = Integer.parseInt(tokenizer.nextToken());
            }
            ArrayDeque<Integer> maxStocks = new ArrayDeque<>();
            long maxValue = 0;
            for (int j = 0; j < N; j++) {
                maxValue = Math.max(maxValue, days[j]);
            }
            for (int j = 0; j < N; j++) {
                if (days[j] == maxValue) {
                    maxStocks.addLast(j);
                }
            }
            long result = 0;
            int curr = 0;
            while (!maxStocks.isEmpty() && curr < N) {
                int index = maxStocks.pollFirst();
                for (int k = curr; k < index; k++) {
                    result += (days[index] - days[k]);
                }
                curr = index;
            }
            if (curr < N) {
                PriorityQueue<Stock> remainStocks = new PriorityQueue<>();
                for (int j = curr; j < N; j++) {
                    remainStocks.add(new Stock(j, days[j]));
                }
                while (!remainStocks.isEmpty()) {
                    Stock stock = remainStocks.poll();
                    for (int j = curr; j < N; j++) {
                        if (j > stock.index || stock.cost < days[j]) {
                            continue;
                        }
                        result += (stock.cost - days[j]);
                    }
                    curr = stock.index;
                }
            }
            writer.write(result + "\n");
        }
        writer.close();
        reader.close();
    }

    private static class Stock implements Comparable<Stock> {
        private int index;
        private int cost;

        public Stock(final int index, final int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(final Stock other) {
            return Integer.compare(other.cost, this.cost);
        }
    }
*/

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine());
            int[] days = new int[N];

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                days[j] = Integer.parseInt(tokenizer.nextToken());
            }

            long maxValue = 0;
            long result = 0;

            int length = N - 1;

            for (int j = length; j >= 0; j--) {
                if (maxValue < days[j]) {
                    maxValue = days[j];
                } else {
                    result += (maxValue - days[j]);
                }
            }

            writer.write(result + "\n");
        }

        writer.close();
        reader.close();
    }
}
