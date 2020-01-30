#include <iostream>
#include <string.h>

using namespace std;

int N;
int map[100][100];
long long dp[100][100];

int checkRange(int y, int x) // 범위 체크
{
	return (x >= 0 && x < N && y >= 0 && y < N) ? true : false;
}

long long dfs(int x, int y)
{
	long long cnt = 0;

	if (map[y][x] == -1) 
		return 1;
	else if (map[y][x] == 0) 
		return 0;

	if (dp[y][x] != -1) return dp[y][x];

	if (checkRange(y, x + map[y][x])) // 우
		cnt += dfs(x + map[y][x], y);

	if (checkRange(y + map[y][x], x)) // 하
		cnt += dfs(x, y + map[y][x]);

	dp[y][x] = cnt;

	return cnt;
}

int main()
{
	cin >> N;

	for (int i = 0; i < N; i++)
	{
		memset(dp[i], -1, sizeof(long long)*100);
		for (int j = 0; j < N; j++)
			cin >> map[i][j];
	}

	map[N - 1][N - 1] = -1;
	cout << dfs(0, 0) << endl;
	return 0;
}