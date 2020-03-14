package algo_200311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class ¿°¶ó´ë¿ÕÀÇÀÌ¸§Á¤·Ä {
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		int N;
		String str;
		Set<String> set = new HashSet<>();
		Iterator<String> it;
		PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				char a,b;
				if(s1.length() > s2.length()) {
					return 1;
				}else if(s1.length() == s2.length()) {
					for(int i=0;i<s1.length();i++) {
						a = s1.charAt(i);
						b = s2.charAt(i);
						if(a > b) {
							return 1;
						}else if(a < b) {
							return -1;
						}
					}
				}
				return -1;
			}
		});

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			for (int i = 0; i < N; i++) {
				pq.offer(in.readLine());
			}
			sb.append("#").append(t+1).append("\n");
			while(!pq.isEmpty()) {
				str = pq.poll();
				if(set.contains(str)) {
					continue;
				}else {
					set.add(str);
				}
				sb.append(str).append("\n");
			}
			set.clear();
		}
		System.out.println(sb.toString());
	}
}
