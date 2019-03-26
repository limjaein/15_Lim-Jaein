#include <iostream>
#include <string.h>
#include <vector>

using namespace std;

int n, m;
vector<vector<int>> staff;
int task[1000];
bool isTaken[1000];

int dfs(int num)
{
	if (isTaken[num]) return false;

	isTaken[num] = true;

	for (int i = 0; i < staff[num].size(); i++)
	{
		if (task[staff[num][i]] == -1 || dfs(task[staff[num][i]]))
		{
			task[staff[num][i]] = num;
			return true;
		}
	}
	return false;
}

int solution()
{
	int cnt = 0;
	for (int i = 0; i < n; i++)
	{
		memset(isTaken, false, sizeof(bool)*n);
		if (dfs(i))
			cnt++;
	}
	return cnt;
}

int main()
{
	int num;
	int temp;
	cin >> n >> m;

	staff.resize(n);
	memset(task, -1, sizeof(int)*m);
	for (int i = 0; i < n; i++)
	{
		cin >> num;
		while (num--)
		{
			cin >> temp;
			staff[i].push_back(temp - 1);
		}
	}

	cout << solution() << endl;
}