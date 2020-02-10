package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이차원배열과연산 {
	static final int SIZE = 3;
	static int r, c, k;
	static int count;

	static ArrayList<ArrayList<Integer>> arr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		ArrayList<Integer> line = new ArrayList<>();
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new ArrayList<ArrayList<Integer>>();
		count = 0;

		for (int i = 0; i < SIZE; i++) {
			String str = in.readLine();
			st = new StringTokenizer(str, " ");
			for (int j = 0; j < SIZE; j++) {
				line.add(Integer.parseInt(st.nextToken()));
			}
			arr.add(line);
			line = new ArrayList<>();
		}
		
		System.out.println(solution());
	}

	private static int solution() {
		while (true) { // return 조건
			if(arr.size()>=r && arr.get(0).size()>=c && arr.get(r - 1).get(c - 1) == k)
				break;
			if (arr.size() >= arr.get(0).size()) { // R
				sort(true);
			} else {
				sort(false);
				reverse();
			}
			count++;
			if(count > 100)
				return -1;
		}
		return count;
	}

	private static void reverse() {
		ArrayList<ArrayList<Integer>> n_arr = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> line = new ArrayList<>();
		// 행열 바꾸기
		for (int row = 0; row < arr.get(0).size(); row++) {
			for (int col = 0; col < arr.size(); col++) {
				line.add(arr.get(col).get(row));
			}
			n_arr.add(line);
			line = new ArrayList<>();
		}
		arr = n_arr;
	}

	private static void sort(boolean isRow) {
		HashMap<Integer, Integer> counter = new HashMap<>();
		Iterator<Integer> it;
		int key;
		PriorityQueue<Number> pq = new PriorityQueue<>(new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				if (o1.cnt > o2.cnt)
					return 1;
				else if (o1.cnt == o2.cnt) {
					if (o1.num > o2.num)
						return 1;
				}
				return -1;
			}
		});
		int n;
		Number tmp;
		ArrayList<Integer> line = new ArrayList<>();
		ArrayList<ArrayList<Integer>> n_arr = new ArrayList<ArrayList<Integer>>();
		int max;
		int R,C;

		max = 0;
		if (isRow) {
			R = arr.size();
			C = arr.get(0).size();
		}else {
			R = arr.get(0).size();
			C = arr.size();
		}
		
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (isRow) {
					n = arr.get(row).get(col);
				}else {
					n = arr.get(col).get(row);
				}
				if(n == 0)
					continue;
				if (counter.containsKey(n)) {
					counter.replace(n, counter.get(n) + 1);
				} else {
					counter.put(n, 1);
				}
			}
			it = counter.keySet().iterator();

			while (it.hasNext()) {
				key = it.next();
				pq.add(new Number(key, counter.get(key)));
			}

			while (!pq.isEmpty()) {
				tmp = pq.poll();
				line.add(tmp.num);
				line.add(tmp.cnt);
			}
			n_arr.add(line);
			max = Math.max(max, line.size());
			line = new ArrayList<>();
			counter.clear();
		}
		arr = n_arr;
		n_arr = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < arr.size(); i++) {
			int ad = max - arr.get(i).size();
			for (int j = 0; j < ad; j++)
				arr.get(i).add(0);
		}
	}
}

class Number {
	int num;
	int cnt;

	public Number(int num, int cnt) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.cnt = cnt;
	}
}
