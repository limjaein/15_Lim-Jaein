package algo_200314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 요리사 {
	static int N;
	static int[][] input;
	static int isSelected;
	static int min_diff;
	static Set<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		list = new HashSet<>();

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			input = new int[N][N];
			isSelected = 0;
			min_diff = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			getComb(0, 0);
			list.clear();
			sb.append("#").append(t + 1).append(" ").append(min_diff).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void getComb(int idx, int cnt) {
		if (list.contains(~isSelected)) {
			return;
		}
		if (cnt == N / 2) {
			min_diff = Math.min(min_diff, Math.abs(getSynergy(isSelected) - getSynergy(~isSelected)));
			return;
		}
		if (idx == N && (N / 2 - 1 - idx) < cnt) { // 마지막 인덱스 이거나 더이상 진행해도 N/2개를 채울 수 없을 때
			return;
		}
		getComb(idx + 1, cnt);
		isSelected |= 1 << idx;
		getComb(idx + 1, cnt + 1);
		isSelected &= ~(1 << idx);
	}

	private static int getSynergy(int num) {
		int sum = 0;
		for (int i = N - 1; i >= 0; i--) {
			if ((num & 1 << i) != 0) {
				for (int j = 0; j < i; j++) {
					if ((num & 1 << j) != 0) {
						sum += (input[i][j] + input[j][i]);
					}
				}
			}
		}
		list.add(num);
		return sum;
	}
}
