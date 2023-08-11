import java.util.Scanner;

public class BOJ_10844 {//[BOJ_10844]계단수 jaehwan solved - dp 사용
    static long mod = 1000000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long D[][] = new long[N + 1][11];
        for (int i = 1; i <= 9; i++) {
            D[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            D[i][0] = D[i - 1][1];//0이면 옆자리는 1만 가능
            D[i][9] = D[i - 1][8];//9면 0불가 8만 가능
            for (int j = 1; j <= 8; j++) {
                D[i][j] = (D[i - 1][j - 1] + D[i - 1][j + 1]) % mod;
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + D[N][i]) % mod;
        }
        System.out.println(sum);
    }
}
/*
# 주의 사항
1) N번째 자릿수의 자릿값이 0인 경우 : 다음 자릿수의 자릿값은 1밖에 올 수 없다.

2) N번째 자릿수의 자릿값이 9인 경우 : 다음 자릿수의 자릿값은 8밖에 올 수 없다.

예제 해석

예제 -> 1
1-> 1
2-> 1,3
3-> 2,4
4-> 3,5
5-> 4,6
6-> 5,7
7-> 6,8
8-> 7,9
9-> 8
여기서 중복을 모두 제외시 1,2,3,4,5,6,7,8,9 총 9가지가 남는다

예제 입력 2
10
12
23
34
45
56
67
78
89

21
32 처럼 뒤집은거 8개

1 + 8 + 8 = 17

3 입력시 
1 + 3 + 3 + 4 + 4 + 4 + 4 + 4 + 3 + 2 = 32

*/
