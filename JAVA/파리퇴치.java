package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파리퇴치 {
	static int N, M;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int max_result;

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			max_result = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - M + 1; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					max_result = Math.max(max_result, hit(i, j));
				}
			}
			sb.append("#").append(t).append(" ").append(max_result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int hit(int y, int x) {
		int sum = 0;
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sum += arr[y+i][x+j];
			}
		}
		
		return sum;
	}

}
