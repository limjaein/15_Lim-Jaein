#include <iostream>
#include <string>
#include <stack>

using namespace std;

// 괄호 쌍
char brPair[3][2] = { '(',')' , '[',']' , '{','}' };
string input;

// 여는 괄호인지 체크하는 함수
bool isOpenBracket(char br)
{
	for (int i = 0; i < 3; i++)
	{
		if (brPair[i][0] == br)
			return true;
	}
	return false;
}

//괄호 두개가 맞는 쌍인지 체크하는 함수
bool isPairBracket(char open, char close)
{
	for (int i = 0; i < 3; i++)
	{
		if (brPair[i][0] == open && brPair[i][1] == close)
			return true;
	}
	return false;
}

//전체 괄호 체크 함수
bool checkBracket()
{
	stack<char> stack; // 여는 괄호만 넣는 스택

	for (int i = 0; i < input.length(); i++)
	{
		if (isOpenBracket(input[i]))
			stack.push(input[i]);
		else
		{
			if (stack.size())
			{
				if (isPairBracket(stack.top(), input[i]))
				{
					// 올바른 쌍일 때 스택에서 제거
					stack.pop(); 
					continue;
				}
				else
					return false;
			}
			else
				return false;
		}
	}

	if (stack.size() == 0) // 스택에 남은 괄호가 없으면
		return true;
	else
		return false;
}

int main()
{
	int C;

	cin >> C;

	while (C--)
	{
		cin >> input;
		checkBracket() ? cout << "YES" << endl : cout << "NO" << endl;
	}
	return 0;
}
