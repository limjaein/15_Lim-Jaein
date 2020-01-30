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
		temp = list[i].pos; // ������ �� �� ���
		idx = time % (list[i].red + list[i].green) + 1;
		if (idx > list[i].red) // �ʷϺ�
			continue; // �ǳʰ�
		else // ������
			time += list[i].red - idx + 1;
	}

	cout << time + (L - list.back().pos) << endl;
}