
#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>

using namespace std;

const int INF = 1234567;
int N, M, X;
vector<pair<int, int>> Ginput[1001]; // ind : ����, (����, �Ÿ�)
vector<pair<int, int>> Binput[1001]; // ind : ����, (����, �Ÿ�)
int go[1001];
int back[1001];

void dijkstra(int* dist, vector<pair<int, int>> input[])
{
	priority_queue<pair<int, int>> pq; // -�Ÿ�, ���� ���
	int c, d, n, nd; // current, distance, next, next_distance
	int result = 0;

	dist[X] = 0;
	pq.push({ 0, X });
	while (!pq.empty())
	{
		c = pq.top().second;
		d = -pq.top().first;
		pq.pop();
		if (dist[c] < d) continue; // ť���� ���� ���� �̹� �ִܰŸ��� �ƴϸ�
		for (int i = 0; i < input[c].size(); i++) // ���� ��� Ž��
		{
			n = input[c][i].first;
			nd = d + input[c][i].second;
			if (nd < dist[n]) // �����Ÿ��� �� ���� ���
			{
				dist[n] = nd;
				pq.push({ -nd, n });
			}
		}
	}
}

int main()
{
	int s, e, t;
	int result = 0;
	cin >> N >> M >> X;
	for (int i = 0; i < N + 1; i++)
		go[i] = back[i] = INF;
	for (int i = 0; i < M; i++)
	{
		cin >> s >> e >> t;
		Ginput[s].push_back({ e, t });
		Binput[e].push_back({ s, t });
	}
	dijkstra(go, Ginput);
	dijkstra(back, Binput);
	for (int i = 0; i < N + 1; i++)
	{
		if (go[i] != INF && back[i] != INF)
			result = max(result, go[i]+back[i]);
	}
	cout << result;
}