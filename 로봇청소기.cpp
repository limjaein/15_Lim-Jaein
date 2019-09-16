#include <iostream>
#include <string.h>

using namespace std;

int N, M;
int room[51][51];
int cnt = 0;
int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };

void solution(int r, int c, int d);


bool checkPos(int y, int x)
{
	return (y >= 0 && x >= 0 && y < N && x < M) ? true : false;
}
void search(int r, int c, int d)
{
	int s_r = r;
	int s_c = c;
	int s_d = d;

	for (int i = 0; i < 4; i++) // �� ���� ���캽
	{
		s_d -= 1;
		if (s_d < 0) s_d = 3;

		switch (s_d)
		{
		case 0: // ��
			s_r--;
			break;
		case 1: // ��
			s_c++;
			break;
		case 2: // ��
			s_r++;
			break;
		case 3: // ��
			s_c--;
			break;
		default:
			break;
		}
		//a
		if (checkPos(s_r, s_c) && room[s_r][s_c] == 0)
		{
			solution(s_r, s_c, s_d);
			return;
		}
		else // b
		{
			s_r = r;
			s_c = c;
			continue;
		}
	}

	switch (d)
	{
	case 0: // ��
		r++;
		break;
	case 1: // ��
		c--;
		break;
	case 2: // ��
		r--;
		break;
	case 3: // ��
		c++;
		break;
	default:
		break;
	}

	//d
	if (!checkPos(r, c) || room[r][c] == 1) // ��
		return;
	else // c
		search(r, c, d);

}

void clean(int r, int c)
{
	if (room[r][c] == 0) // û�� �ȵ� ��ĭ
		cnt++;

	room[r][c] = -1; // û�� ��-��
}

void solution(int r, int c, int d)
{
	clean(r, c);
	search(r, c, d);
}

int main()
{
	int r, c, d;

	cin >> N >> M;
	cin >> r >> c >> d;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
			cin >> room[i][j];

	}
	solution(r, c, d);

	cout << cnt << endl;
}