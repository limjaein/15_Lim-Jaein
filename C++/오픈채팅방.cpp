#include <string>
#include <vector>
#include <map>
#include <sstream> // string stream

using namespace std;

const string ENTER = "님이 들어왔습니다.";
const string LEAVE = "님이 나갔습니다.";


vector<string> solution(vector<string> record) {
	vector<string> answer;
	vector<pair<string, string>> log;
	map<string, string> list; // id, name
	map<string, string>::iterator iter;
	string first, name, id;
	stringstream ss;

	for (int i = 0; i < record.size(); i++)
	{
		ss.str(record[i]);
		ss >> first >> id >> name;
		if (first == "Enter")
		{
			if (list.count(id) == 0)
				list.insert(make_pair(id, name));
			else
			{
				iter = list.find(id);
				iter->second = name;
			}
			log.push_back(make_pair(id, ENTER));
		}
		else if (first == "Change")
		{
			iter = list.find(id);
			iter->second = name;
		}
		else
			log.push_back(make_pair(id, LEAVE));
		ss.clear();
	}

	for (int i = 0; i < log.size(); i++)
		answer.push_back(list[log[i].first] + log[i].second);

	return answer;
}