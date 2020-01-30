#include <iostream>
#include <string.h>
#include <vector>
#include <math.h>

using namespace std;

typedef struct wheel
{
	int ind; // 순서 번호
	int val; // N : 0 , S : 1
} Wheel;

Wheel input[4][8];
int K; // 회전 횟수
vector<bool> isDiff;
bool isVisited[4];

int returnValue(int num, int ind) // num 번째 톱니바퀴의 ind 번째 value
{
	for (int i = 0; i < 8; i++)
	{
		if (input[num][i].ind == ind)
			return input[num][i].val;
	}
}

void equalCheck()
{
	int l, r;

	for (int i = 0; i < 3; i++)
	{
		l = returnValue(i, 2);
		r = returnValue(i+1, 6);

		if (l != r)
			isDiff.push_back(true);
		else
			isDiff.push_back(false);
	}
}

void rotate(int num, int dir) // -1 반시계방향, 1 시계방향
{
	isVisited[num] = true;
	for (int i = 0; i < 8; i++)
	{
		if (input[num][i].ind == 0 && dir == -1)
			input[num][i].ind = 7;
		else if (input[num][i].ind == 7 && dir == 1)
			input[num][i].ind = 0;
		else
			input[num][i].ind += dir;
	}

	if (num + 1 < 4 && isVisited[num + 1] == false && isDiff[num]) // 오른쪽 보기
		rotate(num + 1, -dir);

	if (num - 1 >= 0 && isVisited[num - 1] == false && isDiff[num - 1]) // 왼쪽 보기
		rotate(num - 1, -dir);
}

int sum()
{
	int result = 0;

	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 8; j++)
		{
			// 12시 방향이고 S극이면
			if (input[i][j].ind == 0 && input[i][j].val == 1)
				result += (int)pow(2, i);
		}
	}
	return result;
}

int main()
{
	int num, dir; // 톱니 번호, 방향
	string line;

	for (int i = 0; i < 4; i++)
	{
		cin >> line;
		for (int j = 0; j < 8; j++)
		{
			input[i][j].ind = j;
			input[i][j].val = (int)line.at(j) - '0'; // c to i
		}
	}

	cin >> K;

	while (K--)
	{
		memset(isVisited, false, sizeof(bool) * 4);
		isDiff.clear();

		cin >> num >> dir;
		equalCheck();
		rotate(num - 1, dir);
	}

	cout << sum() << endl;
}