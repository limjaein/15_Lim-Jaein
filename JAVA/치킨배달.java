package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 치킨배달 {
	static int N, M;
	static int result;
	static boolean[] idx;
	static int[][] dist;
	static ArrayList<Pos> home;
	static ArrayList<Pos> list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		home = new ArrayList<>();
		result = 1234567;
		int num;

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j < N + 1; j++) {
				num = Integer.parseInt(st.nextToken());
				if (num == 2)
					list.add(new Pos(i, j));
				else if (num == 1)
					home.add(new Pos(i, j));
			}
		}
		dist = new int[home.size()][list.size()];

		for (int i = 0; i < home.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				dist[i][j] = Math.abs(home.get(i).x - list.get(j).x) + Math.abs(home.get(i).y - list.get(j).y);
			}
		}

		idx = new boolean[list.size()];

		if (list.size() == M) {
			Arrays.fill(idx, true);
			result = calDist();
		} else
			selectStore(0, 0);
		System.out.println(result);
	}

	private static void selectStore(int i, int cnt) {
		if (cnt == M) {
			result = Math.min(result, calDist());
			return;
		}

		for (; i < list.size(); i++) {
			idx[i] = true;
			selectStore(i + 1, cnt + 1);
			idx[i] = false;
		}
	}

	private static int calDist() {
		int sum = 0;
		int sum_tmp;
		for (int i = 0; i < home.size(); i++) {
			sum_tmp = 1234567;
			for (int j = 0; j < list.size(); j++) {
				if (idx[j]) {
					sum_tmp = Math.min(sum_tmp, dist[i][j]);
				}
			}
			sum += sum_tmp;
		}
		return sum;
	}
}

class Pos {
	int y;
	int x;

	Pos(int y, int x) {
		this.y = y;
		this.x = x;
	}
}