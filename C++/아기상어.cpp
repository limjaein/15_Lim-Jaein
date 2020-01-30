#include <iostream>
#include <queue>
#include <string.h>
#include <vector>

using namespace std;

typedef struct SHARK {
	int x, y, size, cnt;
}SHARK;

typedef struct FISH {
	int x, y, size, dist;
}FISH;

int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };
int input[21][21];
SHARK shark;
int N;

bool cmp(FISH a, FISH b)
{
	if (a.dist < b.dist)
		return true;
	if (a.dist > b.dist)
		return false;
	if (a.y < b.y)
		return true;
	if (a.y > b.y)
		return false;

	if (a.x < b.x)
		return true;
	else
		return false;
}

int solution()
{
	vector<FISH> fishes;
	SHARK s;
	int isVisited[21][21];
	int nx, ny;
	int time = 0;

	while (1)
	{
		memset(isVisited, false, sizeof(isVisited));

		queue<SHARK> q;
		q.push({shark.x, shark.y, 0, 0}); // size 부분 안씀
		isVisited[shark.y][shark.x] = true;
		while (!q.empty())
		{
			s = q.front();
			q.pop();
			for (int i = 0; i < 4; i++)
			{
				nx = s.x + dx[i];
				ny = s.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || isVisited[ny][nx])
					continue;
				if (input[ny][nx] == 0 || shark.size == input[ny][nx])
					q.push({ nx, ny, 0, s.cnt + 1 });
				else
				{
					if (shark.size > input[ny][nx])
						fishes.push_back({ nx, ny, input[ny][nx], s.cnt + 1 });
				}
				isVisited[ny][nx] = true;
			}
		}
		if (fishes.size() == 0)
			return time;
		sort(fishes.begin(), fishes.end(), cmp);

		// 우선 순위 높은것 먹음
		input[fishes[0].y][fishes[0].x] = 0;
		time += fishes[0].dist;
		shark.cnt++;
		shark.y = fishes[0].y;
		shark.x = fishes[0].x;
		if (shark.cnt == shark.size)
		{
			shark.cnt = 0;
			shark.size++;
		}
		fishes.clear();
	}
}

int main()
{
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cin >> input[i][j];
			if (input[i][j] == 9)
			{
				shark = { j, i, 2, 0 };
				input[i][j] = 0;
			}
		}
	}
	cout << solution() << endl;
}