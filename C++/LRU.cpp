//#include <iostream>
//#include <sstream> // ���ڿ� ��ū
//#include <list>
//#include <vector>
//
//using namespace std;
//
//list<char> stack;
//
//list<char>::iterator find(char c)
//{
//	list<char>::iterator pos;
//	for (pos = stack.begin(); pos != stack.end(); pos++)
//		if (*pos == c) break;
//	return pos;
//}
//
//void LRU(int max_size, string s)
//{
//	list<char>::iterator pos;
//	char c;
//	stack.clear();
//	for (int i = 0; i < s.length(); i++)
//	{
//		c = s[i];
//		if (c == '!')
//		{
//			for (pos = stack.begin(); pos != stack.end(); pos++)
//				cout << *pos;
//			cout << endl;
//			continue;
//		}
//		pos = find(c);
//		if (pos != stack.end()) // ĳ��
//			stack.erase(pos);
//		else
//		{
//			if (stack.size() == max_size)
//				stack.pop_front();
//		}
//		stack.push_back(c);
//		}	
//	return;
//}
//
//int main()
//{
//	string input;
//	int i = 1;
//	int max_size;
//	while (true)
//	{
//		cin >> max_size >> input;
//		if (max_size == 0)
//			return 0;
//		cout << "Simulation " << i << endl;
//		LRU(max_size,input);
//		i++;
//	};
//}
#include <iostream>
#include <unordered_map>
#include <vector>
#include <list>
#include <string>

using namespace std;

int N;
list<char> linkedlist;
unordered_map<char, list<char>::iterator> hash_map;

void LRUCache(vector<char> alp)
{
	char tmp;
	for (int i = 0; i < alp.size(); i++)
	{
		if (alp[i] == '!')
		{
			for (list<char>::reverse_iterator it = linkedlist.rbegin(); it != linkedlist.rend(); it++)
				cout << *it;
			cout << endl;
			continue;
		}
		if (hash_map.find(alp[i]) == hash_map.end()) // ĳ�� hit ����
		{
			if (linkedlist.size() == N) // ���� ��
			{
				tmp = linkedlist.back();
				linkedlist.pop_back();
				hash_map.erase(tmp); // �ؽÿ��� �����
			}
		}
		else
			linkedlist.erase(hash_map[alp[i]]);
		linkedlist.push_front(alp[i]);
		hash_map[alp[i]] = linkedlist.begin();
	}
}

int main()
{
	int i = 1;
	string input;
	vector<char> alp;
	while (true)
	{
		cin >> N;
		if (N == 0) return 0;
		cout << "Simulation " << i << endl;
		cin >> input;
		for (int s = 0; s < input.length(); s++)
			alp.push_back(input[s]);
		LRUCache(alp);
		i++;
		alp.clear();
		linkedlist.clear();
		hash_map.clear();
	}
}