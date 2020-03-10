package algo_200309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 벽돌깨기 {
	static int N, W, H;
	static int[][] map;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { -1, 0, 1, 0 };
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			result = H * W;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, map);
			sb.append("#").append(t + 1).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int round, int[][] arr) {
		int[][] origin = new int[H][W];
		int[][] tmp = new int[H][W];
		if (round == N) {
			result = Math.min(result, count(arr));
			return;
		}
		copy(origin, arr);
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (origin[j][i] > 0) {
					// 배열 복사
					copy(tmp, origin);
					// 터트리기
					boom(j, i, tmp);
					down(tmp);
					dfs(round + 1, tmp);
					break;
				}
			}
		}
	}

	private static int count(int[][] arr) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void down(int[][] arr) {
		Stack<Integer> st = new Stack<>();
		int diff;
		for (int x = 0; x < W; x++) {
			for (int y = H - 1; y >= 0; y--) {
				if (arr[y][x] != 0) {
					st.push(arr[y][x]);
				}
			}
			diff = H - st.size();
			for (int i = 0; i < diff; i++) {
				st.push(0);
			}
			for (int y = 0; y < H; y++) {
				arr[y][x] = st.pop();
			}
		}
	}

	private static void boom(int y, int x, int[][] arr) {
		int dist;
		Queue<position> q = new LinkedList<>();
		position cur;
		q.offer(new position(y, x));
		int ny, nx;

		while (!q.isEmpty()) {
			cur = q.poll();

			if (arr[cur.y][cur.x] == 0) { // 다른데서 처리됨
				continue;
			}
			dist = arr[cur.y][cur.x] - 1;
			arr[cur.y][cur.x] = 0;
			if (dist == 0) {
				continue;
			}
			for (int i = 0; i < 4; i++) {
				ny = cur.y;
				nx = cur.x;
				for (int d = 0; d < dist; d++) {
					ny += dy[i];
					nx += dx[i];
					if (ny < 0 || nx < 0 || ny >= H || nx >= W || arr[ny][nx] == 0) {
						continue;
					}
					q.offer(new position(ny, nx));
				}
			}
		}
	}

	private static void copy(int[][] new_arr, int[][] arr) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				new_arr[i][j] = arr[i][j];
			}
		}
	}
}

class position {
	int y;
	int x;

	position(int y, int x) {
		this.y = y;
		this.x = x;
	}
}