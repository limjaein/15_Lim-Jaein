//시간초과
//#include <iostream>
//#include <string.h>
//
//using namespace std;
//
//int n, k;
//int cnt = 0;
//int input[101];
//int sum[100001];
//
//void solution(int num, int min)
//{
//	int next = 0;
//	int count = 0;
//
//	for (int i = min; i < n; i++)
//	{
//		next = num + input[i];
//
//		if (next <= k)
//		{
//			sum[next]++;
//			solution(next, i);
//		}
//	}
//}
//
//int main()
//{
//	cin >> n >> k;
//	for (int i = 0; i < n; i++)
//		cin >> input[i];
//	memset(sum, 0, sizeof(int) * (k+1));
//
//	solution(0, 0);
//	cout << sum[k] << endl;
//}

#include <iostream>
#include <string.h>

using namespace std;

int n, k;
int cnt = 0;
int input[101];
int sum[100001];

void solution(int num, int min)
{
	int next = 0;
	int count = 0;

	for (int i = min; i < n; i++)
	{
		next = num + input[i];

		if (next <= k)
		{
			sum[next]++;
			solution(next, i);
		}
	}
}

int main()
{
	cin >> n >> k;
	for (int i = 0; i < n; i++)
		cin >> input[i];
	memset(sum, 0, sizeof(int) * (k+1));

	solution(0, 0);
	cout << sum[k] << endl;
}