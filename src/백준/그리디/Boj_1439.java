package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1439 {

    /*
    SOLVED: 24.02.02 (금)
    뒤집기 - 실버5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split("");
        // 간단하게 그냥 0과 1의 집합의 갯수 중 더 적은 것으로 선택하면 된다. (ex: 1001일 시 1은 2번, 0은 1번 있으므로 0 선택)
        String last = input[0]; // 첫 글자로 설정
        int zeroCount = 0;
        int oneCount = 0;
        // 첫 글자의 값에 따라 카운트 초기화
        if (last.equals("0")) {
            zeroCount++;
        } else {
            oneCount++;
        }
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals(last)) {
                continue;
            } else {
                if (last.equals("0")) { // last가 0이었고 반대라면 one++
                    oneCount++;
                } else { // last가 1이었고 반대라면 zero++
                    zeroCount++;
                }
                last = input[i]; // 이후 last 갱신
            }
        }

        writer.write(Math.min(zeroCount, oneCount) + ""); // 더 적은 것 선택
        writer.close();
        reader.close();
    }
}
