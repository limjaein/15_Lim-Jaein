#include <iostream>
#include <string>
#include <stack>

using namespace std;

char brPair[3][2] = { '(',')' , '[',']' , '{','}' };
string input;

bool isOpenBracket(char br)
{
	for (int i = 0; i < 3; i++)
	{
		if (brPair[i][0] == br)
			return true;
	}
	return false;
}

bool isPairBracket(char open, char close)
{
	for (int i = 0; i < 3; i++)
	{
		if (brPair[i][0] == open && brPair[i][1] == close)
			return true;
	}
	return false;
}

bool checkBracket()
{
	stack<char> stack;

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

	if (stack.size() == 0)
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
