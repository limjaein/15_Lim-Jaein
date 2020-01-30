#include <iostream>
#include <string.h>

using namespace std;

int N;
int input[17][17];
int dp[3][17][17];

int solution(int y, int x, int mode)
{
	int cnt = 0;

	if (y == N - 1 && x == N - 1) // 끝점 도착
		return 1;

	if (dp[mode][y][x] != -1)
		return dp[mode][y][x];

	if (mode == 0 || mode == 2)
	{
		if (x + 1 < N && input[y][x + 1] != 1) // 좌표 체크, 벽 체크
			cnt += solution(y, x + 1, 0);
	}

	if (mode == 1 || mode == 2)
	{
		if (y + 1 < N && input[y + 1][x] != 1) // 좌표 체크, 벽 체크
			cnt += solution(y + 1, x, 1);
	}

	if (y + 1 < N && x + 1 < N)
	{
		if (input[y][x + 1] != 1 && input[y + 1][x] != 1 && input[y + 1][x + 1] != 1) // 대각선 벽 체크
			cnt += solution(y + 1, x + 1, 2);
	}

	dp[mode][y][x] = cnt;

	return dp[mode][y][x];
}

int main()
{
	int result = 0;

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		memset(input[i], 0, sizeof(int) * N);
		for (int j = 0; j < N; j++)
			cin >> input[i][j];
	}

	memset(dp, -1, sizeof(dp));

	// 파이프 가로이기 때문에
	solution(0, 1, 0);

	for (int i = 0; i < 3; i++)
	{
		if (dp[i][0][1] != -1)
			result += dp[i][0][1];
	}

	if (result <= 0) result = 0;
	cout << result << endl;
}