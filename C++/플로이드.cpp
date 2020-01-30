#include <iostream>
#include <algorithm>

using namespace std;

const int INF = 987654321;
int arr[101][101];

int main()
{
	int a, b, c, n, m;
	cin >> n >> m;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
			arr[i][j] = INF;
	}
	for (int i = 0; i < m; i++)
	{
		cin >> a >> b >> c;
		a--; b--;
		arr[a][b] = min(arr[a][b], c);
	}
	for (int k = 0; k < n; k++)
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (i == k || j == k || i == j) continue;
				arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j]);
			}
		}
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (arr[i][j] == INF)
				cout << 0;
			else
				cout << arr[i][j];
			cout << " ";
		}
		cout << endl;
	}
}