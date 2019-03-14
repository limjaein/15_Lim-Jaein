#include <iostream>
#include <vector>
#include <stack>

using namespace std;

vector<int> vec;
vector<pair<int,int>> trace; // 추적 배열

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
	int N, input;
	int index;
	int pre_index = -1;
	int length = 0;
	stack<int> st;

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> input;

		if (i == 0 || vec.back() < input)
		{
			vec.push_back(input);
			trace.push_back(make_pair(pre_index + 1, input));
			pre_index = trace.back().first;
		}
		else // 더 작은 수 일때
		{
			index = lower_bound(input);
			vec[index] = input;
			trace.push_back(make_pair(index, input));
		}
	}

	length = vec.size();
	cout << length << endl;

	for (int i = trace.size() - 1; i >= 0; i--)
	{
		if (trace[i].first == length - 1)
		{
			st.push(trace[i].second);
			length--;
		}
	}

	while (st.size())
	{
		cout << st.top() << "\t";
		st.pop();
	}
}
