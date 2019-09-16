#define _CTR_SECURE_NO_WARNING
#include <iostream>
#include <string.h>

using namespace std;

int N;
int input[101] = { 0 };
long long dp[101][21]; // cnt dp¿˙¿Â

long long solution(int ind, int sum)
{
	int temp;
	long long result = 0;

	if (dp[ind][sum] != -1)
		return dp[ind][sum];

	if (ind == N - 1)
	{
		if (sum == input[N - 1])
			return 1;
		else
			return 0;
	}

	temp = sum + input[ind];
	if (temp <= 20)
		result += solution(ind + 1, temp);

	temp = sum - input[ind];
	if (temp >= 0)
		result += solution(ind + 1, temp);

	dp[ind][sum] = result;

	return result;
}

int main()
{
	cin >> N;

	for (int i = 0; i < N; i++)
	{
		scanf_s("%d", &input[i]);
		memset(dp[i], -1, sizeof(long long) * 21);
	}

	cout << solution(1, input[0]) << endl;
}