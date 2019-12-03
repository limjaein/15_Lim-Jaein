#include <iostream>
#include <string.h>

using namespace std;

namespace {
	enum {
		BLUE = 0, WHITE = 1
	};
}
const int maxSize = 129;
int N;
int paper[maxSize][maxSize];

typedef struct Pos {
	int x, y;
}Pos;

class Cnt {
private:
	int b, w;

public:
	Cnt() {
		b = w = 0;
	}
	Cnt(int bCnt, int wCnt) {
		b = bCnt;
		w = wCnt;
	}
	int getBlueCnt() {
		return b;
	}
	int getWhiteCnt() {
		return w;
	}
	void addCnt(Cnt cnt) {
		b += cnt.b;
		w += cnt.w;
	}
};

bool isAll1Color(int size, Pos p, bool color)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			if (paper[p.y + i][p.x + j] != color)
				return false;
		}
	}
	return true;
}

Cnt countPaper(int size, Pos p)
{
	Cnt cnt;
	int half;

	if (isAll1Color(size, p, 1))
		return { 1, 0 };
	else if (isAll1Color(size, p, 0))
		return { 0, 1 };

	half = size / 2;
	cnt.addCnt(countPaper(half, p));
	cnt.addCnt(countPaper(half, {p.x + half, p.y}));
	cnt.addCnt(countPaper(half, {p.x, p.y + half}));
	cnt.addCnt(countPaper(half, { p.x + half,p.y + half }));
	return cnt;
}

int main()
{
	Cnt result;
	cin >> N;
	memset(paper, 0, sizeof(paper));
	for (int i = 0; i < N; i++) 
	{
		for (int j = 0; j < N; j++)
			cin >> paper[i][j];
	}
	result = countPaper(N, { 0, 0 });
	cout << result.getWhiteCnt() << endl;
	cout << result.getBlueCnt() << endl;
}