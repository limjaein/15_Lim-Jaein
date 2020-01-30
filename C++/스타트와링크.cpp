#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int result = 123456789;
int N;
int S[21][21];

void diff(vector<int> a, vector<int> b)
{
	int a_sum = 0;
	int b_sum = 0;

	for (int i = 0; i < a.size() - 1; i++)
	{
		for (int j = i + 1; j < a.size(); j++)
		{
			a_sum += (S[a[i]][a[j]] + S[a[j]][a[i]]);
			b_sum += (S[b[i]][b[j]] + S[b[j]][b[i]]);
		}
	}
	result = min(result, abs(a_sum - b_sum));
}
void solution()
{
	vector<int> n, ind;
	vector<int> a, b;

	for (int i = 0; i < N; i++)
		n.push_back(i);
	for (int i = 0; i < N / 2; i++)
	{
		ind.push_back(0);
		ind.push_back(1);
	}
	sort(ind.begin(), ind.end());

	do{
		a.clear();
		b.clear();
		for (int i = 0; i < N; i++)
		{
			if (ind[i] == 0)
				a.push_back(i);
			else
				b.push_back(i);
		}
		diff(a, b);
	}while (next_permutation(ind.begin(), ind.end()));
}

int main()
{
	cin >> N;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
			cin >> S[i][j];
	}
	solution();
	cout << result;
}