package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시 {
	static final int LEFT = 0;
	static final int UP = 1;
	static final int RIGHT = 2;
	static final int DOWN = 3;
	static int N, M;
	static int[][] room;
	static ArrayList<CCTV> list;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		list = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		result = N * M;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] > 0 && room[i][j] < 6)
					list.add(new CCTV(i, j, room[i][j]));
			}
		}
		if (list.size() > 0) {
			search(0);
		} else {
			result = count();
		}
		System.out.println(result);
	}

	public static void check(int y, int x, int dir) {
		while (true) {
			y += dy[dir];
			x += dx[dir];
			if (y < 0 || x < 0 || y >= N || x >= M || (room[y][x] > 0 && room[y][x] <= 6))
				return;
			room[y][x] = -1;
		}
	}

	public static void uncheck(int y, int x, int dir) {
		while (true) {
			y += dy[dir];
			x += dx[dir];
			if (y < 0 || x < 0 || y >= N || x >= M  || (room[y][x] > 0 && room[y][x] <= 6))
				return;
			room[y][x] = 0;
		}
	}

	public static void search(int idx) {
		if (idx == list.size()) {
			result = Math.min(result, count());
			return;
		}

		switch (list.get(idx).type) {
		case 1:
			for (int i = 0; i < 4; i++) {
				check(list.get(idx).y, list.get(idx).x, i);
				search(idx + 1);
				uncheck(list.get(idx).y, list.get(idx).x, i);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				check(list.get(idx).y, list.get(idx).x, i);
				check(list.get(idx).y, list.get(idx).x, i + 2);
				search(idx + 1);
				uncheck(list.get(idx).y, list.get(idx).x, i);
				uncheck(list.get(idx).y, list.get(idx).x, i + 2);
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				check(list.get(idx).y, list.get(idx).x, i);
				check(list.get(idx).y, list.get(idx).x, (i + 1) % 4);
				search(idx + 1);
				uncheck(list.get(idx).y, list.get(idx).x, i);
				uncheck(list.get(idx).y, list.get(idx).x, (i + 1) % 4);
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				check(list.get(idx).y, list.get(idx).x, i);
				check(list.get(idx).y, list.get(idx).x, (i + 1) % 4);
				check(list.get(idx).y, list.get(idx).x, (i + 2) % 4);
				search(idx + 1);
				uncheck(list.get(idx).y, list.get(idx).x, i);
				uncheck(list.get(idx).y, list.get(idx).x, (i + 1) % 4);
				uncheck(list.get(idx).y, list.get(idx).x, (i + 2) % 4);
			}
			break;
		case 5:
			for (int i = 0; i < 4; i++)
				check(list.get(idx).y, list.get(idx).x, i);
			
			search(idx + 1);

			for (int i = 0; i < 4; i++)
				uncheck(list.get(idx).y, list.get(idx).x, i);
		
			break;
		}
	}

	public static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (room[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}

class CCTV {
	int y;
	int x;
	int type;

	CCTV(int y, int x, int type) {
		this.y = y;
		this.x = x;
		this.type = type;
	}
}