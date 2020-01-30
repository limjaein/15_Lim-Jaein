#include <iostream>
#include <algorithm>

using namespace std;

int max_sum = -1;
int N, M;
int input[501][501];
int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };
bool isVisited[501][501];

int checkPos(int y, int x)
{
	return (y >= 0 && x >= 0 && y < N && x < M) ? true : false;
}

void dfs(int y, int x, int cnt, int sum)
{
	int m_y, m_x;

	if (cnt == 4)
	{
		max_sum = max(max_sum, sum);
		return;
	}

	isVisited[y][x] = true;

	for (int i = 0; i < 4; i++)
	{
		m_y = y + dy[i];
		m_x = x + dx[i];

		if (!checkPos(m_y, m_x) || isVisited[m_y][m_x])
			continue;

		dfs(m_y, m_x, cnt + 1, sum + input[y][x]);
	}

	isVisited[y][x] = false;
}

void putO(int y, int x)
{
	int minValue = 1001;
	int sum = input[y][x];
	int m_y, m_x;
	int cnt = 0;

	for (int i = 0; i < 4; i++)
	{
		m_y = y + dy[i];
		m_x = x + dx[i];
		if (!checkPos(m_y, m_x) || isVisited[m_y][m_x])
			continue;
		sum += input[m_y][m_x];
		cnt++;
		minValue = min(minValue, input[m_y][m_x]);
	}

	if (cnt == 4)
		sum -= minValue;

	max_sum = max(max_sum, sum);
}

int main()
{
	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		memset(isVisited[i], false, sizeof(bool) * M);
		for (int j = 0; j < M; j++)
			cin >> input[i][j];
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			dfs(i, j, 0, 0);
			putO(i, j);
		}
	}

	cout << max_sum << endl;
}