#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N;
typedef struct consult {
	int T;
	int P;
	int date;
} consult;
vector<consult> input;

int solution(int ind)
{
	int t, p;
	int sum = 0;

	if (ind >= N)
		return 0;

	t = input[ind].T;
	p = input[ind].P;

	for (int i = ind+t; i < N; i++)
		sum = max(sum, solution(i));

	return sum + p;
}

int main()
{
	int t, p;
	int result = 0;

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> t >> p;

		if (i + t > N)
			input.push_back({ 1, 0, i });
		else
			input.push_back({ t, p, i });
	}

	for (int i = 0; i < N; i++)
		result = max(result, solution(i));

	cout << result << endl;
}