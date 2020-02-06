package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 쇠막대기자르기 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + cutStick(in.readLine()));
		}

	}

	private static int cutStick(String stick) {
		Queue<Character> q = new LinkedList<>();
		int cnt = 0;
		char c;
		for (int i = 0; i < stick.length(); i++) {
			c = stick.charAt(i);
			switch (c) {
			case '(':
				if (stick.charAt(i + 1) == ')') {
					cnt += q.size();
					i++;
				} else {
					q.offer(c);
				}
				break;
			case ')':
				q.poll();
				cnt += 1;
				break;
			}
		}
		return cnt;
	}
}
