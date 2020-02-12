package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 행렬찾기 {
	static int N;
	static int[][] arr;
	static boolean[][] isVisited;
	static PriorityQueue<Square> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		list = new PriorityQueue<>();
		int T = Integer.parseInt(in.readLine());
		Square s;
		int size;

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(in.readLine());
			arr = new int[N][N];
			isVisited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] != 0 && !isVisited[i][j]) {
						isVisited[i][j] = true;
						findSquare(i, j);
					}
				}
			}

			size = list.size();
			System.out.print("#" + t + " " + size + " ");
			for (int i = 0; i < size; i++) {
				s = list.poll();
				System.out.print(s.row + " " + s.col + " ");
			}
			System.out.println();
		}

	}

	private static void findSquare(int y, int x) {
		Square sq = new Square(1, 1);

		sq.col = expandCol(y, x);
		sq.row = expandRow(y, x, sq.col);

		list.offer(sq);
	}

	private static int expandCol(int y, int x) {
		int max_x = 1;

		for (int i = x + 1; i < N; i++) {
			if (arr[y][i] == 0)
				break;
			else {
				isVisited[y][i] = true;
				max_x++;
			}
		}

		return max_x;
	}

	private static int expandRow(int y, int x, int max_x) {
		int max_y = 1;
		boolean RowCheck;

		for (int i = y + 1; i < N; i++) {
			RowCheck = true;
			for (int j = 0; j < max_x; j++) {
				if (arr[i][x + j] == 0) {
					RowCheck = false;
					break;
				}
			}
			if (RowCheck) {
				for (int j = 0; j < max_x; j++) {
					isVisited[i][x + j] = true;
				}
				max_y++;
			}else
				break;
		}

		return max_y;
	}
}

class Square implements Comparable<Square> {
	int row;
	int col;

	Square(int r, int c) {
		row = r;
		col = c;
	}

	@Override
	public int compareTo(Square s) {
		int my_area = row*col;
		int t_area = s.row*s.col;
		if(my_area > t_area)
			return 1;
		else if(my_area == t_area) {
			if(row > s.row)
				return 1;
		}
		return -1;
	}
}