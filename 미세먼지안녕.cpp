#include <iostream>

using namespace std;

int R, C, T;
int input[51][51]; // -1�� ����û����, 0 �̻��� �̼�����
int demo[51][51];
bool isVisited[51][51];
// input�� ������ ����, demo���� Ȯ�� ����
// isVisited���� input �����ߴ��� ���� ����
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
		if (x < 0 && y < 0 && x >= C && y >= R) // ���� üũ
			continue;

		if (input[y + dy[i]][x + dx[i]] == -1)
			continue;

		if (input[y + dy[i]][x + dx[i]]) // ���� ������
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
	// ���� ����û����� �ݽð�������� ��ȯ
	// �Ʒ����� �ð�������� ��ȯ
	// �ٶ��� �Ҹ� ��ĭ�� �̵�
	// ���� û���⿡ ���� �����
	
	// ����������
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
	// T�ʰ� ������ �濡 �����ִ� �̼������� �� ���ϱ�

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