#include <iostream>
#include <vector>
#include <queue>
#include <string.h>
#include <algorithm>

using namespace std;

typedef struct info {
	int y, x, cnt;
}info;
int N, M;
int input[51][51];
bool isVisited[51][51];
int dx[8] = { -1,-1,-1,0,0,1,1,1 };
int dy[8] = { -1,0,1,-1,1,-1,0,1 };
vector<pair<int, int>> sharks; // y, x

int solution()
{
	int result = 1;
	queue<info> q;
	int l = sharks.size();
	info nInfo;
	int ny, nx;

	for (int i = 0; i < l; i++)
	{
		q.push({ sharks[i].first, sharks[i].second, 0 });
		memset(isVisited, false, sizeof(isVisited));
		while (!q.empty())
		{
			nInfo = q.front();
			q.pop();
			for (int i = 0; i < 8; i++)
			{
				ny = nInfo.y + dy[i];
				nx = nInfo.x + dx[i];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M || input[ny][nx] == -1 || isVisited[ny][nx])
					continue;
				if (input[ny][nx] == 0 || input[ny][nx] > nInfo.cnt + 1)
				{
					input[ny][nx] = nInfo.cnt + 1;
					q.push({ ny, nx, nInfo.cnt + 1 });
				}
				isVisited[ny][nx] = true;
			}
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
			result = max(result, input[i][j]);
	}
	return result;
}

int main()
{
	cin >> N >> M;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> input[i][j];
			if (input[i][j] == 1)
			{
				sharks.push_back({ i,j });
				input[i][j] = -1;
			}
		}
	}
	cout << solution() << endl;
}