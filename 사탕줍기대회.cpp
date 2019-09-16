#define _CTR_SECURE_NO_WARNING
#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

int M, N;
int m_line[100001];
int n_line[100001];

int main()
{
	int temp, result;

	while (1)
	{
		cin >> M >> N;
		if (M == 0 && N == 0)
			return 0;

		memset(m_line, 0, sizeof(int) * M);
		memset(n_line, 0, sizeof(int) * N);

		for (int i = 0; i < M; i++)
		{
			for (int j = 0; j < N; j++)
			{
				scanf_s("%d", &temp);
				n_line[j] = temp;
				if (j > 1)
					n_line[j] += max(n_line[j - 2], n_line[j - 3]);
				m_line[i] = max(m_line[i], n_line[j]);
			}
		}

		result = 0;
		for (int i = 0; i < M; i++)
		{
			if (i > 1)
				m_line[i] += max(m_line[i - 2], m_line[i - 3]);
			result = max(result, m_line[i]);
		}

		cout << result << endl;
	}
}