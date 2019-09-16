#define _CTR_SECURE_NO_WARNING
#include <iostream>
#include <vector>
#include <string.h>
#include <math.h>

using namespace std;

int input[51][51];
bool check[51][51];
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int N, L, R;

bool checkPos(int y, int x)
{
	return (y >= 0 && x >= 0 && y < N && x < N) ? true : false;
}
void move(vector<pair<int,int>> list)
{
	int sum = 0;
	for (int i = 0; i < list.size(); i++)
		sum += input[list[i].first][list[i].second];
	sum = (int)(sum / list.size());

	for (int i = 0; i < list.size(); i++)
		input[list[i].first][list[i].second] = sum;
}

vector<pair<int, int>> dfs(int y, int x, vector<pair<int, int>> list)
{
	int m_y, m_x, diff;

	check[y][x] = true;
	list.push_back(make_pair(y, x));

	for (int i = 0; i < 4; i++)
	{
		m_y = y + dy[i];
		m_x = x + dx[i];
		diff = abs(input[y][x] - input[m_y][m_x]);
		if (checkPos(m_y, m_x) && diff >= L && diff <= R && !check[m_y][m_x])
		{
			list = dfs(m_y, m_x, list);
		}
	}
	return list;
}

int main()
{
	bool end = true;
	int cnt = 0;
	vector<pair<int, int>> temp = {};

	cin >> N >> L >> R;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
			scanf_s("%d", &input[i][j]);
	}

	while (end)
	{
		end = false;
		memset(check, false, sizeof(check));
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (!check[i][j])
				{
					temp = dfs(i, j, {});
					if (temp.size() > 1)
					{
						if (!end) // 첫 인구이동일 경우
						{
							end = true; // 다음 while 진행
							cnt++;
						}
						move(temp); // 인구이동
					}
				}
			}
		}
	}
	cout << cnt << endl;
}