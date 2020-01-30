#include <iostream>
#include <string>

using namespace std;

long long cal(string str)
{
	long long result = 1;

	for (int i = 0; i < str.size(); i++)
		result *= stoi(str.substr(i,1));

	return result;
}

int main()
{
	string input = "";
	string result = "NO";
	cin >> input;

	if (input.size() != 1)
	{
		for (int i = 1; i < input.size(); i++)
		{
			if (cal(input.substr(0, i)) == cal(input.substr(i, input.size() - 1)))
			{
				result = "YES";
				break;
			}
		}
	}

	cout << result << endl;

	return 0;
}