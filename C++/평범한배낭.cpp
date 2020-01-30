#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

int N, K;
int w[101];
int v[101];
int dp[1001][100001];

void sol(int y, int x) 
{
	int tmp;
	if (x < w[y]) // 자신 포함 안될때
		tmp = dp[y - 1][x];
	else
		tmp = max(dp[y - 1][x], v[y] + dp[y - 1][x - w[y]]);
	dp[y][x] = tmp;
}

int main()
{
	memset(dp, 0, sizeof(dp));

	cin >> N >> K;

	for (int i = 1; i < N+1; i++)
		cin >> w[i] >> v[i];

	for (int i = 1; i < N + 1; i++)
	{
		for (int j = 1; j < K + 1; j++)
			sol(i, j);
	}

	cout << dp[N][K];
}