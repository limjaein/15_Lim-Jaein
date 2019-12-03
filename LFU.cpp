//코드아님, 조금이상
#include <iostream>
#include <unordered_map>
#include <map>
#include <queue>
#include <string>
#include <list>

using namespace std;

int max_size;

void LFUCache()
{
	string input;
	list<int> output;
	list<int>::iterator out;
	unordered_map<int, list<int>::iterator> pointer;
	map<int, int> counter;
	int num, min_value;
	cin >> input;
	for (int i = 0; i < input.length(); i++)
	{
		num = input[i] - '0';
		if (pointer.find(num) == pointer.end()) // 캐싱 실패
		{
			if (output.size() == max_size) // 가득 찼으면
			{
				min_value = 1234567;
				for (list<int>::iterator it = output.begin(); it != output.end(); it++)
				{
					if (min_value >= counter[*it])
					{
						min_value = counter[*it];
						out = it;
					}
				}
				// 삭제되는 key값 부분에 넣기
				counter.insert({ num, 1 });
				counter.erase(*out);
				pointer[num] = pointer[*out];
				pointer.erase(*out);
				output.insert(out, num);
				output.erase(out);
			}
			else
			{
				counter.insert({ num, 1 });
				pointer[num] = output.begin();
				output.push_front(num);
			}
		}
		else // 캐싱 성공
			counter[num]++; // 계수기 값 ++

		for (list<int>::reverse_iterator it = output.rbegin(); it != output.rend(); it++)
			cout << *it << " ";
		cout << endl;
	}
}

int main()
{
	cin >> max_size;

	LFUCache();
}