package 백준.구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1629 {

    /*
    SOLVED: 24.01.02 (화)
    곱셈 - 실버1
    https://www.youtube.com/watch?v=mzLx_NWpuSY을 참고했다.
    특정 수식을 알아야 한다는 점에서 실제 코테에서 이런 문제는 잘 나오지 않을 거 같다..
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // 입력: 입력 범위는 int에서 가능하지만 함수가 long이기 때문에 이에 맞추어 설정
        long A = Long.parseLong(tokenizer.nextToken());
        long B = Long.parseLong(tokenizer.nextToken());
        long C = Long.parseLong(tokenizer.nextToken());

        writer.write(calculate(A, B, C) + ""); // 함수 호출 후 결과 출력
        writer.close();
        reader.close();
    }

    private static long calculate(long a, long b, long c) {
        // 만약 한 번만 곱하게 된다면: 그대로 a % c 값을 리턴하면 된다.
        if (b == 1) {
            return a % c;
        }
        // 2^4 = 2^2 * 2^2 구조임을 생각하면 된다. 절반을 뜻한다.
        long half = calculate(a, b / 2, c);
        // 2^4 % 3 = (2^2 * 2^2) % 3 = {(2^2) % 3} * {(2^2) % 3}이 가능하다.
        half = (half * half) % c;
        // 만약 b가 홀수라면 (ex: 5), 한 번 더 곱해야 한다. 2^5 = (2^2) * (2^2) * (2^1)임을 생각하면 된다.
        if (b % 2 != 0) {
            half = (half * a) % c;
        }
        // 결과 리턴
        return half;
    }
}
