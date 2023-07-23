- [读取整数](#读取整数)
	- [一行一个整数](#一行一个整数)
	- [一行两个整数](#一行两个整数)
	- [每行多个整数](#每行多个整数)
- [每行整数数目不定](#每行整数数目不定)
- [读取字符串](#读取字符串)
	- [第一行为字符串数目，第二行为用空格隔开的字符串](#第一行为字符串数目第二行为用空格隔开的字符串)
	- [每行为用空格隔开的字符串](#每行为用空格隔开的字符串)
	- [每行为用","隔开的字符串](#每行为用隔开的字符串)

# 读取整数

## 一行一个整数

```Python
t = int(input())
```

## 一行两个整数

一行两个整数，用空格分开

```Python
while True:
	try:
		x = list(map(int, input().split()))
		# 使用数据x
		# print(x[0]+x[1])
	except:
		break
```

## 每行多个整数

每行多个整数，其中第一个显示本行后面有多少个整数，0为结束输入

```Python
import sys
for line in sys.stdin:
	x = list(map(int, line.split()))
	if x[0] == 0:
		break
	# 使用数据x
```

或者

```Python
x = input.split()
n = int(x[0])
for j in range(1, n + 1):
	# 使用数据int(x[j])
```

# 每行整数数目不定

每行整数数目不定，空格隔开

```Python
try:
	while True:
		x = list(map(int, input.split()))
		# 使用数据x
except:
	pass
```

# 读取字符串

## 第一行为字符串数目，第二行为用空格隔开的字符串

```Python
n = input();
x = input().split()
# 使用数据x
```

## 每行为用空格隔开的字符串

```Python
while True:
	try:
		x = input().split()
		# 使用数据x
	except:
		break
```

## 每行为用","隔开的字符串

```Python
while True:
	try:
		x = input.split(',')
		# 使用数据x
	except:
		break
```
