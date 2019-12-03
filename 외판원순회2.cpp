#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

const int INF = 987654321;
int N;
int input[10][10];
int dp[10][1 << 10]; // 현재, 지나온 노드

int solution(int idx, int list)
{
	int result = INF;
	if (dp[idx][list] != -1)
		return dp[idx][list];

	if (list == (1 << N) - 1) // 마지막
	{
		if (input[idx][0] == 0) return INF;
		else return input[idx][0];
	}

	for (int i = 1; i < N; i++)
	{
		if ((list & (1 << i)) == 0 && input[idx][i] != 0) // 방문안했고, 길이 있으면
			result = min(result, input[idx][i] + solution(i, list | (1 << i)));
	}
	return dp[idx][list] = result;
}
int main()
{
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
			cin >> input[i][j];
	}
	memset(dp, -1, sizeof(dp));

	cout << solution(0, 1);
}