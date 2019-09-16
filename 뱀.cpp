// 풀다가 중단
#include <iostream>
#include <vector>
#include <string.h>
#include <deque>

using namespace std;

int N;
int sec;
int map[101][101];
int t_cnt = 0;
vector<pair<int, int>> turn;
deque<pair<int, int>> snake;

int checkPos(int y, int x)
{
	return (y >= 0 && x >= 0 && y < N && x < N) ? true : false;
}

bool isWall(int y, int x)
{
	return ((y == 0 || x == 0) && !(y == 0 && x == 0)) ? true : false;
}

void solution(int y, int x, int d)
{
	int m_y = y;
	int m_x = x;

	if (!checkPos(m_y, m_x) || isWall(m_y, m_x) || map[m_y][m_x] == 2)
		return;

	sec++;

	if (turn[t_cnt].first == sec)
	{
		t_cnt++;
		d += turn[t_cnt].second; // 방향 전환
		if (d > 3)
			d = 0;
		else if (d < 0)
			d = 3;
	}

	switch (d)
	{
	case 0: // 서
		m_x--;
		break;
	case 1: // 북
		m_y--;
		break;
	case 2: // 동
		m_x++;
		break;
	case 3: // 남
		m_y++;
		break;
	default:
		break;
	}

	snake.push_front({ m_y,m_x });
	if (map[m_y][m_x] == 1) // 사과면
		map[m_y][m_x] = 0;
	else
		snake.pop_back();

	solution(m_y, m_x, d);
}

int main()
{
	int r, y, x, change, w, temp;
	char d;

	cin >> N;
	cin >> r;

	for (int i = 0; i < N; i++)
		memset(map[i], 0, sizeof(int) * N);

	for (int i = 0; i < r; i++)
	{
		cin >> y >> x;
		map[y][x] = 1;
	}

	cin >> change;

	for (int i = 0; i < change; i++)
	{
		cin >> w >> d;
		if (d == 'D')
			temp = 1;
		else
			temp = -1;
		turn.push_back({ w, temp });
	}

	snake.push_front({ 0,0 });
	solution(0, 0, 2);

	cout << sec << endl;
}