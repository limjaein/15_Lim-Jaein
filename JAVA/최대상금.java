package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 최대상금 {
	static int max_cnt;
	static int result;
	static Set<String> way;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			String number = st.nextToken();
			max_cnt = Integer.parseInt(st.nextToken());
			way = new HashSet<>();
			result = 0;

			int[] arr = new int[number.length()];
			for (int i = 0; i < number.length(); i++) {
				arr[i] = number.charAt(i) - '0';
			}
			
			comb(0, arr);
			sb.append("#").append(t + 1).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void comb(int cnt, int[] list) {
		int tmp;
		int[] tmp_list = new int[list.length];
		int value = 0;

		for (int i = 0; i < list.length; i++) {
			tmp_list[i] = list[i];
			value += tmp_list[i] * Math.pow(10, list.length - i - 1);
		}
		
		if(way.contains(value+""+cnt)) // 123""3 따로 저장됨
			return;
		way.add(value+""+cnt);
		
		if (cnt == max_cnt) {
			result = Math.max(result, value);
			return;
		}

		for (int i = 0; i < tmp_list.length - 1; i++) {
			for (int j = i + 1; j < tmp_list.length; j++) {
				tmp = tmp_list[j];
				tmp_list[j] = tmp_list[i];
				tmp_list[i] = tmp;

				comb(cnt + 1, tmp_list);

				tmp = tmp_list[j];
				tmp_list[j] = tmp_list[i];
				tmp_list[i] = tmp;
			}
		}
		return;
	}
}
