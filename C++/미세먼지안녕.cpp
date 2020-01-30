#include <iostream>
#include <queue>
#include <string.h>

using namespace std;

int R, C, T;
int input[51][51]; // -1은 공기청정기, 0 이상은 미세먼지
int m[2][2];
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };
typedef struct room {
	int y;
	int x;
	int dust;
} room;
queue<room> list;

void spread()
{
	int y, x, dust;

	while (list.size())
	{
		y = list.front().y;
		x = list.front().x;
		dust = list.front().dust;
		list.pop();

		int out = dust / 5;
		int m_x, m_y;

		for (int i = 0; i < 4; i++)
		{
			m_x = x + dx[i];
			m_y = y + dy[i];

			if (m_x < 0 || m_y < 0 || m_x >= C || m_y >= R) // 범위 체크
				continue;

			if (input[m_y][m_x] == -1)
				continue;

			input[m_y][m_x] += out;
			input[y][x] -= out;
		}
	}
}

void upCare()
{
	int temp = 0;
	int prev = 0;
	int y = m[0][0];
	int x = m[0][1];

	for (int i = x + 1; i < C; i++)
	{
		temp = input[y][i];
		input[y][i] = prev;
		prev = temp;
	}

	for (int i = y - 1; i >= 0; i--)
	{
		temp = input[i][C-1];
		input[i][C-1] = prev;
		prev = temp;
	}

	for (int i = C - 2; i >= 0; i--)
	{
		temp = input[0][i];
		input[0][i] = prev;
		prev = temp;
	}

	for (int i = 1; i < y; i++)
	{
		temp = input[i][0];
		input[i][0] = prev;
		prev = temp;
	}
}

void downCare()
{
	int temp = 0;
	int prev = 0;
	int y = m[1][0];
	int x = m[1][1];

	for (int i = x + 1; i < C; i++)
	{
		temp = input[y][i];
		input[y][i] = prev;
		prev = temp;
	}

	for (int i = y + 1; i < R; i++)
	{
		temp = input[i][C-1];
		input[i][C-1] = prev;
		prev = temp;
	}

	for (int i = C - 2; i >= 0; i--)
	{
		temp = input[R-1][i];
		input[R-1][i] = prev;
		prev = temp;
	}

	for (int i = R - 2; i > y; i--)
	{
		temp = input[i][0];
		input[i][0] = prev;
		prev = temp;
	}
}

int main()
{
	int mCnt = 0;
	int sum = 0;

	cin >> R >> C >> T;
	for (int i = 0; i < R; i++)
	{
		for (int j = 0; j < C; j++)
		{
			cin >> input[i][j];

			if (input[i][j] == -1)
			{
				m[mCnt][0] = i;
				m[mCnt][1] = j;
				mCnt++;
			}
		}
	}
	// T초가 지난후 방에 남아있는 미세먼지의 양 구하기

	while (T--)
	{
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				if (input[i][j] > 0)
				{
					room r = { i,j,input[i][j] };
					list.push(r);
				}
			}
		}

		spread();
		upCare();
		downCare();
	}

	for (int i = 0; i < R; i++)
	{
		for (int j = 0; j < C; j++)
		{
			if (input[i][j] > 0)
				sum += input[i][j];
		}
	}

	cout << sum << endl;
}
