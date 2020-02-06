package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 드래곤커브 {
	static int MAX = 100;
	static int N;
	static Curve[] list;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int x, y, d, g;
		arr = new boolean[MAX + 1][MAX + 1];

		N = Integer.parseInt(in.readLine());
		list = new Curve[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			arr[y][x] = true;
			rotate(y + dy[d], x + dx[d], d, g);
		}

		System.out.println(count());
	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				if (arr[i][j] && arr[i][j + 1] && arr[i + 1][j + 1] && arr[i + 1][j])
					cnt++;
			}
		}
		return cnt;
	}

	private static void rotate(int y, int x, int d, int max) {
		Stack<Integer> st = new Stack<>(); // 끝점, 방향 저장
		Stack<Integer> next_st = new Stack<>();
		int n_d;

		st.add(d);
		next_st.add(d);
		arr[y][x] = true;

		for (int i = 0; i < max; i++) {
			while (!st.isEmpty()) {
				d = st.peek();
				st.pop();
				n_d = (d + 1) % 4;
				y += dy[n_d];
				x += dx[n_d];
				next_st.add(n_d); // 파생된 선들
			}
			st.addAll(next_st);
		}

		arr[y][x] = true;

		while (!next_st.isEmpty()) {
			d = next_st.peek();
			next_st.pop();
			y -= dy[d];
			x -= dx[d];
			arr[y][x] = true;
		}
	}
}

class Curve {
	int x;
	int y;
	int d;

	Curve(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}