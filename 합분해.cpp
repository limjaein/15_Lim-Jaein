#include <iostream>
#include <string.h>

using namespace std;

int dp[201][201];

int solution(int n, int k)
{
	int sum = 0;

	if (k == 1 || n == 0) // 한가지 경우밖에 없음
		return 1;
	if (dp[n][k] != -1) return dp[n][k];

	for (int i = 0; i <= n; i++)
	{
		sum += solution(n - i, k - 1)%1000000000;
		sum %= 1000000000;
	}

	dp[n][k] = sum;
	return dp[n][k];
}

int main()
{
	int N, K;
	cin >> N >> K;
	memset(dp, -1, sizeof(dp));
	cout << solution(N, K);
}