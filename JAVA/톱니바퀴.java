package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 톱니바퀴 {
	static final int MAX = 4;
	static int[][] data;
	static int[] index; // 각 톱니바퀴의 첫 인덱스 저장

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input;
		int R, num, dir;
		data = new int[MAX + 1][2 * MAX];
		index = new int[MAX + 1];

		for (int i = 1; i < MAX + 1; i++) {
			input = in.readLine();
			for (int j = 0; j < input.length(); j++)
				data[i][j] = Integer.parseInt(input.charAt(j) + "");
		}
		R = Integer.parseInt(in.readLine());
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			num = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			checkLeftRight(num, dir);
		}

		System.out.println(sum());
	}

	private static int sum() {
		int cnt = 0;

		cnt += data[1][index[1]];
		cnt += data[2][index[2]] * 2;
		cnt += data[3][index[3]] * 4;
		cnt += data[4][index[4]] * 8;

		return cnt;
	}

	private static void checkLeftRight(int num, int dir) {
		int i = num;
		int left, right;
		int ndir = dir;
		int[] r = new int[MAX + 1];

		r[num] = dir;

		while (i > 1) { // 왼쪽 탐색
			left = index[i] - 2;
			if (left < 0)
				left += 2 * MAX;

			right = index[i - 1] + 2;
			right %= 2 * MAX;

			if (data[i][left] == data[i - 1][right])
				break;
			else {
				ndir *= -1;
				r[i - 1] = ndir;
			}
			i--;
		}
		i = num;
		ndir = dir;
		while (i < MAX) {
			left = index[i + 1] - 2;
			if (left < 0)
				left += 2 * MAX;

			right = index[i] + 2;
			right %= 2 * MAX;

			if (data[i][right] == data[i + 1][left])
				break;
			else {
				ndir *= -1;
				r[i + 1] = ndir;
			}
			i++;
		}

		for (i = 1; i < MAX + 1; i++) {
			if (r[i] != 0)
				rotate(i, r[i]);
		}
	}

	private static void rotate(int num, int dir) {
		index[num] -= dir;
		if (index[num] < 0)
			index[num] += 2 * MAX;
		else
			index[num] %= 2 * MAX;
	}

}
