#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
	int count;
	int out, in;
	int result = 0;

	cin >> count >> count;

	for (int i = 1; i < 3; i++)
	{
		cin >> out >> in;

		count = count - out + in;
		
		result = max(result, count);
	}

	cin >> count >> count;

	cout << result << endl;

	return 0;
}
