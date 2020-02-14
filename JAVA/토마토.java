package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토 {
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] stock;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		stock = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				stock[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
	}

	private static int solution() {
		Pos p = new Pos(-1, -1);
		int ny,nx;
		int time = 0;
		Queue<Pos> next_list = new LinkedList<>();
		Queue<Pos> list = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (stock[i][j] == 1)
					list.add(new Pos(i, j));
			}
		}

		while (true) {
			while(!list.isEmpty()) {
				p = list.poll();
				for (int k = 0; k < 4; k++) {
					ny = p.y + dy[k];
					nx = p.x + dx[k];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M || stock[ny][nx] != 0)
						continue;
					stock[ny][nx] = 1;
					next_list.offer(new Pos(ny, nx));
				}
			}

			if(next_list.size() == 0){
				if(isAllRiped())
					return time;
				else
					return -1;
			}
			
			list.addAll(next_list);
			next_list.clear();
			time++;
		}
	}

	private static boolean isAllRiped() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (stock[i][j] == 0)
					return false;
			}
		}
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
