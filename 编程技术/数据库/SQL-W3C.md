
<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [1. SQL简介](#1-sql简介)
  - [1.1 SQL格式](#11-sql格式)
  - [1.2 主键](#12-主键)
    - [1.3 数据完整性](#13-数据完整性)
- [2. SQL语句结构](#2-sql语句结构)
  - [2.1 数据查询语言(DQL)](#21-数据查询语言dql)
    - [2.1.1 `SELECT`](#211-select)
    - [2.1.2 `WHERE`](#212-where)
    - [2.1.3 `ORDER BY`](#213-order-by)
    - [2.1.4 `GROUP BY`](#214-group-by)
    - [2.1.5 `HAVING`](#215-having)
    - [子查询](#子查询)
  - [2.2 数据操作语言(DML)](#22-数据操作语言dml)
    - [2.2.1 `INSERT`](#221-insert)
    - [2.2.2 `UPDATE`](#222-update)
    - [2.2.3 `DELETE`](#223-delete)
  - [2.3 事务处理语言(TPL)](#23-事务处理语言tpl)
  - [2.4 数据控制语言(DCL)](#24-数据控制语言dcl)
  - [2.5 数据定义语言(DDL)](#25-数据定义语言ddl)
    - [`CREATE`](#create)
    - [`ALTER`](#alter)
    - [`DROP`](#drop)
  - [2.6 指针控制语言(CCL)](#26-指针控制语言ccl)
  - [`JOIN`连接](#join连接)
  - [`UNION`组合](#union组合)
  - [`view`视图](#view视图)
  - [约束](#约束)
    - [创建/删除约束](#创建删除约束)
    - [`NOT NULL`约束](#not-null约束)
    - [`UNIQUE`约束](#unique约束)
    - [`PRIMARY KEY`约束](#primary-key约束)
    - [`FOREIGN KEY`约束](#foreign-key约束)
    - [`DEFAULT`约束](#default约束)
    - [`CHECK`约束](#check约束)
  - [索引](#索引)
  - [别名](#别名)
  - [事务](#事务)
  - [其他](#其他)
    - [`USE`](#use)
    - [序列](#序列)
    - [运算符](#运算符)
    - [表达式](#表达式)
    - [临时表](#临时表)
- [3. 数据类型](#3-数据类型)
  - [3.1 `NULL`](#31-null)
  - [3.2 通用数据类型](#32-通用数据类型)
- [4. SQL 函数](#4-sql-函数)
  - [`Date`函数](#date函数)
  - [Aggregate函数](#aggregate函数)
    - [`MAX()`函数](#max函数)
    - [`MIN()`函数](#min函数)
    - [`COUNT()`函数](#count函数)
    - [`AVG()`函数](#avg函数)
    - [`SUM()`函数](#sum函数)
    - [`FIRST()`函数](#first函数)
    - [`LAST()`函数](#last函数)
    - [`()`函数](#函数)
    - [`()`函数](#函数-1)
    - [`()`函数](#函数-2)
    - [`()`函数](#函数-3)
  - [Scalar函数](#scalar函数)
    - [`UPPER()`、`UCASE()`、`LOWER()`、`LCASE()`函数](#upper-ucase-lower-lcase函数)
    - [`MID()`函数](#mid函数)
    - [`LEN()`函数](#len函数)
    - [`ROUND()`函数](#round函数)
    - [`FORMAT()`函数](#format函数)
    - [`()`函数](#函数-4)
    - [`()`函数](#函数-5)
    - [`()`函数](#函数-6)
    - [`()`函数](#函数-7)
    - [`()`函数](#函数-8)
  - [其他函数](#其他函数)
    - [`FIELD()`函数](#field函数)
- [5. SQL 主机](#5-sql-主机)
- [6. SQL 总结](#6-sql-总结)

<!-- /code_chunk_output -->


# 1. SQL简介

[SQL-W2C教程网址](https://www.w3cschool.cn/sql/)

[SQL-W3C英文网址](https://www.w3schools.com/sql/default.asp)

* RDMS/RDBMS：关系数据库管理系统

## 1.1 SQL格式

* SQL缩进规范要求：
	* 使用空格来缩进
	* 每个缩进层次使用2个空格
	* 每行最多使用80个字符
	* 每个子句应该独占一行
	* 每个子句的参数应该缩进一个层次。

* [SQL格式化工具](https://www.w3cschool.cn/tools/index?name=sql_formatter)

## 1.2 主键

* 定义：主键是表中唯一标识表记录的字段

* 特征：
	* 主键必须包含唯一的值
	* 主键列不能包含`NULL`值

### 1.3 数据完整性

* 每个关系数据库管理系统都存在以下类型的数据完整性：
	* 实体完整性−表中没有重复行
	* 域完整性−通过限制值的类型、格式或范围来强制执行给定列的有效条目
	* 引用完整性−不能删除其他记录使用的行
	* 用户定义的完整性−强制执行一些不属于实体、域或引用完整性的特定业务规则

# 2. SQL语句结构

* SQL语法规则：
	* SQL语句总是以关键字开始，如`SELECT`、`INSERT`、`UPDATE`、`DELETE`、`DROP`、`CREATE`
	* SQL语句以分号结尾
	* SQL不区分大小写，意味着`update`与`UPDATE`相同。

## 2.1 数据查询语言(DQL)

* 数据查询语言用以从表中获得数据，确定数据怎样在应用程序给出，命令包括：`SELECT`、`WHERE`、`ORDER BY`、`GROUP BY`与`HAVING`

### 2.1.1 `SELECT`

* `SELECT`语法用于从数据库中选择数据，返回的数据存储在结果表中，称为结果集。语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name
	WHERE condition
	GROUP BY column_name
	HAVING condition
	ORDER BY column_name
	```

* `SELECT FROM`语法如下所示↓：
	```SQL
	-- 检索多列
	SELECT column_name1, column_name2, ...
	FROM table_name;

	-- 检索所有列
	SELECT *
	FROM table_name;
	```

* `SELECT DISTINCT`：消除重复记录，只返回不同的值。语法如下所示↓：
	```SQL
	SELECT DISTINCT column_name1, column_name2, ...
	FROM table_name
	WHERE [condition];
	```

* `SELECT TOP`：用于指定要返回的记录数值，返回大量记录会影响性能。语法如下所示↓：
	* 并不是所有的RDMS都支持`SELECT TOP`子句。MySQL支持`LIMIT`子句来选择有限数量的记录，Oracle使用`ROWNUM`子句
	```SQL
	-- SQL Server / MS Access
	SELECT TOP number|percent column_name
	FROM table_name
	WHERE condition;

	-- MySQL
	SELECT column_name
	FROM table_name
	WHERE condition
	LIMIT number;

	-- Oracle
	SELECT column_name
	FROM table_name
	WHERE ROWNUM <= number;
	```

* `SELECT INTO`：从一个表中复制数据，然后将数据插入到另一个表中。语法如下所示↓：
	* 将使用`SELECT`语句中定义的列名和类型创建新表，可以使用`AS`语句来应用一个新名称
	* 可以用于在另一种模式下创建一个新的空表，只需添加`WHERE`子句，使查询返回时没有数据
	```SQL
	-- 把所有列都复制到新表中
	SELECT *
	INTO newtable [IN externaldb]
	FROM table_name1;

	-- 只复制希望的列插入到新表中
	SELECT column_name
	INTO newtable [IN externaldb]
	FROM table_name1;

	-- 创建新的空表
	SELECT *
	INTO newtable
	FROM table_name1
	WHERE 1 = 0;
	```

### 2.1.2 `WHERE`

* `WHERE`：用于过滤记录，提取满足指定标准的记录。语法如下所示↓：
	```SQL
	-- SELECT
	SELECT DISTINCT column_name1, column_name2, ...
	FROM table_name;

	-- UPDATE
	UPDATE table_name
	SET column_name1 = [new value]
	WHERE condition;

	-- DELETE
	DELETE FROM table_name
	WHERE condition;
	```

### 2.1.3 `ORDER BY`

* `ORDER BY`：用于按升序或降序对结果集进行排序，默认情况下按升序排序记录，如果需要按降序对记录进行排序，可以使用`DESC`关键字。语法如下所示↓：
	* 可以在`ORDER BY`子句中使用多个列，但要确保用于对该列进行排序的列在列表中
	```SQL
	SELECT column_name1, column_name2, ...
	FROM table_name
	ORDER BY column_name1, column_name2, ... ASC|DESC;
	```

### 2.1.4 `GROUP BY`

* `GROUP BY`：通常与集合函数(如`COUNT`、`MAX`、`MIN`、`SUM`、`AVG`)一起使用，以按一个或多个列对结果集进行分组。语法如下所示↓：
	* `GROUP BY`语句用于结合`Aggregate`函数，根据一个或多个列对结果集进行分组
	```SQL
	SELECT column_name
	FROM table_name
	WHERE condition
	GROUP BY column_name
	ORDER BY column_name;
	```

### 2.1.5 `HAVING`

* `HAVING`：指定过滤条件，从而控制查询结果中哪些组可以出现在最终结果里面。语法如下所示↓：
	* 在SQL中添加`HAVING`子句原因是`WHERE`子句无法与`Aggregate`函数一起使用
	* `WHERE`子句对被选择的列施加条件；`HAVING`子句对`GROUP BY`子句所产生的组施加条件
	* `SELECT`查询中，`HAVING`子句必须跟随`GROUP BY`子句，在`ORDER BY`子句(如果有的话)之前
	```SQL
	SELECT column_name
	FROM table_name
	WHERE condition
	GROUP BY column_name
	HAVING condition
	ORDER BY column_name;
	```

### 子查询

* 子查询：又称为内查询或嵌套查询，是一种嵌套在其他SQL查询的`WHERE`子句中的查询
	* 用于为主查询返回其所需数据，或者对检索数据进行进一步的限制
	* 子查询可以在`SELECT`、`INSERT`、`UPDATE`和`DELETE`语句中，同`=`、`<`、`<=`、`>`、`>=`、`IN`、`BETWEEN`等运算符一起使用

* 子查询使用规则：
	* 子查询必须括在`()`中
	* 子查询的`SELECT`子句只能有一个列，除非主查询中有多个列，用于与子查询选中的列相比较
	* 子查询不能使用`ORDER BY`，不过主查询可以使用。在子查询中，`GROUP BY`可以起到同`ORDER BY`相同的作用
	* 返回多行数据的子查询只能同多值操作符一起使用，如`IN`操作符
	* `SELECT`列表中不能包含任何对`BLOB`、`ARRAY`、`CLOB`或`NCLOB`类型值的引用
	* 子查询不能直接用在聚合函数中
	* `BETWEEN`操作符不能同子查询一起使用，但是`BETWEEN`操作符可以用在子查询中

* `SELECT`语句中的子查询：通常情况下子查询都与`SELECT`语句一起使用。语法如下所示↓：
	```SQL
	SELECT column_name [, column_name2]
	FROM table_name [, table_name2]
	WHERE column_name OPERATOR
		(SELECT column_name [, column_name2]
		FROM table_name [, table_name2]
		[WHERE]);
	```

* `INSERT`语句中的子查询：`INSERT`语句可以将子查询返回的数据插入到其他表中。子查询中选取的数据可以被任何字符、日期或者数值函数所修饰。语法如下所示↓：
	```SQL
	INSERT INTO table_name [(column_name [, column_name2])]
		SELECT *|column_name [, column_name2]
		FROM table_name [, table_name2]
		[WHERE VALUE OPERATOR];
	```

* `UPDATE`语句中的子查询：当子查询同`UPDATE`一起使用的时候，既可以更新单个列，也可以更新多个列。语法如下所示↓：
	```SQL
	-- 语法示例似乎不太对
	UPDATE table_name
	SET column_name = new_value
	[WHERE OPERATOR [VALUE]
		(SELECT column_name
		FROM TABLE_NAME)
		[WHERE]];
	```

* `DELETE`语句中的子查询：语法示例如下↓：
	```SQL
	-- 语法示例似乎不太对
	DELETE FROM table_name
	[WHERE OPERATOR [VALUE]
		(SELECT column_name
		FROM table_name)
		[WHERE]]
	```

## 2.2 数据操作语言(DML)

* 数据操纵语言用于检索、插入和修改数据，数据操纵语言是最常见的SQL命令，命令如下所示↓：
	* `INSERT`：插入（创建记录）
	* `DELETE`：删除（删除记录）
	* `UPDATE`：修改（修改记录）
	* `SELECT`：检索（从一个或多个表检索某些记录）

### 2.2.1 `INSERT`

* `INSERT INTO`：用于向表中插入新的数据行，还可以使用`AND`或`OR`运算符组合多个条件。语法如下所示↓：
	```SQL
	-- 指定插入数据的列的名称
	INSERT INTO table_name (column_name1, column_name2, ...)
	VALUES (value1, value2, ...);

	-- 为表中所有的列添加值，则不需要指定列的名称
	-- 但是需要确保值的顺序与表中的列顺序相同
	INSERT INTO table_name
	VALUES (value1, value2, ...);

	-- 使用另一个表填充一个表
	-- 另一个表所查询的字段与本表要插入数据的字段需要一一对应
	INSERT INTO first_table_name [(column_name1, column_name2, ..., column_nameN)]
	SELECT column_name1, column_name2, ..., column_nameN
	FROM second_table_name
	[WHERE condition]
	```

* `INSERT INTO SELECT`：从表中复制数据，并将数据插入现有的表中。目标表中的任何现有行都不会受到影响。语法如下所示↓：
	```SQL
	INSERT INTO table_name2
	SELECT * FROM table_name1;

	INSERT INTO table_name2
	(column_name)
	SELECT column
	FROM table_name1;
	```

### 2.2.2 `UPDATE`

* `UPDATE`：用于更新表中已存在的记录，还可以使用`AND`或`OR`运算符组合多个条件。语法如下所示↓：
	* 如果省略`WHERE`子句，所有记录都将被更新
	```SQL
	UPDATE table_name
	SET column_name1 = value1, column_name2 = value2, ...
	WHERE condition;
	```

### 2.2.3 `DELETE`

* `DELETE`：用于删除表中现有记录。语法如下所示↓：
	* 如果省略`WHERE`子句，所有记录都将被删除，但是表的结构、属性和索引将保持不变
	```SQL
	DELETE FROM table_name
	WHERE condition;
	```

## 2.3 事务处理语言(TPL)

* 事务处理语言用于确保被DML语句影响的表的所有行及时得以更新，命令包括：`BEGIN TRANSACTION`、`COMMIT`与`ROLLBACK`

## 2.4 数据控制语言(DCL)

* 数据控制语言为用户提供权限控制命令，命令如下所示↓：
	* `GRANT`：授予权限
	* `REVOKE`：撤销已授予的权限

## 2.5 数据定义语言(DDL)

* 数据定义语言用于改变数据库结构，包括创建、更改和删除数据库对象，命令如下所示↓：
	* `CREATE TABLE`：创建(在数据库中创建新表、表视图或其他对象)
	* `ALTER TABLE`：更改(修改现有的数据库对象，如表)
	* `DROP TABLE`：删除(删除数据库中的整个表、表或其他对象的视图)

### `CREATE`

* `CREATE DATABASE`：创建数据库，在RDBMS中，数据库名称应该始终是唯一的。语法如下所示：`CREATE DATABASE dbname;`
	* 在创建任何数据库之前，需要确保自己拥有管理权限
	* 查看可用的数据库：`SHOW DATABASE`

* `CREATE TABLE`：创建数据库中的表，表由行和列组成，每个表都必须有个表名。语法如下所示↓：
	* `column_name`：列的名称
	* `data_type`：列的数据类型，如`varchar`、`integer`、`dicimal`、`date`等
	* `size`：列的最大长度
	```SQL
	CREATE TABLE table_name	(
		column_name1 data_type,
		column_name2 data_type,
		...
	);
	```

* `CREATE TABLE`无法原样拷贝数据表，因为复制表必须和原表拥有一样的索引、默认值等，拷贝数据表如下所示↓：
	* 方法一：使用MySQL关系型数据库管理系统时可以克隆数据表，步骤如下所示↓：
		1. 获取数据表的完整结构：使用`SHOW CERATE TABLE`获取指定了原表的结构、索引等信息的`CREATE TABLE`语句
		2. 改变表名，创建新表：将语句中的表名修改为克隆表的名字，然后执行该语句
		3. 执行`INSERT INTO ... SELECT`语句复制表中的数据
	* 方法二：语法如下所示↓：
		* `CREATE TABLE table_new_name LIKE table_old_name;`：创建新表，约束和原表相同，只拷贝表结构，没有拷贝表的数据
		* `CREATE TABLE table_new_name AS SELECT table_old_name;`：创建新表，没有原表的完整约束，会把原表的数据拷贝一份
	```SQL
	CREATE TABLE table_new_name LIKE table_old_name;
	INSERT INTO table_new_name SELECT * FROM table_old_name;
	-- 或
	CREATE TABLE table_new_name AS SELECT table_old_name;
	```

### `ALTER`

* `ALTER TABLE`：添加、删除或修改现有数据表中的列，也可以添加或删除现有数据表上的约束。语法如下所示↓：
	* 添加、删除现有数据表中的列：
	```SQL
	-- 添加
	ALTER TABLE table_name
	ADD column_name type_name;

	-- 删除
	ALTER TABLE table_name
	DROP COLUMN column_name;
	```
	* 更改现有数据表中列的数据类型：
	```SQL
	-- SQL Server、MS Access
	ALTER TABLE table_name
	ALTER COLUMN column_name type_name;
	-- MySQL、Oracle
	ALTER TABLE table_name
	MODIFY COLUMN column_name type_name;
	```
	* 对约束的修改见[约束](#约束)。删除约束语法如下所示↓：
	```SQL
	-- SQL Server、MS Access、Oracle
	ALTER TABLE table_name
	DROP CONSTRAINT constraint_name;
	-- MySQL
	ALTER TABLE table_name
	DROP INDEX constraint_name;
	```

### `DROP`

* `DROP`：用于删除索引、表和数据库

* `DROP INDEX`：删除表中的索引。语法如下所示↓：
	```SQL
	-- MS Access
	DROP INDEX index_name ON table_name;

	-- MS SQL Server
	DROP INDEX table_name.index_name;

	-- DB2/Oracle
	DROP INDEX index_name;

	--MySQL
	ALTER TABLE table_name DROP INDEX index_name;
	```

* `DROP TABLE`：删除现有表中的数据和表结构。语法如下所示：`DROP TABLE table_name;`

* `DROP VIEW`：删除视图。语法如下所示：`DROP VIEW view_name`

* `DROP DATABASE`：删除数据库。语法如下所示：`DROP DATABASE database_name;;

* `TRUNCATE TABLE`：只删除现有表中的数据，而不删除表本身。语法如下所示：`TRUNCATE TABLE table_name;`

## 2.6 指针控制语言(CCL)

* 指针控制语言用于对一个或多个表单独行的操作，命令包括：`DECLARE CURSOR`、`FETCH INTO`与`UPDATE WHERE CURRENT`

## `JOIN`连接

* `JOIN`基于多个表之间的共同字段，把多个表的行结合起来。即先确定一个主表作为结果集，然后把其他表的行有选择性地"连接"在主表结果集上
	* `INNER JOIN`：当两个表中都存在匹配时，才返回行
	* `LEFT JOIN`：即使右表中没有匹配，也从左表返回所有的行
	* `RIGHT JOIN`：即使左表中没有匹配，也从右表中返回所有的行
	* `FULL JOIN`：只要其中一个表中存在匹配，则返回行，既将左连接和右连接的结果组合在一起
	* `SELF JOIN`：用于将表连接到自己，就好像该表是两个表一样，临时重命名了SQL语句中的至少一个表
	* `CARTESIAN JOIN`：从两个或多个连接表返回记录集的笛卡尔积

* `INNER JOIN`：根据连接谓词来组合两个表中的字段，以创建一个新的结果表，选择两个表中具有匹配值的记录。语法如下所示↓：
	* `INNER JOIN`与`JOIN`是相同的
	* 如果表中至少有一个匹配项，`INNER JOIN`关键字将返回一行；如果两个表中不存在匹配行，则不会列出行
	```SQL
	-- 两张表
	SELECT column_name
	FROM table_name1
	INNER JOIN table_name2 ON table_name1.column_name = table_name2.column_name;

	-- 三张表
	SELECT column_name
	FROM ((tabel1
	INNER JOIN table_name2 ON table_name1.column_name1 = table_name2.column_name1)
	INNER JOIN table_name3 ON table_name1.column_name2 = table_name3.column_name2);
	```

* `LEFT JOIN`：返回左表(table_name1)中的所有行，即使在右表(table_name2)中没有匹配。如果在右表中没有匹配，结果是`NULL`。语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name1
	LEFT JOIN table_name2
	ON table_name1.column_name = table_name2.column_name;
	-- 或
	SELECT column_name
	FROM table_name1
	LEFT OUTER JOIN table_name2
	ON table_name1.column_name = table_name2.column_name;
	```

* `RIGHT JOIN`：返回右表(table_name2)中的所有行，即使在左表(table_name1)中没有匹配。如果在左表中没有匹配，结果是`NULL`。语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name1
	RIGHT JOIN table_name2
	ON table_name1.column_name = table_name2.column_name;
	-- 或
	SELECT column_name
	FROM table_name1
	RIGHT OUTER JOIN table_name2
	ON table_name1.column_name = table_name2.column_name;
	```

* `FULL OUTER JOIN`：当左表(table_name1)或右表(table_name2)记录匹配时，将返回所有记录，既将左连接和右连接的结果组合在一起。语法如下所示↓：
	* 返回左表和右表中所有的行，即使左表或右表中的行在另一个表中没有匹配项
	```SQL
	SELECT column_name
	FROM table_name1
	FULL JOIN table_name2
	ON table_name1.column_name = table_name2.column_name;
	-- 或
	SELECT column_name
	FROM table_name1
	FULL OUTER JOIN table_name2
	ON table_name1.column_name = table_name2.column_name;
	```
	* 如果数据库不支持全连接，如MySQL，那么可以使用`UNION ALL`子句来将左连接和右连接结果组合在一起，语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name1
	LEFT JOIN table_name2
	ON table_name1.column_name = table_name2.column_name
	UNION ALL
	SELECT column_name
	FROM table_name1
	RIGHT JOIN table_name2
	ON table_name1.column_name = table_name2.column_name
	```

* `CARTESIAN JOIN`：笛卡尔连接(交叉连接)返回两个或者更多的连接表中记录的笛卡尔乘积，既相当于连接谓词总是为真或者缺少连接谓词的内连接。语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name1, table_name2 [, table_name3]
	```

* `Self JOIN`：自连接，是一种常规的联接，但表本身是连接的。语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name1 T1, table_name1 T2;

## `UNION`组合

* `UNION`：用于组合两个或更多`SELECT`语句的结果集，而不返回任何重复的行。语法如下所示↓：
	* `UNION`中的每个`SELECT`语句必须具有相同的列数和数据类型
	* 每个`SELECT`语句中的列必须以相同的顺序排列
	* 每个`SELECT`语句必须由相同数目的列和列表达式
	* 每个`SELECT`语句的长度不必相同
	* `UNION`结果集中的列名总是等于`UNION`中第一个`SELECT`语句中的列名
	```SQL
	-- UNION
	SELECT column_name FROM table_name1
	UNION
	SELECT column_name FROM table_name2;

	SELECT column_name FROM table_name1
	[WHERE condition]
	UNION
	SELECT column_name FROM table_name2
	[WHERE condition];
	```

* `UNION ALL`：用于组合两个或更多`SELECT`语句(包括重复行)的结果集。语法如下所示↓：
	* 适用于`UNION`子句的相同规则同样适用于`UNION ALL`操作符
	```SQL
	-- UNION
	SELECT column_name FROM table_name1
	UNION ALL
	SELECT column_name FROM table_name2;

	SELECT column_name FROM table_name1
	[WHERE condition]
	UNION ALL
	SELECT column_name FROM table_name2
	[WHERE condition];
	```

* 类似`UNION`的子句
	* `INTERSECT`：组合两个`SELECT`语句，但只返回两个`SELECT`语句中都有的行
	* `EXCEPT`：组合两个`SELECT`语句，返回第一个`SELECT`语句中存在，但是第二个`SELECT`语句中不存在的行

## `view`视图

* 视图是基于SQL语句的结果集的可视化表
	* 视图包含行和列，就像真正的表一样
	* 视图可以创建自一个、多个表或其他视图
	* 视图中的字段是一个或多个数据库中真实表中的字段
	* 视图总是显示最新数据。每当用户查询视图时，数据库引擎就使用视图的SQL语句重新构建数据
	* 在使用时，可以将视图视为一个"虚拟表"

* 视图允许的操作：
	* 以用户或者某些类型的用户感觉自然或者直观的方式来组织数据
	* 限制对数据的访问，从而使得用户能够看到或者修改(某些情况下)他们需要的数据
	* 从多个表中汇总数据，以产生报表

* `CREATE VIEW`：创建视图。语法如下所示↓：
	```SQL
	CREATE VIEW view_name AS
	SELECT column_name
	FROM table_name
	[WHERE condition];
	```

* `WITH CHECK OPTION`：该指令是`CREATE VIEW`语句的一个可选项，用于保证所有的`UPDATE`和`INSERT`语句都满足视图定义中的条件。如果不能满足这些条件，`UPDATE`或`INSERT`就会返回错误。语法如下所示↓：
	```SQL
	CREATE VIEW view_name AS
	SELECT column_name
	FROM table_name
	WHERE condition
	WITH CHECK OPTION;
	```

* 更新视图：在视图上也可以使用修改数据的DML语句，如`INSERT`、`UPDATE`、`DELETE`。语法如下所示↓：
	* 视图可以在特定的情况下更新，更新限制如下所示↓：
		* `SELECT`语句中不能包含`DISTINCT`关键字、不能包含任何汇总(summary)函数、不能包含任何集合(set)函数、不能包含任何集合(set)运算符、不能包含`ORDER BY`子句
		* 视图中不能包含连接操作符、不能包含伪列或表达式
		* `FROM`子句中不能有多个数据表
		* `WHERE`子句中不能包含子查询
		* 查询语句中不能有`GROUP BY`或`HAVING`
		* 计算得出的列不能更新
		* 视图必须包含原始数据表中所有的`NOT NULL`列，从而使`INSERT`查询生效
	* 更新视图后，最终更新的还是原始数据表，只是其结果反应在了视图上
	```SQL
	-- 更新数据
	UPDATE view_name
	SET column_name = value_name
	WHERE condition;

	-- 插入新行
	INSERT INTO view_name
	VALUES (value_name1, value_name2, ...);

	-- 删除视图中的行
	DELETE FROM view_name
	WHERE condition;
	```

* `DROP VIEW`：删除视图。语法如下所示：`DROP VIEW view_name`

## 约束

* 约束时作用域数据表中列上的规则，用于限制表中数据的类型。约束的存在保证了数据库中数据的精确性和可靠性。约束有列级和表级之分，列级约束作用于单一的列，而表级约束作用与整张数据表

* 常用约束
	* `NOT NULL`：保证列中数据不能有`NULL`值
	* `DEFAULT`：提供该列数据未指定时所采用的默认值
	* `UNIQUE`：保证列中的所有数据各不相同
	* 主键约束：唯一标识数据表中的行/记录
	* 外键约束：唯一标识其他表中的一条行/记录
	* `CHECK`约束：保证列中的所有值满足某一条件
	* 索引：用于在数据库中快速创建或检索数据

* 完整性约束：用于保证关系型数据库中数据的精确性和唯一性
	* 对于关系型数据库来说，数据完整性由参照完整性(Referential integrity, RI)来保证
	* 有很多种约束可以起到参照完整性的作用，这些约束包括主键约束、外键约束、唯一性约束以及上面提到的其他约束

### 创建/删除约束

* 创建约束：当使用`CREATE TABLE`语句创建表时，或者在使用`ALTER TABLE`语句创建表之后，可以指定约束。语法如下所述↓：
	```SQL
	CREATE TABLE table_name (
		column_name1 type_name constraint,
		column_name2 type_name constraint,
		...
	);

	CREATE TABLE table_name (
		column_name type_name(size) constraint_name,
		column_name type_name(size) constraint_name,
		...
	)
	```

* 删除约束：任何现有约束都可以通过在`ALTER TABLE`命令中指定`DROP CONSTRAINT`选项的方法删除掉。语法如下所示↓：
	```SQL
	-- 去除主键约束
	ALTER TABLE table_name DROP CONSTRAINT EMPLOYEES_PK;

	-- Oracle中删除主键
	ALTER TABLE table_name DROP PRIMARY KEY;
	```

### `NOT NULL`约束

* `NOT NULL`：在默认的情况下，表的列接受`NULL`值，`NOT NULL`约束强制列不接受`NULL`值。语法如下所示↓：
	* `NOT NULL`约束强制字段始终包含值，即如果不向字段添加值，就无法插入新纪录或者更新记录
	```SQL
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL,
		column_name2 type_name,
		...
	);

	-- 添加 NOT NULL 约束
	ALTER TABLE table_name
	MODIFY column_name type_name NOT NULL;
	```

### `UNIQUE`约束

* `UNIQUE`约束：唯一标识数据库表中的每条记录
	* `UNIQUE`和`PRIMARY KEY`约束均为列或集合提供了唯一性的保证，`PRIMER kEY`约束拥有自动定义的`UNIQUE`约束。每个表可以有多个`UNIQUE`约束，但是每个表只能有一个`PRIMER KEY`约束
	* `CREATE TABLE`时的`UNIQUE`约束语法如下所示↓：
	```SQL
	-- MySQL
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL,
		column_name2 type_name,
		...
		UNIQUE (column_name1)
	);

	-- SQL Server、Oracle、MS Access
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL UNIQUE,
		column_name2 type_name NOT NULL,
		...
	);

	-- 命名并定义多个列的 UNIQUE 约束如下所示
	-- MySQL、SQL Server、Oracle、MS Access
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL,
		column_name2 type_name,
		...
		CONSTRAINT constraint_name UNIQUE (column_name1, column_name2)
	);
	```
	* `ALTER TABLE`时的`UNIQUE`约束语法如下所示↓：
	```SQL
	-- 单列
	ALTER TABLE table_name
	ADD UNIQUE (column_name);

	-- 多列
	ALTER TABLE table_name
	ADD CONSTRAINT constraint_name UNIQUE (column_name1, column_name2);
	```
	* 删除`UNIQUE`约束语法如下所示↓：
	```SQL
	-- MySQL
	ALTER TABLE table_name
	DROP INDEX constraint_name;

	-- SQL Server、Oracle、MS Access
	ALTER TABLE table_name
	DROP CONSTRAINT constraint_name;
	```

### `PRIMARY KEY`约束

* `PRIMARY KEY`：唯一标识数据库表中的每条记录
	* 主键必须包含唯一的值，主键列不能包含`NULL`值，每个表都应该有一个主键，并且每个表只能有一个主键
	* `CREATE TABLE`时的`PRIMARY KEY`约束语法如下所示↓：
	```SQL
	-- MySQL
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL,
		column_name2 type_name,
		...
		PRIMARY KEY (column_name)
	);

	-- SQL Server、Oracle、MS Access
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL PRIMARY KEY,
		column_name2 type_name NOT NULL,
		...
	);

	-- 命名并定义多个列的 PRIMARY KEY 约束如下所示
	-- MySQL、SQL Server、Oracle、MS Access
	CREATE TABLE table_name (
		column_name1 NOT NULL,
		column_name2,
		...
		CONSTRAINT primary_key_name PRIMARY KEY (column_name1, column_name2)
	);
	-- 上述实例中只有一个主键 primary_key_name，但是 primary_key_name 的值是由两个列组成的
	```
	* `ALTER TABLE`时的`PRIMARY KAY`约束语法如下所示↓：
		* 如果使用`ALTER TABLE`语句添加主键，必须把主键声明为不包含`NULL`值(在表首次创建时设置)
	```SQL
	-- 单列
	ALTER TABLE table_name
	ADD PRIMARY KEY (column_name);

	-- 多列
	ALTER TABLE table_name
	ADD CONSTRAINT primary_key_name PRIMARY KEY (column_name1, column_name2);
	```
	* 撤销`PRIMARY KEY`约束语法如下所示↓：
	```SQL
	-- MySQL
	ALTER TABLE table_name
	DROP PRIMARY KEY;

	-- SQL Server、Oracle、MS Access
	ALTER TABLE table_name
	DROP CONSTRAINT primary_key_name;
	```

### `FOREIGN KEY`约束

* `FOREIGN KEY`：一个表中的外键`FOREIGN KEY`指向另一个表中的主键`PRIMARY KAY`，用于防止破坏表之间连接的行为，也能防止非法数据插入外键列，因为它必须是它指向的那个表中的值之一
	* `CREATE TABLE`时的`FOREIGN KEY`约束语法如下所示↓：
	```SQL
	-- MySQL
	CREATE TABLE table_name1 (
		column_name1 type_name NOT NULL,
		column_name2 type_name,
		...
		PRIMARY KEY (column_name1),
		FOREIGN KEY (column_name2) REFERENCES table_name2(table_name2_column_name)
	);

	-- SQL Server、Oracle、MS Access
	CREATE TABLE table_name1 (
		column_name1 type_name NOT NULL PRIMARY KEY,
		column_name2 type_name FOREIGN KEY REFERENCES table_name2(table_name2_column_name),
		...
	);

	-- 命名并定义多个列的 FOREIGN KEY 约束如下所示
	-- MySQL、SQL Server、Oracle、MS Access
	CREATE TABLE table_name1 (
		column_name1 type_name NOT NULL,
		column_name2 type_name,
		...
		PRIMARY KEY (column_name1),
		CONSTRAINT pri
		FOREIGN KEY (column_name2) REFERENCES table_name2(table_name2_column_name)
	);
	-- 上述实例中只有一个外键 foreign_key_name，但是 foreign_key_name 的值是由两个列组成的
	```
	* `ALTER TABLE`时的`FOREIGN KAY`约束语法如下所示↓：
	```SQL
	-- 单列 MySQL、SQL Server、Oracle、MS Access
	ALTER TABLE table_name1
	ADD FOREIGN KEY (column_name)
	REFERENCES table_name2(table_name2_column_name)

	-- 多列 MySQL、SQL Server、Oracle、MS Access
	ALTER TABLE table_name1
	ADD CONSTRAINT foreign_name
	FOREIGN KEY (column_name)
	REFERENCES table_name2(table_name2_column_name)
	```
	* 撤销`FOREIGN KEY`约束语法如下所示↓：
	```SQL
	-- MySQL
	ALTER TABLE table_name
	DROP FOREIGN KEY foreign_name

	-- SQL Server、Oracle、MS Access
	ALTER TABLE table_name
	DROP CONSTRAINT foreign_name
	```

### `DEFAULT`约束

* `DEFAULT`：向列中插入默认值，如果没有规定其他的值，那么会将默认值添加到所有的新纪录
	* `CREATE TABLE`时的`DEFAULT`约束语法如下所示↓：
	```SQL
	-- MySQL、SQL Server、Oracle、MA Access
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL,
		column_name2 type_name DEFAULT default_value,	-- 直接设置值
		column_name3 type_name DEFAULT GETDATE(),		--使用函数
		...
	);
	```
	* `ALTER TABLE`时的`DEFAULT`约束语法如下所示↓：
	```SQL
	-- MySQL
	ALTER TABLE table_name
	ALTER column_name SET DEFAULT default_value;

	-- SQL Server、MS Access
	Alter TABLE table_name
	ADD CONSTRAINT default_column_name DEFAULT(default_value) FOR column_name;

	-- Oracle
	ALTER TABLE table_name
	MODIFY column_name DEFAULT default_value;
	```
	* 撤销`DEFAULT`约束语法如下所示↓：
	```SQL
	-- MySQL
	ALTER TABLE table_name
	ALTER column DROP DEFAULT;

	-- SQL Server、Oracle、MS Access
	ALTER TABLE table_name
	ALTER COLUMN column_name DROP DEFAULT;
	```

### `CHECK`约束

* `CHECK`：限制列中的值的范围
	* 如果对单个列定义`CHECK`约束那么该列只允许特定的值
	* 如果对一个表定义`CHECK`约束，那么此约束会基于行中其他列的值，在特定的列中对值进行限制
	* `CREATE TABLE`时的``约束语法如下所示↓：
	```SQL
	-- MySQL
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL,
		...
		CHECK (condition_expression)
	);

	-- SQL Server、Oracle、MS Access
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL CHECK (condition_expression),
		...
	);

	-- 命名并定义多个列的 CHECK 约束如下所示
	-- MySQL、SQL Server、Oracle、MS Access
	CREATE TABLE table_name (
		column_name type_name NOT NULL,
		...
		CONSTRAINT check_name CHECK (condition_expression1 AND condition_expression2)
	);
	```
	* `ALTER TABLE`时的``约束语法如下所示↓：
	```SQL
	-- 单列
	ALTER TABLE table_name
	ADD CHECK (condition_expression);

	-- 多列
	ALTER TABLE table_name
	ADD CONSTRAINT check_name CHECK (condition_expression1 AND condition_expression2);
	```
	* 撤销``约束语法如下所示↓：
	```SQL
	-- MySQL
	ALTER TABLE table_name
	DROP CHECK check_name

	-- SQL Server、Oracle、MS Access
	ALTER TABLE table_name
	DROP CONSTRAINT check_name
	```

## 索引

* 索引是一种特殊的查询表，可以被数据库引擎用来借宿数据的检索
	* 索引就是指向表中数据的指针
	* 索引能够提高`SELECT`查询和`WHERE`子句的速度，但是会降低包含`UPDATE`语句或`INSERT`语句的数据输入过程的速度
	* 索引的创建与删除不会对表中的数据产生影响
	* 与`UNIQUE`约束相同，索引可以是唯一的，这种情况下，索引会阻止列中(或者列的组合，其中某些列有索引)出现重复的条目

* `CREATE INDEX`：创建索引，允许对索引命名，指定要创建索引的表以及对哪些列进行索引，还可以指定索引按照升序或者降序排列。语法如下所示↓：
	* 单列索引：基于单一的字段创建
	* 唯一索引：不止用于提升查询性能，还用于保证数据完整性。唯一索引不允许向表中插入任何重复值
	* 隐式索引：由数据库服务器在创建某些对象的时候自动生成，例如对于主键约束和唯一约束，数据库服务器会自动创建索引
	```SQL
	CREATE INDEX index_name ON table_name;
	-- 多列
	CREATE INDEX index_name
	ON table_name(column_name1, column_name2);

	-- 单列索引
	CREATE INDEX index_name
	ON table_name(column_name);

	-- 唯一索引
	CREATE UNIQUE INDEX index_name
	ON table_name(column_name);
	```

* `DROP INDEX`：删除索引，删除索引可能会降低或提高数据库性能。语法如下所示↓：
	```SQL
	DROP INDEX table_name.index_name;
	```

* 避免使用索引的情况：
	* 小的数据表不应当使用索引
	* 需要频繁进行大批量的更新或者插入操作的表
	* 列中包含大数或`NULL`值
	* 频繁操作的列不宜创建索引

## 别名

* 可以为表名称或列名称指定别名。语法如下所示↓：
	* 如果别名包含空格，则需要双引号或方括号
	* 别名用于为表或表中的列提供临时名称，数据库中的实际表名不会更改
	* 别名通常用于使列名更具可读性
	* 一个别名值存在于查询期间
	* 表别名的使用是在特定SQL语句中重命名表
	* 列别名用于为特定SQL查询重命名表的列
	* 使用别名的场景：查询涉及多个表、用于查询函数、需要把两个或更多的列放在一起、列名长或可读性差
	```SQL
	-- 列的别名
	SELECT column_name AS alias_name
	FROM table_name
	WHERE [condition];

	-- 表的别名
	SELECT column_name
	FROM table_name AS alias_name
	WHERE [condition];
	```

## 事务

* 事务：在数据库上按照一定的逻辑顺序执行的任务列表，既可以由用户手动执行，也可以由某种数据库程序自动执行
	* 事务实际上就是对数据库的一个或者多个更改。在某张表上创建更新或者删除记录就是在使用事务
	* 控制事务以保证数据完整性，并对数据库做出处理，对数据库来说非常重要
	* 通常将多个SQL查询组合在一起，并将其作为某个事务一部分来执行

* 事务的属性：
	* 原子性：保证任务中的所有操作都执行完毕；否则，事务会在出现错误时终止，并回滚之前所有操作到原始状态
	* 一致性：如果事务成功执行，则数据库的状态进行了正确的转变
	* 隔离性：保证不同的事务相互独立、透明地执行
	* 持久性：即使出现系统故障，之前成功执行的事务的结果也会持久存在

* 事务控制：有四个命令用于控制事务
	* `COMMIT`：提交更改
	* `ROLLBACK`：回滚更改
	* `SAVEPOINT`：在事务内部创建一系列可以`ROLLBACK`的还原点
	* `SET TRANSACTION`：命名事务

* `COMMIT`：用于保存事务对数据库所做的更改。该命令会将上次`COMMIT`命令或者`ROLLBACK`命令执行以来所有的事务都保存到数据库中。语法为：`COMMIT;`

* `ROLLBACK`：撤销尚未保存到数据库中的事务。该指令只能撤销自上次`COMMIT`命令或者`ROLLBACK`命令执行以来的事务。语法为：`ROLLBACK;`

* `SAVEPOINT`：事务中的一个状态点，使得可以将事务回滚至特定的点，而不是将整个事务都撤销。语法如下所示↓：
	```SQL
	-- 创建保存点
	SAVEPOINT savepoint_name;

	-- 回滚至某一保存点
	ROLLBACK TO savepoint_name;

	-- 删除先前创建的保存点
	RELEASE SAVEPOINT savepoint_name;
	```

* `SET TRANSACTION`：初始化数据库事务，指定随后的事务的各种特征。语法为：`SET TRANSACTION [READ WRITE | READ ONLY];`

## 其他

### `USE`

* `USE`：用于选择SQL架构中的任何现有数据库。当SQL Schema中有多个数据库时，在开始操作之前，需要选择一个执行所有操作的数据库。语法如下所示↓：`USE DatabaseName`。数据库名称在RDBMS中必须是唯一的
	* 查看可用的数据库：`SHOW DATABASES`

### 序列

* 序列：根据需要产生的一组有序整数(1,2,3...)
	* 序列在数据库中经常用到，因为许多应用要求数据表中的每一行都有一个唯一的值

* `AUTO_INCREMENT`：自动增量字段，在新记录插入表中时生成一个唯一的数字。语法如下所示↓：
	* 默认情况下，`AUTO_INCREMENT`的初始值为1，每个新纪录增加1
	* 在为表`table_name`插入记录时，不需要为`column_name1`指定值，会自动添加唯一值
	```SQL
	-- MySQL
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL AUTO_INCREMENT,
		column_name2 type_name,
		...
		PRIMARY KEY (column_name1)
	);
	-- 修改初始值
	ALTER TABLE column_name1 AUTO_INCREMENT = start_num;
	-- 指定初始值
	CREATE TABLE table_name (
		column_name1 type_name NOT NULL AUTO_INCREMENT = start_num;
		column_name2 type_name,
		...
		PRIMARY KEY (column_name1)
	);

	-- 对列重新定序
	ALTER TABLE INSECT DROP column_name1;
	ALTER TABLE insect
	ADD column_name1 type_name NOT NULL AUTO_INCREMENT FIRST,
	ADD PRIMARY KEY(column_name1);

	-- SQL Server
	CREATE TABLE table_name (
		column_name1 type_name IDENTITY(start_num, stride_num) PRIMARY KEY,
		column_name2 type_name NOT NULL,
		...
	);

	-- Access
	CREATE TABLE table_name (
		column_name1 PRIMARY KEY AUTOINCREMENT(start_num, stride_num),
		column_name2 type_name NOT NULL,
		...
	);
	```

* 在Oracle中，需要使用序列对象(生成数组序列)创建自动增量字段。语法如下所示↓：
	* `CREATE SEQUENCE`：创建序列对象
	```SQL
	CREATE SEQUENCE seq_name
	MINVALUE min_value
	START WITH start_num
	INCREMENT BY stride_num
	CACHE cache_num;
	```
	* `nextval`：检索下一个值
	```SQL
	INSERT INTO table_name (column_name1, column_name2)
	VALUES (seq_name.nextval, value2)

### 运算符

* 运算符主要用于`WHERE`子句中

* 算数运算符如下所示↓：

|算数运算符|描述|
|:-:|:-:|
|`+`|加法|
|`-`|减法|
|`*`|乘法|
|`/`|除法|
|`%`|取余|

* 比较运算符如下所示↓：

|比较运算符|描述|
|:-:|:-:|
|`=`|等于|
|`!=`或`<>`|不等于|
|`>`|大于|
|`>=`|大于等于|
|`!>`|不大于|
|`<`|小于|
|`<=`|小于等于|
|`!<`|不小于|

* 逻辑运算符如下所示↓：

|逻辑运算符|描述|
|:-:|:-:|
|`ALL`|将值与另一个值集中的所有值进行比较|
|`AND`|在`WHERE`子句中指定多个条件|
|`ANY`|根据条件将值与列表中的任何适用值进行比较|
|`BETWEEN`|搜索在给定最小值和最大值内的值|
|`EXISTS`|搜索指定表中是否存在满足特定条件的行|
|`IN`|将值与给定的文字值列表进行比较|
|`LIKE`|适用通配符运算符将值与类似值进行比较|
|`NOT`|否定运算符|
|`OR`|组合`WHERE`子句中的多个条件|
|`IS NULL`|将值与`NULL`进行比较|
|`UNIQUE`|搜索指定表的每一行的唯一性(无重复项)|

* `AND`、`OR`与`NOT`运算符：`AND`与`OR`用于根据一个以上的条件过滤记录，组合多个条件以缩小SQL语句中的数据；`NOT`用于对条件取反。语法如下所示↓：
	```SQL
	-- AND
	SELECT column_name1, column_name2, ...
	FROM table_name
	WHERE condition1 AND condition2 AND condition3 ...;

	-- OR
	SELECT column_name1, column_name2, ...
	FROM table_name
	WHERE condition1 OR condition2, OR condition3 ...;

	-- NOT
	SELECT column_name1, column_name2, ...
	FROM table_name
	WHERE NOT condition;
	```

* `LIKE`：在`WHERE`子句中搜索列中的指定模式。语法如下所示↓：
	* 有两个通配符与`LIKE`一起使用
		* `%`：匹配零个、一个或多个字符(在MS Access中使用`*`而不是`%`)
		* `_`：匹配一个字符(在MS Access中使用`?`而不是`_`)
		* 在MS Access和SQL Server中可以使用`[charlist]`定义要匹配的字符的集合和范围；使用`[^charlist]`或`[!charlist]`定义不匹配字符的集合和范围
	* 可以使用功能`AND`或`OR`组合任意数量的条件
	```SQL
	SELECT column_name
	FROM table_name
	WHERE column_nameN LIKE pattern;
	```

* `IN`：在`WHERE`子句中指定多个值，是多个`OR`条件的简写。语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name
	WHERE column_name IN (value1, value2, ...);

	SELECT column_name
	FROM table_name
	WHERE column_name IN (SELECT STATEMENT);
	```

* `BETWEEN`：选取介于两个值之间的数据范围内的值，值可以是数字、文本或日期。在不同的数据库中，`BETWEEN`区间的开闭情况是不同的。语法如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name
	WHERE column_name [NOT] BETWEEN value1 AND value2;
	```

### 表达式

* 表达式是计算值的一个或多个值、运算符和SQL函数的组合，也可以使用表达式查询数据库中的特定数据集

* 布尔表达式：基于匹配单个数值获取数据。示例如下所示↓：
	```SQL
	SELECT column_name
	FROM table_name
	WHERE values = 100;
	```

* 数值表达式：在任何查询中执行任何数学运算。示例如下所示↓：`SELECT (15 + 6) AS ADDITION`

* 日期表达式：返回当前系统日期和时间值。示例如下所示↓：`SELECT CURRENT_TIMESTAMP;`、`SELECT GETDATE();;`


### 临时表

* 临时表：某些RDMS支持临时表，能够像操作普通的SQL数据表一样，使用`SELECT`、`UPDATE`、`JOIN`等功能来存储或操作中间结果，有时候对保存临时数据非常有用。语法如下所示↓：
	* 临时表只在会话期间存在，如果在PHP中操作数据库，那么临时表将在脚本执行完毕时自动销毁；如果通过MySQL的客户端连接到MySQL数据库服务器，那么临时表将会存在到关闭客户端或者手动将其删除
	* 当使用`SHOW TABLE`命令时，临时表不会出现在结果列表中
	```SQL
	-- MySQL
	CREATE TEMPORARY TABLE table_name (
		column_name1 data_type(size),
		column_name2 data_type(size) DEFAULT value_name,
		...
	);

	DROP TABLE table_name;
	```

# 3. 数据类型

## 3.1 `NULL`

* `NULL`：用于表示缺失的值，表中的`NULL`值表示该值所处的字段为空
	* 如果表中的字段是可选的，则可以插入新纪录或更新记录而不向该字段添加值，随后该字段将被保存为`NULL`
	* `NULL`的处理与其他值不同，无法比较`NULL`与`0`

* 测试`NULL`：使用`IS NULL`与`IS NOT NULL`。语法如下所示↓：
	* 注意不可以使用比较运算符
	```SQL
	SELECT column_name
	FROM table_name
	WHERE column_name IS [NOT] NULL;
	```

* 创建表时，`NOT NULL`表示给定列必须按照其数据类型明确赋值。相关语法如下所示↓：
	```SQL
	CERATE TABLE table_name(
		column_name1	type_name	NOT NULL,
		column_name2	type_name	NOT NULL,
		column_name3 type_name,
		column_name4 type_name
		PRIMARY KEY (column_name)
	)
	```

## 3.2 通用数据类型

* 创建表时必须决定表中的每个列的数据类型。通用数据类型如下所示↓：

|数据类型|描述|
|:-:|:-:|
|`CHARACTER(n)`|字符/字符串，固定长度是n|
|`VARCHAR(n)`或`CHARACTER VARYING(n)`|字符/字符串，可变长度，最大长度n|
|`BINARY(n)`|二进制串，固定长度n|
|`BOOLEAN`|存储`TRUE`或`FALSE`值|
|`VARBINARY(n)`或`BINARY VARYING(n)`|二进制串，可变长度，最大长度n|
|`INTEGER(p)`|整数值，精度(即有效位数)p|
|`SMALLINT`|整数值，精度5|
|`INTEGER`|整数值，精度10|
|`BIGINT`|整数值，精度19|
|`DECIMAL(P, s)`|精确数值，精度p，小数点后位数s|
|`NUMERIC(p, s)`|精确数值，精度p，小数点后位数s|
|`FLOAT(p)`|近似数值，尾数精度p。一个采用以10位基数的指数计数法的浮点数，该类型的size参数由一个指定最小精度的单一数字组成|
|`REAL`|近似数值，尾数精度7|
|`FLOAT`|近似数值，尾数精度16|
|`DOUBLE PRECISION`|近似数值，尾数精度16|
|`DATE`|存储年、月、日的值|
|`TIME`|存储小时、分、秒的值|
|`TIMESTAMP`|存储年、月、日、小时、分、秒的值|
|`INTERVAL`|由一些整数字段组成，代表一段时间，取决于区间的类型|
|`ARRAY`|元素的固定程度的有序集合|
|`MULTISET`|元素的可变长度的无序集合|
|`XML`|存储XML数据|

* 不同的数据库为数据类型定义提供了不同的选择，不同数据库平台上某些数据类型的通用名称如下所示↓：

|数据类型|Access|SQLServer|Oracle|MySQL|PostgreSQL|
|:-:|:-:|:-:|:-:|:-:|:-:|
|`boolean`|`Yes/No`|`Bit`|`Byte`|`N/A`|`Boolean`|
|`integer`|`Number(integer)`|`Int`|`Number`|`Int`、`Integer`|`Int`、`Integer`|
|`float`|`Number(single)`|`Float`、`Real`|`Number`|`Float`|`Numeric`|
|`currency`|`Currency`|`Money`|`N/A`|`N/A`|`Money`|
|`string`(固定)|`N/A`|`Char`|`Char`|`Char`|`Char`|
|`string`(可变)|`Text`(<256)、`Memo`(65K+)|`Varchar`|`Varchar`、`Varchar2`|`Varchar`|`Varchar`|
|`binary object`|`OLE Object`、`Memo`|`Binary`(固定到8K)、`Varbinary`(<8K)、`Image`(<2GB)|`Long`、`Raw`|`Blob`、`Text`|`Binary`、`Varbinary`|

# 4. SQL 函数

## `Date`函数

* MySQL `Date`函数如下所示↓：

|函数|描述|
|:-:|:-:|
|`NOW()`|返回当前的日期和时间|
|`CURDATE()`|返回当前的日期|
|`CURTIME()`|返回当前的时间|
|`DATE()`|提取日期或日期/时间表达式的日期部分|
|`EXTRACT()`|返回日期/时间的单独部分|
|`DATE_ADD()`|向日期添加指定的时间间隔|
|`DATE_SUB()`|从日期减去指定的时间间隔|
|`DATEDIFF()`|返回两个日期之间的天数|
|`DATE_FROMAT()`|用不同的格式显示日期/时间|

* MySQL `Date`数据类型如下所示↓：
	* `DATE`：`YYYY-MM-DD`
	* `DATETIME`：`YYYY-MM-DD HH:MM:SS`
	* `TIMESTAMP`：`YYYY-MM-DD HH:MM:SS`
	* `YEAR`：`YYYY`或`YY`

* SQL Server `Date`函数如下所示↓：

|函数|描述|
|:-:|:-:|
|`GETDATE()`|返回当前的日期和时间|
|`DATEPART()`|返回日期/时间的单独部分|
|`DATEADD()`|在日期中添加或减去指定的时间间隔|
|`DATEDIFF()`|返回两个日期之间的时间|
|`CONVERT()`|用不同的格式显示日期/时间|

* SQL Server `Date`数据类型如下所示↓：
	* `DATE`：`YYYY-MM-DD`
	* `DATETIME`：`YYYY-MM-DD HH:MM:SS`
	* `SMALLDATETIME`：`YYYY-MM-DD HH:MM:SS`
	* `TIMESTAMP`：唯一的数字

## Aggregate函数

* SQL Aggregate函数计算从列中取得的值，返回一个单一的值
	* `AVG()`：返回；平均值
	* `COUNT()`：返回行数
	* `FIRST()`：返回第一个记录的值
	* `LAST()`：返回最后一个记录的值
	* `MAX()`：返回最大值
	* `MIN()`：返回最小值
	* `SUM()`：返回和

### `MAX()`函数

* `MAX()`函数：返回所选列的最大值。语法如下所示↓：
	```SQL
	SELECT MAX(column_name)
	FROM table_name
	WHERE condition;
	```

### `MIN()`函数

* `MIN()`函数：返回所选列的最小值。语法如下所示↓：
	```SQL
	SELECT MIN(column_name)
	FROM table_name
	WHERE condition;
	```

### `COUNT()`函数

* `COUNT()`函数：返回匹配指定条件的行数。语法如下所示↓：
	* `COUNT(column_name)`：返回指定列的值的数目(`NULL`不计入)
	```SQL
	SELECT COUNT(column_name)
	FROM table_name
	WHERE condition;
	```
	* `COUNT(*)`：返回表中的记录数
	```SQL
	SELECT COUNT(*)
	FROM table_name
	WHERE condition;
	```
	* `COUNT(DISTINCT column_name)`：返回指定列的不同值的数目。适用于Oracle和Microsoft SQL Server，无法用于Microsoft Access
	```SQL
	SELECT COUNT(DISTINCT column_name)
	FROM table_name
	WHERE condition;
	```

### `AVG()`函数

* `AVG()`函数：返回数字列的平均值。语法如下所示↓：
	```SQL
	SELECT AVG(column_name)
	FROM table_name
	WHERE condition;
	```

### `SUM()`函数

* `SUM()`函数：返回数字列的总和。语法如下所示↓：
	```SQL
	SELECT SUM(column_name)
	FROM table_name
	WHERE condition;
	```

### `FIRST()`函数

* `FIRST()`函数：返回指定的列中第一个记录的值。语法如下所示↓：
	```SQL
	-- MS Access
	SELECT FIRST(column_name)
	FROM table_name;

	-- SQL Server
	SELECT TOP 1 column_name
	FROM table_name
	ORDER BY column_name ASC;

	-- MySQL
	SELECT column_name FROM table_name
	ORDER BY column_name ASC
	LIMIT 1;

	-- Oracle
	SELECT column_name FROM table_name
	ORDER BY column_name ASC
	WHERE condition;
	```

### `LAST()`函数

* `LAST()`函数：返回指定的列中最后一个记录的值。语法如下所示↓：
	```SQL
	-- MS Access
	SELECT LAST(column_name)
	FROM table_name;

	-- SQL Server
	SELECT TOP 1 column_name
	FROM table_name
	ORDER BY column_name DESC;

	-- MySQL
	SELECT column_name FROM table_name
	ORDER BY column_name DESC
	LIMIT 1;

	-- Oracle
	SELECT column_name FROM table_name
	ORDER BY column_name DESC
	WHERE condition;
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

## Scalar函数

* SQL Scalar函数基于输入值，返回一个单一的值
	* `UPPER()`、`UCASE()`：将某个字段转换为大写
	* `LOWER()`、`LCASE()`：将某个字段转换为小写
	* `MID()`：从某个文本字段提取字符
	* `LEN()`：返回某个文本字段的长度
	* `ROUND()`：对某个数值字段进行指定小数位数的四舍五入
	* `NOW()`：返回当前的系统日期和时间
	* `FORMAT()`：格式化某个字段的显示方式

### `UPPER()`、`UCASE()`、`LOWER()`、`LCASE()`函数

* `UPPER(s)`、`UCASE(s)`函数：将字符串s中的字母全部转换成大写。语法如下所示↓：
	```SQL
	SELECT UPPER('qwer');

	SELECT UCASE(column_name)
	FROM table_name;
	```

* `LOWER(s)`、`LCASE(s)`函数：将字符串s中的字母字符全部转换成小写。语法如下所示↓：
	```SQL
	SELECT LOWER('QWER');

	SELECT LCASE(column_name)
	FROM table_name;
	```

### `MID()`函数

* `MID()`函数：从文本字段中提取字符。语法如下所示↓：
	* `column_name`：必需，要提取字符的字段
	* `start_index`：必需，开始位置(起始值是1)
	* `length`：可选，要返回的字符数。如果省略，则返回从`start_index`开始的剩余文本
	```SQL
	SELECT MID(column_name, start_index[, length])
	FROM table_name;
	```

### `LEN()`函数

* `LEN()`函数：返回文本字段中值的长度。语法如下所示↓：
	```SQL
	SELECT LEN(column_name)
	FROM table_name;
	```

### `ROUND()`函数

* `ROUND()`函数：把数值字段舍入为指定的小数位数。语法如下所示↓：
	* `column_name`：必需，要舍入的字段
	* `decimals`：必需，要返回的小数位数
	```SQL
	SELECT ROUND(column_name, decimals)
	FROM table_name;
	```

### `FORMAT()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

### `()`函数

* `()`函数：。语法如下所示↓：
	```SQL
	```

## 其他函数

### `FIELD()`函数

* `FIELD()`函数：返回在`str1, str2,...`列表中`str`的索引(从1开始)，如果没有找到则返回0。语法如下所示：`FIELD(str, str1, str2,...)`

# 5. SQL 主机

# 6. SQL 总结
