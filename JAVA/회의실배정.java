package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 회의실배정 {
	static int N;
	static PriorityQueue<Conf> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		Conf conf;
		int time = 0;
		int cnt = 0;
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			pq.offer(new Conf(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!pq.isEmpty()) {
			conf = pq.poll();
			if(time <= conf.s_time) {
				time = conf.e_time;
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}

class Conf implements Comparable<Conf> {
	int s_time;
	int e_time;

	Conf(int s, int e) {
		s_time = s;
		e_time = e;
	}

	@Override
	public int compareTo(Conf o) {
		if(this.e_time > o.e_time) {
			return 1;
		}else if(this.e_time == o.e_time) {
			if(this.s_time > o.s_time) {
				return 1;
			}
		}
		return -1;
	}

}