#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, C;
int arr[31] = { 0 };
int cnt = 0;

void dfs(int s, int e, int sum, vector<int>& v)
{
	if (sum > C) return;
	if (s > e) // 전체를 다봤으면
	{
		v.push_back(sum);
		return;
	}
	dfs(s + 1, e, sum + arr[s], v);
	dfs(s + 1, e, sum, v);

}

int main()
{
	vector<int> v1;
	vector<int> v2;
	cin >> N >> C;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	dfs(0, N / 2, 0, v1);
	sort(v1.begin(), v1.end());
	dfs(N / 2+1, N-1, 0, v2);
	sort(v2.begin(), v2.end());
	
	for (int i = 0; i < v1.size(); i++)
	{
		for (int j = 0; j < v2.size(); j++)
		{
			if (v1[i] + v2[j] <= C)
				cnt++;
		}
	}
	cout << cnt << endl;
}