package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시험감독 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr;
		long cnt = 0;
		int N = Integer.parseInt(in.readLine());
		int a, b;
		arr = new int[N];

		if (N == 1)
			arr[0] = Integer.parseInt(in.readLine());
		else {
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(in.readLine(), " ");
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			arr[i] -= a;
			cnt++;
			if (arr[i] > 0) {
				cnt += arr[i] / b;
				if (arr[i] % b != 0)
					cnt++;
			}
		}
		System.out.println(cnt);
	}

}
