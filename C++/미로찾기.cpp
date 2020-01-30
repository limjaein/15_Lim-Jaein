#include <iostream>
#include <string.h>
#include <string>
#include <queue>

using namespace std;

typedef struct Pos {
	int y, x;
}Pos;
const int maxInput = 101;
int N, M;
int input[maxInput][maxInput];
bool isVisited[maxInput][maxInput];
int dx[4] = { -1, 1, 0, 0 };
int dy[4] = { 0, 0, 1, -1 };

bool checkPos(Pos p)
{
	return (p.y <= 0 || p.x <= 0 || p.y > N || p.x > M) ? false : true;
}
int bfs()
{
	queue<pair<Pos, int>> q;
	pair<Pos, int> tmp;
	Pos npos;

	q.push({ { 1, 1 }, 1 });
	isVisited[1][1] = true;
	while (!q.empty())
	{
		tmp = q.front();
		q.pop();
		for (int i = 0; i < 4; i++)
		{
			npos.y = tmp.first.y + dy[i];
			npos.x = tmp.first.x + dx[i];
			if (!checkPos(npos) || input[npos.y][npos.x] == 0 || isVisited[npos.y][npos.x])
				continue;
			if (npos.y == N && npos.x == M)
				return tmp.second + 1; // cnt++
			q.push({ npos, tmp.second + 1 });
			isVisited[npos.y][npos.x] = true;
		}
	}
}

int main()
{
	string str;
	memset(input, 0, sizeof(input));
	memset(isVisited, false, sizeof(isVisited));
	cin >> N >> M;
	for (int i = 0; i < N; i++)
	{
		cin >> str;
		for (int j = 0; j < M; j++)
			input[i + 1][j + 1] = str[j] - '0';
	}
	cout << bfs();
}