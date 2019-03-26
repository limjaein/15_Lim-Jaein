#include <iostream>
#include <string.h>

using namespace std;

int N,M;
bool isVisited[100][100];
char map[100][100];

int checkRange(int x, int y) // 범위 체크
{
	return (x >= 0 && x < M && y >= 0 && y < N) ? true : false;
}

void search(int x, int y)
{
	isVisited[y][x] = true;

	if (map[y][x] == '-' && checkRange(x + 1, y) && map[y][x] == map[y][x+1])
		search(x + 1, y);
	else if (map[y][x] == '|' && checkRange(x, y + 1) && map[y][x] == map[y+1][x])
		search(x, y + 1);
}

int solution()
{
	int cnt = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (isVisited[i][j] == false)
			{
				search(j, i);
				cnt++;
			}
		}
	}
	return cnt;
}

int main()
{
	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		memset(isVisited[i], false, sizeof(bool) * 100);
		for (int j = 0; j < M; j++)
			cin >> map[i][j];
	}

	cout << solution() << endl;

	return 0;
}