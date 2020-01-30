#define _CTR_SECURE_NO_WARNING
#include <iostream>
#include <string.h>
#include <vector>
#include <algorithm>

using namespace std;

typedef struct cctv {
	int x, y;
	int type;
} cctv;
int N, M;
int input[8][8];
vector<cctv> cctv_list;
// 오 위 왼 아래
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, -1, 0, 1};
int result;

void clear(int (*room)[8], int (*def)[8]) // 초기화 함수
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
			room[i][j] = def[i][j];
	}
}

void watch(int(*room)[8], int y, int x, int dir)
{
	while (1) // 범위 안 일경우
	{
		y += dy[dir];
		x += dx[dir];

		if (!(y >= 0 && y < N && x >= 0 && x < M))
			return;

		if (room[y][x] == 6)
			return;
		if(room[y][x] == 0)
			room[y][x] = -1;
	}
}

int count(int(*room)[8])
{
	int cnt = 0;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (room[i][j] == 0)
				cnt++;
		}
	}
	return cnt;
}

void solution(int (*prev)[8], int idx)
{
	cctv now;
	int room[8][8];

	if (idx == cctv_list.size()) // 마지막 cctv이면 사각지대 개수 세기
	{
		result = min(result, count(prev));
		return;
	}
	
	clear(room, prev); // room 초기화

	now = cctv_list[idx];
	switch (now.type)
	{
		case 1:
			for (int i = 0; i < 4; i++)
			{
				watch(room, now.y, now.x, i);
				solution(room, idx + 1);
				clear(room, prev);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++)
			{
				watch(room, now.y, now.x, i);
				watch(room, now.y, now.x, i + 2);
				solution(room, idx + 1);
				clear(room, prev);
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++)
			{
				watch(room, now.y, now.x, i);
				watch(room, now.y, now.x, (i + 1) % 4);
				solution(room, idx + 1);
				clear(room, prev);
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++)
			{
				watch(room, now.y, now.x, i);
				watch(room, now.y, now.x, (i + 1) % 4);
				watch(room, now.y, now.x, (i + 2) % 4);
				solution(room, idx + 1);
				clear(room, prev);
			}
			break;
		case 5:
			for (int i = 0; i < 4; i++)
				watch(room, now.y, now.x, i);
			solution(room, idx + 1);
			break;
		default:
			break;
	}
}

int main()
{
	int room[8][8];
	cin >> N >> M;
	result = N * M;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			scanf_s("%d", &input[i][j]);
			if (input[i][j] > 0 && input[i][j] < 6)
				cctv_list.push_back({ j,i,input[i][j] }); // x, y, type
		}
	}

	solution(input, 0);

	cout << result << endl;
}