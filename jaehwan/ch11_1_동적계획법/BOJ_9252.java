import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class BOJ_9252{//[B0J_9252]LCS jaehwan solved - dp 사용
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();
        int len1 = s1.length;
        int len2 = s2.length;
        int[][] dp = new int[len1+1][len2+1];

        for(int i=1; i<len1+1; i++) {
            for(int j=1; j<len2+1; j++) {
                if(s1[i-1] != s2[j-1]) //비교한 값이 다를 경우 배열의 왼쪽 값중 큰값을 선택해 저장
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                else //값이 같으면 대각선 왼쪽 +1 값을 저장
                    dp[i][j] = dp[i-1][j-1] + 1;
            }
        }

        int ans = dp[len1][len2];
        System.out.println(ans);
        int i = len1;
        int j = len2;

        Stack<Character> stack = new Stack<>(); //출력 스택

        while(i >=1 && j >= 1) {
            // 위와 같은 경우 위로 이동
            if(dp[i][j] == dp[i-1][j])
                i--;
                // 왼쪽과 같은 경우 왼쪽으로 이동
            else if(dp[i][j] == dp[i][j-1])
                j--;
            else {
                // 대각선으로 같다면
                stack.push(s1[i-1]);
                i--; j--;
            }
        }

        while(!stack.isEmpty())
            System.out.print(stack.pop());
    }
}
//2차원 배열을을 사용해야한다 자기위 왼쪽 값에+1 이 붙어서 이어진다 이 뜻은 같은 글자가 하나 늘어났다는 뜻이다.
//즉 중복된 문자열을 발견하면 +1을 해주고 매칸마다 자기위 왼쪽칸의 값을 가진다 아니면 기존 값중 가장 큰값으로이어진다.

//순열이라 순서도 중요한데 순서대로 비교해서 나아가므로 처음부터 중복된 값을 계속 계산해가면 되는 문제였고 문자열 처리가 익숙하지 않았다.
