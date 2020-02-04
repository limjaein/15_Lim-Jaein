package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 사다리조작 {
	public static int N, M, H;
	public static boolean[][] ladder;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladder = new boolean[H][N + 2];
		int a, b;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ladder[a - 1][b] = true;
		}

		// 가로선 0,1,2,3
		for (int i = 0; i < 4; i++) {
			if (combination(0, 0, i)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println("-1");
		return;
	}

	private static boolean isPossible(int y, int x) {
		return (ladder[y][x] || ladder[y][x - 1] || ladder[y][x + 1]) ? false : true;
	}

	private static boolean isFinished() {
		int r;
		for (int row = 1; row < N + 1; row++) {
			r = row;
			for (int col = 0; col < H; col++) {
				if (ladder[col][r])
					r += 1;
				else if (ladder[col][r - 1])
					r -= 1;
			}
			if (r != row)
				return false;
		}
		return true;
	}

	private static boolean combination(int y, int cnt, int pick) {
		if (cnt == pick) {
			if (isFinished())
				return true;
			else
				return false;
		}
		
		for (int col = y; col < H; col++) {
			for (int row = 1; row < N; row++) {
				if (isPossible(col, row)) {
					ladder[col][row] = true;
					if (combination(col, cnt + 1, pick))
						return true;
					ladder[col][row] = false;
				}
			}
		}
		return false;
	}

}