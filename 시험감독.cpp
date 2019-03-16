#include <iostream>
#include <string.h>
#include <math.h>

using namespace std;

int A[1000000]; // 응시자 수

int main()
{
	int N; // 시험장 수
	int B, C; // 총감독, 부감독 감시 가능 인원 수

	long long sum = 0;

	cin >> N;
	memset(A, 0, sizeof(int)*N);

	for (int i = 0; i < N; i++)
		cin >> A[i];

	cin >> B >> C;

	for (int i = 0; i < N; i++)
	{
		A[i] -= B;
		sum++;

		if(A[i] > 0)
			sum += ceil((double)A[i] / C);
	}

	cout << sum << endl;

	return 0;
}
