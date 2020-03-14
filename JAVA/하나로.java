package algo_200311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ÇÏ³ª·Î {
	static int N;
	static double E;
	static int[] list_x;
	static int[] list_y;
	static double[][] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			list_x = new int[N];
			list_y = new int[N];
			dist = new double[N][N];
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				list_x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				list_y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(in.readLine());
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					dist[i][j] = dist[j][i] = (Math.pow(list_y[i] - list_y[j], 2)
							+ Math.pow(list_x[i] - list_x[j], 2));
				}
			}
			sb.append("#").append(t + 1).append(" ").append(Math.round(findResult())).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static double findResult() {
		double sum = 0;
		int cnt = N - 1;
		Info next = new Info(0, Double.MAX_VALUE);
		ArrayList<Integer> fin = new ArrayList<>();
		ArrayList<Integer> not_fin = new ArrayList<>();
		double value;

		fin.add(0);
		for (int i = 1; i < N; i++) {
			not_fin.add(i);
		}
		while (cnt-- != 0) {
			next.pay = Double.MAX_VALUE;
			for (int i = 0; i < fin.size(); i++) {
				for (int j = 0; j < not_fin.size(); j++) {
					value = E * dist[fin.get(i)][not_fin.get(j)];
					if (value < next.pay) {
						next.idx = j;
						next.pay = value;
					}
				}
			}
			fin.add(not_fin.get(next.idx));
			not_fin.remove(next.idx);
			sum += next.pay;
		}

		return sum;
	}
}

class Info {
	int idx;
	double pay;

	Info(int idx, double pay) {
		this.idx = idx;
		this.pay = pay;
	}
}
