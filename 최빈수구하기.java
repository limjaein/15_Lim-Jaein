import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public final static int ST_MAX = 1000;
    public final static int SC_MAX = 101;
    public static int[] cnt;
     
    public static int find()
    {
        int score = 0;
        int max_count = 0;
         
        for(int i=0; i<SC_MAX; i++)
        {
            if(cnt[i] >= max_count)
            {
                max_count = cnt[i];
                score = i;
            }
        }
        return score;
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T, num;
        int[] result;
        T = sc.nextInt();
        result = new int[T];
        cnt = new int[SC_MAX];
        sc.nextLine();
        for(int i=0; i<T; i++)
        {
            Arrays.fill(cnt, 0);
            sc.nextLine();
            for(int j=0; j<ST_MAX; j++)
            {
                num = sc.nextInt();
                cnt[num]++;
            }
            result[i] = find();
        }
        for(int i=0; i<T; i++)
            System.out.println("#"+(i+1)+" "+result[i]);
    }
}