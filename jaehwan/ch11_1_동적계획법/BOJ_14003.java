import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14003 { //[BOJ_14003]LIS jaehwan solved - dp 사용
    static int N=0, maxLength=0;
    static int A[] = new int[1000001];//수열 데이터 저장
    static int B[] = new int[1000001];//현재 가장 유력한 증가 수열
    static int D[] = new int[1000001];//0~i까지 i를 포함하는 최장 증가 수열의 길이
    static int ans[] = new int[1000001];//정답수열 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken()); //A배열에 값 저장
        }
        int index;
        B[++maxLength] = A[1];//B[1]=A[1]초기화
        D[1] = 1; //여기도 초기화
        for (int i = 2; i <= N; i++) {
            if (B[maxLength] < A[i]) {  //가장 마지막 수열보다 현재 수열이 큰 경우
                maxLength++;            //최대 수열길이 한칸 증가
                B[maxLength] = A[i];    //더큰걸 발견했으니 배열 끝네 값을 추가
                D[i] = maxLength;       //길이 추가
            } else { //이제 이 부분을 체크해줘야함
                //자신이 들어갈 수 있는 위치를 찾는 시간을 줄이기위해 이진탐색 사용
                index = binarysearch(1, maxLength, A[i]); // B배열에서 data[i]보다 처음으로 크거나 같아지는 원소의 index 찾기
                B[index] = A[i]; //현재 수열값 저장
                D[i] = index;    //최장 길이를 갱신
            }
        }
        System.out.println(maxLength); //가장 긴 증가하는 부분 수열 길이 출력
        index = maxLength;

        for (int i = N; i >= 1; i--) { //뒤에서 부터 탐색하면서 정답 수열 저장하기
            if (D[i] == index ) { //제일 큰 값부터 하나씩 찾아서 다음 배열로 이동 1까지
                ans[index] = A[i];
                index--;
            }
        }
        for (int i = 1; i <= maxLength; i++)
            System.out.print(ans[i] + " "); //출력할 배열 출력하기
    }
    // 현재 수열이 들어 갈 수 있는 위치를 빠르게 찾아주기 위한 바이너리 서치 구현
    static int binarysearch(int l, int r, int now) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (B[mid] < now)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
/*
점화식 D[i]=max({D[k]})+1(k=1~i-1)

* */
