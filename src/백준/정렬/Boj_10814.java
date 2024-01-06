package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_10814 {

    private static class Member implements Comparable<Member> {

        private int index;
        private int age;
        private String name;

        public Member(final int index, final int age, final String name) {
            this.index = index;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Member other) {
            if (this.age == other.age) {
                return Integer.compare(this.index, other.index);
            }
            return Integer.compare(this.age, other.age);
        }
    }

    /*
    SOLVED: 24.01.07 (토)
    나이순 정렬 - 실버5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000
        Member[] members = new Member[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int age = Integer.parseInt(tokenizer.nextToken());
            String name = tokenizer.nextToken();
            members[i] = new Member(i, age, name);
        }

        Arrays.sort(members);
        for (Member member : members) {
            writer.write(member.age + " " + member.name + "\n");
        }

        writer.close();
        reader.close();
    }
}
