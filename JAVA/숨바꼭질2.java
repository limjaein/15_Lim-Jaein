package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질2 {
	static final int MAX = 987654321;
	static int N, K;
	static int min_time;
	static int cnt;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		isVisited = new boolean[100001];
		min_time = MAX;
		cnt = 0;

		if (N == K) {
			System.out.println(0);
			System.out.println(1);
		} else if (N > K) {
			System.out.println(N - K);
			System.out.println(1);
		} else {
			bfs();
			System.out.println(min_time);
			System.out.println(cnt);
		}
	}

	private static void bfs() {
		Queue<Inf> q = new LinkedList<>();
		Inf cur;
		int next;

		q.offer(new Inf(N, 0));

		while (!q.isEmpty()) {
			cur = q.poll();
			if(cur.cnt > min_time)
				continue;
			
			isVisited[cur.pos] = true;
			
			for (int i = 0; i < 3; i++) {
				if (i == 0) {
					next = cur.pos - 1;
				} else if (i == 1) {
					next = cur.pos + 1;
				} else {
					next = cur.pos * 2;
				}

				if (next < 0 || next > 100000 || isVisited[next])
					continue;

				if (next == K) {
					if (min_time == MAX) {
						min_time = cur.cnt + 1;
					}

					if (min_time == cur.cnt + 1) {
						cnt++;
					}
					continue;
				}

				q.offer(new Inf(next, cur.cnt + 1));
			}
		}
	}
}

//class Inf {
//	int pos;
//	int cnt;
//
//	Inf(int p, int c) {
//		this.pos = p;
//		this.cnt = c;
//	}
//}