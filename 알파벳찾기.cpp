#include <iostream>
#include <string>
#include <string.h>
#include <map>

using namespace std;

const int alp_num = 26;

int main()
{
	string input;
	int idx;

	char alp[alp_num] = { 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
						'q','r','s','t','u','v','w','x','y','z' };
	map<char, int> idxes;

	cin >> input;
	for (int i = 0; i < alp_num; i++)
		idxes[alp[i]] = -1;
	for (int i = 0; i < input.length(); i++)
	{
		if (idxes[input[i]] == -1) idxes[input[i]] = i;
	}
	for (map<char, int>::iterator it = idxes.begin(); it != idxes.end(); it++)
		cout << it->second << " ";
}