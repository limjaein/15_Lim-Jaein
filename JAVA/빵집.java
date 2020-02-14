package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빵집 {
	static int R, C;
	static char[][] road;
	static int[] dir = { -1, 0, 1 };
	// 방향으로 우선수위 주기 !

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String line;
		int cnt = 0;
		road = new char[R + 1][C + 1];

		for (int i = 1; i < R + 1; i++) {
			line = in.readLine();
			for (int j = 1; j < C + 1; j++) {
				road[i][j] = line.charAt(j - 1);
			}
		}

		for (int s = 1; s < R + 1; s++) {
			if(dfs(s, 1))
				cnt++;
		}

		System.out.println(cnt);
	}
	
	private static boolean dfs(int y, int x) {
		int ny, nx;

		if (x == C) {
			return true;
		}

		for (int i = 0; i < 3; i++) {
			ny = y + dir[i];
			nx = x + 1;
			if (ny > R || ny <= 0 || road[ny][nx] == 'x')
				continue;
			road[ny][nx] = 'x';
			if(dfs(ny, nx))
				return true;
		}
		return false;
	}

}
