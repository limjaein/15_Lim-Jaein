#include <iostream>
#include <string.h>

using namespace std;

int N;
int dp[10][101];

int solution(int len, int n)
{
	long long sum = 0;

	if (N == len)
		return 1;
	if (dp[n][len] != -1)
		return dp[n][len];
	if (n > 0)
		sum += solution(len + 1, n - 1);
	if (n < 9)
		sum += solution(len + 1, n + 1);

	dp[n][len] = sum % 1000000000;

	return dp[n][len];
}

int main()
{
	long long result = 0;
	cin >> N;

	for (int i = 0; i < 10; i++)
		memset(dp[i], -1, sizeof(int) * 101);

	for (int i = 1; i < 10; i++)
	{
		result += solution(1, i);
		result %= 1000000000;
	}

	cout << result << endl;

	return 0;
}