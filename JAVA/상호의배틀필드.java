package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의배틀필드 {
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;

	static int H, W, N;
	static char[][] map;
	static String input;
	static char[] shell = { '^', '>', 'v', '<' };
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;
		int T = Integer.parseInt(in.readLine());
		int y = 0, x = 0;
		boolean isFind;

		for (int t = 0; t < T; t++) {
			isFind = false;
			st = new StringTokenizer(in.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];

			for (int i = 0; i < H; i++) {
				line = in.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					if (isFind)
						continue;

					for (int s = 0; s < 4; s++) {
						if (map[i][j] == shell[s]) {
							y = i;
							x = j;
							isFind = true;
						}
					}
				}
			}
			N = Integer.parseInt(in.readLine());
			input = in.readLine();
			startGame(y, x);
			System.out.print("#" + (t + 1) + " ");
			printMap();
		}

	}

	private static void printMap() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	private static void startGame(int y, int x) {
		int ny, nx;
		int dir = 0;
		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
			case 'U':
				dir = UP;
				map[y][x] = shell[UP];
				break;
			case 'D':
				dir = DOWN;
				map[y][x] = shell[DOWN];
				break;
			case 'L':
				dir = LEFT;
				map[y][x] = shell[LEFT];
				break;
			case 'R':
				dir = RIGHT;
				map[y][x] = shell[RIGHT];
				break;
			case 'S':
				dir = -1;
				break;
			default:
				break;
			}

			if (dir == -1) { // S
				for (int s = 0; s < 4; s++) {
					if (map[y][x] == shell[s]) {
						dir = s;
						break;
					}
				}
				ny = y;
				nx = x;
				while (true) {
					ny += dy[dir];
					nx += dx[dir];
					if(ny < 0 || ny >= H || nx < 0 || nx >= W || map[ny][nx] == '#')
						break;
					if (map[ny][nx] == '*') {
						map[ny][nx] = '.';
						break;
					}
				}
			} else {
				map[y][x] = shell[dir];
				ny = y + dy[dir];
				nx = x + dx[dir];
				if (ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] != '.')
					continue;
				map[y][x] = '.';
				y = ny;
				x = nx;
				map[y][x] = shell[dir];
			}
		}
	}

}
