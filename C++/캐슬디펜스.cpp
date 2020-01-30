#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>

using namespace std;

typedef struct pos {
	int x, y;
}pos;
typedef struct enemy {
	pos p;
	int dist;
};
int result = 0;
int N, M, D;
int map[16][16];
int test[16][16];

bool cmp(enemy a, enemy b)
{
	if (a.dist < b.dist)
		return true;
	else if (a.dist == b.dist)
	{
		if (a.p.x < b.p.x)
			return true;
	}
	return false;
}
bool isFinished(int r)
{
	for (int i = 0; i < N - r; i++)
	{
		for (int j = 0; j < M; j++)
			if (test[i][j] == 1) return false;
	}
	return true;
}

void solution()
{
	vector<pos> enemies;
	vector<pos> shooter;
	vector<enemy> list; // dist, ind
	vector<int> ind;
	vector<pos> target;
	int cnt;
	int row = 0;
	int dist = 0;

	for (int i = 0; i < M - 3; i++)
		ind.push_back(0);
	for (int i = 0; i < 3; i++)
		ind.push_back(1);

	sort(ind.begin(), ind.end());

	for (int i = 0; i < N; i++) // 적 리스트
	{
		for (int j = 0; j < M; j++)
		{
			if (map[i][j] == 1)
				enemies.push_back({ j,i });
		}
	}

	do {
		cnt = 0;
		row = 0;

		for (int i = 0; i < N; i++) // map 초기화
		{
			for (int j = 0; j < M; j++)
				test[i][j] = map[i][j];
		}

		for (int i = 0; i < M; i++)
		{
			if (ind[i] == 1)
				shooter.push_back({ i, N });
		}

		while (!isFinished(row) && row < N)
		{
			for (int s = 0; s < 3; s++) // 궁수 마다 가장 가까운 적
			{
				for (int i = 0; i < enemies.size(); i++)
				{
					if (enemies[i].y + row >= N || test[enemies[i].y][enemies[i].x] == 0) // 지나간 줄
						continue;
					dist = abs(enemies[i].y + row - shooter[s].y) + abs(enemies[i].x - shooter[s].x);
					if(dist <= D)
						list.push_back({ enemies[i] , dist });
				}
				if (list.size() == 0)
					continue;
				sort(list.begin(), list.end(), cmp);
				target.push_back(list.front().p);
				list.clear();
			}

			for (int i = 0; i < target.size(); i++)
			{
				if (test[target[i].y][target[i].x] == 1) {
					test[target[i].y][target[i].x] = 0;
					cnt++;
				}
			}
			target.clear();
			row++; // 다음 줄
		}
		result = max(result, cnt);
		shooter.clear();
	} while (next_permutation(ind.begin(), ind.end()));
}
int main()
{
	cin >> N >> M >> D;
	memset(map, 0, sizeof(map));
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
			cin >> map[i][j];
	}
	solution();
	cout << result << endl;
}