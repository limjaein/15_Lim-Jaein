#include <iostream>
#include <vector>
#include <string.h>
#include <algorithm>
static const int INPUT_MAX = 10;

using namespace std;

vector<vector<int>> input; // 입력 값
int result = INPUT_MAX * INPUT_MAX; // 최소 색종이 수

pair<int,int> isFinished(vector<vector<int>> v)
{
	for (int i = 0; i < INPUT_MAX; i++)
	{
		for (int j = 0; j < INPUT_MAX; j++)
		{
			if (v[i][j] != 0) return {i, j};
		}
	}
	return {-1, -1};
}
void attach(vector<vector<int>>& v, int y, int x, int size)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
			v[y + i][x + j] = 0;
	}
}
void detach(vector<vector<int>>& v, int y, int x, int size)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
			v[y + i][x + j] = 1;
	}
}
int calSize(vector<vector<int>> v, int y, int x)
{
	for (int size = 1; size <= 5; size++)
	{
		if (y + size > INPUT_MAX || x + size > INPUT_MAX)
			return size - 1;
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if (v[y + i][x + j] == 0)
					return size - 1;
			}
		}
	}
	return 5;
}

void dfs(vector<vector<int>> v, int y, int x, int* cnt, int total_cnt)
{
	pair<int, int> p;
	int n_y, n_x;
	bool over = false;
	int max_size = 5;

	for (int i = 0; i < 5; i++)
	{
		if (cnt[i] > 5) 
			return;
	}

	if (total_cnt > result) return;

	p = isFinished(v);
	n_y = p.first;
	n_x = p.second;
	if (n_y == -1 && n_x == -1)
	{
		result = min(result, total_cnt);
		return;
	}

	max_size = calSize(v, n_y, n_x);

	while (max_size)
	{
		attach(v, n_y, n_x, max_size);
		cnt[max_size - 1]++;
		dfs(v, n_y, n_x, cnt, total_cnt + 1);
		cnt[max_size - 1]--;
		detach(v, n_y, n_x, max_size);
		max_size--;
	}
}

int main()
{
	int cnt[5] = { 0, };
	input.assign(INPUT_MAX, vector<int>(INPUT_MAX, -1));
	for (int i = 0; i < INPUT_MAX; i++)
	{
		for (int j = 0; j < INPUT_MAX; j++)
			cin >> input[i][j];
	}

	dfs(input, 0, 0, cnt, 0);

	if (result == INPUT_MAX * INPUT_MAX) result = -1;
	cout << result << endl;
}