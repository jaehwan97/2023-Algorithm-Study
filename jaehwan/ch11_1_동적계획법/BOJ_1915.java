import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1915 {//[BOJ_1915]가장큰정사각형 jaehwan solved - dp 사용
    static int[][] dp;
    static int[][] map;
    static int n, m, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = str.charAt(j - 1) - '0';//문자열로 입력 받고 그 인덱스를 기준으로 다시 정수로 바꿔서 저장한다.
            }
        }

        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1) {
                    int cross = dp[i - 1][j - 1]; //왼쪽 위(대각선)
                    int up = dp[i - 1][j]; //위
                    int left = dp[i][j - 1]; //왼쪽
                    dp[i][j] = Math.min(Math.min(left, up), cross) + 1; //left,up,cross중 가장 작은 값
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        bw.write(max * max + "");
        bw.flush();
        bw.close();
    }
}
/*
배열을 무엇으로 채울까? 점화식 고민->
한가지 숫자로 정사각형을 나타내려면 한변의 길이가 필요함
한변의 길이를 어떻게 구할수 있을까?0이 아니고 1일 경우 자신의 주위를 정찰
사각형의 오른쪽 아래 꼭지점으로 정하자(반복문을 돌때 그 이전 데이터가 필요하니까)
*/
