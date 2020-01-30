#include <iostream>
#include <string.h>
#include <vector>
#include <algorithm>

using namespace std;

int num;
int input[11];
int op[10];
int MAX = -1000000000, MIN = 1000000000;
vector<int> vec;

int operation(int a, int b, int op)
{
	switch (op)
	{
	case 0:
		return a + b;
	case 1:
		return a - b;
	case 2:
		return a * b;
	case 3:
		return a / b;
	}
}

void solution()
{
	int result;

	do
	{
		int i = 1;
		result = input[0];
		for (auto it = vec.begin(); it != vec.end(); ++it)
		{
			result = operation(result, input[i], *it);
			i++;
		}
		MAX = max(MAX, result);
		MIN = min(MIN, result);

	} while (next_permutation(vec.begin(), vec.end()));
}

int main()
{
	cin >> num;
	memset(input, -1, sizeof(int)*num);
	memset(op, -1, sizeof(int)*(num - 1));

	for (int i = 0; i < num; i++)
		cin >> input[i];

	for (int i = 0; i < 4; i++)
	{
		cin >> op[i];
		for (int j = 0; j < op[i]; j++)
			vec.push_back(i);
	}

	solution();

	cout << MAX << endl;
	cout << MIN << endl;

	return 0;
}
