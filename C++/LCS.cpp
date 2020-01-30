#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

int len[2];
int dp[1001][1001];
char input[2][1001];

int solution()
{
	for (int i = 1; i < len[1] + 1; i++)
	{
		for (int j = 1; j < len[0] + 1; j++)
		{
			if (input[1][i] == input[0][j])
				dp[i][j] = dp[i - 1][j - 1] + 1;
			else
				dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
		}
	}
	return dp[len[1]][len[0]];
}

int main()
{
	string str;
	for (int i = 0; i < 2; i++)
	{
		cin >> str;
		len[i] = str.length();
		input[i][0] = ' ';
		for (int j = 1; j < len[i] + 1; j++)
			input[i][j] = str[j - 1];
	}
	memset(dp, 0, sizeof(dp));
	cout << solution();
}