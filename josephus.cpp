#include <iostream>
#include <string.h>

using namespace std;

int N, K;
int circle[1000];

void suicide()
{
	int sum = 0;
	int i = 0;
	int gap = K - 1; // 0 번째 초기화

	while (sum != (N - 2))
	{
		if (circle[i] != 0)
			gap++; // 간격 ++

		if (gap == K)
		{
			circle[i] = 0;
			sum++;
			gap = 0; // 간격 초기화
		}

		i += 1;
		i %= N;
	}
}

int main()
{
	int C;

	cin >> C;

	while (C--)
	{
		cin >> N >> K;
		memset(circle, -1, sizeof(int) * 1000);

		suicide();

		for (int i = 0; i < N; i++)
		{
			if (circle[i] == -1)
				cout << i + 1 << '\t';
		}

		cout << endl;
	}

	return 0;
}
