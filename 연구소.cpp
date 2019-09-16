#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
int pick = 3;
typedef struct space {
	int x;
	int y;
};
int map[9][9];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, -1, 0, 1 };
vector<space> empty_list;
vector<space> virus_list;

void spread(int x, int y)
{
	int m_x, m_y;
	for (int i = 0; i < 4; i++)
	{
		m_x = x + dx[i];
		m_y = y + dy[i];

		if (m_x < 0 || m_y < 0 || m_x >= M || m_y >= N)
			continue;

		if (map[m_y][m_x] == 0)
		{
			map[m_y][m_x] = -1;
			spread(m_x, m_y);
		}
	}
}

int solution()
{
	vector<int> arr;
	int result = 0;
	int sum = 0;
	int x, y;

	for (int i = 0; i < pick; i++)
		arr.push_back(1);
	for (int i = 0; i < empty_list.size() - pick; i++)
		arr.push_back(0);

	sort(arr.begin(), arr.end());

	do {

		sum = 0;
		for (int i = 0; i < empty_list.size(); i++)
		{
			if (arr[i] == 1)
			{
				x = empty_list[i].x;
				y = empty_list[i].y;

				map[y][x] = 1;
			}
		}

		for (int i = 0; i < virus_list.size(); i++)
		{
			spread(virus_list[i].x, virus_list[i].y);
		}

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				if (map[i][j] == 0)
					sum++;
				else if (map[i][j] == -1)
					map[i][j] = 0;
			}
		}

		result = max(result, sum);


		for (int i = 0; i < empty_list.size(); i++)
		{
			if (arr[i] == 1)
			{
				x = empty_list[i].x;
				y = empty_list[i].y;

				map[y][x] = 0;
			}
		}

	} while (next_permutation(arr.begin(), arr.end()));

	return result;
}

int main()
{
	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> map[i][j];

			if (map[i][j] == 0)
				empty_list.push_back({ j, i });
			else if (map[i][j] == 2)
				virus_list.push_back({ j, i });
		}
	}

	cout << solution() << endl;
}