package algo_200303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ≈ª√‚ {
	static int R, C;
	static char[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][][] isVisited;
	static Pos D, S;
	static Queue<Pos> water;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		String line = "";
		int result = 0;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isVisited = new boolean[R][C][2];
		water = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			line = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'D') {
					D = new Pos(i, j, 0);
				} else if (map[i][j] == 'S') {
					S = new Pos(i, j, 0);
				} else if (map[i][j] == '*') {
					water.offer(new Pos(i, j, -1));
				}
			}
		}
		result = bfs();
		if (result == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(result);
		}
	}

	public static int bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		int w_cnt, s_cnt;
		q.offer(S);
		Pos p, cur;
		Pos np = new Pos(0, 0, 0);

		while (!q.isEmpty()) {
			s_cnt = q.size();
			for (int i = 0; i < s_cnt; i++) {
				cur = q.poll();
				if (map[cur.y][cur.x] != 'S') { // π∞ø° ∏‘»˚
					continue;
				}
				// ∞ÌΩøµµƒ° ¿Ãµø
				for (int j = 0; j < 4; j++) {
					np.y = cur.y + dy[j];
					np.x = cur.x + dx[j];
					if (np.y < 0 || np.x < 0 || np.y >= R || np.x >= C || map[np.y][np.x] == 'S') {
						continue;
					}
					if (map[np.y][np.x] == 'D') {
						return cur.cnt + 1;
					} else if (map[np.y][np.x] == '.') {
						map[np.y][np.x] = 'S';
						q.offer(new Pos(np.y, np.x, cur.cnt + 1));
					}
				}
			}

			w_cnt = water.size();
			// π∞ ¿Ãµø
			for (int i = 0; i < w_cnt; i++) {
				p = water.poll();
				for (int j = 0; j < 4; j++) {
					np.y = p.y + dy[j];
					np.x = p.x + dx[j];
					np.cnt = p.cnt + 1;
					if (np.y < 0 || np.x < 0 || np.y >= R || np.x >= C || map[np.y][np.x] == '*') {
						continue;
					}
					if (map[np.y][np.x] == '.' || map[np.y][np.x] == 'S') {
						map[np.y][np.x] = '*';
						water.offer(new Pos(np.y, np.x, -1));
					}
				}
			}
		}
		return -1;
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
