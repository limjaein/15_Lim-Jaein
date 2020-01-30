#include <iostream>
#include <string.h>
#include <queue>

using namespace std;

bool comp[101][101];
bool isVisited[101];
int n;

int solution()
{
	int cnt = 0;
	queue<int> q;
	int node;
	q.push(1);
	isVisited[1] = true;

	while (!q.empty())
	{
		node = q.front();
		q.pop();
		for (int i = 1; i < n+1; i++)
		{
			if (comp[node][i] && node != i && !isVisited[i])
			{
				q.push(i);
				isVisited[i] = true;
				cnt++;
			}
		}
	}
	return cnt;
}
int main()
{
	int a, b, c;
	cin >> n >> c;
	memset(comp, false, sizeof(comp));
	memset(isVisited, false, sizeof(isVisited));
	for (int i = 0; i < c; i++)
	{
		cin >> a >> b;
		comp[a][b] = comp[b][a] = true;
	}
	cout << solution();
}