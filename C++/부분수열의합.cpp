#include <iostream>
#include <string.h>

using namespace std;

int main()
{
	int N, S;
	int sub, sum;
	int arr[21];
	int cnt = 0;
	
	cin >> N >> S;
	for (int i = 0; i < N; i++)
		cin >> arr[i];
	sub = (1 << N) - 1;
	while (sub)
	{
		sum = 0;
		for (int i = 0; i < N; i++)
		{
			if (sub & (1 << i))
				sum += arr[i];
		}
		if (sum == S)
			cnt++;
		sub--;
	}
	cout << cnt;
}