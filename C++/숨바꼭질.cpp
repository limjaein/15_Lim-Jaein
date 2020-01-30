#include <iostream>
#include <string.h>
#include <algorithm>
#include <queue>

using namespace std;

queue<pair<int,int>> q; // 위치, cnt
int N, K;
int isVisited[100001];

bool check(int pos)
{
	return (pos >= 0 && pos <= 100000 && isVisited[pos - 1] == -1) ? true : false;
}

int solution()
{
	int pos;
	int cnt = 0;

	q.push(make_pair(N,0));
	isVisited[N - 1] = 1;

	while (q.size())
	{
		pos = q.front().first;
		cnt = q.front().second;
		q.pop();

		if (pos == K)
			return cnt;

		if (check(pos + 1))
		{
			q.push(make_pair(pos + 1, cnt + 1));
			isVisited[pos] = 1;
		}

		if (check(pos - 1))
		{
			q.push(make_pair(pos - 1, cnt + 1));
			isVisited[pos - 2] = 1;
		}

		if (check(pos * 2))
		{
			q.push(make_pair(pos * 2, cnt + 1));
			isVisited[pos * 2 - 1] = 1;
		}
	}
}

int main()
{
	cin >> N >> K;

	memset(isVisited, -1, sizeof(int) * 100001);

	if (N >= K)
		cout << N - K << endl;
	else
		cout << solution() << endl;

	return 0;
}
