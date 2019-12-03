#include <iostream>
#include <string.h>

using namespace std;

int dp[2][41];

int fibonacci(int n, bool i) {
	if (dp[i][n] == -1)
		dp[i][n] = fibonacci(n - 1, i) + fibonacci(n - 2, i);
	return dp[i][n];
}
int main()
{
	int c, n;
	cin >> c;
	memset(dp, -1, sizeof(dp));
	dp[0][0] = dp[1][1] = 1;
	dp[0][1] = dp[1][0] = 0;
	for (int i = 0; i < c; i++)
	{
		cin >> n;
		fibonacci(n, 0);
		cout << dp[0][n] << " ";
		fibonacci(n, 1);
		cout << dp[1][n] << endl;
	}
}