#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

int dp[10001];
int w[10001];

int sol(int i)
{
	int tmp;
	if (dp[i] != -1)
		return dp[i];
	tmp = max(sol(i - 3) + w[i - 1] + w[i], sol(i - 2) + w[i]);
	dp[i] = max(tmp, sol(i - 1));
	return dp[i];
}

int main()
{
	int c;
	cin >> c;
	for (int i = 1; i < c + 1; i++)
		cin >> w[i];
	memset(dp, -1, sizeof(dp));
	dp[0] = 0;
	dp[1] = w[1];
	dp[2] = w[1] + w[2];
	for (int i = 3; i < c + 1; i++)
		sol(i);
	cout << dp[c];
}