#include <iostream>
#include <string>

using namespace std;

const int input_size = 2;

bool sol(string str1, string str2)
{
	int str_size = str1.size();
	int result = 0;
	string sub1[2] = { "" };
	string sub2[2] = { "" };

	if (str1 == str2) return true;

	if (str_size % 2) // 홀수면 종료
		return false;

	// 분할
	sub1[0].assign(str1, 0, 0 + str_size/2);
	sub2[0].assign(str2, 0, 0 + str_size/2);
	sub1[1].assign(str1, str_size/2, str_size/2);
	sub2[1].assign(str2, str_size/2, str_size/2);

	// 재귀
	if (sol(sub1[0], sub2[0]) && sol(sub1[1], sub2[1]) || sol(sub1[0], sub2[1]) && sol(sub1[1], sub2[0]))
		return true;
	else
		return false;
}

int main()
{
	string input[input_size] = { "" };

	for (int i = 0; i < input_size; i++)
		cin >> input[i];

	if (sol(input[0], input[1]))
		cout << "YES" << endl;
	else
		cout << "NO" << endl;

	return 0;
}
