package D0131;

import java.util.Scanner;

public class 별찍기7 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N;
		N = s.nextInt();
		for (int i = 0; i < 2 * N - 1; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				if (i == N - 1 || j == N - 1)
					System.out.print("*");
				else {
					if (i < N) { // 마름모 위쪽
						if (j < N) { // 마름모 위 - 왼
							if (i + j >= N - 1)
								System.out.print("*");
							else
								System.out.print(" ");
						} else { // 마름모 위 - 오른
							if (j - i <= N - 1)
								System.out.print("*");
						}
					} else { // 마름모 아래쪽
						if (j < N) { // 아래 - 왼
							if (i - j < N)
								System.out.print("*");
							else
								System.out.print(" ");
						} else { // 아래 - 오른
							if (i + j < 3*N-2)
								System.out.print("*");
						}
					}
				}
			}
			System.out.print(" ");
			System.out.println();
		}
	}

}
