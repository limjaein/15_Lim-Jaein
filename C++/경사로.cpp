#include <iostream>
#include <string.h>

using namespace std;

int N, L;
int result = 0;
int map[101][101];

bool check(int num, int x, int y, int mode)
{
	int cnt = 0;
	for (int i = 0; i < L; i++)
	{
		switch (mode)
		{
		case 1:
			if (map[y][x + i] != num)
				return false;
			break;
		case 2:
			if (map[y][x - i - 1] != num)
				return false;
			break;
		case 3:
			if (map[y + i][x] != num)
				return false;
			break;
		case 4:
			if (map[y - i - 1][x] != num)
				return false;
			break;
		}
		cnt++;
		if (cnt == L)
		{
			return true;
		}
	}


}
void row()
{
	int prev;
	bool isUsed[101] = {false};
	bool check = true;
	int j;

	for (int i = 0; i < N; i++)
	{
		prev = map[i][0];
		check = true;
		memset(isUsed, false, sizeof(isUsed));
		j = 1;
		while (j < N)
		{
			if (!isUsed[j])
			{
				if (abs(prev - map[i][j]) > 1) break; // ���� �ٷ�

				if (prev > map[i][j]) // ���� ���� ���� ��
				{
					if (j + L > N) // ���ΰ� �ε��� �ʰ�
						break;
					for (int l = 0; l < L; l++) // ���� Ž��
					{
						if (isUsed[j + l] || map[i][j + l] != map[i][j]) // ���ΰ� �ְų�, �������� ���� ���
							check = false; // �� �� ����
					}
					if (!check) // ���� ����� ����
						break;
					for (int l = 0; l < L; l++)
						isUsed[j + l] = true;
				}
				else if (prev < map[i][j]) // ������ �濡 ����
				{
					if (j - L < 0) // ���ΰ� �ε��� �ʰ�
						break;
					for (int l = 1; l < L + 1; l++) // 1�� ������ Ž��
					{
						if (isUsed[j - l] || map[i][j - l] != map[i][j - 1])
							check = false; // �� �� ����
					}
					if (!check)
						break;
					for (int l = 1; l < L + 1; l++)
						isUsed[j - l] = true;
				}
			}

			prev = map[i][j];
			if (j == N - 1) // ������ ĭ�� ��
				result++;
			j++; // ���� ĭ
		}
	}
}
void column()
{
	int prev;
	bool isUsed[101] = { false };
	bool check = true;
	int j;

	for (int i = 0; i < N; i++)
	{
		prev = map[0][i];
		check = true;
		memset(isUsed, false, sizeof(isUsed));
		j = 1;
		while (j < N)
		{
			if (!isUsed[j])
			{
				if (abs(prev - map[j][i]) > 1) break; // ���� �ٷ�

				if (prev > map[j][i]) // ���� ���� ���� ��
				{
					if (j + L > N) // ���ΰ� �ε��� �ʰ�
						break;
					for (int l = 0; l < L; l++) // ���� Ž��
					{
						if (isUsed[j + l] || map[j + l][i] != map[j][i]) // ���ΰ� �ְų�, �������� ���� ���
							check = false; // �� �� ����
					}
					if (!check) // ���� ����� ����
						break;
					for (int l = 0; l < L; l++)
						isUsed[j + l] = true;
				}
				else if (prev < map[j][i]) // ������ �濡 ����
				{
					if (j - L < 0) // ���ΰ� �ε��� �ʰ�
						break;
					for (int l = 1; l < L + 1; l++) // 1�� ������ Ž��
					{
						if (isUsed[j - l] || map[j - l][i] != map[j - 1][i])
							check = false; // �� �� ����
					}
					if (!check)
						break;
					for (int l = 1; l < L + 1; l++)
						isUsed[j - l] = true;
				}
			}

			prev = map[j][i];
			if (j == N - 1) // ������ ĭ�� ��
				result++;
			j++; // ���� ĭ
		}
	}
}
int main()
{
	cin >> N >> L;
	
	for (int i = 0; i < N; i++)
	{
		memset(map[i], 0, sizeof(int) * N);
		for (int j = 0; j < N; j++)
			scanf_s("%d", &map[i][j]);
	}

	//����
	row();
	//����
	column();
	cout << result << endl;
}