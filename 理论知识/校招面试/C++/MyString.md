<font size = 5>

# MyString类实现

strcpy会拷贝'\0'

strlen不会计算'\0'长度

封装前↓：

```C++
class MyString {
private:
  int size;
  char* data;

public:
  // 构造函数
  MyString() {
    size = 0;
    data = new char[1];
    data[0] = '\0';
  }

  MyString(const char* s) {
    if (s == nullptr) {
      size = 0;
      data = new char[1];
      data[0] = '\0';
    } else {
      size = strlen(s);
      data = new char[size + 1];
      strcpy(data, s);
    }
  }

  MyString(const MyString& s) {
    size = s.size;
    data = new char[size + 1];
    strcpy(data, s.data);
  }

  MyString(const string& s) {
    if (s.empty()) {
      size = 0;
      data = new char[1];
      data[0] = '\0';
    } else {
      size = s.size();
      data = new char[size + 1];
      strcpy(data, s.data());
    }
  }

  // 析构函数
  ~MyString() {
    delete[] data;
  }

  // 赋值函数
  MyString& operator=(const MyString& s) {
    if (this == &s)
      return *this;
    size = strlen(s.data);
    delete[] data;
    data = new char[size + 1];
    strcpy(data, s.data);
    return *this;
  }

  // +符号重载
  MyString& operator+(const MyString& s) {
    int resSize = size + s.size;
    char* resData = new char[resSize + 1];
    strcpy(resData, data);
    strcpy(resData + size, s.data);
    delete[] data;
    size = resSize;
    data = resData;
    return *this;
  }
};
```

封装后↓：

```C++
class MyString {
private:
  int size;
  char* data;

public:
  // 封装后的函数
  void buildEmpty() {
    size = 0;
    data = new char[1];
    data[0] = '\0';
  }
  void buildCopy(const char* s) {
    size = strlen(s);
    data = new char[size + 1];
    strcpy(data, s);
  }

  // 构造函数
  MyString() {
    buildEmpty();
  }

  MyString(const MyString& s) {
    buildCopy(s.data);
  }

  MyString(const char* s) {
    if (s == nullptr)
      buildEmpty();
    else
      buildCopy(s);
  }

  MyString(const string& s) {
    if (s.empty())
      buildEmpty();
    else
      buildCopy(s.data());
  }

  // 析构函数
  ~MyString() {
    delete[] data;
  }

  // 赋值函数
  MyString& operator=(const MyString& s) {
    if (this == &s)
      return *this;
    delete[] data;
    buildCopy(s.data);
    return *this;
  }

  // +符号重载
  MyString& operator+(const MyString& s) {
    int resSize = size + s.size;
    char* resData = new char[resSize + 1];
    strcpy(resData, data);
    strcpy(resData + size, s.data);
    delete[] data;
    size = resSize;
    data = resData;
    return *this;
  }
};
```
