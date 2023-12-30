package 프로그래머스.lv2;

public class JadenCase_문자열_만들기 {

    /*
    SOLVED: 23.12.30 (토)
    JadenCase 문자열 만들기 - Lv2
    너무 중복된 코드가 많아 비효율적으로 풀은 것 같다.
     */
    public String solution(String s) {
        StringBuilder builder = new StringBuilder("");
        String[] tokens = s.split(" ");

        for (int i = 0; i < tokens.length; i++) {
            String[] values = tokens[i].split("");
            if (!isAlphabet(values[0])) {
                for (int j = 1; j < values.length; j++) {
                    values[j] = values[j].toLowerCase();
                }
                builder.append(String.join("", values));
                if (i != tokens.length - 1) {
                    builder.append(" ");
                }
                continue;
            }
            values[0] = values[0].toUpperCase();
            for (int j = 1; j < values.length; j++) {
                values[j] = values[j].toLowerCase();
            }
            builder.append(String.join("", values));
            if (i != tokens.length - 1) {
                builder.append(" ");
            }
        }

        // 마지막에 원래 공백이었을 경우 없어졌으므로 공백 추가
        for (int i = builder.toString().length(); i < s.length(); i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private boolean isAlphabet(String s) {
        for (char i = 'a'; i <= 'z'; i++) {
            String alphabet = String.valueOf(i);
            String alphabetUpper = alphabet.toUpperCase();
            if (alphabet.equals(s) || alphabetUpper.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
