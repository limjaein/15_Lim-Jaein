////heap, merge sort
//#include <iostream>
//#include <algorithm>
//
//using namespace std;
//
//int N;
//int sorted[1000001] = {0};
//
//void heapSort()
//{
//
//}
//
//void merge(int arr[], int mid, int m, int n)
//{
//	int i = m;
//	int j = mid + 1;
//	int k = m;
//
//	while (i <= mid && j <= n)
//	{
//		if (arr[i] < arr[j])
//		{
//			sorted[k] = arr[i];
//			i++;
//		}
//		else
//		{
//			sorted[k] = arr[j];
//			j++;
//		}
//		k++;
//	}
//	if (i > mid)
//	{
//		for (int t = j; t <= n; t++)
//		{
//			sorted[k] = arr[t];
//			k++;
//		}
//	}
//	else
//	{
//		for (int t = i; t <= mid; t++)
//		{
//			sorted[k] = arr[t];
//			k++;
//		}
//	}
//	for (int t = m; t <= n; t++)
//		arr[t] = sorted[t];
//}
//
//void mergeSort(int arr[], int m, int n)
//{
//	int mid;
//	if (m < n)
//	{
//		mid = (m + n) / 2;
//		//분할
//		mergeSort(arr, m, mid);
//		mergeSort(arr, mid + 1, n);
//		// 병합
//		merge(arr, mid, m, n);
//	}
//}
//
//int main()
//{
//	int input[1000001];
//
//	cin >> N;
//	for (int i = 0; i < N; i++)
//		cin >> input[i];
//
//	mergeSort(input, 0, N - 1);
//
//	for (int i = 0; i < N; i++)
//		cout << input[i] << '\n';
//}