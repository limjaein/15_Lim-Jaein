#define _CTR_SECURE_NO_WARNING
#include <iostream>
#include <string.h>

using namespace std;

int w, h;
int map[51][51];
bool check[51][51];
int dx[8] = {0, 1, 1, 1, 0, -1, -1, -1};
int dy[8] = {-1, -1, 0, 1, 1, 1, 0, -1};

bool checkPos(int y, int x)
{
	return (y >= 0 && y < h && x >= 0 && x < w) ? true : false;
}

void dfs(int y, int x)
{
	int m_y, m_x;

	check[y][x] = true;
	for (int i = 0; i < 8; i++)
	{
		m_y = y + dy[i];
		m_x = x + dx[i];
		if (checkPos(m_y, m_x) && !check[m_y][m_x] && map[m_y][m_x] == 1)
			dfs(m_y, m_x);
	}
}

int main()
{
	int cnt;
	while (1)
	{
		cin >> w >> h;
		if (w == 0 && h == 0)
			break;

		// 변수 초기화
		cnt = 0;
		memset(map, -1, sizeof(map));
		memset(check, false, sizeof(check));

		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
				scanf_s("%d", &map[i][j]);
		}

		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				if (!check[i][j] && map[i][j] == 1)
				{
					cnt++;
					dfs(i, j);
				}
				else
					check[i][j] = true;
			}
		}

		cout << cnt << endl;
	}
}