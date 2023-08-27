import java.io.IOException;
import java.util.Scanner;

public class BOJ_11049 {//[BOJ_11049]행렬곱연산최솟값 jaehwan solved - dp 사용
    static int N;
    static Matrix[] M;
    static int[][] D;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = new Matrix[N + 1];
        D = new int[N + 1][N + 1];

        for (int i = 0; i < D.length; i++)
            for (int j = 0; j < D[i].length; j++)
                D[i][j] = -1;
        for (int i = 1; i <= N; i++) {
            int y = sc.nextInt();
            int x = sc.nextInt();
            M[i] = new Matrix(y, x);
        }
        System.out.println(excute(1, N));
    }

    // top-down 재귀적 방식
    static int excute(int s, int e) {
        int result = Integer.MAX_VALUE;
        if (D[s][e] != -1) //이미 계산한 구간
            return D[s][e];
        if (s == e) //행렬이 1개면 1개일때 구하는 연산인데 연산이 필요하지 않으므로 0리턴
            return 0;
        if (s + 1 == e) // 행렬이 2개일때는 기본식
            return M[s].y * M[s].x * M[e].x;
        for (int i = s; i < e; i++) //행렬이 3개 이상일때
            //한칸 VS 나머지 / 두칸 VS 나머지 이런식으로 비교해 나감
           result = Math.min(result, M[s].y * M[i].x * M[e].x + excute(s, i) + excute(i + 1, e));
        return D[s][e] = result;
    }
    static class Matrix {
        private final int y;
        private final int x;

        Matrix(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
/*
곱셈 순서에 따라 값이 달라짐 -> 이중 최소값을 구해라

초반 몇개 해보는게 어려움

행렬의 곱을 하려면 왼쪽 행렬의 열과 오른쪽 행렬의 행의 크기가 같아야만 가능함 즉 인접 행렬 끼리만 곱셈이 가능

DP[i][j] = 필요한 곱셈 연산 수 최솟값
*/
