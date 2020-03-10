package algo_200309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 오나의여신님 {
	static int N, M;
	static char[][] map;
	static Pos start;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static Queue<Pos> dq;
	static boolean[][] DisVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		String line;
		int result;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			dq = new LinkedList<>();
			DisVisited = new boolean[N][M];
			map = new char[N][M];
			
			for (int i = 0; i < N; i++) {
				line = in.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'S') {
						start = new Pos(i, j, 0);
					} else if (map[i][j] == '*') {
						dq.add(new Pos(i, j, 0));
						DisVisited[i][j] = true;
					}
				}
			}
			result = bfs();
			
			sb.append("#").append(t + 1).append(" ");
			if (result == -1) {
				sb.append("GAME OVER");
			} else {
				sb.append(result);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int bfs() {
		Queue<Pos> hq = new LinkedList<>();
		boolean[][] HisVisited = new boolean[N][M];
		Set<Pos> route = new HashSet<>();
		int h_cnt, d_cnt;
		Pos cur;
		int ny, nx;

		hq.offer(start);
		HisVisited[start.y][start.x] = true;

		while (!hq.isEmpty()) {
			d_cnt = dq.size();
			for (int i = 0; i < d_cnt; i++) {
				cur = dq.poll();
				for (int j = 0; j < 4; j++) {
					ny = cur.y + dy[j];
					nx = cur.x + dx[j];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M || DisVisited[ny][nx] || map[ny][nx] == 'D'
							|| map[ny][nx] == 'X') {
						continue;
					}
					dq.offer(new Pos(ny, nx, 0));
					route.add(new Pos(ny, nx, 0));
					DisVisited[ny][nx] = true;
				}
			}
			h_cnt = hq.size();
			for (int i = 0; i < h_cnt; i++) {
				cur = hq.poll();
				for (int j = 0; j < 4; j++) {
					ny = cur.y + dy[j];
					nx = cur.x + dx[j];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M || HisVisited[ny][nx] || DisVisited[ny][nx]
							|| map[ny][nx] == 'X') {
						continue;
					}
					if (map[ny][nx] == 'D') {
						return cur.cnt + 1;
					}
					hq.offer(new Pos(ny, nx, cur.cnt + 1));
					HisVisited[ny][nx] = true;
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
	
	@Override
	public boolean equals(Object obj) {
		Pos p = (Pos)obj;
		if(this.y == p.y && this.x == p.x)
			return true;
		else
			return false;
	}

	Pos(int y, int x, int c) {
		this.y = y;
		this.x = x;
		this.cnt = c;
	}
}