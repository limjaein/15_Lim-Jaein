#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> compare;

int main()
{
	int n, m = 0;
	vector<int> input;

	cin >> n;
	compare.resize(n);

	for (int i = 0; i < n; i++)
		cin >> compare[i];

	sort(compare.begin(), compare.end());

	cin >> m;
	input.resize(m);

	for (int i = 0; i < m; i++)
		cin >> input[i];

	for (int i = 0; i < m; i++)
	{
		if (binary_search(compare.begin(), compare.end(), input[i]))
			cout << "1 ";
		else
			cout << "0 ";
	}
}