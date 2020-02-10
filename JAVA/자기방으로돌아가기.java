package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 자기방으로돌아가기 {
	static int N;
	static movement[] student;
	static int[] corridor;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine().trim());
		int l, r, tmp;

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			student = new movement[N];
			corridor = new int[201];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				l = Integer.parseInt(st.nextToken());
				r = Integer.parseInt(st.nextToken());
				if (l % 2 != 0)
					l++;
				if (r % 2 != 0)
					r++; 
				if (l > r) {
					tmp = l;
					l = r;
					r = tmp;
				}
				student[i] = new movement(l, r);
			}
			System.out.println("#" + (t + 1) + " " + move());
		}
	}

	public static int move() {
		int result = 1;
		for (int i = 0; i < N; i++) {
			for (int l = student[i].left/2; l < student[i].right/2 + 1; l++) {
				corridor[l]++;
				result = Math.max(result, corridor[l]);
			}
		}
		return result;
	}
}

class movement {
	int left;
	int right;

	public movement(int l, int r) {
		// TODO Auto-generated constructor stub
		left = l;
		right = r;
	}
}