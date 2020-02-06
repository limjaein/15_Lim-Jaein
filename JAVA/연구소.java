package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 연구소 {
	static int N, M;
	static int[][] arr;
	static boolean[][] isVisited;
	static ArrayList<Pos> virus;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int max_result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virus = new ArrayList<>();
		arr = new int[N][M];
		max_result = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2)
					virus.add(new Pos(i, j));
			}
		}

		Combination(0, 0, 0);
		System.out.println(max_result);
	}

	private static void Combination(int y, int x, int num) {
		if (num == 3) {
			max_result = Math.max(max_result, getSafeArea());
			return;
		}
		if (x == M) {
			y++;
			x = 0;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0)
					continue;
				arr[i][j] = 1;
				Combination(y, x + 1, num + 1);
				arr[i][j] = 0;
			}
		}
	}

	public static int getSafeArea() {
		int cnt = 0;
		isVisited = new boolean[N][M];

		for (int i = 0; i < virus.size(); i++) {
			dfs(virus.get(i).y, virus.get(i).x);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0)
					cnt++;
				else if(arr[i][j] == -1)
					arr[i][j] = 0;
			}
		}
		return cnt;
	}

	public static void dfs(int y, int x) {
		int ny, nx;
		isVisited[y][x] = true;

		for (int j = 0; j < 4; j++) {
			ny = y + dy[j];
			nx = x + dx[j];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M || arr[ny][nx] != 0 || isVisited[ny][nx])
				continue;
			arr[ny][nx] = -1;
			dfs(ny, nx);
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
