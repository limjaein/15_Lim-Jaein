#include <iostream>

using namespace std;

int n, m, M, T, R;
int mb;

bool exercise(int b)
{
	return (b + T > M) ? false : true;
}

int rest(int b)
{
	return (b - R < m) ? m : b - R;
}

int solution()
{
	int cnt = 0;
	int time = 0;
	mb = m;

	while (cnt < n)
	{
		if (exercise(mb))
		{
			cnt++;
			mb += T;
		}
		else
			mb = rest(mb);
		time++;
	}

	return time;
}

int main()
{
	cin >> n >> m >> M >> T >> R;

	if (M - m < T)
		cout << -1 << endl;
	else
		cout << solution() << endl;
}