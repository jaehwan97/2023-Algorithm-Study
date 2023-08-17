import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1328 {//[BOJ_1328]빌딩 jaehwan solved - dp 사용
    static final int MOD=1000000007;
    static int N,L,R;
    static long[][][] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        dp=new long[101][101][101];
        dp[1][1][1]=1;
        dp[2][2][1]=dp[2][1][2]=1;
        for (int n = 3; n <= N; n++) {
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    dp[n][l][r] = (dp[n-1][l-1][r] + dp[n-1][l][r-1] + dp[n-1][l][r]*(n-2)) % MOD;
                }
            }
        }
        System.out.println(dp[N][L][R]);
    }
}
//3차월 배열
/*
건물이
1개일때 1
d[1][1][1]
2개일때 2
d[2][2][1] d[2][1][2]
3개일때 6
d[3][2][2] * 2
d[3][3][1] d[3][1][3]
d[3][2][1] d[3][1][2]

(높이가 모두 다른 건물이라서 가능)
dp[n][l][r]가 되는 경우의 수를 구하려면, 다음과 같이 3가지 경우가 있다.

1. n-1개의 건물을 세웠을 때, 왼쪽에서 보이는 빌딩의 개수가 (l-1)개, 오른쪽에서 보이는 빌딩의 개수가
   r개인 상태(dp[n-1][l-1][r])에서 가장 왼쪽에 n번째 빌딩을 세우는 경우


2. n-1개의 건물을 세웠을 때, 왼쪽에서 보이는 빌딩 개수가 l개, 오른쪽에서 보이는 빌딩 개수가 (r-1)개인
   상태(dp[n-1][l][r-1])에서 가장 오른쪽에 n번째 빌딩을 세우는 경우


3. n-1개의 건물을 세워을 때, 왼쪽에서 보이는 빌딩 개수가 l개, 오른쪽에서 보이는 빌딩 개수가 r개인
   상태(dp[n-1][l][r])에서 중간에 n번째 빌딩을 세우는 경우
   이 때, 중간에 세울 수 있는 경우의 수는 양 끝을 제외한 (n-2)가지가 있다.

이를 바탕으로 점화식을 세우면,

★ dp[n][l][r]은 우리가 정의할 배열의 수이므로 그 이전 값들의 경우의 수를 더해서 가진다.

dp[n][l][r] = dp[n-1][l-1][r] + dp[n-1][l][r-1] + dp[n-1][l][r]*(n-2)
*/
