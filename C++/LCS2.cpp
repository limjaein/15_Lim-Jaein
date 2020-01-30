#include <iostream>
#include <string.h>
#include <algorithm>
#include <stack>

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

void print(int l)
{
	stack<char> st;
	cout << l << endl;
	for (int i = len[1]; i > 0; i--)
	{
		for (int j = 1; j < len[0] + 1; j++)
		{
			if (dp[i][j] == l && input[1][i] == input[0][j])
			{
				st.push(input[1][i]);
				l--;
				if (l == 0)
				{
					while (!st.empty())
					{
						cout << st.top();
						st.pop();
					}
					return;
				}
				break;
			}
		}
	}
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
	print(solution());
}