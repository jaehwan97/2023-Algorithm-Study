import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1935 {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int[] arr;
    public static void main(String[] args) throws IOException {
        int n=Integer.parseInt(br.readLine()); //숫자
        arr=new int[n];
        String s = br.readLine();//표기식
        Stack<Double> st=new Stack<>();

        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if('A' <= ch && ch <= 'Z'){ //알파벳이면
                st.push((double)arr[ch-'A']); //숫자로 만들어서 넣어줌
            }
            else { //연산식이다.
                double a=st.pop();
                double b=st.pop();
                switch (ch){
                    case '+':
                        st.push(b+a);
                        break;
                    case '-':
                        st.push(b-a);
                        break;
                    case '*':
                        st.push(b*a);
                        break;
                    case '/':
                        st.push(b/a);
                        break;
                }
            }
        }
        System.out.printf("%.2f", st.pop());
    }
}
//ABC*+DE/-
//(A+B*C) - (D/E) =(1+2*3)-(4/5) = 7 - 0.8= 6.2

//7-0.8 =6.2
