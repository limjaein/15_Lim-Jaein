#include <iostream>
#include <algorithm>

using namespace std;

int N, K;
int min_cnt = 20000;
int coin[100];

void solution(int charge, int cnt)
{
	for (int i = 0; i < N; i++)
	{
		for (int j = coin[i]; j < K + 1; j++)
		{
			if (charge - coin[j] > 0)
			{
				solution(charge - coin[j], cnt + 1);
			}
			else if (charge - coin[j] == 0)
			{
				min_cnt = min(min_cnt, cnt + 1);
				return;
			}
		}
	}
}

int main()
{
	cin >> N >> K;

	for (int i = 0; i < N; i++)
		cin >> coin[i];
	
	solution(K, 0);

	cout << min_cnt << endl;
}