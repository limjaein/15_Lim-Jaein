#include <iostream>
#include <vector>

using namespace std;

int N, M, L;
vector<int> total;

int game(int i, int cnt)
{
	int next;

	if (total[i] == M)
		return cnt;

	if (total[i] % 2 == 0)
		next = (i - L + N) % N;
	else
		next = (i + L) % N;
	total[next]++;
	return game(next, cnt + 1);
}

int main()
{
	cin >> N >> M >> L;
	total.assign(N, 0);
	total[0]++;
	cout << game(0, 0);
}