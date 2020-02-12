package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Magnetic {
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = 10;
		int result;
		boolean isMoved;

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			do {
				isMoved = move();
			} while (isMoved);
			result = count();
			sb.append("#").append(t + 1).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean move() {
		boolean isMoved = false;

		for (int i = N - 1; i >= 0; i--) { // N극 체크
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					if (i == N - 1) {
						arr[i][j] = 0;
						if (!isMoved)
							isMoved = true;
						continue;
					}
					if (arr[i + 1][j] == 0) {
						arr[i][j] = 0;
						arr[i + 1][j] = 1;
						if (!isMoved)
							isMoved = true;
					}
				}
			}
		}

		for (int i = 0; i < N; i++) { // S극 체크
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 2) {
					if (i == 0) {
						arr[i][j] = 0;
						if (!isMoved)
							isMoved = true;
						continue;
					}
					if (arr[i - 1][j] == 0) {
						arr[i][j] = 0;
						arr[i - 1][j] = 2;
						if (!isMoved)
							isMoved = true;
					}
				}
			}
		}
		return isMoved;
	}

	private static int count() {
		int state = 2; // S극
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[j][i] == 0)
					continue;
				if(state != arr[j][i]) { // 다른 극이면
					state = arr[j][i];
					if(arr[j][i] == 2) 
						cnt++;
				}
			}
		}
		return cnt;
	}

}


