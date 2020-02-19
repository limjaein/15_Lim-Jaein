package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 배열돌리기4 {
	static final int MAX = 5001;
	static int N, M, K;
	static boolean[] isChecked;
	static Info[] list;
	static int[][] input;
	static Stack<Integer> stack;
	static int min_row;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int r, c, s;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new int[N + 1][M + 1];
		list = new Info[K];
		isChecked = new boolean[K];
		stack = new Stack<>();
		min_row = MAX;

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j < M + 1; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			list[i] = new Info(r, c, s);
		}

		combination();
		System.out.println(min_row);
	}

	private static void combination() {
		if (stack.size() == K) {
			// 회전 계산
			min_row = Math.min(min_row, calMinRow());
			return;
		}

		for (int i = 0; i < K; i++) {
			if (isChecked[i])
				continue;
			stack.push(i);
			isChecked[i] = true;
			combination();
			isChecked[i] = false;
			stack.pop();
		}
	}

	private static int calMinRow() {
		Iterator<Integer> it = stack.iterator();
		int[][] tmp = new int[N + 1][M + 1];
		int sum;
		int result = MAX;
		int y, x, size;
		int r, c;
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				tmp[i][j] = input[i][j];
			}
		}

		while (it.hasNext()) {
			int idx = it.next();
			// idx 번째의 info로 rotate
			y = list[idx].r - list[idx].s;
			x = list[idx].c - list[idx].s;
			size = list[idx].s * 2;
			r = 0;
			c = 0;
			while (size > 1) {
				r = y;
				c = x;
				q.offer(tmp[r][c]);

				while (c++ < x + size) {
					q.offer(tmp[r][c]);
					tmp[r][c] = q.poll();
				}
				c--;

				while (r++ < y + size) {
					q.offer(tmp[r][c]);
					tmp[r][c] = q.poll();
				}
				r--;

				while (c-- > x) {
					q.offer(tmp[r][c]);
					tmp[r][c] = q.poll();
				}
				c++;

				while (r-- > y) {
					q.offer(tmp[r][c]);
					tmp[r][c] = q.poll();
				}

				y += 1;
				x += 1;
				size -= 2;
				q.clear();
			}

		}

		for (int i = 1; i < N + 1; i++) {
			sum = 0;
			for (int j = 1; j < M + 1; j++) {
				sum += tmp[i][j];
			}
			result = Math.min(result, sum);
		}
		return result;
	}
}

class Info {
	int r, c, s;

	Info(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}