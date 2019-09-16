#include <iostream>

using namespace std;

int R, C, T;
int input[51][51]; // -1은 공기청정기, 0 이상은 미세먼지
int demo[51][51];
bool isVisited[51][51];
// input에 최종본 저장, demo에서 확산 적용
// isVisited에서 input 접근했는지 정보 저장
int m[2][2];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void spread(int y, int x)
{
	int remain = input[y][x];
	int out = input[y][x]/5;
	if (isVisited[y][x])
		return;
	isVisited[y][x] = true;

	for (int i = 0; i < 4; i++)
	{
		if (x < 0 && y < 0 && x >= C && y >= R) // 범위 체크
			continue;

		if (input[y + dy[i]][x + dx[i]] == -1)
			continue;

		if (input[y + dy[i]][x + dx[i]]) // 값이 있으면
		{
			spread(y + dy[i], x + dx[i]);
		}
		demo[y + dy[i]][x + dx[i]] += out;
		remain -= out;
	}

	demo[y][x] = remain;
}

void upCare()
{
	int next = 0;
	int prev = 0;
	int x = m[0][0];
	int y = m[0][1];
	// 위쪽 공기청정기는 반시계방향으로 순환
	// 아래쪽은 시계방향으로 순환
	// 바람이 불면 한칸씩 이동
	// 공기 청정기에 들어가면 사라짐
	
	// 오른쪽으로
	for (int i = x; i < C; i++)
	{
		next = input[y][i];
		input[y][i] = prev;
	}

	for (int i = y; i >= 0; i--)
	{
		next = input[i][x];
		input[i][x] = prev;
	}

	for (int i = C-1; i >= 0; i--)
	{
		next = input[0][i];
		input[0][i] = prev;
	}
	
	for (int i = 0; i < y; i++)
	{
		next = input[i][x];
		input[i][x] = prev;
	}
}

void downCare()
{
	int next = 0;
	int prev = 0;
	int x = m[1][0];
	int y = m[1][1];

	for (int i = x; i < C; i++)
	{
		next = input[y][i];
		input[y][i] = prev;
	}

	for (int i = y; i < R; i++)
	{
		next = input[i][x];
		input[i][x] = prev;
	}

	for (int i = C - 1; i >= 0; i--)
	{
		next = input[R - 1][i];
		input[R - 1][i] = prev;
	}

	for (int i = R - 1; i < y; i++)
	{
		next = input[i][0];
		input[i][0] = prev;
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
					spread(i, j);
			}
		}
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
}