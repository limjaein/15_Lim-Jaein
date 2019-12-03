#include <iostream>
#include <string.h>
#include <queue>

using namespace std;

int dx[8] = { -2,-1,1,2,-2,-1,1,2 };
int dy[8] = { -1,-2,-2,-1,1,2,2,1 };
bool isVisited[301][301];
pair<int, int> s, e;
int l;

typedef struct info {
	int x, y;
	int cnt;
}info;

int bfs() // cnt return
{
	queue<info> Q;
	int x, y, cnt;
	if (s.second == e.second && s.first == e.first)
		return 0;

	Q.push({ s.first, s.second, 0 });
	isVisited[s.second][s.first] = true;
	while (!Q.empty())
	{
		x = Q.front().x;
		y = Q.front().y;
		cnt = Q.front().cnt;
		Q.pop();

		for (int i = 0; i < 8; i++)
		{
			if (y + dy[i] == e.second && x + dx[i] == e.first) 
				return cnt + 1;
			if (!isVisited[y + dy[i]][x + dx[i]] && y + dy[i] >= 0 && y + dy[i] < l && x + dx[i] >= 0 && x + dx[i] < l)
			{
				Q.push({ x + dx[i], y + dy[i], cnt + 1 });
				isVisited[y + dy[i]][x + dx[i]] = true;
			}
		}
	}
}

int main()
{
	int tc, x, y;

	cin >> tc;
	
	while (tc--)
	{
		memset(isVisited, false, sizeof(isVisited));
		cin >> l;
		cin >> x >> y;
		s = make_pair(x, y);
		cin >> x >> y;
		e = make_pair(x, y);

		cout << bfs() << endl;
	}
}