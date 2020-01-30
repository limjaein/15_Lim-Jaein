//insertion, bubble sort
#include <iostream>
#include <algorithm>

using namespace std;

int N;
int input[1001];

void insertionSort()
{
	int tmp, idx, val;

	for (int i = 0; i < N - 1; i++)
	{
		idx = i;
		val = input[i];
		for (int j = i + 1; j < N; j++)
		{
			if (val > input[j])
			{
				val = input[j];
				idx = j;
			}
		}
		tmp = input[i];
		input[i] = val;
		input[idx] = tmp;
	}
}

void bubbleSort()
{
	int tmp = 0;

	for (int i = 0; i < N - 1; i++)
	{
		for (int j = 0; j < N - 1 - i; j++)
		{
			if (input[j] > input[j + 1])
			{
				tmp = input[j + 1];
				input[j + 1] = input[j];
				input[j] = tmp;
			}
		}
	}
}

int main()
{
	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> input[i];

	insertionSort();

	for (int i = 0; i < N; i++)
		cout << input[i] << endl;
}