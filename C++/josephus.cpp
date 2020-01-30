#include <iostream>
#include <string.h>

using namespace std;

int N, K;
int circle[1000];

void suicide()
{
	int sum = 0; // 자살한 사람 수
	int i = 0; // index
	int gap = K - 1; // 0 번째 초기화를 위해 K - 1로 초기화

	while (sum != (N - 2)) // 2명만 살아남을 때 까지 진행
	{
		if (circle[i] != 0) // 살아있는 사람이면 간격에 포함
			gap++;

		if (gap == K) // K명째 일 때
		{
			circle[i] = 0; // 자살
			sum++; // 자살한 사람 수 포함
			gap = 0; // 간격 초기화
		}

		i += 1; // 다음 index
		i %= N; // 전체 인원을 넘을 경우 N의 나머지 값 
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
			if (circle[i] == -1) // 자살하지 않은 사람의 번호 출력
				cout << i + 1 << '\t';
		}

		cout << endl;
	}

	return 0;
}
