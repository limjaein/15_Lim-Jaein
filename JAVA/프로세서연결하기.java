package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;
 
public class 프로세서연결하기 {
    static int T, N;
    static int[] core;
    static ArrayList<Pos> core_list;
    static int core_cnt;
    static int max_cnt;
    static int min_sum;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(in.readLine());
        core_list = new ArrayList<>();
 
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(in.readLine());
            max_cnt = 0;
            min_sum = 987654321;
            core = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        core[i] |= 1 << (N - 1 - j);
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
                            continue;
                        }
                        core_list.add(new Pos(i, N - 1 - j));
                    }
                }
            }
            dfs(0, 0, 0);
            sb.append("#").append(t + 1).append(" ").append(min_sum).append("\n");
            core_list.clear();
        }
        System.out.println(sb.toString());
    }
 
    private static void dfs(int idx, int cnt, int sum) {
        Pos p;
        boolean isMoved = false;
 
        if (idx == core_list.size()) {
            if (cnt > max_cnt) {
                max_cnt = cnt;
                min_sum = sum;
            }else if(cnt == max_cnt) {
                min_sum = Math.min(min_sum, sum);
            }
            return;
        }
 
        p = core_list.get(idx);
 
        for (int j = 0; j < 4; j++) {
            if (isPossible(p.y, p.x, j)) {
                dfs(idx + 1, cnt + 1, sum + put(p.y, p.x, j, true));
                put(p.y, p.x, j, false);
                isMoved = true;
            }
        }
        if(!isMoved) {
        	dfs(idx + 1, cnt, sum);
        }
    }
 
    private static int put(int y, int x, int dir, boolean flag) {
        int cnt = 0;
        while (true) {
            y += dy[dir];
            x -= dx[dir];
            if (y < 0 || x < 0 || y >= N || x >= N)
                return cnt;
            if (flag) {
                core[y] |= 1 << x;
            } else {
                core[y] &= ~(1 << x);
            }
            cnt++;
        }
    }
 
    private static boolean isPossible(int y, int x, int dir) {
        boolean cnt = false;
        while (true) {
            y += dy[dir];
            x -= dx[dir];
            if (y < 0 || x < 0 || y >= N || x >= N)
                return cnt;
            if ((core[y] & (1 << x)) != 0)
                return false;
            cnt = true;
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
