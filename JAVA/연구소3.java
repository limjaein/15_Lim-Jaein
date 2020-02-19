package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소3 {
	static int N, M;
	static int[][] arr;
	static ArrayList<pos> virus;
	static boolean[] isVisited;
	static int result;
	static int total_cnt;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		virus = new ArrayList<>();
		total_cnt = 0;
		result = N*N;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					virus.add(new pos(i, j, 0));
				} else if (arr[i][j] == 0) {
					total_cnt++;
				}
			}
		}
		isVisited = new boolean[virus.size()];
		
		if(total_cnt == 0) {
			System.out.println(0);
			return;
		}

		for (int i = 0; i < virus.size(); i++) {
			combination(i, 0);
		}
		
		if(result == N*N)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	private static void combination(int idx, int cnt) {

		if (cnt == M) {
			result = Math.min(result, count());
			return;
		}
		if(idx == virus.size())
			return;

		isVisited[idx] = true;
		combination(idx + 1, cnt + 1);
		isVisited[idx] = false;
		combination(idx + 1, cnt);

	}

	private static int count() {
		Queue<pos> q = new LinkedList<>();
		boolean[][] check = new boolean[N][N];
		int cnt = total_cnt;
		int ny, nx;
		int time = N*N;
		pos p;

		for (int i = 0; i < virus.size(); i++) {
			if (!isVisited[i])
				continue;
			check[virus.get(i).y][virus.get(i).x] = true;
			q.offer(virus.get(i));
		}
		
		while (!q.isEmpty()) {
			p = q.poll();
			for (int i = 0; i < 4; i++) {
				ny = p.y + dy[i];
				nx = p.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || arr[ny][nx] == 1 || check[ny][nx]) {
					continue;
				}
				check[ny][nx] = true;
				if(arr[ny][nx] != 2) {
					cnt--;
					if(cnt == 0) {
						time = Math.min(time, p.t + 1);
						return time;
					}
				}
				q.offer(new pos(ny, nx, p.t + 1));
			}
		}
		return N * N;
	}

}

//class pos {
//	int y, x, t;
//
//	pos(int y, int x, int t) {
//		this.y = y;
//		this.x = x;
//		this.t = t;
//	}
//}