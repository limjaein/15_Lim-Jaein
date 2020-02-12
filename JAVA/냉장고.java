package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Refrigerator implements Comparable<Refrigerator> {
	int left;
	int right;

	public Refrigerator(int l, int r) {
		this.left = l;
		this.right = r;
	}
	@Override
	public int compareTo(Refrigerator o) {
		return (this.left >= o.left) ? 1: -1;
	}
}

public class 냉장고 {
	static int N;
	static Refrigerator[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Refrigerator> pq = new PriorityQueue<>();
		N = Integer.parseInt(in.readLine());
		list = new Refrigerator[N];
		Refrigerator rf;
		int L;
		int R;
		int cnt = 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			pq.offer(new Refrigerator(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		rf = pq.poll();
		L = rf.left;
		R = rf.right;
		
		while (!pq.isEmpty()) {
			rf = pq.poll();
			if(rf.left > R || rf.right < L) { // ����� ���� ����
				cnt++;
				L = rf.left;
				R = rf.right;
			}else {
				if(rf.left > L)
					L = rf.left;
				if(rf.right < R)
					R = rf.right;
			}
		}
		System.out.println(cnt);
	}
}
