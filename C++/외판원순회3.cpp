#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <vector>
#include <string.h>
#include <math.h>

using namespace std;

const double INF = 987654321.0;
int N;
double map[16][16];
double dp[16][1 << 16];

double solution(int n, int list)
{
	double result = INF;
	if (dp[n][list] != 0)
		return dp[n][list];

	if (list == (1 << N) - 1) // ¸¶Áö¸·
	{
		if (map[n][0] == 0) return INF;
		else return map[n][0];
	}

	for (int i = 1; i < N; i++)
	{
		if (map[n][i] != 0 && (list & (1 << i)) == 0)
			result = min(result, solution(i, list | (1 << i)) + map[n][i]);
	}
	return dp[n][list] = result;
}
int main()
{
	double dist;
	int y, x;
	vector<pair<int, int>> cities;
	cin >> N;
	memset(map, 0, sizeof(map));
	memset(dp, 0, sizeof(dp));

	for (int i = 0; i < N; i++)
	{
		cin >> y >> x;
		cities.push_back({ y, x });
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = i + 1; j < N; j++)
		{
			dist = sqrt(pow(cities[i].first - cities[j].first, 2) + pow(cities[i].second - cities[j].second, 2));
			map[i][j] = map[j][i] = dist;
		}
	}
	printf("%lf", solution(0, 1));
}