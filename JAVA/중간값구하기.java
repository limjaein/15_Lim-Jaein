import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N, num;
        int[] arr;
         
        N = sc.nextInt();
        arr = new int[N];
        sc.nextLine();
        for(int i=0; i<N; i++)
        {
            num = sc.nextInt();
            arr[i] = num;
        }
        Arrays.sort(arr);
        System.out.println(arr[arr.length/2]);
    }
 
}
