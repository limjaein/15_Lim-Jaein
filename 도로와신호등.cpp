#include <iostream>
#include <vector>

using namespace std;

typedef struct traffic
{
	int pos;
	int red;
	int green;
} TL;
vector<traffic> list;
int N, L;

int main()
{
	int p, r, g, idx;
	int temp = 0;
	int time = 0;

	cin >> N >> L;
	for (int i = 0; i < N; i++)
	{
		cin >> p >> r >> g;
		list.push_back({ p,r,g });
	}

	for (int i = 0; i < list.size(); i++)
	{
		time += list[i].pos - temp;
		temp = list[i].pos; // 이전값 뺄 때 사용
		idx = time % (list[i].red + list[i].green) + 1;
		if (idx > list[i].red) // 초록불
			continue; // 건너감
		else // 빨간불
			time += list[i].red - idx + 1;
	}

	cout << time + (L - list.back().pos) << endl;
}