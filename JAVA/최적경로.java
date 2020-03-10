package algo_200302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 최적경로 {
	static int N;
	static Pos home;
	static Pos company;
	static ArrayList<Pos> list;
	static boolean[] isVisited;
	static int min_result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		list = new ArrayList<Pos>();

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			isVisited = new boolean[N];
			min_result = 987654321;

			st = new StringTokenizer(in.readLine(), " ");
			company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				list.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			permutation(new int[N], 0);
			list.clear();
			sb.append("#").append(t).append(" ").append(min_result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void permutation(int[] pList, int idx) {
		if (idx == N) {
			int sum = 0;
			int cur = 0;
			Pos target;
			for (int i = 0; i < N; i++) {
				cur = pList[i];
				if (i == 0) {
					target = company;
				} else {
					target = list.get(pList[i - 1]);
				}
				sum += Math.abs(target.y - list.get(cur).y);
				sum += Math.abs(target.x - list.get(cur).x);
			}
			sum += Math.abs(home.y - list.get(cur).y);
			sum += Math.abs(home.x - list.get(cur).x);
			min_result = Math.min(min_result, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if(isVisited[i]) {
				continue;
			}
			isVisited[i] = true;
			pList[idx] = i;
			permutation(pList, idx+1);
			isVisited[i] = false;
		}
	}
}

//class Pos {
//	int y;
//	int x;
//
//	Pos(int y, int x) {
//		this.y = y;
//		this.x = x;
//	}
//}