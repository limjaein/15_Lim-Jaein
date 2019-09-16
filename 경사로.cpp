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
				if (abs(prev - map[i][j]) > 1) break; // 다음 줄로

				if (prev > map[i][j]) // 새로 경사로 만들 때
				{
					if (j + L > N) // 경사로가 인덱스 초과
						break;
					for (int l = 0; l < L; l++) // 경사로 탐색
					{
						if (isUsed[j + l] || map[i][j + l] != map[i][j]) // 경사로가 있거나, 평평하지 않은 경우
							check = false; // 한 줄 실패
					}
					if (!check) // 경사로 만들기 실패
						break;
					for (int l = 0; l < L; l++)
						isUsed[j + l] = true;
				}
				else if (prev < map[i][j]) // 지나온 길에 경사로
				{
					if (j - L < 0) // 경사로가 인덱스 초과
						break;
					for (int l = 1; l < L + 1; l++) // 1개 전부터 탐색
					{
						if (isUsed[j - l] || map[i][j - l] != map[i][j - 1])
							check = false; // 한 줄 실패
					}
					if (!check)
						break;
					for (int l = 1; l < L + 1; l++)
						isUsed[j - l] = true;
				}
			}

			prev = map[i][j];
			if (j == N - 1) // 마지막 칸일 때
				result++;
			j++; // 다음 칸
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
				if (abs(prev - map[j][i]) > 1) break; // 다음 줄로

				if (prev > map[j][i]) // 새로 경사로 만들 때
				{
					if (j + L > N) // 경사로가 인덱스 초과
						break;
					for (int l = 0; l < L; l++) // 경사로 탐색
					{
						if (isUsed[j + l] || map[j + l][i] != map[j][i]) // 경사로가 있거나, 평평하지 않은 경우
							check = false; // 한 줄 실패
					}
					if (!check) // 경사로 만들기 실패
						break;
					for (int l = 0; l < L; l++)
						isUsed[j + l] = true;
				}
				else if (prev < map[j][i]) // 지나온 길에 경사로
				{
					if (j - L < 0) // 경사로가 인덱스 초과
						break;
					for (int l = 1; l < L + 1; l++) // 1개 전부터 탐색
					{
						if (isUsed[j - l] || map[j - l][i] != map[j - 1][i])
							check = false; // 한 줄 실패
					}
					if (!check)
						break;
					for (int l = 1; l < L + 1; l++)
						isUsed[j - l] = true;
				}
			}

			prev = map[j][i];
			if (j == N - 1) // 마지막 칸일 때
				result++;
			j++; // 다음 칸
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

	//가로
	row();
	//세로
	column();
	cout << result << endl;
}