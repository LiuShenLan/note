- [1. SQL必知必会数据库创建](#1-sql必知必会数据库创建)
- [2. 检索数据SELECT](#2-检索数据select)
	- [2.1 检索单个列](#21-检索单个列)
	- [2.2 检索多个列](#22-检索多个列)
	- [2.3 检索所有列](#23-检索所有列)
	- [2.4 检索不同的值](#24-检索不同的值)
	- [2.5 限制结果(LIMIT)](#25-限制结果limit)
	- [2.6 使用注释](#26-使用注释)
- [3. 排序检索数据](#3-排序检索数据)
	- [3.1 排序数据(ORDER BY)](#31-排序数据order-by)
	- [3.2 按多个列排序](#32-按多个列排序)
	- [3.3 按列位置排序](#33-按列位置排序)
	- [3.4 指定排序方向(DESC)](#34-指定排序方向desc)
- [4. 过滤数据(WHERE)](#4-过滤数据where)
	- [4.1 使用WHERE子句](#41-使用where子句)
	- [4.2 WHERE子句操作符](#42-where子句操作符)
- [5. 高级数据过滤](#5-高级数据过滤)
	- [5.1 组合WHERE子句(AND、OR)](#51-组合where子句andor)
	- [5.2 IN操作符](#52-in操作符)
	- [5.3 NOT操作符](#53-not操作符)
- [6. 用通配符进行过滤](#6-用通配符进行过滤)
	- [6.1 LIKE操作符](#61-like操作符)
		- [6.2 %通配符](#62-通配符)
		- [6.3 \_通配符](#63-_通配符)
		- [6.4 \[\]通配符](#64-通配符)
	- [6.5 使用通配符的技巧](#65-使用通配符的技巧)
- [7. 创建计算字段](#7-创建计算字段)
	- [7.1 计算字段](#71-计算字段)
	- [7.2 拼接字段](#72-拼接字段)
		- [7.3 使用别名(AS)](#73-使用别名as)
	- [7.4 执行算术计算](#74-执行算术计算)
- [8. 使用数据处理函数](#8-使用数据处理函数)
	- [8.1 函数](#81-函数)
	- [8.2 文本处理函数](#82-文本处理函数)
	- [8.3 数值处理函数](#83-数值处理函数)
- [9. 汇总数据](#9-汇总数据)
	- [9.1 聚集函数](#91-聚集函数)
		- [9.1.1 AVG()](#911-avg)
		- [9.1.2 COUNT()](#912-count)
		- [9.1.3 MAX()/MIN()](#913-maxmin)
		- [9.1.4 SUM()](#914-sum)
	- [9.2 聚集不同值](#92-聚集不同值)
	- [9.3 组合聚集函数](#93-组合聚集函数)
- [10. 分组数据](#10-分组数据)
	- [10.1 创建分组(GROUP BY)](#101-创建分组group-by)
	- [10.2 过滤分组(HAVING)](#102-过滤分组having)
	- [10.3 分组和排序](#103-分组和排序)
	- [10.4 SELECT子句顺序](#104-select子句顺序)
- [11. 使用子查询](#11-使用子查询)
	- [11.1 利用子查询进行过滤](#111-利用子查询进行过滤)
	- [11.2 作为计算字段使用子查询](#112-作为计算字段使用子查询)
- [12. 联结表](#12-联结表)
	- [12.1 创建联结(INNER JOIN ON)](#121-创建联结inner-join-on)
- [13. 创建高级联结](#13-创建高级联结)
	- [13.1 使用表别名](#131-使用表别名)
	- [13.2 使用不同类型的联结](#132-使用不同类型的联结)
		- [13.2.1 等值联结/内联结](#1321-等值联结内联结)
		- [13.2.2 自联结](#1322-自联结)
		- [13.2.3 自然联结](#1323-自然联结)
		- [13.2.4 外联结(OUTER JOIN ON)](#1324-外联结outer-join-on)
	- [13.3 使用带聚集函数的联结](#133-使用带聚集函数的联结)
	- [13.4使用联结和联结条件](#134使用联结和联结条件)
- [14. 组合查询(UNION)](#14-组合查询union)
	- [14.1 组合查询](#141-组合查询)
	- [14.2创建组合查询](#142创建组合查询)
		- [14.2.2 UNION规则](#1422-union规则)
		- [14.2.3 重复的行](#1423-重复的行)
		- [14.2.4 对组合查询结果排序](#1424-对组合查询结果排序)
- [15. 插入数据(INSERT)](#15-插入数据insert)
	- [15.1数据插入](#151数据插入)
	- [15.2从一个表复制到另一个表(SELECT INTO)](#152从一个表复制到另一个表select-into)
- [16. 更新和删除数据](#16-更新和删除数据)
	- [16.1 更新数据(UPDATE)](#161-更新数据update)
	- [16.2 删除数据(DELETE)](#162-删除数据delete)
	- [16.3 更新和删除的指导原则](#163-更新和删除的指导原则)
- [17. 创建和操纵表](#17-创建和操纵表)
	- [17.1 创建表(CREATE TABLE)](#171-创建表create-table)
	- [17.2 更新表(ALTER TABLE)](#172-更新表alter-table)
	- [17.3 删除表(DROP)](#173-删除表drop)
	- [17.4 重命名表](#174-重命名表)
- [18. 使用视图](#18-使用视图)
	- [18.1 视图](#181-视图)
	- [18.2 创建视图(CREATE VIEW)](#182-创建视图create-view)
		- [18.2.1 利用视图简化复杂的联结](#1821-利用视图简化复杂的联结)
		- [18.2.2 ⽤视图重新格式化检索出的数据](#1822-视图重新格式化检索出的数据)
		- [18.2.3 用视图过滤不想要的数据](#1823-用视图过滤不想要的数据)
		- [18.2.4 使用视图与计算字段](#1824-使用视图与计算字段)
- [19. 使用存储过程](#19-使用存储过程)
	- [19.1 存储过程](#191-存储过程)
	- [19.2 为什么要使用存储过程](#192-为什么要使用存储过程)
	- [19.3 执行存储过程(EXECUTE)](#193-执行存储过程execute)
	- [19.4 创建存储过程](#194-创建存储过程)
- [20. 管理事务处理(COMMIT、ROLLBACK)](#20-管理事务处理commitrollback)
	- [20.1 事务处理](#201-事务处理)
	- [20.2 控制事务处理](#202-控制事务处理)
		- [20.2.1 撤销/回退(ROLLBACK)](#2021-撤销回退rollback)
		- [20.2.2 提交(COMMIT)](#2022-提交commit)
		- [20.2.3 使用保留点](#2023-使用保留点)
- [21. 使用游标](#21-使用游标)
	- [21.1 游标](#211-游标)
	- [21.2 使用游标](#212-使用游标)
		- [21.2.1 创建游标(DECLARE)](#2121-创建游标declare)
		- [21.2.2 使用游标(OPEN CURSOR)](#2122-使用游标open-cursor)
		- [21.2.3 访问游标数据(FETCH)](#2123-访问游标数据fetch)
		- [21.2.4 关闭游标](#2124-关闭游标)
- [22. 高级SQL特性](#22-高级sql特性)
	- [22.1 约束](#221-约束)
		- [22.1.1 主键](#2211-主键)
		- [22.1.2 外键](#2212-外键)
		- [22.1.3 唯一约束](#2213-唯一约束)
		- [22.1.4 检查约束](#2214-检查约束)
	- [22.2 索引](#222-索引)
	- [22.3 触发器](#223-触发器)
	- [22.4 数据库安全](#224-数据库安全)
- [附录：SQL语句的语法](#附录sql语句的语法)
	- [1. ALTER TABLE](#1-alter-table)
	- [2. COMMIT](#2-commit)
	- [3. CREATE INDEX](#3-create-index)
	- [4. CREATE PROCEDURE](#4-create-procedure)
	- [5. CREATE TABLE](#5-create-table)
	- [6. CREATE VIEW](#6-create-view)
	- [7. DELETE](#7-delete)
	- [8. DROP](#8-drop)
	- [9. INSERT](#9-insert)
	- [10. INSERT SELECT](#10-insert-select)
	- [11. ROOLBACK](#11-roolback)
	- [12. SELECT](#12-select)
	- [13. UPDATE](#13-update)
- [附录：SLQ数据类型](#附录slq数据类型)
	- [1. 字符串数据类型](#1-字符串数据类型)
	- [2. 数值数据类型](#2-数值数据类型)
	- [3. 日期和时间数据类型](#3-日期和时间数据类型)
	- [4. 二进制数据类型](#4-二进制数据类型)

# 1. SQL必知必会数据库创建

1. Download and install MySQL or MariaDB
2. Download and install MySQL Workbench
3. Open MySQL Workbench
4. In the left column, SQL Development, look at list of connections, if localhost is present sklip to step 11
5. Click on New Connection to display the Setup New Connection dialog
6. Set Connection Name to localhost
7. Specify the user login and password (if no user was specified then the login will be root)
8. Click the Test Connection button
9. If all ok, click OK to create the connection
10. The connection will now be listed, and in future you can just double-click on it directly
11. Double-click on the database to open the SQL Editor
12. To create a new database (called a Schema in MySQL and MariaDB) click on the Create New Schema button (it is the one that looks like a yellow barrel with a + sign next to it), this will display a dialog box.
13. Set name to tysql, you can leave all the other fields blank and click Apply. You’ll be prompted for verification and click Apply again to create the database
14. You can now type SQL in the editor window, but you must first make sure that your newly created database is selected. If it is selected its name will be in bold and the name will be displayed in the title bar. If something other than tysql is selected, double-click on tysql in the Object Browser
15. Copy and paste contents of Create (may see warnings about keys, ok)
16. Execute Query button (yellow lightning bolt) to execute
17. Copy and paste contents if Populate
18. Test it with SELECT * FROM Customers;

翻译：

1. 下载并安装MySQL或MariaDB
2. 下载并安装MySQL Workbench
3. 打开MySQL Workbench
4. 在左侧的SQLDevelopment列中，查看连接列表，如果localhost存在，请跳至步骤11
5. 单击"新建连接"以显示"设置新连接"对话框
6. 将连接名称设置为localhost
7. 指定用户登录名和密码(如果没有指定用户，则登录名将是root)
8. 单击测试连接按钮
9. 如果一切OK，点击OK创建连接
10. 现在会列出连接，以后直接双击就可以了
11. 双击数据库打开SQL编辑器
12. 要创建一个新数据库(在MySQL和MariaDB中称为Schema)，请单击Create New Schema按钮(它看起来像旁边有一个+号的黄色桶)，这将显示一个对话框。
13. 将name设置为tysql，其他字段可以留空，点击Apply。系统将提示您进行验证，然后再次单击"Apply"以创建数据库
14. 您现在可以在编辑器窗口中键入SQL，但您必须首先确保选择了您新创建的数据库。如果选中它，其名称将以粗体显示，并且名称将显示在标题栏中。如果选择了tysql以外的内容，请在对象浏览器中双击tysql
15. 复制粘贴Create的内容(可能会看到关于keys的警告，ok)
16. 执行查询按钮(黄色闪电)执行
17. 复制和粘贴内容如果填充
18. 用SELECT * FROM Customers进行测试

部分翻译如下所示↓：

|英文|中文|
|:-:|:-:|
|database|数据库|
|DBMS|数据库管理系统|
|table|表|
|schema|模式|
|column|列|
|row|行|
|keyword|关键字|
|primary key|主键|

* 主键的限制：
	* 任意两行都不具有相同的主键值
	* 每一行都必须具有一个主键值(主键列不允许NULL值)
	* 主键列中的值不允许修改或更新
	* 主键值不能重用(如果某行从表中删除，它的主键不能赋给以后的新行)
	* 多个列作为主键时列值的组合必须唯一，但单个列的值可以不唯一

# 2. 检索数据SELECT

## 2.1 检索单个列

SQL语句忽略空格，语句可以在一个长行上输入，也可以分成许多行

从table_name表中检索一个名为column_name的列：

```SQL
SELECT column_name
FROM table_name;
```

多条SQL语句必须以分号`;`分隔

SQL语句不区分大小写，习惯关键字大写，列名和表名小写

在处理SQL语句时，其中所有空格都被忽略。SQL语句可以写成⻓⻓的⼀⾏，也可以分写在多⾏

## 2.2 检索多个列

```SQL
SELECT column1, column2, column3
FROM table_name;
```

在选择多个列时，⼀定要在列名之间加上逗号，但最后⼀个列名后不加

## 2.3 检索所有列

```SQL
SELECT *
FROM table_name;
```

检索不需要的列通常会降低检索和应⽤程序的性能

## 2.4 检索不同的值

只返回不同的值：

```SQL
SELECT DISTINCT column_name
FROM table_name;
```

除非指定的两列完全相同，否则所有的行都会被检索出来：

```SQL
SELECT DISTINCT column1, column2
FROM table_name;
```

## 2.5 限制结果(LIMIT)

返回不超过num行的数据:

```SQL
SELECT column_name
FROM table_name LIMIT num;
```

返回从下标num2行起的num1行数据:

```SQL
SELECT column_name
FROM table_name
LIMIT num1 OFFSET num2;
```

等价于

```SQL
SELECT column_name
FROM table_name
LIMIT num2, num1;
```

## 2.6 使用注释

```SQL
-- 行内注释

# 行首注释

/*
多行注释
*/
```

# 3. 排序检索数据

## 3.1 排序数据(ORDER BY)

如果不排序，数据⼀般将以它在底层表中出现的顺序显⽰，这有可能是数据最初添加到表中的顺序。但是，如果数据随后进⾏过更新或删除，那么这个顺序将会受到DBMS重⽤回收存储空间的⽅式的影响。关系数据库设计理论认为，如果不明确规定排序顺序，则不应该假定检索出的数据的顺序有任何意义。

⼦句(clause)：SQL语句由⼦句构成，有些⼦句是必需的，有些则是可选的。⼀个⼦句通常由⼀个关键字加上所提供的数据组成。⼦句的例⼦如SELECT语句的FROM⼦句。

ORDER BY子句：为了明确地排序⽤SELECT语句检索出的数据，可使⽤ORDER BY⼦句。ORDER BY⼦句取⼀个或多个列的名字，据此对输出进⾏排序。

对column_name列以字⺟顺序排序数据：

```SQL
SELECT column_name
FROM table_name
ORDER BY column_name;
```

在指定⼀条ORDER BY⼦句时，应该保证它是SELECT语句中最后⼀条⼦句。

通常，ORDER BY⼦句中使⽤的列将是为显⽰⽽选择的列。但是，⽤⾮检索的列排序数据是完全合法的。

## 3.2 按多个列排序

首先按照column1进行排序，然后按照column2进行排序。仅在多个行具有相同的column1值时才对行按照column2进行排序，若column1中的值都是唯一的，则不会按column2进行排序。

```SQL
SELECT column_name
FROM table_name
ORDER BY column1, column2;
```

## 3.3 按列位置排序

在`column1, column2,column3`中按照第num1和num2列进行排序：

```SQL
SELECT column1, column2,column3
FROM table_name
ORDER BY num1, num2;
```

在根据不出现在SELECT中的列进行排序时不能使用这种写法

## 3.4 指定排序方向(DESC)

按照column降序进行排序：

```SQL
SELECT column_name
FROM table_name
ORDER BY column DESC;
```

先按照column1降序排列，然后按照column2升序进行排列：

```SQL
SELECT column_name
FROM table_name
ORDER BY column1 DESC column2;
```

DESC只应用到直接位于其前面的列名，如果想在多个列上进行降序排序，必须对每一列指定DESC关键字

升序关键字为`ASC`，不过默认就是升序

在字典(dictionary)排序顺序中，A被视为与a相同

# 4. 过滤数据(WHERE)

## 4.1 使用WHERE子句

WHERE子句在table_name(FROM子句)之后给出：

```SQL
SELECT column1, column2
FROM table_name
WHERE column2 = ***;
```

* 同时使用WHERE子句与ORDER BY子句，时，应让ORDER BY子句位于WHERE子句之后

## 4.2 WHERE子句操作符

|WHERE子句操作符|说明|
|:-:|:-:|
|=|等于|
|!=或<>|不等于|
|<|小于|
|<=|小于等于|
|!|不小于|
|>|大于|
|>=|大于等于|
|!>|不大于|
|BETWEEN|在指定的两个值之间|
|IS NULL|为NULL值|

单引号用来限定字符串，如果将值与字符串类型的列进行比较，就需要限定引号。用来与数值列进行比较的值不用引号

BETWEEN匹配num_low与num_high范围中所有的值，包括num_low与num_high：

```SQL
SELECT column1, column2
FROM table_name
WHERE column2 BETWEEN num_low AND num_high;
```

匹配column_name为NULL的行。检测是否为NULL不能用= NULL，而要用IS NULL：

```SQL
SELECT column_name
FROM table_name
WHERE column_name IS NULL;
```

# 5. 高级数据过滤

## 5.1 组合WHERE子句(AND、OR)

SQL允许给出多个WHERE子句，以AND子句或者OR子句的方式使用

`AND`用在WHERE子句中的关键字，用来指示检索满足所有给定条件的行。每个条件间都要使用`AND`关键字

`OR`用在WHERE子句中的关键字，用来表示检索匹配任一给定条件的行。

优先级：`()`>`AND`>`OR`

## 5.2 IN操作符

`IN`操作符⽤来指定条件范围，范围中的每个条件都可以进⾏匹配。`IN`取⼀组由逗号分隔、括在圆括号中的合法值。

`IN`操作符完成了与`OR`相同的功能，一般比一组`OR`操作符执行得更快，并且可以包含其他SELECT语句，能够更动态的建立WHERE子句。

```SQL
SELECT column_name
FROM table_name
WHERE column IN (value1, value2)
ORDER BY column;
```

等价于

```SQL
SELECT column_name
FROM table_name
WHERE column = value1 OR column = value2
ORDER BY column;
```

## 5.3 NOT操作符

`NOT`WHERE子句中用来否定其后条件的关键字

```SQL
SELECT column
FROM table1
WHERE NOT column = value
ORDER BY column;
```

等价于

```SQL
SELECT column
FROM table1
WHERE column <> value
ORDER BY column;
```
# 6. 用通配符进行过滤

## 6.1 LIKE操作符

通配符：用来匹配值的一部分的特殊字符

搜索模式：由字面值、通配符或两者组合构成的搜索条件

谓词：操作符何时不是操作符？答案是它作为谓词时。从技术上说，LIKE是谓词⽽不是操作符。但最终的结果是相同的

通配符搜索只能用于文本字段(串)，非文本数据类型字段不能使用通配符搜索

### 6.2 %通配符

`%`表示任何字符出现任意次数，还能匹配0个字符。代表搜索模式中给定位置的0个、1个或多个字符。但不会匹配NULL

检索任意以Fish起头的词。`%`指示DBMS接收Fish之后的任意字符，而不管它有多少字符：

```SQL
SELECT column
FROM table1
WHERE column LIKE 'Fish%';
```

匹配任何位置上包含文本bean bag的值，不论它之前或之后出现什么字符：

```SQL
SELECT column
FROM table1
WHERE column LIKE '%bean bag%';
```

根据DBMS的不同及其配置，搜索是可以区分大小写的

* 注意空格：包括Access在内的许多DBMS都⽤空格来填补字段的内容。例如，如果某列有50个字符，⽽存储的⽂本为Fish bean bag toy(17个字符)，则为填满该列需要在⽂本后附加33个空格。这样做⼀般对数据及其使⽤没有影响，但是可能对上述SQL语句有负⾯影响。⼦句WHERE prod_name LIKE 'F%y'只匹配以F开头、以y结尾的prod_name。如果值后⾯跟空格，则不是以y结尾，所以Fishbean bag toy就不会检索出来。简单的解决办法是给搜索模式再增加⼀个%号：'F%y%'还匹配y之后的字符(或空格)。更好的解决办法是⽤函数去掉空格

### 6.3 _通配符
`_`只匹配单个字符

可匹配12 inch teddy bear与18 inch teddy bear，无法匹配8 inch teddy bear：

```SQL
SELECT column
FROM table1
WHERE column LIKE '__ inch teddy bear';
```

### 6.4 []通配符

`[]`用来指定一个字符集，它必须匹配指定位置(通配符的位置)的一个字符，也只能匹配单个字符。此通配符可以通过前缀字符`^`来否定

只有微软的Access和SQL Server支持集合

匹配所有以J或M开头的行：

```SQL
SELECT column
FROM table1
WHERE column LIKE '[JM]%';
```

匹配所有不以J或M开头的行：

```SQL
SELECT column
FROM table1
WHERE column LIKE '^[JM]%';
```

等价于

```SQL
SELECT column
FROM table1
WHERE NOT column LIKE '[JM]%';
```

## 6.5 使用通配符的技巧

SQL通配符搜索一般比其他搜索要耗费更长的处理时间。

不要过度使用通配符。如果其他操作符能达到相同的⽬的，应该使⽤其他操作符。

在确实需要使⽤通配符时，也尽量不要把它们⽤在搜索模式的开始处。把通配符置于开始处，搜索起来是最慢的。

# 7. 创建计算字段

## 7.1 计算字段

字段：基本上与列的意思相同，经常互换使用，不过数据库列一般称为列，而术语字段通常与计算字段一起使用。

只有数据库知道SELECT语句中哪些列是实际的表列，哪些列是计算字段。从客⼾端(如应⽤程序)来看，计算字段的数据与其他列的数据的返回⽅式相同

在SQL语句内可完成的许多转换和格式化⼯作都可以直接在客⼾端应⽤程序内完成。但⼀般来说，在数据库服务器上完成这些操作⽐在客⼾端中完成要快得多

## 7.2 拼接字段

* 拼接：将值联结到一起(将一个值附加到另一个值)构成单个值。

Access和SQL Server使用`+`，DB2、Oracle、PostgreSQL、SQLite和Open Office Base使用`||`

这两个SELECT拼接column1中的数据、包含一个空格和一个左圆括号的字符串、存储在column2中的数据、包含一个右圆括号的字符串：

```SQL
SELECT column1 + ' (' + column2 + ')'
FROM table1
ORDER BY column;
```

等价于

```SQL
SELECT column1 || ' (' || column2 || ')'
FROM table1
ORDER BY column;
```

* RTRIM()函数：去掉字符串右边的空格
* LTRIM()函数：去掉字符串左边的空格
* TRIM()函数：去掉字符串左右两边的空格

使用RTRIM()函数去除多余的填充为列宽的空格

```SQL
SELECT RTRIM(column1) + ' (' + RTRIM(column2) + ')'
FROM table1
ORDER BY column;
```

等价于

```SQL
SELECT RTRIM(column1) || ' (' || RTRIM(column2) || ')'
FROM table1
ORDER BY column;
```

### 7.3 使用别名(AS)

拼接字段没有名字，只是一个值，可以在SQL中查询，但无法用于客户端应用中

别名：一个字段或值的替换名，用`AS`关键字赋予

```SQL
SELECT RTRIM(column1) + ' (' + RTRIM(column2) + ')'
    AS column3
FROM table1
ORDER BY column;
```

或

```SQL
SELECT RTRIM(column1) || ' (' || RTRIM(column2) || ')'
    AS column3
FROM table1
ORDER BY column;
```

在MySQL中使用Concat函数

```SQL
SELECT Concat(RTRIM(column1), ' (', RTRIM(column2), ')')
    AS column3
FROM table1
ORDER BY column;
```

`AS`指示SQL创建一个包含指定计算结果的名为column3的计算字段

`AS关键字是可选的，还可以用于在实际的表列名包含不合法的字符(如空格)时重新命名它，在原来的名字含混或容易误解时扩充它。

别名既可以是⼀个单词也可以是⼀个字符串。如果是后者，字符串应该括在引号中。虽然这种做法是合法的，但不建议这么去做。多单词的名字可读性⾼，不过会给客⼾端应⽤带来各种问题。因此，别名最常⻅的使⽤是将多个单词的列名重命名为⼀个单词的名字

Oracle中没有AS关键字

## 7.4 执行算术计算

SQL算术操作符
|操作符|说明|
|:-:|:-:|
|+|加|
|-|减|
|*|乘|
|/|除|
|()|区分优先顺序|

```SQL
SELECT column1, column2, column3,
  column2 * column3 AS column4
FROM table1
WHERE ***
ORDER BY column5;
```

SELECT语句为测试、检验函数和计算提供了很好的⽅法。虽然SELECT通常⽤于从表中检索数据，但是省略了FROM⼦句后就是简单地访问和处理表达式，例如SELECT 3 * 2;将返回6，SELECTTrim(' abc ');将返回abc，SELECT Now();使⽤Now()函数返回当前⽇期和时间

# 8. 使用数据处理函数

## 8.1 函数

与SQL语句不同，SQL函数是不可移植的

函数可以用作SELECT语句的列表成分，也可以作为SELECT语句的其他成分，如在WHERE子句中使用，在其他SQL语句中使用等

## 8.2 文本处理函数

|函数|说明|
|:-:|:-:|
|RTRIM()|去掉字符串右边的空格|
|LTRIM()|去掉字符串左边的空格|
|TRIM()|去掉字符串左右两边的空格|
|LEFT()(或使用子字符串函数)|返回字符串左边的字符|
|RIGHT()(或使用子字符串函数)|返回字符串右边的字符|
|LENGTH()(也使用DATALENGTH()或LEN())|返回字符串的长度|
|LOWER()(Access使用LCASE())|将字符串转换为小写|
|UPPER()(Access使用UCASE())|将字符串转换为大写|
|SOUNDEX()|返回字符串的SOUNDEX值|

SOUNDEX是⼀个将任何⽂本串转换为描述其语⾳表⽰的字⺟数字模式的算法。SOUNDEX考虑了类似的发⾳字符和⾳节，使得能对字符串进⾏发⾳⽐较⽽不是字⺟⽐较。虽然SOUNDEX不是SQL概念，但多数DBMS都提供对SOUNDEX的⽀持

## 8.3 数值处理函数

|函数|说明|
|:-:|:-:|
|ABS()|返回一个数的绝对值|
|SIN()|返回一个角度的正弦|
|COS()|返回一个角度的余弦|
|TAN()|返回一个角度的正切|
|EXP()|返回一个数的指数值|
|PI()|返回圆周率|
|SQRT()|返回一个数的平方根|

# 9. 汇总数据

## 9.1 聚集函数

聚集函数：对某些行运行的函数，计算并返回一个值

|函数|说明|
|:-:|:-:|
|AVG()|返回某列的平均值|
|COUNT()|返回某列的行数|
|MAX()|返回某列的最大值|
|MIN()|返回某列的最小值|
|SUM()|返回某列值之和|

利⽤标准的算术操作符，所有聚集函数都可⽤来执⾏多个列上的计算

### 9.1.1 AVG()

AVG()只能⽤来确定特定数值列的平均值，⽽且列名必须作为函数参数给出。为了获得多个列的平均值，必须使⽤多个AVG()函数

AVG()函数忽略列值为NULL的⾏

```SQL
SELECT AVG(column1) AS column2
FROM table1
WHERE column3 = ***;
```

### 9.1.2 COUNT()

* 使⽤COUNT(*)对表中⾏的数⽬进⾏计数，不管表列中包含的是空值(NULL)还是⾮空值。

* 使⽤COUNT(column)对特定列中具有值的⾏进⾏计数，忽略NULL值。

```SQL
SELECT COUNT(*) AS column
FROM table1;

SELECT COUNT(column1) AS column2
FROM table1;
```

### 9.1.3 MAX()/MIN()

MAX()/MIN()要求指定列名

虽然MAX()/MIN()⼀般⽤来找出最⼤/小的数值或⽇期值，但许多(并⾮所有)DBMS允许将它⽤来返回任意列中的最⼤/小值，包括返回⽂本列中的最⼤/小值。在⽤于⽂本数据时，MAX()/MIN()返回按该列排序后的最后⼀⾏/最前面的行

MAX()/MIN()忽略值为NULL的行

```SQL
SELECT MAX(column1) AS column2
FROM table1;
```

### 9.1.4 SUM()

SUM()函数忽略列值为NULL的⾏

```SQL
SELECT SUM(column1) AS column2
FROM table1
WHERE column3 = ***;
```

## 9.2 聚集不同值

聚集函数使用方法：指定DISTINCT参数，只包含不同的值，不指定DISTINCT则默认为ALL，

只考虑各个不同的数据：

```SQL
SELECT AVG(DISTINCT column1) AS column2
FROM table1
WHERE ***;
```

DISTINCT用于COUNT()时，只能用于COUNT()指定列名，而不能用于COUNT(*)

DISTINCT用于MAX()与MIN()没有意义

## 9.3 组合聚集函数

```SQL
SELECT COUNT(*) AS column1
  MIN(column2) AS column3
  MAX(column4) AS column5
  AVG(column6) AS column7
FROM table1;
```

在指定别名以包含某个聚集函数的结果时，使⽤表中实际的列名作为别名虽然合法，但是许多SQL不支持

聚集函数⽤来汇总数据。SQL⽀持5个聚集函数，可以⽤多种⽅法使⽤它们，返回所需的结果。这些函数很⾼效，它们返回结果⼀般⽐在客⼾端应⽤程序中计算要快得多。

# 10. 分组数据

## 10.1 创建分组(GROUP BY)

GROUP BY子句只是DBMS分组数，然后对每个组而不是整个结果集进行聚集

* GROUP BY子句可以包含任意数目的列，因而可以对分组进行嵌套，更细致地进行数据分组
* 如果在GROUP BY⼦句中嵌套了分组，数据将在最后指定的分组上进⾏汇总。换句话说，在建⽴分组时，指定的所有列都⼀起计算(所以不能从个别的列取回数据)
* GROUP BY⼦句中列出的每⼀列都必须是检索列或有效的表达式(但不能是聚集函数)。如果在SELECT中使⽤表达式，则必须在GROUP BY⼦句中指定相同的表达式。不能使⽤别名
* ⼤多数SQL实现不允许GROUP BY列带有⻓度可变的数据类型(如⽂本或备注型字段)
* 除聚集计算语句外，SELECT语句中的每⼀列都必须在GROUP BY⼦句中给出
* 如果分组列中包含具有NULL值的⾏，则NULL将作为⼀个分组返回。如果列中有多⾏NULL值，它们将分为⼀组
* GROUP BY⼦句必须出现在WHERE⼦句之后，ORDER BY⼦句之前

按column1排序并分组数据，对每个column1而不是整个table1计算column2一次：

```SQL
SELECT column1, COUNT(*) AS column2
FROM table1
GROUP BY column1;
```

## 10.2 过滤分组(HAVING)

HAVING支持所有WHERE操作符，句法相同。WHERE过滤行，HAVING过滤分组。也可以理解为，WHERE在数据分组前过滤，HAVING在分组后过滤，并且WHERE排除的行不包括在分组中。这可能会改变计算值，从而影响HAVING子句中基于这些值过滤掉的分组。

HAVING与WHERE⾮常类似，如果不指定GROUP BY，则⼤多数DBMS会同等对待它们。使⽤HAVING时应该结合GROUP BY⼦句，⽽WHERE⼦句⽤于标准的⾏级过滤

WHERE子句过滤所有column3至少为4的行，然后按column1分组，HAVING子句过滤column1计数为2或2以上的分组：

```SQL
SELECT column1, COUNT(*) AS column2
FROM table1
WHERE column3 >= 4
GROUP BY column1
HAVING COUNT(*) >= 2;
```

## 10.3 分组和排序

|ORDER BY|GROUP BY|
|:-:|:-:|
|对产生的输出排序|对行分组，但输出可能不是分组的顺序|
|任意列都可以使用(甚至非选择的列也可以使用)|只可能使用选择列或表达式列，而且必须使用每个选择列表达式|
|不一定需要|如果与聚集函数一起使用列(或表达式)，则必须使用|

⼀般在使⽤GROUP BY⼦句时，应该也给出ORDER BY⼦句。这是保证数据正确排序的唯⼀⽅法。不要仅依赖GROUP BY排序数据

使用GROUP BY子句按column1分组数据，以便COUNT(*)函数能够返回column1中的数目HAVING子句过滤数据，使得只返回包含三个或更多的column1行最后，用ORDER BY子句排序输出：

```SQL
SELECT column1, COUNT(*) AS column2
FROM table1
GROUP BY column1
HAVING COUNT(*) >= 3
ORDER BY column2, column1;
```

## 10.4 SELECT子句顺序

|子句|说明|是否必须使用|
|:-:|:-:|:-:|
|SELECT|要返回的列或表达式|是|
|FROM|从中检索数据的表|仅在从表选择数据时使用|
|WHERE|行级过滤|否|
|GROUP BY|分组说明|仅在按组计算聚集时使用|
|HAVING|组级过滤|否|
|ORDER BY|输出排序顺序|否|

# 11. 使用子查询

## 11.1 利用子查询进行过滤

子查询常用语WHERE子句的IN操作符中，以及用来填充计算列。在SELECT语句中，子查询总是从内向外处理。

最里面的SELECT首先查询并返回column1，此列表用于其外面的子查询的WHERE子句。外面的子查询返回column2，此列表用于最外层查询的WHERE子句。最外层查询返回所需的数据：

```SQL
SELECT column4, column5
FROM table3
WHERE column3 IN (SELECT column3
                  FROM table2
                  WHERE column1 IN (SELECT column1
                                    FROM table1
                                    WHERE column2 = ***));
```

可以使用联结的方式完成同样的任务

* 作为子查询的SELECT语句只能查询单个列，企图检索多个列将返回错误

## 11.2 作为计算字段使用子查询

首先子查询对table1中的column1进行检索，然后将计数结果返回作为column2：

```SQL
SELECT column3,
       column4,
       (SELECT COUNT(*)
        FROM table1
        WHERE table1.column1 = table2.column1) AS column 2
FROM table2
ORDER BY column3;
```

table.column为完全限定列名，用`.`分隔表名和列名，必须在有可能混淆列名时使用。

# 12. 联结表

## 12.1 创建联结(INNER JOIN ON)

创建联结只需要指定要联结的所有表以及关联他们的方式。首先列出所有表，然后定义表之间的关系

等值联结/内联结：WHERE子句需要完全限定列名(column1在table1中，column2在table2中)

```SQL
SELECT column1, column2, column3
FROM table1, table2
WHERE table1.column4 = table2.column4;
```

标准语法：使用table1 INNER JOIN table2 ON condition

```SQL
SELECT column1, column2, column3
FROM table1 INNER JOIN table2
  ON table1.column4 = table2.column4;
```

由没有联结条件的表关系返回的结果为笛卡⼉积。检索出的⾏的数⽬将是第⼀个表中的⾏数乘以第⼆个表中的⾏数

SQL不限制一条SELECT语句中可以联结的表的数目：

```SQL
SELECT column1, column2, column3, column4
FROM table1, table2, table3
WHERE table1.column5 = table2.column5
  AND table2.column6 = table3.column6
  AND column7 = ***;
```

子查询可以用联结实现：

```SQL
SELECT column4, column5
FROM table3
WHERE column3 IN (SELECT column3
                  FROM table2
                  WHERE column1 IN (SELECT column1
                                    FROM table1
                                    WHERE column2 = ***));
```

等价于

```SQL
SELECT column4, column5
FROM table1, table2, table3
WHERE table2.column1 = table1.column1
  AND table3.column3 = table2.column3
  AND column2 = ***;
```
# 13. 创建高级联结

## 13.1 使用表别名

```SQL
SELECT column1, column2
FROM table1 AS C, table2 AS O, table3 AS OI
WHERE C.column3 = O.column3
  AND OI.column4 = O.column4
  AND column5 = ***;
```

* 表别名不仅能用于WHERE子句，还能用于SELECT的列表、ORDER BY子句以及其他语句部分

* 表别名只在查询执行中使用

* 列别名能返回到客户端，但表别名不能返回到客户端

## 13.2 使用不同类型的联结

### 13.2.1 等值联结/内联结

(column1在table1中，column2在table2中)

WHERE写法：

```SQL
SELECT column1, column2, column3
FROM table1, table2
WHERE table1.column4 = table2.column4;
```

标准写法：

```SQL
SELECT column1, column2, column3
FROM table1 INNER JOIN table2
  ON table1.column4 = table2.column4;
```

### 13.2.2 自联结

WHERE首先联结两个表，然后按照第二个表c2中的column3过滤数据，返回所需的数据：

```SQL
SELECT c1.column1, c1.column2, c1.column3
FROM table1 AS c1, table1 AS c2
WHERE c1.column2 = c2.column2
  AND c2.column3 = ***;
```

等价于

```SQL
SELECT column1, column2, column3
FROM table1
WHERE column2 = (SELECT column2
                 FROM table2
                 WHERE column3 = ***);
```

查询同一个表中column3=***的数据

⾃联结通常作为外部语句，⽤来替代从相同表中检索数据的使⽤⼦查询语句。一般DBMS处理联结比处理子查询快得多

### 13.2.3 自然联结

⽆论何时对表进⾏联结，⾄少有⼀列不⽌出现在⼀个表中(被联结的列)。标准的联结(内联结)返回所有数据，相同的列甚⾄多次出现。⾃然联结排除多次出现，使每⼀列只返回⼀次。

系统不完成自然联结，需要由⾃⼰完成。⾃然联结要求你只能选择那些唯⼀的列，⼀般通过对⼀个表使⽤通配符(SELECT *)，⽽对其他表的列使⽤明确的⼦集来完成

```SQL
SELECT C.*, O.column1, O.column2, OI.column3, OI.column4, OI.column5
FROM  table1 AS C, table2 AS O, table3 AS OI
WHERE C.column6 = O.column6,
  AND OI.column1 = OI.column1
  AND column3 = ***;
```

### 13.2.4 外联结(OUTER JOIN ON)
内联结：

```SQL
SELECT table1.column1, table2.column2
FROM table1 INNER JOIN table2
  ON table1.column1 = table2.column1;
```

或

```SQL
SELECT table1.column1, table2.column2
FROM table1, table2
WHERE table1.column1 = table2.column1;
```

外联结：联结包含了在相关的表中没有关联行的行

```SQL
SELECT table1.column1, table2.column2
FROM table1 LEFT OUTER JOIN table2
  ON table1.column1 = table2.column1;
```

使⽤了关键字OUTER JOIN来指定联结类型(⽽不是在WHERE⼦句中指定)。外联结还包括没有关联⾏的⾏。在使⽤OUTER JOIN语法时，必须使⽤RIGHT或LEFT关键字指定包括其所有⾏的表(RIGHT指出的是OUTER JOIN右边的表，⽽LEFT指出的是OUTER JOIN左边的表)。上⾯的例⼦使⽤LEFT OUTER JOIN从FROM⼦句左边的表(table1表)中选择所有⾏。为了从右边的表中选择所有⾏，需要使⽤RIGHT OUTER JOIN

全外联结：检索两个表中的所有行并关联那些可以关联的行。与左外联结或右外联结包含一个表的不关联的行不同，全外联结包含两个表的不关联的行。全外联结使用关键字`FULL OUTER JOIN`。(Access、MariaDB、MySQL、Open Office Base或SQLite不支持FULL OUTER JOIN语法)

## 13.3 使用带聚集函数的联结

这条SELECT语句使用INNER JOIN将table1和table2互相关联，GROUP BY子句按column1分组数据。因此函数调用COUNT(table2.column2)对每个column1计数，将它作为column3返回：

```SQL
SELECT table1.column1, COUNT(table2.column2) AS column3
FROM table1 INNER JOIN table2
  ON table1.column1 = table2.column1
GROUP BY table1.column1;
```

## 13.4使用联结和联结条件

* 注意所使⽤的联结类型。⼀般我们使⽤内联结，但使⽤外联结也有效

* 关于确切的联结语法，应该查看具体的⽂档，看相应的DBMS⽀持何种语法

* 保证使⽤正确的联结条件(不管采⽤哪种语法)，否则会返回不正确的数据

* 应该总是提供联结条件，否则会得出笛卡⼉积

* 在⼀个联结中可以包含多个表，甚⾄可以对每个联结采⽤不同的联结类型。虽然这样做是合法的，⼀般也很有⽤，但应该在⼀起测试它们前分别测试每个联结。这会使故障排除更为简单

# 14. 组合查询(UNION)

## 14.1 组合查询

并/复合查询：SQL允许执⾏多个查询(多条SELECT语句)，并将结果作为⼀个查询结果集返回

使用情况：

* 在一个查询中从不同的表返回数据结构

* 对一个表执行多个查询，按一个查询返回数据

多数情况下，组合相同表的两个查询所完成的⼯作与具有多个WHERE⼦句条件的⼀个查询所完成的⼯作相同。即任何具有多个WHERE⼦句的SELECT语句都可以作为⼀个组合查询

## 14.2创建组合查询

UNION指示DBMS执行这两条SELECT语句，并把输出组合成一个查询结果集：

```SQL
SELECT column1, column2, column3
FROM table1
WHERE column4 IN (data1, data2, data3)
UNION
SELECT column1, column2, column3
FROM table1
WHERE column1 = data4;
```

等价于

```SQL
SELECT column1, column2, column3
FROM table1
WHERE column4 IN (data1, data2, data3)
  OR column1 = data4;
```

使⽤UNION组合SELECT语句的数⽬，SQL没有标准限制，但是最好参考一下DBMS文档

### 14.2.2 UNION规则

* UNION必须由两条或两条以上的SELECT语句组成，两条语句之间用关键字UNION分隔

* UNION中的每个查询必须包含相同的列、表达式或聚集函数(不过，各个列不需要以相同的次序列出)

* 列数据类型必须兼容：类型不必完全相同，但必须是DBMS可以隐含转换的类型(例如，不同的数值类型或不同的日期类型)

### 14.2.3 重复的行

UNION从查询结果集中⾃动去除了重复的⾏，即它的⾏为与⼀条SELECT语句中使⽤多个WHERE⼦句条件⼀样。使用UNION时，重复的行会被自动取消。如果想返回所有的匹配行，可使用UNION ALL而不是UNION

这⼀课⼀开始我们说过，UNION⼏乎总是完成与多个WHERE条件相同的⼯作。UNION ALL为UNION的⼀种形式，它完成WHERE⼦句完成不了的⼯作。如果确实需要每个条件的匹配⾏全部出现(包括重复⾏)，就必须使⽤UNION ALL，⽽不是WHERE。

### 14.2.4 对组合查询结果排序

在用UNION组合查询时，只能使用一条ORDER BY子句，并且它必须位于最后一条SELECT子句之后。虽然ORDER BY子句只是最后一条SELECT语句的组成部分，但实际上DBMS将用它来排序所有SELECT语句返回的所有结果。

某些DBMS还支持另外两种指令：

* `UNION: EXCEPT`/`MINUS`可以用来检索只在第一个表中存在而在第二个表中不存在的行

* `INTERSECT`可用来检索两个表中都存在的行。

UNION在需要组合多个表的数据时也很有用，即使是有不匹配列名的表，在这种情况下，可以将UNION与别名组合，检索一个结果集

# 15. 插入数据(INSERT)

## 15.1数据插入

`INSERT`的几种插入方式：插入完整的行、插入行的一部分、插入某些查询结果

将数据data插入到表table1中新建一行，datax与columnx对应

```SQL
INSERT INTO table1(column1,
                  column2,
                  column3,
                  ....,
                  columnn,
                  )
VALUES(data1,
       data2,
       data3,
       ...,
       datan);
```

可以将columnx省略，此时datax将和table1中列的定义次序对应，不建议省略：

如果表的定义允许，则可以在INSERT中省略某些列，省略的列必须满足以下某个条件：该列定义为允许NULL值或在表定义中给出默认值(如果不给出值则将使用默认值)如果表中不允许有NULL值或者默认值，则此时不能省略INSERT中的列

INSERT还可以插入检索出的数据

使用INSERT SELECT从table2中将所有数据导入table1。SELECT从table1中检索出要插入的值，列出的每一列对应于table1后所跟的每一列：

```SQL
INSERT INTO table1(column1,
                   column2,
                   column3,
                   ....,
                   columnn,
                   )
SELECT column1,
       column2,
       column3,
       ...,
       columnn
FROM table2;
```

如果table2为空，则仍可以运行，但没有行被插入

在INSERT和SELECT中不一定要求列名匹配，DBMS使用的是列的位置

INSERT SELECT中的SELECT语句可以包含WHERE子句，以过滤插入的数据

INSERT一般只插入一行，要插入多行需要执行多个INSERT语句，但是INSERT SELECT可以插入多行，所有被SELECT返回的都将被INSERT插入

## 15.2从一个表复制到另一个表(SELECT INTO)

SELECT INTO将数据复制到一个新表。INSERT SELECT是导出数据，SELECT INTO是导入数据

这条SELECT语句创建一个名为table1的新表，并把table2表的整个内容复制到table1中：

```SQL
SELECT *
INTO table1
FROM table2;
```

因为使用的是SELECT *，所以将在table1中创建与table2每一列相同的列

如果只想复制部分的列，可以明确给出列名，而不是使用*通配符

MariaDB、MySQL、Oracle、PostgreSQL和SLQite使用以下语法：

```SQL
CREATE TABLE table1 AS
SELECT * FROM table2;
```

使用SELECT INTO时，任何SELECT选项和子句都可以使用，包括WHERE和GROUP BY，可以利用联结从多个表插入数据，并且不管从多少个表中检索数据，数据都只能插入到一个表中

# 16. 更新和删除数据

## 16.1 更新数据(UPDATE)

UPDATE的两种使用方式：更新表中的特定行/更新表中的所有行

```SQL
UPDATE table1
SET column1 = data1,
    column2 = data2
WHERE column3 = data3;
```

UPDATE以要更新的表名开始，SET子句设置column为指定的值。没有WHERE子句，DBMS将更新table1中所有的行

UPDATE语句中可以使用子查询，使得能用SELECT语句检索出的数据更新列数据

有的DBMS支持在UPDATE中使用FROM子句，用一个表的数据更新另一个表的行

要删除某个列的值，可设置它为NULL(假如表定义允许NULL)

## 16.2 删除数据(DELETE)

DELETE的两种使用方式：从表中删除特定的行/从表中删除所有行

```SQL
DELETE FROM table1
WHERE column = data;
```

DELETE FROM指定要从中删除数据的表名。WHERE子句过滤要删除的行，如果省略WHERE子句，则将删除column中的所有行

使用外键确保引用完整性的一个好处是DBMS可以防止删除某个关系需要用到的行

DELETE不需要列名或通配符，DELETE删除整行而不是删除列，要删除指定的列，需要使用UPDATE语句

DELETE从表中删除行，甚至是删除表中所有行，但DELETE不删除表本身

如果想从表中删除所有行，则使用TRUNCATE TABLE语句更快(因为不记录数据的变动)

## 16.3 更新和删除的指导原则

* 除⾮确实打算更新和删除每⼀⾏，否则绝对不要使⽤不带WHERE⼦句的UPDATE或DELETE语句

* 保证每个表都有主键，尽可能像WHERE⼦句那样使⽤它(可以指定各主键、多个值或值的范围)

* 在UPDATE或DELETE语句使⽤WHERE⼦句前，应该先⽤SELECT进⾏测试，保证它过滤的是正确的记录，以防编写的WHERE⼦句不正确

* 使⽤强制实施引⽤完整性的数据库(第12节)这样DBMS将不允许删除其数据与其他表相关联的⾏

* 有的DBMS允许数据库管理员施加约束，防⽌执⾏不带WHERE⼦句的UPDATE或DELETE语句

# 17. 创建和操纵表

## 17.1 创建表(CREATE TABLE)

创建表的方法：多数DBMS都具有交互式创建和管理数据库表的工具，并且表也可以直接用SQL语句操纵

```SQL
CREATE TABLE table1
(
  column1 CHAR(10)      NOT NULL,
  column2 CHAR(254)     NOT NULL  DEFAULT data,
  column3 DECIMAL(8, 2) NOT NULL,
  column4 VARCHAR(1000) NULL
);
```

表名紧跟CREATE TABLE关键字，实际的表定义(所有列)在圆括号中，各列之间用逗号分隔，每列的定义以列名开始(列名在表中必须是唯一的)，后跟列的数据类型。NOT NULL为指定列不允许为NULL，如果不写则默认为NULL，即允许列中值为NULL，然后可以使用DEFAULT关键字指定默认值，在插入行时如果不给出值，DBMS将自动采用默认值

在创建新的表时，指定的表名必须不存在，否则会出错。防止意外覆盖已有的表，SQL要求首先手动删除该表，然后再重建它，而不是简单地用创建语句覆盖它

只有不允许NULL值的列可作为主键，允许NULL值的列不能作为唯一标识

DEFAULT将系统日期用作默认日期函数↓：

|DBMS|函数/变量|
|:-:|:-:|
|Access|NOW()|
|DB2|CURRENT_DATE|
|MySQL|CURRENT_DATE()|
|Oracle|SYSDATE|
|PostgreSQL|CURRENT_DATE|
|SQL Server|GETDATE()|
|SQLite|date('now')|

## 17.2 更新表(ALTER TABLE)

* 理想情况下，不要在表中包含数据时对其进⾏更新。应该在表的设计过程中充分考虑未来可能的需求，避免今后对表的结构做⼤改动

* 所有的DBMS都允许给现有的表增加列，不过对所增加列的数据类型(以及NULL和DEFAULT的使⽤)有所限制。

* 许多DBMS不允许删除或更改表中的列。

* 多数DBMS允许重新命名表中的列。

* 许多DBMS限制对已经填有数据的列进⾏更改，对未填有数据的列⼏乎没有限制。

使用ALTER TABLE更改表结构，必须给出下面的信息：在ALTER TABLE之后给出要更改的表名(该表必须存在)，然后列出要做哪些更改

这条语句给table1增加一个名为column的列，其数据类型为CHAR：

```SQL
ALTER TABLE table1
ADD column CHAR(20);
```

复杂的表结构更改一般需要手动删除过程，它涉及以下步骤↓：

1. ⽤新的列布局创建⼀个新表

2. 使⽤INSERT SELECT语句从旧表复制数据到新表。有必要的话，可以使⽤转换函数和计算字段

3. 检验包含所需数据的新表

4. 重命名旧表(如果确定，可以删除它)

5. ⽤旧表原来的名字重命名新表

6. 根据需要，重新创建触发器、存储过程、索引和外键

## 17.3 删除表(DROP)

删除整个表：

```SQL
DROP TABLE table1;
```

删除表没有确认，也不能撤销

许多DBMS允许强制实施有关规则，防⽌删除与其他表相关联的表。在实施这些规则时，如果对某个表发布⼀条DROP TABLE语句，且该表是某个关系的组成部分，则DBMS将阻⽌这条语句执⾏，直到该关系被删除为⽌。如果允许，应该启⽤这些选项，它能防⽌意外删除有⽤的表

## 17.4 重命名表

|DBMS|重命名函数|
|:-:|:-:|
|DB2、MariaDB、MySQL、Oracle、PostgreSQL|RENAME|
|SQL Server|sp_rename|
|SQLite|ALTER TABLE|

所有重命名操作的基本语法都要求指定旧表名和新表名。不过，存在DBMS实现差异

# 18. 使用视图

## 18.1 视图

视图是虚拟的表。与包含数据的表不⼀样，视图只包含使⽤时动态检索数据的查询。它不包含任何列或数据，包含的是一个查询

视图为虚拟的表。它们包含的不是数据⽽是根据需要检索数据的查询。视图提供了⼀种封装SELECT语句的层次，可⽤来简化数据处理，重新格式化或保护基础数据。

DBMS⽀持：Microsoft Access不⽀持视图，没有与SQL视图⼀致的⼯作⽅式。MySQL从版本5起开始⽀持视图。SQLite仅⽀持只读视图，所以视图可以创建，可以读，但其内容不能更改。

视图的常见应用：

* 重⽤SQL语句

* 简化复杂的SQL操作。在编写查询后，可以⽅便地重⽤它⽽不必知道其基本查询细节

* 使⽤表的⼀部分⽽不是整个表

* 保护数据。可以授予⽤⼾访问表的特定部分的权限，⽽不是整个表的访问权限

* 更改数据格式和表⽰。视图可返回与底层表的表⽰和格式不同的数据

创建视图之后，可以⽤与表基本相同的⽅式使⽤它们。可以对视图执⾏SELECT操作，过滤和排序数据，将视图联结到其他视图或表，甚⾄添加和更新数据(添加和更新数据存在某些限制)

视图仅仅是⽤来查看存储在别处数据的⼀种设施。视图本⾝不包含数据，因此返回的数据是从其他表中检索出来的。在添加或更改这些表中的数据时，视图将返回改变过的数据

因为视图不包含数据，所以每次使⽤视图时，都必须处理查询执⾏时需要的所有检索。如果⽤多个联结和过滤创建了复杂的视图或者嵌套了视图，性能可能会下降得很厉害

视图创建和使⽤的⼀些最常⻅的规则和限制(限制随不同的DBMS而不同)：

* 与表⼀样，视图必须唯⼀命名(不能给视图取与别的视图或表相同的名字)

* 对于可以创建的视图数⽬没有限制

* 创建视图，必须具有⾜够的访问权限。这些权限通常由数据库管理⼈员授予

* 视图可以嵌套，即可以利⽤从其他视图中检索数据的查询来构造视图。所允许的嵌套层数在不同的DBMS中有所不同(嵌套视图可能会严重降低查询的性能)

* 许多DBMS禁⽌在视图查询中使⽤ORDER BY⼦句

* 有些DBMS要求对返回的所有列进⾏命名，如果列是计算字段，则需要使⽤别名

* 视图不能索引，也不能有关联的触发器或默认值

* 有些DBMS把视图作为只读的查询，这表⽰可以从视图检索数据，但不能将数据写回底层表。

* 有些DBMS允许创建这样的视图，它不能进⾏导致⾏不再属于视图的插⼊或更新。例如有⼀个视图，只检索带有电⼦邮件地址的顾客。如果更新某个顾客，删除他的电⼦邮件地址，将使该顾客不再属于视图。这是默认⾏为，⽽且是允许的，但有的DBMS可能会防⽌这种情况发⽣

## 18.2 创建视图(CREATE VIEW)

CREATE VIEW只能用于创建不存在的视图

删除视图view_name：

```SQL
DROP VIEW view_name;
```

覆盖或更新视图，必须首先删除它，然后再重新创建

### 18.2.1 利用视图简化复杂的联结

创建一个名为view的视图，它联结三个表：

```SQL
CREATE VIEW view AS
SELECT column1, column2, column3
FROM table1, table2, table3
WHERE table1.column4 = table2.column4
  AND table3.column5 = table2.column5;
```

这条语句通过WHERE子句从视图中检索特定数据：

```SQL
SELECT column1, column2
FROM view
WHERE column3 = data;
```

当DBMS处理此查询时，它将指定的WHERE⼦句添加到视图查询中已有的WHERE⼦句中，以便正确过滤数据

### 18.2.2 ⽤视图重新格式化检索出的数据
该语句使⽤与SELECT语句相同的查询创建视图：

```SQL
CREATE VIEW view AS
SELECT RTRIM(column) + ' (' + RTRIM(column2) + ')'
       AS table1
FROM table2;
```

等价于

```SQL
CREATE VIEW view AS
SELECT RTRIM(column) || ' (' || RTRIM(column2) || ')'
       AS table1
FROM table2;
```

要检索数据，可以使用以下语句

```SQL
SELECT *
FROM view;
```

### 18.2.3 用视图过滤不想要的数据

该语句过滤了column3列中具有NULL值的行，使它们不被检索出来。然后可以像使用其他表一样使用视图view：

```SQL
CREATE VIEW view AS
SELECT column1, column2, column3
FROM table1
WHERE column3 IS NOT NULL;
```

从视图检索数据时如果使⽤了⼀条WHERE⼦句，则两组⼦句(⼀组在视图中，另⼀组是传递给视图的)将⾃动组合

### 18.2.4 使用视图与计算字段

```SQL
CREATE VIEW view AS
SELECT column1,
       column2,
       column3 * column4 AS column5
FROM table1;

SELECT *
FROM view
WHERE column1 = data;
```

等价于

```SQL
SELECT column2,
       column3 * column4 AS column5
FROM table1
WHERE column1 = data;
```

# 19. 使用存储过程

## 19.1 存储过程

存储过程就是为以后使⽤⽽保存的⼀条或多条SQL语句。可将其视为批⽂件，虽然它们的作⽤不仅限于批处理

Microsoft Access和SQLite不⽀持存储过程

## 19.2 为什么要使用存储过程

优点：

* 通过把处理封装在⼀个易⽤的单元中，可以简化复杂的操作

* 由于不要求反复建⽴⼀系列处理步骤，因⽽保证了数据的⼀致性。如果所有开发⼈员和应⽤程序都使⽤同⼀存储过程，则所使⽤的代码都是相同的。这⼀点的延伸就是防⽌错误。需要执⾏的步骤越多，出错的可能性就越⼤。防⽌错误保证了数据的⼀致性。

* 简化对变动的管理。如果表名、列名或业务逻辑(或别的内容)有变化，那么只需要更改存储过程的代码。使⽤它的⼈员甚⾄不需要知道这些变化。这⼀点的延伸就是安全性。通过存储过程限制对基础数据的访问，减少了数据讹误(⽆意识的或别的原因所导致的数据讹误)的机会。

* 因为存储过程通常以编译过的形式存储，所以DBMS处理命令的⼯作较少，提⾼了性能。

* 存在⼀些只能⽤在单个请求中的SQL元素和特性，存储过程可以使⽤它们来编写功能更强更灵活的代码

* 即简单、安全、高性能

缺陷：

* 不同DBMS中的存储过程语法有所不同。事实上，编写真正的可移植存储过程⼏乎是不可能的。不过，存储过程的⾃我调⽤(名字以及数据如何传递)可以相对保持可移植。因此，如果需要移植到别的DBMS，⾄少客⼾端应⽤代码不需要变动

* ⼀般来说，编写存储过程⽐编写基本SQL语句复杂，需要更⾼的技能，更丰富的经验。

* 因此，许多数据库管理员把限制存储过程的创建作为安全措施(主要受上⼀条缺陷的影响)

## 19.3 执行存储过程(EXECUTE)

EXECUTE接受存储过程名和需要传递给它的任何参数

执行一个名为function的存储过程，参数为parameter：

```SQL
EXECUTE function(parameter1,
                 parameter2,
                 parameter3,
                 parameter4);
```

存储过程所完成的工作：

* 验证传递的数据，保证所有参数都有值

* ⽣成⽤作主键的唯⼀ID

* 执行操作

对于具体的DBMS，可能包括以下的执⾏选择：

* 参数可选，具有不提供参数时的默认值；

* 不按次序给出参数，以"参数=值"的⽅式给出参数值。

* 输出参数，允许存储过程在正执⾏的应⽤程序中更新所⽤的参数。

* ⽤SELECT语句检索数据。

* 返回代码，允许存储过程返回⼀个值到正在执⾏的应⽤程序

## 19.4 创建存储过程

Oracle版本

```SQL
CREATE PROCEDURE function (
  parameter OUT INTEGER
)
IS
column1 INTEGER
BEGIN
  SELECT COUNT(*) INTO column1
  FROM table1
  WHERE NOT column2 IS NULL;
  parameter := column1;
END;
```

这个存储过程有一个名为parameter的参数，此参数从存储过程返回一个值而不是传递一个值给存储过程，关键字OUT用来指示这种行为。存储过程的代码括在BEGIN和END语句中，这里执行一条简单的SELECT语句，然后用检索出的数据column1设置parameter

调用语句为：

```SQL
var ReturnValue NUMBER
EXEC function(:ReturnValue);
SELECT ReturnValue;
```

首先声明变量ReturnValue来保存存储过程返回的任何值然后执行存储过程，再使用SELECT语句显示返回的值

Oracle支持IN(传递至给存储过程)、OUT(从存储过程返回值)、INOUT(既传递值给存储过程也从存储过程返回值)类型的参数。

SQL Server版本

```SQL
CREATE PROCEDURE function
AS
DECLARE @cnt INTEGER
SELECT @cnt - COUNT(*)
FROM table1
WHERE NOT column IS NULL;
RETURN @cnt;
```

此存储过程没有参数

调用语句为：

```SQL
DECLARE @ReturnValue INT
EXECUTE @ReturnValue = function;
SELECT @ReturnValue;
```

DECLARE语句声明了一个变量来保存存储过程返回的任何值，然后执行存储过程，再使用SELECT语句显示返回的值

例：

```SQL
CREATE PROCEDURE NewOrder @cust_id CHAR(10)
AS
DECLARE @order_num INTEGER  -- Declare variable for order number
SELECT @order_num=MAX(order_num)  -- Get current highest order number
FROM Orders
SELECT @order_num=@order_num+1  -- Determine next order number
INSERT INTO Orders(order_num, order_date, cust_id)  -- Insert new order
VALUES(@order_num, GETDATE(), @cust_id)
RETURN @order_num;  -- Return order number
```

此存储过程在Orders表中创建⼀个新订单。它只有⼀个参数，即下订单顾客的ID(@cust_id)。订单号(@order_num)和订单⽇期这两列在存储过程中⾃动⽣成。代码⾸先声明⼀个局部变量来存储订单号(@order_num)。接着，检索当前最⼤订单号(使⽤MAX()函数)并增加1(使⽤SELECT语句)。然后⽤INSERT语句插⼊由新⽣成的订单号、当前系统⽇期(⽤GETDATE()函数检索)和传递的顾客ID组成的订单。最后，⽤RETURN @order_num返回订单号

# 20. 管理事务处理(COMMIT、ROLLBACK)

## 20.1 事务处理

使⽤事务处理，通过确保成批的SQL操作要么完全执⾏，要么完全不执⾏，来维护数据库的完整性。

事务处理是⼀种机制，⽤来管理必须成批执⾏的SQL操作，保证数据库不包含不完整的操作结果。利⽤事务处理，可以保证⼀组操作不会中途停⽌，它们要么完全执⾏，要么完全不执⾏(除⾮明确指⽰)。如果没有错误发⽣，整组语句提交给(写到)数据库表；如果发⽣错误，则进⾏回退(撤销)，将数据库恢复到某个已知且安全的状态

术语：

* 事务(transaction)：指⼀组SQL语句

* 回退(rollback)：指撤销指定SQL语句的过程

* 提交(commit)：指将未存储的SQL语句结果写⼊数据库表

* 保留点(savepoint)：指事务处理中设置的临时占位符(placeholder)，可以对它发布回退(与回退整个事务处理不同)

SELECT语句(回退SELECT语句也没有必要)，也不能回退CREATE或DROP操作。事务处理中可以使⽤这些语句，但进⾏回退时，这些操作也不撤销

## 20.2 控制事务处理

管理事务的关键在于将SQL语句组分解为逻辑块，并明确规定数据何时应该回退，何时不应该回退

SQL Server语法(要求明确标识事务处理块的开始和结束)↓：

```SQL
BEGIN TRANSACTION
...
COMMIT TRANSACTION
```

在这个例子中，BEGIN TRANSACTION和COMMIT TRANSACTION语句之间的SQL必须完全执行或者完全不执行

MariaDB、MySQL语法↓：

```SQL
START TRANSACTION
...
```

Oracle语法↓：
```SQL
SET TRANSACTION
...
```

PostgreSQL使⽤ANSI SQL语法↓：

```SQL
BEGIN
...
```

多数实现没有明确标识事务处理在何处结束。事务⼀直存在，直到被中断。通常，COMMIT⽤于保存更改，ROLLBACK⽤于撤销

### 20.2.1 撤销/回退(ROLLBACK)

SQL的ROLLBACK命令⽤来回退(撤销)SQL语句

执行DELETE操作，然后用ROLLBACK语句撤销

```SQL
DELETE FROM table1;
ROLLBACK;
```

在事务处理块中，DELETE操作(与INSERT和UPDATE操作⼀样)并不是最终的结果

### 20.2.2 提交(COMMIT)

⼀般的SQL语句都是针对数据库表直接执⾏和编写的。这就是所谓的隐式提交(implicit commit)，即提交(写或保存)操作是⾃动进⾏的

在事务处理块中，提交不会隐式进⾏。不过，不同DBMS的做法有所不同。有的DBMS按隐式提交处理事务端，有的则不这样

SQL Server使用COMMIT语句进行明确的提交：

```SQL
BEGIN TRANSACTION
DELETE table1 WHERE column = data
DELETE table2 WHERE column = data
COMMIT TRANSACTION
```

在这个例子中，从数据库中完全删除data，因为涉及更新两个数据表，所以使用事务处理块。最后的COMMIt仅在不出错时更新。如果第一条DELETE起作用，但第二条失败，则DELETE不会提交

完成相同任务时Oracle的语法：

```SQL
SET TRANSACTION
DELETE table1 WHERE column = data;
DELETE table2 WHERE column = data;
COMMIT;
```

### 20.2.3 使用保留点

使⽤简单的ROLLBACK和COMMIT语句，就可以写⼊或撤销整个事务。但是，只对简单的事务才能这样做，复杂的事务可能需要部分提交或回退

要⽀持回退部分事务，必须在事务处理块中的合适位置放置占位符。这样，如果需要回退，可以回退到某个占位符。在SQL中，这些占位符称为保留点

每个保留点都要取能够标识它的唯⼀名字，以便在回退时，DBMS知道回退到何处

在MariaDB、MySQL和Oracle中创建保留点，可使⽤SAVEPOINT语句：

```SQL
SAVEPOINT savepoint;
```

在MariaDB、MySQL和Oracle中回退时的语法为↓：

```SQL
ROLLBACK TO savepoint;
```

在SQL Server中创建保留点语法为↓：

```SQL
SAVE TRANSACTION savepoint;
```

在SQL Server中回退时的语法为↓：

```SQL
ROLLBACK TRANSACTION savepoint;
```

SQL Server例子↓：

```SQL
BEGIN TRANSACTION

INSERT INTO Customers(cust_id, cust_name)
VALUES('1000000010', 'Toys Emporium');

SAVE TRANSACTION StartOrder;

INSERT INTO Orders(order_num, order_date, cust_id)
VALUES(20100,'2001/12/1','1000000010');

IF @@ERROR <> 0 ROLLBACK TRANSACTION StartOrder;

INSERT INTO OrderItems(order_num, order_item, prod_id, quantity,
item_price)
VALUES(20100, 1, 'BR01', 100, 5.49);

IF @@ERROR <> 0 ROLLBACK TRANSACTION StartOrder;

INSERT INTO OrderItems(order_num, order_item, prod_id, quantity,
item_price)
VALUES(20100, 2, 'BR03', 100, 10.99);

IF @@ERROR <> 0 ROLLBACK TRANSACTION StartOrder;

COMMIT TRANSACTION
```

这⾥的事务处理块中包含了4条INSERT语句。在第⼀条INSERT语句之后定义了⼀个保留点，因此，如果后⾯的任何⼀个INSERT操作失败，事务处理最近回退到这⾥。在SQL Server中，可检查⼀个名为@@ERROR的变量，看操作是否成功。(其他DBMS使⽤不同的函数或变量返回此信息)如果@@ERROR返回⼀个⾮0的值，表⽰有错误发⽣，事务处理回退到保留点。如果整个事务处理成功，发布COMMIT以保留数据

# 21. 使用游标

## 21.1 游标

结果集是SQL查询所检索出来的结果

有时，需要在检索出来的⾏中前进或后退⼀⾏或多⾏，这就是游标的⽤途所在。游标(cursor)是⼀个存储在DBMS服务器上的数据库查询，它不是⼀条SELECT语句，⽽是被该语句检索出来的结果集。在存储了游标之后，应⽤程序可以根据需要滚动或浏览其中的数据

Microsoft Access不⽀持游标，SQLite⽀持的游标称为步骤(step)，本课讲述的基本概念适⽤于SQLite的步骤，但语法可能完全不同

不同的DBMS⽀持不同的游标选项和特性。常⻅的⼀些选项和特性如下:

* 能够标记游标为只读，使数据能读取，但不能更新和删除

* 能控制可以执⾏的定向操作(向前、向后、第⼀、最后、绝对位置、相对位置等)

* 能标记某些列为可编辑的，某些列为不可编辑的

* 规定范围，使游标对创建它的特定请求(如存储过程)或对所有请求可访问

* 指⽰DBMS对检索出的数据(⽽不是指出表中活动数据)进⾏复制，使数据在游标打开和访问期间不变化

## 21.2 使用游标

使⽤游标涉及⼏个明确的步骤:

* 在使⽤游标前，必须声明(定义)它。这个过程实际上没有检索数据，它只是定义要使⽤的SELECT语句和游标选项

* ⼀旦声明，就必须打开游标以供使⽤。这个过程⽤前⾯定义的SELECT语句把数据实际检索出来。

* 对于填有数据的游标，根据需要取出(检索)各⾏。

* 在结束游标使⽤时，必须关闭游标，可能的话，释放游标(依赖于具体的DBMS)

声明游标后，可根据需要频繁地打开和关闭游标。在游标打开时，可根据需要频繁地执⾏取操作

### 21.2.1 创建游标(DECLARE)

DECLARE用来定义和命名游标，并定义相应的SELECT语句，根据需要带WHERE和其他⼦句

DB2、MariaDB、MySQL和SQL Server创建游标的语法：

```SQL
DECLARE cur CURSOR
FOR
SELECT * FROM table1
WHERE column IS NULL
```

Oracle和PostgreSQL创建游标的语法：

```SQL
DECLARE CURSOR cur
IS
SELECT * FROM table1
WHERE column IS NULL
```

### 21.2.2 使用游标(OPEN CURSOR)

使用OPEN CURSOR打开游标

```SQL
OPEN CURSOR cur
```

在处理OPEN CURSOR语句时，执⾏查询，存储检索出的数据以供浏览和滚动

### 21.2.3 访问游标数据(FETCH)

⽤FETCH语句访问游标数据了,FETCH指出要检索哪些⾏，从何处检索它们以及将它们放于何处(如变量名)

Oracle例：

```SQL
DECLARE TYPE cur IS REF CURSOR
  RETURN Customers%ROWTYPE;

DECLARE parameter Customers%ROWTYPE
BEGIN
  OPEN cur;
  FETCH cur INTO parameter;
  CLOSE cur;
END;
```

在这个例⼦中，FETCH⽤来检索当前⾏(⾃动从第⼀⾏开始)，放到声明的变量CustRecord中。对于检索出来的数据不做任何处理

Oracle例：

```SQL
DECLARE TYPE cur IS REF CURSOR
  RETURN Customers%ROWTYPE;

DECLARE parameter Customers%ROWTYPE
BEGIN
  OPEN cur;
  LOOP
  FETCH cur INTO parameter;
  EXIT WHEN cur%NOTFOUND;
  ...
  END LOOP;
  CLOSE cur;
END;
```

这个例⼦使⽤FETCH检索当前⾏，放到⼀个名为parameter的变量中。但不⼀样的是，这⾥的FETCH位于LOOP内，因此它反复执⾏。代码EXIT WHEN cur%NOTFOUND使在取不出更多的⾏时终⽌处理(退出循环)。这个例⼦也没有做实际的处理，实际例⼦中可⽤具体的处理代码替换占位符...

Microsoft SQL Server例：

```SQL
DECLARE @cust_id CHAR(10),
        @cust_name CHAR(50),
        @cust_address CHAR(50),
        @cust_city CHAR(50),
        @cust_state CHAR(5),
        @cust_zip CHAR(10),
        @cust_country CHAR(50),
        @cust_contact CHAR(50),
        @cust_email CHAR(255)
OPEN cur
FETCH NEXT FROM cur
  INTO @cust_id, @cust_name, @cust_address,
       @cust_city, @cust_state, @cust_zip,
       @cust_country, @cust_contact, @cust_email
WHILE @@FETCH_STATUS = 0

BEGIN
FETCH NEXT FROM cur
  INTO @cust_id, @cust_name, @cust_address,
       @cust_city, @cust_state, @cust_zip,
       @cust_country, @cust_contact, @cust_email
END
CLOSE cur
```

在此例中，为每个检索出的列声明⼀个变量，FETCH语句检索⼀⾏并保存值到这些变量中。使⽤WHILE循环处理每⼀⾏，条件`WHILE@@FETCH_STATUS=0`在取不出更多的⾏时终⽌处理(退出循环)。这个例⼦也不进⾏具体的处理，实际代码中，应该⽤具体的处理代码替换其中的...占位符

### 21.2.4 关闭游标

游标在使⽤完毕时需要关闭。此外，SQLServer等DBMS要求明确释放游标所占⽤的资源

DB2、Oracle和PostgreSQL关闭游标的语法：

```SLQ
CLOSE cur
```

MIcrosoft SQL Server关闭游标的语法：

```SQL
CLOSE cur
DEALLOCATE CURSOR cur
```

CLOSE语句⽤来关闭游标。⼀旦游标关闭，如果不再次打开，将不能使⽤。第⼆次使⽤它时不需要再声明，只需⽤OPEN打开它即可。

# 22. 高级SQL特性

## 22.1 约束

关系数据库存储分解为多个表的数据，每个表存储相应的数据。利⽤键来建⽴从⼀个表到另⼀个表的引⽤(由此产⽣了术语引⽤完整性(referentialintegrity))。正确地进⾏关系数据库设计，需要⼀种⽅法保证只在表中插⼊合法数据。

虽然可以在插⼊新⾏时进⾏检查(在另⼀个表上执⾏SELECT，以保证所有值合法并存在)，但最好不要这样做，原因如下：

* 如果在客⼾端层⾯上实施数据库完整性规则，则每个客⼾端都要被迫实施这些规则，⼀定会有⼀些客⼾端不实施这些规则

* 在执⾏UPDATE和DELETE操作时，也必须实施这些规则

* 执⾏客⼾端检查是⾮常耗时的，⽽DBMS执⾏这些检查会相对⾼效

约束：管理如何插入或处理数据库数据的规则

DBMS通过在数据库表上施加约束来实施引⽤完整性。⼤多数约束是在表定义中定义的，如⽤CREATE TABLE或ALTER TABLE语句

有⼏种不同类型的约束，每个DBMS都提供⾃⼰的⽀持

### 22.1.1 主键

主键是⼀种特殊的约束，⽤来保证⼀列(或⼀组列)中的值是唯⼀的，⽽且永不改动。换句话说，表中的⼀列(或多个列)的值唯⼀标识表中的每⼀⾏。这⽅便了直接或交互地处理表中的⾏。没有主键，要安全地UPDATE或DELETE特定⾏⽽不影响其他⾏会⾮常困难

表中任意列只要满⾜以下条件，都可以⽤于主键：

* 任意两⾏的主键值都不相同

* 每⾏都具有⼀个主键值(即列中不允许NULL值)

* 包含主键值的列从不修改或更新。(⼤多数DBMS不允许这么做，如果DBMS允许也不应修改或更新)

* 主键值不能重⽤。如果从表中删除某⼀⾏，其主键值不分配给新⾏

一种定义主键的方式是创建它，如下所示：

```SQL
CREATE TABLE table1
(
column1 CHAR(10)  NOT NULL PRIMARY KEY,
column2 CHAR(50)  NOT NULL,
column3 CHAR(20)  NULL,
column4 CHAR(80)  NULL
);
```

在此例子中给column1列定义关键字PRIMARY KEY，使其成为主键

```SQL
ALTER TABLE table1
ADD CONSTRAINT PRIMARY KEY (column);
```

这里定义相同的列为主键，但使用的是CONSTRAINT语法。此语法也可以用于CREATE TABLE和ALTER TABLE语句

SQLite不允许使⽤ALTER TABLE定义键，要求在初始的CREATE TABLE语句中定义它们

### 22.1.2 外键

外键是表中的⼀列，其值必须列在另⼀表的主键中。外键是保证引⽤完整性的极其重要部分

定义外键的方法：
```SQL
CREATE TABLE table1
(
  column1 INTEGER   NOT NULL  PRIMARY KEY,
  column2 DATETIME  NOT NULL,
  column3 CHAR(10)  NUT NULL  REFERENCES table2(column3)
);
```

其中的表定义使用了REFERENCES关键字，它表示column3中的任何值都必须是table2的column3中的值

相同的⼯作也可以在ALTER TABLE语句中⽤CONSTRAINT语法来完成

```SQL
ALTER TABLE table1
ADD CONSTRAINT
FOREIGN KEY (column1) REFERENCES table2 (column1)
```

除帮助保证引⽤完整性外，外键还有另⼀个重要作⽤。在定义外键后，DBMS不允许删除在另⼀个表中具有关联⾏的⾏

有的DBMS⽀持称为级联删除的特性。如果启⽤，该特性在从⼀个表中删除⾏时删除所有相关的数据

### 22.1.3 唯一约束

唯⼀约束⽤来保证⼀列(或⼀组列)中的数据是唯⼀的。它们类似于主键，但存在以下重要区别：

* 表可包含多个唯⼀约束，但每个表只允许⼀个主键

* 唯⼀约束列可包含NULL值

* 唯⼀约束列可修改或更新

* 唯⼀约束列的值可重复使⽤，如果从表中删除某⼀⾏，其值可以分配给新⾏

* 与主键不⼀样，唯⼀约束不能⽤来定义外键

唯⼀约束的语法类似于其他约束的语法。唯⼀约束既可以⽤`UNIQUE`关键字在表定义中定义，也可以⽤单独的`CONSTRAINT`定义

### 22.1.4 检查约束

检查约束⽤来保证⼀列(或⼀组列)中的数据满⾜⼀组指定的条件。检查约束的常⻅⽤途有以下⼏点：

* 检查最⼩或最⼤值。例如防⽌0个物品的订单(即使0是合法的数)

* 指定范围。例如保证发货⽇期⼤于等于今天的⽇期，但不超过今天起⼀年后的⽇期

* 只允许特定的值。例如在性别字段中只允许M或F

数据类型限制了列中可保存的数据的类型。检查约束在数据类型内⼜做了进⼀步的限制，这些限制极其重要，可以确保插⼊数据库的数据正是需要的数据。不需要依赖于客⼾端应⽤程序或⽤⼾来保证正确获取它，DBMS本⾝将会拒绝任何⽆效的数据

下面的例子对表table1施加了检查约束，它保证column1的数量>0：

```SQL
CREATE TABLE table1
(
  column1 INTEGER   NOT NULL CHECK (column1 > 0),
  column2 INTEGER   NOT NULL,
  column3 CHAR(10)  NOT NULL,
  column4 MONEY     NOT NULL,
);
```

利用这个约束，任何插入(或更新)的行都会被检查，保证column1 > 0

检查名为column的列只包含M或F，可编写如下的ALTER TABLE语句：

```SQL
ADD CONSTRAINT CHECK (column LIKE '[MF]')
```

有的DBMS允许⽤⼾定义⾃⼰的数据类型。它们是定义检查约束(或其他约束)的基本简单数据类型。然后可以将此数据类型⽤于表的定义。定制数据类型的优点是只需施加约束⼀次(在数据类型定义中)，⽽每当使⽤该数据类型时，都会⾃动应⽤这些约束

## 22.2 索引

索引⽤来排序数据以加快搜索和排序操作的速度

主键数据总是排序的，这是DBMS的⼯作。因此，按主键检索特定⾏总是⼀种快速有效的操作。但是，搜索其他列中的值通常效率不⾼。因为表中其他列数据并未按顺序排序，DBMS必须读出表中所有⾏(s从第⼀⾏开始)看其是否匹配。

可以在⼀个或多个列上定义索引，使DBMS保存其内容的⼀个排过序的列表。在定义了索引后，DBMS搜索排过序的索引，找出匹配的位置，然后检索这些⾏。

索引注意事项：

* 索引改善检索操作的性能，但降低了数据插⼊、修改和删除的性能。在执⾏这些操作时，DBMS必须动态地更新索引

* 索引数据可能要占⽤⼤量的存储空间

* 并⾮所有数据都适合做索引。取值不多的数据不如具有更多可能值的数据能通过索引得到那么多的好处

* 索引⽤于数据过滤和数据排序。如果你经常以某种特定的顺序排序数据，则该数据可能适合做索引

* 可以在索引中定义多个列(例如，州加上城市)。这样的索引仅在以州加城市的顺序排序时有⽤。如果想按城市排序，则这种索引没有⽤处

没有严格的规则要求什么应该索引，何时索引。⼤多数DBMS提供了可⽤来确定索引效率的实⽤程序，应该经常使⽤这些实⽤程序

索引⽤CREATE INDEX语句创建，下面的语句在table1表的column列上创建一个简单的索引：

```SQL
CREATE INDEX ind
ON table1 (column)
```

索引必须唯一命名，这里的索引名ind在关键字CREATE INDEX之后定义。ON用来指定被索引的表，而索引中包含的列(此例中仅有一列)在表名后的圆括号中给出

索引的效率随表数据的增加或改变⽽变化，最好定期检查索引，并根据需要对索引进⾏调整

## 22.3 触发器

触发器是特殊的存储过程，它在特定的数据库活动发⽣时⾃动执⾏。触发器可以与特定表上的INSERT、UPDATE和DELETE操作(或组合)相关联

与存储过程不⼀样(存储过程只是简单的存储SQL语句)，触发器与单个的表相关联。与table1表上的INSERT操作相关联的触发器只在table1表中插⼊⾏时执⾏。类似地，table2表上的INSERT和UPDATE操作的触发器只在表上出现这些操作时执⾏

触发器内的代码具有以下数据的访问权：

* INSERT操作中的所有新数据

* UPDATE操作中的所有新数据和旧数据

* DELETE操作中删除的数据

根据所使⽤的DBMS的不同，触发器可在特定操作执⾏之前或之后执⾏，不同DBMS的触发器创建语法差异很⼤

下⾯是触发器的⼀些常⻅⽤途：

* 保证数据⼀致。例如在INSERT或UPDATE操作中将所有州名转换为⼤写。

* 基于某个表的变动在其他表上执⾏活动。例如每当更新或删除⼀⾏时将审计跟踪记录写⼊某个⽇志表

* 进⾏额外的验证并根据需要回退数据。例如保证某个顾客的可⽤资⾦不超限定，如果已经超出，则阻塞插⼊

* 计算计算列的值或更新时间戳

创建⼀个触发器，对所有INSERT和UPDATE操作，将table1表中的column1列转换为⼤写：

SLQ Server语法：

```SQL
CREATE TRIGGER tri
ON table1
FOR INSERT, UPDATE
AS
UPDATE table1
SET column1 = Upper(column1)
WHERE table1.column2 = inserted.column2;
```

Oracle和PostgreSQL语法：

```SQL
CREATE TRIGGER tri
AFTER INSERT OR UPDATE
FOR EACH ROW
BEGIN
UPDATE table1
SET column1 = Upper(column1)
WHERE table1.column2 = :OLD.column2
END;
```

⼀般来说，约束的处理⽐触发器快，因此在可能的时候，应该尽量使⽤约束

## 22.4 数据库安全

⼤多数DBMS都给管理员提供了管理机制，利⽤管理机制授予或限制对数据的访问

任何安全系统的基础都是⽤⼾授权和⾝份确认。这是⼀种处理，通过这种处理对⽤⼾进⾏确认，保证他是有权⽤⼾，允许执⾏他要执⾏的操作。有的DBMS为此结合使⽤了操作系统的安全措施，⽽有的维护⾃⼰的⽤⼾及密码列表，还有⼀些结合使⽤外部⽬录服务服务器

⼀般说来，需要保护的操作有：

* 对数据库管理功能的访问(创建表、更改或删除已存在的表等)

* 对特定数据库或表的访问

* 访问的类型(只读、对特定列的访问等)

* 仅通过视图或存储过程对表进⾏访问

* 创建多层次的安全措施，从⽽允许多种基于登录的访问和控制

* 限制管理⽤⼾账号的能⼒

安全性使⽤SQL的`GRANT`和`REVOKE`语句来管理，不过，⼤多数DBMS提供了交互式的管理实⽤程序，这些实⽤程序在内部使⽤GRANT和REVOKE语句

# 附录：SQL语句的语法

约定：

* `|`符号⽤来指出⼏个选择中的⼀个，因此，`NULL | NOT NULL`表⽰或者给出NULL或者给出NOT NULL

* 包含在⽅括号中的关键字或⼦句(如`[like this]`)是可选的

* 下⾯列出的语法⼏乎对所有DBMS都有效。关于具体语法可能变动的细节需要参考DBMS⽂档

## 1. ALTER TABLE

ALTER TABLE⽤来更新已存在表的结构。为了创建新表，应该使⽤CREATE TABLE。详细信息，请参阅第17课

```SQL
ALTER TABLE table_name
(
  ADD|DROP column datatype [NULL|NOT NULL] [CONSTRAINTS],
  ADD|DROP column datatype [NULL|NOT NULL] [CONSTRAINTS],
  ...
);
```

## 2. COMMIT

COMMIT⽤来将事务写⼊数据库。详细内容请参阅第20课

```SQL
COMMIT [TRANSACTION];
```

## 3. CREATE INDEX

CREATE INDEX⽤于在⼀个或多个列上创建索引。详细内容请参阅第22课

```SQL
CREATE INDEX index_name
ON table_name (column, ...);
```

## 4. CREATE PROCEDURE

CREATE PROCEDURE⽤于创建存储过程。详细内容请参阅第19课，Oracle使用的语法稍有不同

```SQL
CREATE PROCEDURE procedure_name [parameters] [options]
AS
SQL statement;
```

## 5. CREATE TABLE

CREATE TABLE⽤于创建新数据库表。为了更新已经存在的表的结构，应该使⽤ALTER TABLE。详细内容请参阅第17课

```SQL
CREATE TABLE table_name
(
  column datatype [NULL|NOT NULL] [CONSTRAINTS],
  column datatype [NULL|NOT NULL] [CONSTRAINTS],
  ...
);
```

## 6. CREATE VIEW

CREATE VIEW⽤来创建⼀个或多个表上的新视图。详细内容请参阅第18课

```SQL
CREATE VIEW view_name AS
SELECT columns, ...
FROM tables, ...
[WHERE ...]
[GROUP BY ...]
[HAVING ...];
```

## 7. DELETE

DELETE从表中删除⼀⾏或多⾏。详细内容请参阅第16课

```SQL
DELETE FROM table_name
[WHERE ...];
```

## 8. DROP

DROP永久地删除数据库对象(表、视图、索引等)。详细内容请参阅第17、18课

```SQL
DROP INDEX|PROCEDURE|TABLE|VIEW
index_name|procedure_name|table_name|view_name;
```

## 9. INSERT

INSERT为表添加⼀⾏。详细内容请参阅第15课

```SQL
INSERT INTO table_name [(columns, ...)]
VALUES(values, ...);
```

## 10. INSERT SELECT

INSERT SELECT将SELECT的结果插⼊到⼀个表。详细内容请参阅第15课

```SQL
INSERT INTO table_name [(columns, ...)]
SELECT columns, ... FROM table_name, ...
[WHERE ...];
```

## 11. ROOLBACK

ROLLBACK⽤于撤销⼀个事务块。详细内容请参阅第20课

```SQL
ROLLBACK [ TO savepoint_name];
```

或

```SQL
ROLLBACK TRANSACTION;
```

## 12. SELECT

SELECT⽤于从⼀个或多个表(视图)中检索数据。更多的基本信息，请参阅第2、3、4课(2〜14课都与SELECT有关)

```SQL
SELECT column_name, ...
FROM table_name, ...
[WHERE ...]
[UNION ...]
[GROUP BY ...]
[HAVING ...]
[ORDER BY ...];
```

## 13. UPDATE

UPDATE更新表中的⼀⾏或多⾏。详细内容请参阅第16课

```SQL
UPDATE table_name
SET colum_name = value, ...
[WHERE ...];
```

# 附录：SLQ数据类型

数据类型是定义列中可以存储什么数据以及该数据实际怎样存储的基本规则

数据类型⽤于以下⽬的：

* 数据类型允许限制可存储在列中的数据。例如，数值数据类型列只能接受数值。

* 数据类型允许在内部更有效地存储数据。可以⽤⼀种⽐⽂本字符串更简洁的格式存储数值和⽇期时间值。

* 数据类型允许变换排序顺序。如果所有数据都作为字符串处理，则1位于10之前，⽽10⼜位于2之前(字符串以字典顺序排序，从左边开始⽐较，⼀次⼀个字符)。作为数值数据类型，数值才能正确排序

更改包含数据的列可能会导致数据丢失

不同DBMS的数据类型可能有很⼤的不同。在不同DBMS中，即使具有相同名称的数据类型也可能代表不同的东西。

## 1. 字符串数据类型

有两种基本的字符串类型，分别为定⻓字符串和变⻓字符串

定⻓字符串接受⻓度固定的字符串，其⻓度是在创建表时指定的。定⻓列不允许多于指定的字符数⽬。它们分配的存储空间与指定的⼀样多。如果存储的数据少于分配的存储空间，则缺少的字符用空格填充或根据需要补为NULL

变⻓字符串存储任意⻓度的⽂本(其最⼤⻓度随不同的数据类型和DBMS⽽变化)。有些变⻓数据类型具有最⼩的定⻓，⽽有些则是完全变⻓的。不管是哪种，只有指定的数据得以保存(额外的数据不保存)

DBMS处理定⻓列远⽐处理变⻓列快得多。此外，许多DBMS不允许对变⻓列(或⼀个列的可变部分)进⾏索引，这也会极⼤地影响性能

|字符串数据类型|说明|
|:-:|:-:|
|CHAR|1~255个字符的定⻓字符串。它的⻓度必须在创建时规定|
|NCHAR|CHAR的特殊形式，⽤来⽀持多字节或Unicode字符(此类型的不同实现变化很⼤)|
|TEXT(LONG/MEMO/VARCHAR)|变⻓⽂本|
|NVARCHAR|TEXT的特殊形式，⽤来⽀持多字节或Unicode字符(此类型的不同实现变化很⼤)|

不管使⽤何种形式的字符串数据类型，字符串值都必须括在单引号内

如果数值是计算(求和、平均等)中使⽤的数值，则应该存储在数值数据类型列中；如果作为字符串(可能只包含数字)使⽤，则应该保存在字符串数据类型列中

## 2. 数值数据类型

数值数据类型存储数值。多数DBMS⽀持多种数值数据类型，每种存储的数值具有不同的取值范围。显然，⽀持的取值范围越⼤，所需存储空间越多。此外，有的数值数据类型⽀持使⽤⼗进制⼩数点(和⼩数)，⽽有的则只⽀持整数

|数值数据类型|说明|
|:-:|:-:|
|BIT|单个⼆进制位值，或者为0或者为1，主要⽤于开/关标志|
|TINYINT|1字节整数值，⽀持0〜255的数|
|SMALLINT|2字节整数值，⽀持-32768〜32767的数|
|INT/INTEGER|4字节整数值，⽀持-2147483648〜2147483647的数|
|FLOAT/NUMBER|浮点值|
|REAL|4字节浮点值|
|DECIMAL/NUMERIC|定点或精度可变的浮点值|

并⾮所有DBMS都⽀持上表所列出的名称约定和描述

与字符串不⼀样，数值不应该括在引号内

多数DBMS⽀持⼀种⽤来存储货币值的特殊数值数据类型。⼀般记为MONEY或CURRENCY，这些数据类型基本上是有特定取值范围的DECIMAL数据类型，更适合存储货币值

## 3. 日期和时间数据类型

所有DBMS都⽀持⽤来存储⽇期和时间值的数据类型。与数值⼀样，多数DBMS都⽀持多种数据类型，每种具有不同的取值范围和精度。

|日期和时间数据类型|说明|
|:-:|:-:|
|TIME|时间值|
|DATE|⽇期值|
|DATETIME/TIMESTAMP|⽇期时间值|
|SMALLDATETIME|⽇期时间值，精确到分(⽆秒或毫秒)|

不存在所有DBMS都理解的定义⽇期的标准⽅法。多数实现都理解诸如2015-12-30或Dec 30th, 2015等格式，但即使这样，有的DBMS还是不理解它们。⾄于具体的DBMS能识别哪些⽇期格式，请参阅相应的⽂档

ODBC日期(开放数据库连接)：因为每种DBMS都有⾃⼰特定的⽇期格式，所以ODBC创建了⼀种⾃⼰的格式，在使⽤ODBC时对每种数据库都起作⽤。ODBC格式对于⽇期类似于{d '2005-12-30'}，对于时间类似于{t'21:46:29'}，⽽对于⽇期时间类似于{ts '2005-12-3021:46:29'}。如果通过ODBC使⽤SQL，应该以这种⽅式格式化⽇期和时间。

## 4. 二进制数据类型

⼆进制数据类型是最不具有兼容性的数据类型。⼆进制数据类型可包含任何数据，甚⾄可包含⼆进制信息，如图像、多媒体、字处理⽂档等

|二进制数据类型|说明|
|:-:|:-:|
|RAW/BINARY|定⻓⼆进制数据，最多255字节|
|BINARY|定⻓⼆进制数据(最⼤⻓度从255字节到8000字节，有赖于具体的实现)|
|VARBINARY|变⻓⼆进制数据(最⼤⻓度⼀般在255字节到8000字节间变化，依赖于具体的实现)|
|LONG RAW|变⻓⼆进制数据，最⻓2 GB|
