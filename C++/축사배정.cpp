#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

int N, M;
bool isChecked[201]; // 소 체크
int match[201]; // 축사 방문
vector<vector<int>> input;

bool dfs(int cow)
{
	if (isChecked[cow]) return false;

	isChecked[cow] = true;

	for (int i = 0; i < input[cow].size(); i++) // 인접 노드 방문
	{
		if (match[input[cow][i]]== -1 || dfs(match[input[cow][i]]))
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
		memset(isChecked, false, sizeof(bool)*N); // 체크 갱신
		if (dfs(i)) 
			count++; // 매칭 완료
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
			input[i].push_back(temp - 1); // 축사 인덱스 push
		}
	}

	cout << solution() << endl;
	return 0;
}