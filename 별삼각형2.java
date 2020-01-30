package practice;

import java.util.Scanner;

public class 별삼각형2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, m;

		n = sc.nextInt();
		m = sc.nextInt();
		if (n % 2 == 0 || m < 1 || m > 4) {
			System.out.println("INPUT ERROR!");
			return;
		} 
		printStar(n, m);
	}

	public static void printStar(int height, int type) {
		int max_width = height / 2 + 1;
		int width = 0;
		int diff;
		switch (type) {
		case 1:
			for (int i = 0; i < height; i++) {
				if (i >= max_width)
					width--;
				else
					width++;
				for (int j = 0; j < width; j++)
					System.out.print("*");
				System.out.println();
			}
			break;
		case 2:
			for (int i = 0; i < height; i++) {
				if (i >= max_width)
					width--;
				else
					width++;
				for (int j = 0; j < max_width - width; j++)
					System.out.print(" ");
				for (int j = 0; j < width; j++)
					System.out.print("*");
				System.out.println();
			}
			break;
		case 3:
			width = height;
			for (int i = 0; i < height; i++) {

				diff = height - width;

				for (int j = 0; j < diff / 2; j++)
					System.out.print(" ");
				for (int j = 0; j < width; j++)
					System.out.print("*");
				for (int j = 0; j < diff / 2; j++)
					System.out.print(" ");

				if (i < max_width - 1)
					width -= 2;
				else
					width += 2;

				System.out.println();
			}
			break;
		case 4:
			diff = 0;
			width = max_width;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < diff; j++)
					System.out.print(" ");

				for (int j = 0; j < width; j++)
					System.out.print("*");

				if (i < max_width - 1)
					width--;
				else
					width++;

				if (i < max_width - 1)
					diff++;
				System.out.println();
			}
			break;
		}

	}

}
