package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 숨바꼭질4 {
	static final int MAX = 987654321;
	static int N, K;
	static int[] isVisited;
	static int min_time;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		isVisited = new int[100001];
		min_time = MAX;
		Arrays.fill(isVisited, -1);

		if (N == K) {
			System.out.println(0);
			System.out.println(N);
		} else if (N > K) {
			System.out.println(N - K);
			for (int i = N; i >= K; i--) {
				System.out.print(i + " ");
			}
		} else {
			bfs();
		}
	}

	private static void bfs() {
		PriorityQueue<Inf> q = new PriorityQueue<>(new Comparator<Inf>() {
			@Override
			public int compare(Inf o1, Inf o2) {
				return (o1.cnt > o2.cnt) ? 1 : -1;
			}
		});
		Inf cur;
		int next = N;
		Stack<Integer> st = new Stack<>();

		q.offer(new Inf(N, 0));

		while (!q.isEmpty()) {
			cur = q.poll();

			for (int i = 0; i < 3; i++) {
				if (i == 0) {
					next = cur.num - 1;
				} else if (i == 1) {
					next = cur.num + 1;
				} else {
					next = cur.num * 2;
				}

				if (next < 0 || next > 100000 || isVisited[next] != -1)
					continue;
				
				isVisited[next] = cur.num;

				if (next == K) {
					System.out.println(cur.cnt + 1);
					st.push(next);
					while(true) {
						st.push(isVisited[st.peek()]);
						if(st.peek() == N) {
							while(!st.isEmpty()) {
								System.out.print(st.pop()+" ");
							}
							return;
						}
					}
				}
				q.offer(new Inf(next, cur.cnt + 1));
			}
		}
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