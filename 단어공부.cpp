#include <iostream>
#include <vector>
#include <cctype>
#include <unordered_map>
#include <queue>

using namespace std;

typedef struct alp_pair {
	int cnt;
	char c;
};

struct cmp {
	bool operator()(alp_pair a,alp_pair b) {
		return a.cnt < b.cnt;
	}
};

unordered_map<char, int> alp_list;
string input;

void count(char c)
{
	char big = toupper(c);
	if (alp_list.count(big) != NULL)
		alp_list[big]++;
	else
		alp_list[big] = 1;
}

void solution()
{
	alp_pair a, b;
	priority_queue<alp_pair, vector<alp_pair>, cmp> pq;
	for (unordered_map<char, int>::iterator it = alp_list.begin(); it != alp_list.end(); it++)
		pq.push({ it->second, it->first });
	a = pq.top();
	pq.pop();
	if (pq.empty())
	{
		cout << a.c << endl;
		return;
	}
	b = pq.top();
	if (a.cnt == b.cnt)
		cout << "?" << endl;
	else
		cout << a.c << endl;
}

int main()
{
	cin >> input;
	for (int i = 0; i < input.length(); i++)
		count(input[i]);
	solution();
}