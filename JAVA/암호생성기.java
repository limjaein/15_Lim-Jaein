package practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 암호생성기 {
	static int MAX = 8;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t;
		Queue<Integer> q = new LinkedList<>();
		int tmp;
		int minus;

		while (sc.hasNext()) {
			t = sc.nextInt();
			for (int i = 0; i < MAX; i++) {
				tmp = sc.nextInt();
				q.add(tmp);
			}
			tmp = 1; // 초기화
			minus = 1;

			while (true) {
				if (minus == 6)
					minus = 1;
				tmp = q.poll();
				tmp -= minus;
				minus++;
				if (tmp <= 0) {
					q.add(0);
					break;
				}
				q.add(tmp);
			}
			System.out.print("#" + t + " ");
			for (int i = 0; i < MAX; i++)
				System.out.print(q.poll() + " ");
			System.out.println();
		}
		sc.close();

	}

}
