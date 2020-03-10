package algo_200302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 수지의수지맞는여행 {
	static int R, C;
	static char[][] map;
	static boolean[][] isVisited;
	static boolean[] alp;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		String line;
		alp = new boolean[40];

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			isVisited = new boolean[R][C];
			Arrays.fill(alp, false);
			result = 0;

			for (int i = 0; i < R; i++) {
				line = in.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			alp[map[0][0]-'A'] = true;
			isVisited[0][0] = true;
			dfs(new Pos(0, 0, 1));
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(Pos p) {
		Pos np = new Pos(0, 0, 0);
		np.cnt = p.cnt + 1;
		result = Math.max(result, p.cnt);
		
		for (int i = 0; i < 4; i++) {
			np.y = p.y + dy[i];
			np.x = p.x + dx[i];
			if (np.y < 0 || np.x < 0 || np.y >= R || np.x >= C || isVisited[np.y][np.x] || alp[map[np.y][np.x] - 'A']) {
				continue;
			}
			isVisited[np.y][np.x] = true;
			alp[map[np.y][np.x] - 'A'] = true;
			dfs(np);
			isVisited[np.y][np.x] = false;
			alp[map[np.y][np.x] - 'A'] = false;
		}
	}
}

class Pos {
	int y;
	int x;
	int cnt;

	Pos(int y, int x, int c) {
		this.y = y;
		this.x = x;
		this.cnt = c;
	}
}