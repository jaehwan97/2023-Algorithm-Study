import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {//[BOJ_2098]외판원순회 jaehwan solving - dp, 비트 마스크, dfs사용 
    static int N;
    static int[][] c, v;//현재 도시가 C,현재까지 방문한 모든 도시 리스트가 V
    static final int INF = 16000001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        c = new int[16][16];
        v = new int[16][1<<16];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                c[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(v[i], INF);
        }

        System.out.println(dfs(0,1));
    }

    public static int dfs(int node, int visit) {
        if (visit == (1 << N) - 1) { // base case
            if (c[node][0] == 0)
                return INF;
            return c[node][0];
        }
        if (v[node][visit] != INF) { //이미 계산한적 있음 
            return v[node][visit];
        }
        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && c[node][i] != 0) {
                v[node][visit] = Math.min(v[node][visit], dfs(i, visit | (1 << i)) + c[node][i]);
            }
        }
        return v[node][visit];
    }
}
/*
n의 크기가 매우 작아 모든 순서를 완전 탐색

점화식
D[C][V] = 현재 도시가 C,현재까지 방문한 모든 도시 리스트가 V일 때, 앞으로 남은 모든 도시를 경유하는데 필요한 최소 비용

비트마스크 + dfs 필요
ex)
dp[1][0001(2)] : 현재 위치한 곳은 1번 도시이고,
여태까지 방문한 도시는 1번 도시일 때, 전체 도시를 순회하기 위해 남아있는 최소비용

dp[2][0011(2)] : 현재 위치한 곳은 2번 도시이고,
여태까지 방문한 도시는 1,2번 도시일 때,전체 도시를 순회하기 위해 남아있는 최소비용

dp[4][1111(2)] : 현재 위치한 곳은 4번 도시이고,
여태까지 방문한 도시는 1,2,3,4번 도시일 때,전체 도시를 순회하기 위해 남아있는 최소비용

비트로 바꾸는 방법은 방문시 1, 미방문시 0으로 표시후 2진수 10진수로 치환
ex)
4번, 1번 방문 -> 1001 -> D[i][9] 2^3 + 2^0
*/
