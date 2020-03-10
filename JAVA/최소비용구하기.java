package algo_200303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용구하기 {
	static final int MAX = Integer.MAX_VALUE;
	static int N, M;
	static int[][] adj;
	static int start, end;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int s, e, v;

		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		adj = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(adj[i], MAX);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[s][e] = Math.min(adj[s][e], v);
		}

		st = new StringTokenizer(in.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra());
	}

	private static int dijkstra() {
		int[] min_dist = adj[start].clone();
		boolean[] isVisited = new boolean[N + 1];
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		Point next;
		isVisited[start] = true;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (min_dist[j] != MAX && !isVisited[j]) {
					pq.offer(new Point(j, min_dist[j]));
				}
			}
			next = pq.poll();
			if(next == null) {
				break;
			}
			pq.clear();
			isVisited[next.idx] = true;
			for (int j = 1; j < N + 1; j++) {
				if (!isVisited[j] && adj[next.idx][j] != MAX) {
					min_dist[j] = Math.min(min_dist[j], min_dist[next.idx] + adj[next.idx][j]);
				}
			}
		}
		return min_dist[end];
	}
}

class Point implements Comparable<Point> {
	int idx;
	int value;

	Point(int i, int v) {
		idx = i;
		value = v;
	}

	@Override
	public int compareTo(Point o) {
		return (this.value > o.value) ? 1 : -1;
	}
}