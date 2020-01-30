#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

int n, m; // 학생의 수, 친구 쌍의 수
vector<vector<int>> adj_list(10);

int nextVisit(bool list[])
{
	for (int i = 0; i < n; i++)
	{
		if (!list[i])
			return i;
	}
	return -1;
}

int search(bool isVisited[])
{
	int pair;
	int result = 0;
	int here = -1;

	here = nextVisit(isVisited);

	if (here == -1)
		return 1;

	isVisited[here] = true;

	for (int p = 0; p < adj_list[here].size(); p++)
	{
		pair = adj_list[here][p];

		if (isVisited[pair])
			continue;

		isVisited[pair] = true;
		result += search(isVisited);
		isVisited[pair] = false;
	}
	if (here != 0)
		isVisited[here] = false;

	return result;
}

int main()
{
	int testcase;
	int a, b = 0;
	bool isVisited[10];
	int result;

	cin >> testcase;

	while (testcase--)
	{
		cin >> n >> m;
		for (int i = 0; i < m; i++)
		{
			cin >> a >> b;
			
			if (a < b)
				adj_list[a].push_back(b);
			else
				adj_list[b].push_back(a);
		}

		memset(isVisited, 0, sizeof(bool) * 10);
		result = search(isVisited);
		cout << result << endl;

		for (int j = 0; j < adj_list.size(); j++)
			adj_list[j].clear();
	}

	return 0;
}
