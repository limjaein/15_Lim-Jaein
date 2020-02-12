package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수의새로운연산 {
	static int p, q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			sb.append("#").append(t).append(" ").append(solution(p, q)).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static int solution(int p, int q) {
		int num = 1;
		Pos p1 = and(p);
		Pos p2 = and(q);
		int add;

		// p1에 더해줌
		p1.y += p2.y;
		p1.x += p2.x;

		for (int i = 1; i < p1.y; i++) {
			num += i;
		}

		add = p1.y + 1;
		for (int i = 1; i < p1.x; i++) {
			num += add;
			add++;
		}
		return num;
	}

	public static Pos and(int p) {
		int num;
		int prev = 1;
		// 값이 n인 곳 찾기
		int y = 1;
		int x = 1;
		if (p == y)
			return new Pos(y, x);
		// 세로
		while (true) {
			num = prev + y;
			if (p == num) {
				return new Pos(y + 1, x);
			}
			if (p < num) {
				num -= y;
				break;
			}
			prev = num;
			y++;
		}

		// 대각선으로 올라가기
		while (true) {
			y--;
			x++;
			num++;
			if (p == num)
				return new Pos(y, x);
		}
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