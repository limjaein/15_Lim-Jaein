package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숨바꼭질3 {
	static final int MAX = 987654321;
	static int N, K;
	static boolean[] isVisited;
	static int min_time;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		isVisited = new boolean[100001];
		min_time = MAX;

		if (N == K)
			System.out.println(0);
		else if (N > K) {
			System.out.println(N - K);
		} else
			System.out.println(bfs());
	}

	private static int bfs() {
		PriorityQueue<Inf> q = new PriorityQueue<>(new Comparator<Inf>() {
			@Override
			public int compare(Inf o1, Inf o2) {
				return (o1.cnt > o2.cnt) ? 1 : -1;
			}
		});
		Inf cur;
		int next = N;

		q.offer(new Inf(N, 0));

		while (!q.isEmpty()) {
			cur = q.poll();
			if(min_time < cur.cnt)
				break;
			
			isVisited[cur.num] = true; 

			for (int i = 0; i < 3; i++) {
				if (i == 0) {
					next = cur.num - 1;
				} else if (i == 1) {
					next = cur.num + 1;
				} else {
					next = cur.num * 2;
				}

				if (next < 0 || next > 100000 || isVisited[next])
					continue;

				if (next == K) {
					if(i == 2)
						min_time = Math.min(min_time, cur.cnt);
					else
						min_time = Math.min(min_time, cur.cnt + 1);
					continue;
				}

				if (i == 2) {
					q.offer(new Inf(next, cur.cnt));
				} else {
					q.offer(new Inf(next, cur.cnt + 1));
				}
			}
		}
		return min_time;
	}
}

class Inf {
	int num;
	int cnt;

	Inf(int n, int c) {
		this.num = n;
		this.cnt = c;
	}
}