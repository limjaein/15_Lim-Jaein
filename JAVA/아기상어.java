package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
	static int N;
	static Fish shark;
	static int time;
	static int[][] space;
	static boolean[][] isVisited = new boolean[N][N];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		space = new int[N][N];
		time = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if (space[i][j] == 9) {
					shark = new Fish(2, i, j, 0);
					space[i][j] = 0;
				}
			}
		}

		solution();
		System.out.println(time);
	}

	private static void solution() {
		PriorityQueue<Fish> fishes = new PriorityQueue<>(new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				if (o1.dist > o2.dist) // 거리
					return 1;
				else if(o1.dist < o2.dist)
					return -1;
				else {
					if (o1.y > o2.y)  // 위쪽
						return 1;
					else if(o1.y < o2.y)
						return -1;
					else {
						if (o1.x > o2.x) // 왼쪽
							return 1;
						else
							return -1;
					}
				}
			}
		});
		Queue<Fish> q = new LinkedList<>();
		Fish d_fish;
		Fish tmp;
		int cnt = 0;
		int ny, nx, y, x;

		while (true) {
			isVisited = new boolean[N][N];
			q.add(shark);
			isVisited[shark.y][shark.x] = true;

			while (!q.isEmpty()) {
				tmp = q.poll();
				y = tmp.y;
				x = tmp.x;
				if (space[y][x] != 0 && space[y][x] < shark.size) {
					fishes.offer(tmp);
				}
				for (int i = 0; i < 4; i++) {
					ny = y + dy[i];
					nx = x + dx[i];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || isVisited[ny][nx])
						continue;
					isVisited[ny][nx] = true;
					if (space[ny][nx] <= shark.size)
						q.offer(new Fish(space[ny][nx], ny, nx, tmp.dist + 1));
				}
			}

			if (fishes.size() == 0)
				return;

			d_fish = fishes.poll();
			time += d_fish.dist;

			shark.y = d_fish.y;
			shark.x = d_fish.x;
			space[d_fish.y][d_fish.x] = 0;
			fishes.clear();
			q.clear();
			cnt++;

			if (shark.size == cnt) {
				shark.size++;
				cnt = 0;
			}
		}
	}

}

class Fish {
	int size;
	int y;
	int x;
	int dist;

	Fish(int size, int y, int x, int dist) {
		this.size = size;
		this.y = y;
		this.x = x;
		this.dist = dist;
	}
}