#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int line; // 가로, 세로

// 상 하 좌 우
int dx[4] = { 0, 0, -1, 1 };
int dy[4] = { -1, 1, 0, 0 };

int apart[25][25]; // input 배열
vector<int> apartSum;


bool isPossible(int y, int x)
{
	return (y < 0 || y >= line || x < 0 || x >= line) ? false : true;

}
void DFS(int y, int x, int num)
{
	int next_x, next_y;

	apart[y][x] = num;

	for (int i = 0; i < 4; i++) // 상 하 좌 우 탐색
	{
		next_x = x + dx[i];
		next_y = y + dy[i];

		if (isPossible(next_y, next_x) && apart[next_y][next_x] == 1)
		{
			DFS(next_y, next_x, num);
		}
	}
}

int sol()
{
	int count = 1;

	for (int i = 0; i < line; i++)
	{
		for (int j = 0; j < line; j++)
		{
			if (apart[i][j] == 1)
			{
				count++;
				DFS(i, j, count);
			}
		}
	}

	apartSum.assign(count - 1, 0);

	for (int i = 0; i < line; i++)
	{
		for (int j = 0; j < line; j++)
		{
			if (apart[i][j] > 1)
			{
				apartSum[apart[i][j] - 2]++;
			}
		}
	}

	return count - 1;
}

int main()
{
	string input;

	cin >> line;

	for (int i = 0; i < line; i++)
	{
		for (int j = 0; j < line; j++)
			scanf_s("%1d", &apart[i][j]);
	}

	cout << sol() << endl;

	sort(apartSum.begin(), apartSum.end());

	for (int i = 0; i < apartSum.size(); i++)
	{
		cout << apartSum[i] << endl;
	}

	return 0;
}
