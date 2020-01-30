#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

int n, m;
int arr[1000][1000];

int solution(int minimum)
{
	int maxCnt = minimum;

	for (int i = 1; i < n; i++)
	{
		for (int j = 1; j < m; j++)
		{
			if (arr[i][j] > 0)
			{
				arr[i][j] = min(arr[i - 1][j - 1], arr[i - 1][j]);
				arr[i][j] = min(arr[i][j], arr[i][j - 1]) + 1;
				maxCnt = max(maxCnt, arr[i][j]);
			}
		}
	}
	return maxCnt;
}

int main()
{
	int result = 0;
	cin >> n >> m;

	for (int i = 0; i < n; i++)
	{
		memset(arr[i], 0, sizeof(int)*m);
		for (int j = 0; j < m; j++)
		{
			scanf_s("%1d", &arr[i][j]);
			result = max(result, arr[i][j]);
		}
	}

	result = solution(result);
	cout << result*result << endl;

	return 0;
}