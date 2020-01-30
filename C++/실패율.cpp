#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

bool compare(pair<double, int> a, pair<double, int>b)
{
	if (a.first == b.first)
		return (a.second < b.second);
	return (a.first > b.first);
}
vector<int> solution(int N, vector<int> stages) {
	vector<int> sum;
	int fail[501] = { 0 };
	int total = 0;
	vector<int> answer;
	vector<pair<double, int>> list;

	for (int i = 0; i < stages.size(); i++)
		fail[stages[i] - 1]++;

	sum.push_back(0); // 시작도 안한경우
	for (int i = 0; i < N + 1; i++)
	{
		total += fail[i];
		sum.push_back(total);
	}

	for (int i = 0; i < N; i++)
	{
		if (total == sum[i])
			list.push_back(make_pair(0, i + 1));
		else
			list.push_back(make_pair(((double)fail[i] / (double)(total - sum[i])), i + 1));
	}

	sort(list.begin(), list.end(), compare);

	for (int i = 0; i < N; i++)
		answer.push_back(list[i].second);
	return answer;
}