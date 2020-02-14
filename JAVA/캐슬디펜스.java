package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 캐슬디펜스 {
	static int MAX_CNT = 3;
	static int N, M, D;
	static int[][] map;
	static boolean[] isVisited;
	static int max_result;
	static ArrayList<enemy> list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisited = new boolean[M];
		max_result = 0;
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					list.add(new enemy(i, j, N * M));
				}
			}
		}

		combination(0, 0);
		System.out.println(max_result);
	}

	private static void combination(int idx, int cnt) {

		if (cnt == MAX_CNT) {
			max_result = Math.max(max_result, shooting());
			return;
		}
		
		if (idx >= M)
			return;

		isVisited[idx] = true;
		combination(idx + 1, cnt + 1);
		isVisited[idx] = false;
		combination(idx + 1, cnt);

	}

	private static int shooting() {
		PriorityQueue<enemy> q = new PriorityQueue<>(new Comparator<enemy>() {
			@Override
			public int compare(enemy o1, enemy o2) {
				if (o1.dist > o2.dist) {
					return 1;
				} else if (o1.dist == o2.dist) {
					if (o1.x > o2.x) {
						return 1;
					}
				}
				return -1;
			}
		});
		int dist;
		int cnt = 0;
		ArrayList<enemy> enemies = new ArrayList<>();
		ArrayList<enemy> kill = new ArrayList<>();
		int[][] arr = new int[N][M];

		// 초기화
		enemies.addAll(list);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = map[i][j];
			}
		}

		while (enemies.size() > 0) {
			// 공격
			for (int i = 0; i < M; i++) {
				if (!isVisited[i])
					continue;

				// 선택된 궁수일 때
				for (int j = 0; j < enemies.size(); j++) {
					dist = (N - enemies.get(j).y) + Math.abs(i - enemies.get(j).x);
					if (dist > D)
						continue;
					enemies.get(j).dist = dist;
					q.offer(enemies.get(j));
				}

				if (!q.isEmpty()) { // 적 체크
					kill.add(q.poll());
				}
				q.clear();
			}

			// 쏜다
			for (int i = 0; i < kill.size(); i++) {
				if (arr[kill.get(i).y][kill.get(i).x] == 1) {
					arr[kill.get(i).y][kill.get(i).x] = 0;
					cnt++;
				}
			}
			kill.clear();
			enemies.clear();

			// 이동
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1) {
						if (i == N - 1) { // 마지막 칸
							arr[i][j] = 0;
						} else {
							arr[i][j] = 0;
							arr[i + 1][j] = 1;
							enemies.add(new enemy(i + 1, j, N * M));
						}
					}
				}
			}
		}
		return cnt;
	}
}

class enemy {
	int y;
	int x;
	int dist;

	enemy(int y, int x, int d) {
		this.y = y;
		this.x = x;
		dist = d;
	}
}
