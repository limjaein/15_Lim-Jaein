package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 암호만들기 {
	static int L, C;
	static Character[] input;
	static ArrayList<Integer> idxes;
	static boolean[] isChecked;
	static char[] arr = { 'a', 'e', 'i', 'o', 'u' };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new Character[C];
		isChecked = new boolean[C];
		idxes = new ArrayList<>();

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(input, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return (o1 - '0' > o2 - '0') ? 1 : -1;
			}
		});

		Combination(0);
	}

	private static void Combination(int idx) {
		if (idxes.size() == L) {
			int cur;
			int cnt = getCount();
			if (cnt == 0 || L - cnt < 2)
				return;
			for (int i = 0; i < idxes.size(); i++) {
				cur = idxes.get(i);
				System.out.print(input[cur]);
			}
			System.out.println();
			return;
		}

		if (idx == C) {
			return;
		}

		for (int i = idx; i < C; i++) {
			idxes.add(i);
			Combination(i + 1);
			idxes.remove(idxes.size() - 1);
		}
	}

	private static int getCount() {
		int cnt = 0;
		for (int i = 0; i < idxes.size(); i++) {
			for (int j = 0; j < arr.length; j++) {
				if (input[idxes.get(i)].equals(arr[j])) {
					cnt++;
					break;
				}
			}
		}
		return cnt;
	}
}
