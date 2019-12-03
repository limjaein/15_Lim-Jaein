//�ڵ�ƴ�, �����̻�
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
		if (pointer.find(num) == pointer.end()) // ĳ�� ����
		{
			if (output.size() == max_size) // ���� á����
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
				// �����Ǵ� key�� �κп� �ֱ�
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
		else // ĳ�� ����
			counter[num]++; // ����� �� ++

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