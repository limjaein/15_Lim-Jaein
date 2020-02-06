package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 단지번호붙이기_BFS {
	static int N;
	static int[][] arr;
	static int cnt;
	static boolean[][] isVisited;
	static ArrayList<Integer> result;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		isVisited = new boolean[N][N];
		result = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			line = in.readLine();
			for (int j = 0; j < line.length(); j++)
				arr[i][j] = Integer.parseInt(line.charAt(j) + "");
		}

		search();
		print();
	}

	private static void print() {
		Collections.sort(result);
		System.out.println(result.size());

		for (int i : result)
			System.out.println(i);
	}

	private static void search() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1 && !isVisited[i][j]) {
					cnt = 0;
					bfs(i, j);
					result.add(cnt);
				}
			}
		}

	}

	private static void bfs(int y, int x) {
		Queue<Pos> q = new LinkedList<>();
		int ny, nx;
		Pos cur;

		isVisited[y][x] = true;
		cnt++;
		q.offer(new Pos(y, x));

		while (!q.isEmpty()) {
			cur = q.poll();

			for (int i = 0; i < 4; i++) {
				ny = cur.y + dy[i];
				nx = cur.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || arr[ny][nx] == 0 || isVisited[ny][nx])
					continue;

				isVisited[ny][nx] = true;
				cnt++;
				q.offer(new Pos(ny, nx));
			}
		}
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
