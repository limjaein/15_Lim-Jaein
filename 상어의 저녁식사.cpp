#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

int N, M;
bool isChecked[201];
int match[201];
vector<vector<int>> input;

bool dfs(int cow)
{
	if (isChecked[cow]) return false;

	isChecked[cow] = true;

	for (int i = 0; i < input[cow].size(); i++)
	{
		if (match[input[cow][i]] == -1 || dfs(match[input[cow][i]]))
		{
			match[input[cow][i]] = cow;
			return true;
		}
	}

	return false;
}

int solution()
{
	int count = 0;

	for (int i = 0; i < N; i++)
	{
		memset(isChecked, false, sizeof(bool)*N);
		if (dfs(i))
			count++;
	}

	return count;
}

int main()
{
	int size;
	int temp;

	cin >> N >> M;

	memset(match, -1, sizeof(int)*M);
	input.resize(N);

	for (int i = 0; i < N; i++)
	{
		cin >> size;
		for (int j = 0; j < size; j++)
		{
			cin >> temp;
			input[i].push_back(temp - 1);
		}
	}

	cout << N - solution() << endl;
	return 0;
}