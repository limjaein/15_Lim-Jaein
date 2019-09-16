#include <iostream>
#include <vector>
#include <string.h>
#include <math.h>
#include <algorithm>

using namespace std;

int N;
bool check[101][101];
// ��, ��, ��, �Ʒ�
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,-1,0,1 };

void solution(int x, int y, int dir, int gen)
{
	vector<int> list; // ȸ�� �� ����Ʈ
	int line;

	list.push_back(dir); // ù��°
	check[y][x] = true;

	for (int i = 1; i < gen+1; i++)
	{
		line = list.size();
		for (int j = 0; j < pow(2, i)/2; j++)
		{
			// ������ �� ������ �̵�
			x += dx[dir];
			y += dy[dir];

			// ��Ī ������ 90�� ȸ�� ��
			dir = list[line - j - 1] + 1;
			dir %= 4;

			if(!check[y][x]) check[y][x] = true;
			list.push_back(dir);
		}
	}

	check[y + dy[dir]][x + dx[dir]] = true;
}

int main()
{
	int cnt = 0;
	int x, y, dir, gen;
	memset(check, false, sizeof(check));
	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> x >> y >> dir >> gen;
		solution(x, y, dir, gen);
	}

	for (int i = 0; i < 100; i++)
	{
		for (int j = 0; j < 100; j++)
		{
			if (check[i][j] && check[i + 1][j] && check[i][j + 1] && check[i + 1][j + 1])
				cnt++;
		}
	}

	cout << cnt;
}