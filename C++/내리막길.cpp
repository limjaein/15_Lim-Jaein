#include <iostream>
#include <string.h>

using namespace std;

int M, N;
int arr[500][500];
int DP[500][500];

int search(int m, int n)
{
	int sum = 0;

	if (m == (M-1) && n == (N-1))
		return 1;

	if (DP[m][n] == -1)
	{
		if (m > 0)
		{
			if (arr[m - 1][n] < arr[m][n])
				sum += search(m - 1, n);
		}

		if (m < M - 1)
		{
			if (arr[m + 1][n] < arr[m][n])
				sum += search(m + 1, n);
		}

		if (n > 0)
		{
			if (arr[m][n - 1] < arr[m][n])
				sum += search(m, n - 1);
		}

		if (n < N - 1)
		{
			if (arr[m][n + 1] < arr[m][n])
				sum += search(m, n + 1);
		}

		DP[m][n] = sum;
	}

	return DP[m][n];
}

int main()
{
	cin >> M >> N;
	
	for (int i = 0; i < M; i++)
	{
		memset(DP[i], -1, sizeof(int)*N);

		for (int j = 0; j < N; j++)
		{
			cin >> arr[i][j];
		}
	}

	cout << search(0, 0) << endl;
}	
