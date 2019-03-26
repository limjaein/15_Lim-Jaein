#include <iostream>
#include <vector>

using namespace std;

int N;
int country[20][20];

int solution()
{
	int sum = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = i + 1; j < N; j++)
		{
			for (int m = 0; m < N; m++)
			{
				if (m != i && m != j)
				{
					if (country[i][m] + country[m][j] == country[i][j])
					{
						sum += country[i][j];
						break;
					}
					else if (country[i][m] + country[m][j] < country[i][j])
						return -1;
				}
			}
		}
	}
	return sum;
}

int main()
{
	int sum = 0;
	int min = 0;
	cin >> N;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cin >> country[i][j];
			if (i < j)
				sum += country[i][j];
		}
	}

	min = solution();

	if (min == -1)
		cout << min << endl;
	else
		cout << sum - min << endl;

	return 0;
}