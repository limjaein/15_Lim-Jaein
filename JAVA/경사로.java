package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로 {
	static int N, L;
	static int[][] arr;
	static boolean[] isPossible;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		isPossible = new boolean[N];
		boolean row = true;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		cnt = 2 * N;
		for (int i = 0; i < N; i++) {
			Arrays.fill(isPossible, true);
			for (int j = 0; j < N - 1; j++) {
				if (!checkRow(i, j, row, arr[i][j] - arr[i][j + 1])) {
					cnt--;
					break;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(isPossible, true);
			for (int j = 0; j < N - 1; j++) {
				if (!checkRow(i, j, !row, arr[j][i] - arr[j + 1][i])) {
					cnt--;
					break;
				}
			}
		}
		System.out.println(cnt);
	}

	private static boolean checkRow(int y, int x, boolean isRow, int diff) {
		int i = 0;
		int n_x;
		
		if (diff > 1 || diff < -1)
			return false;

		if (diff == 0)
			return true;
		else if(diff == -1)
			x++;

		n_x = x;
		while (i < L) {
			n_x += diff;

			if (n_x < 0 || n_x >= N || !isPossible[n_x])
				return false;
			
			if(isRow) {
				if(arr[y][x] - arr[y][n_x] != 1)
					return false;
				isPossible[n_x] = false;
			}else {
				if(arr[x][y] - arr[n_x][y] != 1)
					return false;
				isPossible[n_x] = false;
			}
			i++;
		}
		return true;
	}

}
