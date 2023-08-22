import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2342 {//[BOJ_2342]DDR jaehwan solved - dp 사용
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new int[100001][5][5];
        //이동할때 걸리는 비용 그래프
        //                0  1  2  3  4
        int mp[][] = {  { 0, 2, 2, 2, 2 },
                        { 2, 1, 3, 4, 3 },
                        { 2, 3, 1, 3, 4 },
                        { 2, 4, 3, 1, 3 },
                        { 2, 3, 4, 3, 1 } };
        int n = 0, s = 1;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 100001; k++)
                    dp[k][i][j] = 100001 * 4;
        dp[0][0][0] = 0;
        while (true) {
            n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;
            for (int i = 0; i < 5; i++) {//오른쪼 다리
                if (n == i)
                    continue;
                for (int j = 0; j < 5; j++) {

                    dp[s][i][n] = Math.min(dp[s - 1][i][j] + mp[j][n], dp[s][i][n]);
                }
            }
            for (int j = 0; j < 5; j++) {
                if (n == j)
                    continue;
                for (int i = 0; i < 5; i++) {
                    dp[s][n][j] = Math.min(dp[s - 1][i][j] + mp[i][n], dp[s][n][j]);
                }
            }
            s++;
        }
        s--;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                min = Math.min(min, dp[s][i][j]);
        System.out.println(min);
    }
}

/*

1 - 2 - 2 - 4 = 8
d[][][]로 자리수를 정했다면 이자리를 채우기위해서 뭘 채워야할까?

d[n-1][l][r'] = ?

오른발
d[n][l][r]=min(d[n-1][l][i]+mp[i][r])

왼발
d[n][l][r]=min(d[n-1][i][r]+mp[i][l])

*/
