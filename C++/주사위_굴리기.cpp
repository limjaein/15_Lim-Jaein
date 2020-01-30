// 다시 풀어보기 - 문제 헷갈림
#include <iostream>

using namespace std;

int N, M, K;
int map[20][20];
int dx[5] = { 0,1,-1,0,0 };
int dy[5] = { 0,0,0,-1,1 };
int dice[7] = { 0 };
int NOW = 1;
int LEFT = 4;
int UP = 2;
int order[1000] = { 0 };

bool checkPos(int x, int y)
{
	return (x >= 0 && y >= 0 && x < M && y < N) ? true : false;
}

void solution(int x, int y)
{
	int dir;
	int tmp;

	for (int i = 0; i < K; i++)
	{
		tmp = NOW;
		dir = order[i];

		if (!checkPos(x + dx[dir], y + dy[dir])) continue;

		x = x + dx[dir];
		y = y + dy[dir];

		switch (dir)
		{
		case 1:
			NOW = LEFT;
			LEFT = 7 - tmp;
			break;
		case 2:
			NOW = 7 - LEFT;
			LEFT = tmp;
			break;
		case 3:
			NOW = 7 - UP;
			UP = tmp;
			break;
		case 4:
			NOW = UP;
			UP = 7 - tmp;
			break;
		default:
			break;
		}

		if (map[y][x] == 0)
			map[y][x] = dice[7 - NOW];
		else
		{
			dice[7 - NOW] = map[y][x];
			map[y][x] = 0;
		}

		cout << dice[NOW] << endl;
	}
}

int main()
{
	int x, y;

	cin >> N >> M >> y >> x >> K;

	for (int i = 0; i < N; i++)
	{
		memset(map[i], 0, sizeof(int) * M);
		for (int j = 0; j < M; j++)
			cin >> map[i][j];
	}

	for (int i = 0; i < K; i++)
		cin >> order[i];

	solution(x, y);
}