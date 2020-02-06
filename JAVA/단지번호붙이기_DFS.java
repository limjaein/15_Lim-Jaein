package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 단지번호붙이기_DFS {
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
					dfs(i, j);
					result.add(cnt);
				}
			}
		}

	}

	private static void dfs(int y, int x) {
		int ny, nx;

		isVisited[y][x] = true;
		cnt++;

		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N || arr[ny][nx] == 0 || isVisited[ny][nx])
				continue;
			dfs(ny, nx);
		}
	}
}
