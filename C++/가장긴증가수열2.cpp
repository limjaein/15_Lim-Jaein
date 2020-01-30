#include <iostream>
#include <vector>

using namespace std;

vector<int> vec;

int lower_bound(int target)
{
	int start, mid, end;

	start = 0;
	end = vec.size() - 1;
	
	while (true)
	{
		mid = (end + start) / 2;

		if (start == end)
			return mid;

		if (vec[mid] < target)
			start = mid + 1;
		else if (vec[mid] >= target)
			end = mid;
	}
}

int main()
{
	int N;
	int input = 0;

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> input;

		if (i == 0 || vec.back() < input)
			vec.push_back(input);
		else // 더 작은 수 일때
			vec[lower_bound(input)] = input;
	}

	cout << vec.size() << endl;

}
