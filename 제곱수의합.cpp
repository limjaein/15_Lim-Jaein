#include <iostream>
#include <string>
#include <algorithm>
#include <math.h>

using namespace std;

const int INF = 123456789;

int dp[100000];

int solution(int n)
{
	int result = INF;
	int max;

	// 가장 가까운 작은 제곱수 : max
	max = sqrt(n);

	if (dp[n - 1] != -1)
		return dp[n - 1];

	while (max)
	{
		if (n - max * max > 0)
			result = min(result, 1 + solution(n - max * max));
		else if (n - max * max == 0)
			return 1;

		max--;
	}

	dp[n - 1] = result;

	return result;
}

int main()
{
	int N;

	cin >> N;

	memset(dp, -1, sizeof(int) * 100000);

	cout << solution(N) << endl;

	return 0;
}
