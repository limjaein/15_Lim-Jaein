package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파스칼의삼각형 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int N;
		int[][] arr;

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][];
			int max = 1;
			for (int i = 0; i < N; i++) {
				arr[i] = new int[max];
				for (int k = 0; k < max; k++) {
					if (k == 0 || k == (max - 1))
						arr[i][k] = 1;
					else
						arr[i][k] = arr[i - 1][k] + arr[i - 1][k - 1];
				}
				max++;
			}

			sb.append("#").append(t).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
