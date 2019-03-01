//시간 초과 나는 코드
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int UP = 0;
const int RIGHT = 1;
const int DOWN = 2;
const int LEFT = 3;
int N, M;
int area[8][8];
// 상 우 하 좌, 시계 방향
int dx[4] = { 0,1,0,-1 };
int dy[4] = { -1,0,1,0 };
int min_cnt = 64;
vector<pair<int, int>> cctv;

void checkArea(int x, int y, int direction, int check) // 1이면 체크 -1이면 복원
{
	if (direction == UP)
	{
		for (int i = y - 1; i >= 0; i--)
		{
			if (area[i][x] == 6) // 벽이면 stop
				break;
			else if (area[i][x] <= 0)
				area[i][x] -= check; // 겹치는 부분 체크
		}
	}
	else if (direction == DOWN)
	{
		for (int i = y + 1; i < N; i++)
		{
			if (area[i][x] == 6) // 벽이면 stop
				break;
			else if (area[i][x] <= 0)
				area[i][x] -= check;

		}
	}
	else if (direction == LEFT)
	{
		for (int i = x - 1; i >= 0; i--)
		{
			if (area[y][i] == 6) // 벽이면 stop
				break;
			else if (area[y][i] <= 0)
				area[y][i] -= check;
		}
	}
	else if (direction == RIGHT)
	{
		for (int i = x + 1; i < M; i++)
		{
			if (area[y][i] == 6) // 벽이면 stop
				break;
			else if (area[y][i] <= 0)
				area[y][i] -= check;
		}
	}
}

int makeCount()
{
	int cnt = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (area[i][j] == 0)
				cnt++;
		}
	}

	return cnt;
}

void solution(int index)
{
	int repeat = 4; // 4방향으로 회전
	int num, x, y;
	int cnt = 0;

	x = cctv[index].second;
	y = cctv[index].first;
	num = area[y][x];

	if (num == 2)
		repeat = 2;

	for (int i = 0; i < repeat; i++)
	{
		cnt = 0;

		if (num == 1)
		{
			checkArea(x, y, UP + i, 1);
		}
		else if (num == 2)
		{
			// 0 2 상하 1 3 좌우
			checkArea(x, y, UP + i, 1);
			checkArea(x, y, DOWN + i, 1);
		}
		else if (num == 3)
		{
			checkArea(x, y, UP + i, 1);
			checkArea(x, y, (RIGHT + i) % 4, 1);
		}
		else if (num == 4)
		{
			checkArea(x, y, UP + i, 1);
			checkArea(x, y, (RIGHT + i) % 4, 1);
			checkArea(x, y, (DOWN + i) % 4, 1);
		}

		if (index < cctv.size() - 1)
		{
			solution(index + 1);
		}
		else
		{
			cnt = makeCount();
			min_cnt = min(min_cnt, cnt);
		}

		//복원
		if (num == 1)
		{
			checkArea(x, y, UP + i, -1);
		}
		else if (num == 2)
		{
			// 0 2 상하 1 3 좌우
			checkArea(x, y, UP + i, -1);
			checkArea(x, y, DOWN + i, -1);
		}
		else if (num == 3)
		{
			checkArea(x, y, UP + i, -1);
			checkArea(x, y, (RIGHT + i) % 4, -1);
		}
		else if (num == 4)
		{
			checkArea(x, y, UP + i, 1);
			checkArea(x, y, (RIGHT + i) % 4, -1);
			checkArea(x, y, (DOWN + i) % 4, -1);
		}
	}

	return;
}

int main()
{
	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> area[i][j];
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (area[i][j] == 5) // 전체 방향은 미리 체크
			{
				checkArea(j, i, UP, 1);
				checkArea(j, i, DOWN, 1);
				checkArea(j, i, LEFT, 1);
				checkArea(j, i, RIGHT, 1);
			}
			else if (area[i][j] != 0 && area[i][j] != 6)
				cctv.push_back(make_pair(i, j));
		}
	}

	if (cctv.size())
		solution(0);
	else
		min_cnt = makeCount();

	cout << min_cnt << endl;

	return 0;

}
