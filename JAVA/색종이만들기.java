package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이만들기 {
	static final int WHITE = 0;
	static final int BLUE = 1;

	static int N;
	static int[][] arr;
	static int b_cnt;
	static int w_cnt;
	static int[] dx = { 0, 1, 0, 1 };
	static int[] dy = { 0, 1, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		b_cnt = 0;
		w_cnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		countPaper(0, 0, N);
		System.out.println(w_cnt);
		System.out.println(b_cnt);
	}

	public static void countPaper(int y, int x, int size) {
		int half = size / 2;
		if (!isPossible(y, x, size)) {
			for (int i = 0; i < 4; i++) {
				countPaper(y + dy[i] * half, x + dx[i] * half, half);
			}
		} else {
			if (arr[y][x] == WHITE)
				w_cnt++;
			else
				b_cnt++;
		}
	}

	public static boolean isPossible(int y, int x, int size) {
		int color = arr[y][x];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[y + i][x + j] != color) {
					return false;
				}
			}
		}
		return true;
	}
}
