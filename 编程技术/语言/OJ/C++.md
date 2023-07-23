- [头文件与重定向](#头文件与重定向)
- [读取整数](#读取整数)
	- [一行一个整数](#一行一个整数)
	- [一行两个整数](#一行两个整数)
	- [每行多个整数](#每行多个整数)
- [每行整数数目不定](#每行整数数目不定)
- [读取字符串](#读取字符串)
	- [第一行为字符串数目，第二行为用空格隔开的字符串](#第一行为字符串数目第二行为用空格隔开的字符串)
	- [每行为用空格隔开的字符串](#每行为用空格隔开的字符串)
	- [每行为用","隔开的字符串](#每行为用隔开的字符串)
- [读取IP地址](#读取ip地址)

# 头文件与重定向

```C++
#include <bits/stdc++.h>

using namespace std;

// 将cin重定向到input.txt
freopen(R"(路径)", "r", stdin);

```

# 读取整数

## 一行一个整数

```C++
int t;
cin >> t;
// 使用数据a
```

## 一行两个整数

一行两个整数，用空格分开

```C++
int a, b;
while (cin >> a >> b) {
	// 使用数据a, b
}
```

或者

```C++
int a, b;
while (scanf("%d %d", &a, &b) != EOF) {
	// 使用数据a, b
}
```

## 每行多个整数

每行多个整数，其中第一个显示本行后面有多少个整数，0为结束输入

```C++
int n;
while (cin >> n && n != 0) {
	int temp;
	while (n--) {
		cin >> temp;
		// 使用数据temp
	}
}
```

# 每行整数数目不定

每行整数数目不定，空格隔开

```C++
int a;
while (cin >> a) {
	// 使用数据a
	if (cin.get() == '\n') {
		// 结束输入
	}
}
```

或者

```C++
char a, c;
while (scanf("%d%c", &a, &c) != EOF) {
	// 使用数据a
	if (c == '\n') {
		// 结束输入
	}
}
```

或者

```C++
string str;
int sum = 0;
while (getline(cin, str)) {
	for (int i = 0; i < str.size(), ++i) {
		if (str[i] == ' ') {
			// 使用数据sum
			sum = 0;
		} else
			sum = sum * 10 + str[i] - '0';
	}
}
```

# 读取字符串

## 第一行为字符串数目，第二行为用空格隔开的字符串

```C++
int n;
cin >> n;
vector<string> data;
string temp;
while (n--) {
	cin >> temp;
	data.push_back(temp);
}
// 使用数据data
```

## 每行为用空格隔开的字符串

```C++
vector<string> data;
string temp;
while (cin >> temp) {
	data.push_back(temp);
	if (cin.get() == '\n') {
		// 使用一行数据
	}
}
```

或者

```C++
vector<string> data;
string temp;
while (cin >> temp) {
	data.push_back(temp);
	while (cin.get() != '\n') {
		cin >> temp;
		data.push_back(temp);
	}
	// 使用一行数据
}
```

或者

```C++
string line;
string temp;
vector<string> data;
while (getline(cin, line)) {
	istringstream ss(line);
	while (ss >> temp)
		data.push_back(temp);
	// 使用一行数据
}
```

## 每行为用","隔开的字符串

```C++
string line;
string temp;
vector<string> data;
while (getline(cin, line)) {
	stringstream ss(line);
	// 或者 ss << line;
	while (getline(ss, temp, ','))
		data.push_back(temp);
	// 使用一行数据
}
```

# 读取IP地址

```C++
char c;
string input;
stringstream ss;
vector<int> ip;
while (cin >> input) {
	cin >> input;
	ss << input;
	ss >> ip[0] >> c
	   >> ip[1] >> c
	   >> ip[2] >> c
	   >> ip[3];
	ss.clear();
}
```

或者

```C++
string input;
vector<int> ip;
string temp;

cin >> input;
for (long i = 0; i < input.length(); i++) {
	if (input[i] != '.') {
		temp += input[i];
	}else{
		ip.push_back(stoi(temp));
		temp.clear();
	}

	if (i == (input.size()-1)){
		ip.push_back(stoi(temp));
		temp.clear();
	}
}
```

或者

```C++
vector<int> ip;
	while(
		scanf(" %d.%d.%d.%d ",
			&ip[0], &ip[1],
			&ip[2], &ip[3])
			 != EOF) {
		// 使用数据ip
}
```
