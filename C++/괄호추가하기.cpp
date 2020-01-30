// ¹Ì¿Ï
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

int N;
vector<int> v;
int result = 0;
string input;

int cal(int a, int b, char c)
{
	if (c == '+')
		return a + b;
	else if (c == '*')
		return a * b;
	else if (c == '-')
		return a - b;
}
void solution(int idx, int sum)
{
	if (idx >= N)
	{
		result = max(result, sum);
		return;
	}

	solution(idx + 2, cal(sum, stoi(input.at(idx + 2) + ""), input.at(idx + 1)));
	solution(idx + 4, cal(sum, stoi(input.at(idx) + ""), v[(idx + 2) / 2]));
}
int main()
{
	string str = "+";
	cin >> N;
	cin >> input;
	input = str.append(input);
	int i = 1;

	while (i < input.length() - 2)
	{
		v.push_back(cal(stoi(input.at(i) + ""), stoi(input.at(i + 2) + ""), input.at(i + 1)));
		i += 2;
	}
	solution(-2, 0);
	cout << result << endl;
}