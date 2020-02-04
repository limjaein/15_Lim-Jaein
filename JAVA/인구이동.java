package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동 {
	static int N, L, R;
	static int cnt;
	static int[][] country;
	static boolean[][] isVisited;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		country = new int[N][N];
		isVisited = new boolean[N][N];
		cnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				country[i][j] = Integer.parseInt(st.nextToken());
		}

		checkCountry();
		System.out.println(cnt);
	}

	private static void checkCountry() {
		boolean isFinished = false;

		while (isFinished == false) {
			isFinished = true;
			for (int i = 0; i < N; i++)
				Arrays.fill(isVisited[i], false);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isVisited[i][j]) {
						if (move(i, j))
							isFinished = false;
					}
				}
			}
			if (!isFinished)
				cnt++;
		}
	}

	private static boolean move(int y, int x) {
		ArrayList<Pos> list = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		Pos p;
		int ny, nx, diff;
		int sum = 0;

		list.add(new Pos(y, x));
		q.add(0);
		isVisited[y][x] = true;

		while (!q.isEmpty()) {
			p = list.get(q.peek());
			q.poll();

			for (int i = 0; i < 4; i++) {
				ny = p.y + dy[i];
				nx = p.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || isVisited[ny][nx])
					continue;

				diff = Math.abs(country[p.y][p.x] - country[ny][nx]);
				if (diff < L || diff > R)
					continue;
				isVisited[ny][nx] = true;
				list.add(new Pos(ny, nx));
				q.add(list.size() - 1);
			}
		}

		if (list.size() == 1)
			return false;

		for (int i = 0; i < list.size(); i++)
			sum += country[list.get(i).y][list.get(i).x];

		sum = sum / list.size();

		for (int i = 0; i < list.size(); i++)
			country[list.get(i).y][list.get(i).x] = sum;

		return true;
	}

}

class Pos {
	int y;
	int x;

	Pos(int y, int x) {
		this.y = y;
		this.x = x;
	}
}