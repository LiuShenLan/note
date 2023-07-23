- [0. 导读](#0-导读)
- [1. 关于对象](#1-关于对象)
	- [1.1 C++对象模型](#11-c对象模型)
	- [1.2 关键词所带来的困扰(struct与class)](#12-关键词所带来的困扰struct与class)
	- [1.3 对象的差异](#13-对象的差异)
		- [1.3.1 指针的类型](#131-指针的类型)
		- [1.3.2 加上多态之后](#132-加上多态之后)
- [2. 构造函数](#2-构造函数)
	- [2.1 默认构造函数的构造操作](#21-默认构造函数的构造操作)
		- [2.1.1 数据成员中包括带有默认构造函数的类成员对象](#211-数据成员中包括带有默认构造函数的类成员对象)
		- [2.1.2 派生自带有默认构造函数的基类](#212-派生自带有默认构造函数的基类)
		- [2.1.3 带有虚函数的类或派生自虚基类的类](#213-带有虚函数的类或派生自虚基类的类)
		- [2.1.4 小结](#214-小结)
	- [2.2 复制构造函数的构造操作](#22-复制构造函数的构造操作)
		- [2.2.1 声明拷贝构造函数](#221-声明拷贝构造函数)
		- [2.2.2 合成拷贝构造函数](#222-合成拷贝构造函数)
	- [2.3 程序转化](#23-程序转化)
		- [2.3.1 明确的初始化操作](#231-明确的初始化操作)
		- [2.3.2 函数参数的初始化](#232-函数参数的初始化)
		- [2.3.3 函数返回值的初始化](#233-函数返回值的初始化)
		- [2.3.4 在使用者层面做优化](#234-在使用者层面做优化)
		- [2.3.5 在编译器层面做优化](#235-在编译器层面做优化)
		- [2.3.6 Copy Constructor：要还是不要](#236-copy-constructor要还是不要)
		- [2.3.7 小结](#237-小结)
	- [2.4 构造函数成员初始化列表](#24-构造函数成员初始化列表)
- [3. Data](#3-data)
	- [3.1 数据成员的绑定](#31-数据成员的绑定)
	- [3.2 数据成员的布局](#32-数据成员的布局)
	- [3.3 数据成员的访问](#33-数据成员的访问)
		- [3.3.1 Static数据成员](#331-static数据成员)
		- [3.3.2 Nonstatic数据成员](#332-nonstatic数据成员)
	- [3.4 "继承"与数据成员](#34-继承与数据成员)
		- [3.4.1 只要继承不要多态](#341-只要继承不要多态)
		- [3.4.2 加上多态](#342-加上多态)
		- [3.4.3 多重继承](#343-多重继承)
		- [3.4.4 虚继承](#344-虚继承)
	- [3.6 指向数据成员的指针](#36-指向数据成员的指针)
- [4. Function](#4-function)
	- [4.1 Member的各种调用方式](#41-member的各种调用方式)
		- [4.1.1 Nonstatic成员函数](#411-nonstatic成员函数)
		- [4.1.2 Virtual虚拟成员函数](#412-virtual虚拟成员函数)
		- [4.1.3 Static静态成员函数](#413-static静态成员函数)
	- [4.2 虚成员函数](#42-虚成员函数)
		- [4.2.1 单一继承下的虚函数](#421-单一继承下的虚函数)
		- [4.2.2 多重继承下的虚函数](#422-多重继承下的虚函数)
		- [4.2.3 虚继承下的虚函数](#423-虚继承下的虚函数)
	- [4.4 指向Member Function的指针](#44-指向member-function的指针)
		- [4.4.1 支持"指向虚函数"的指针](#441-支持指向虚函数的指针)
		- [4.4.2 在多重继承之下，指向成员函数的指针](#442-在多重继承之下指向成员函数的指针)
	- [4.5 Inline Functions](#45-inline-functions)
		- [4.5.1 形式参数(Formal Arguments)](#451-形式参数formal-arguments)
		- [4.5.2 局部变量(Local Variables)](#452-局部变量local-variables)
- [5. 构造、析构、拷贝](#5-构造析构拷贝)
	- [5.1 无继承情况下的对象构造](#51-无继承情况下的对象构造)
		- [5.1.1 抽象数据类型](#511-抽象数据类型)
		- [5.1.2 为继承做准备](#512-为继承做准备)
	- [5.2 继承体系下的对象构造](#52-继承体系下的对象构造)
		- [5.2.1 虚继承](#521-虚继承)
		- [5.2.2 vptr初始化](#522-vptr初始化)
	- [5.3 拷贝赋值操作符函数](#53-拷贝赋值操作符函数)
	- [5.5 析构](#55-析构)
- [6. 运行时](#6-运行时)
	- [6.1 对象的构造和析构](#61-对象的构造和析构)
		- [6.1.1 全局对象](#611-全局对象)
		- [6.1.2 局部静态对象](#612-局部静态对象)
		- [6.1.3 对象数组](#613-对象数组)
		- [6.1.4 Default Constructors和数组](#614-default-constructors和数组)
	- [6.2 new和delete运算符](#62-new和delete运算符)
		- [6.2.1 针对数组的new语意](#621-针对数组的new语意)
		- [6.2.2 Placement Operator new的语意](#622-placement-operator-new的语意)
	- [6.3 临时性对象](#63-临时性对象)
		- [6.3.1 临时性对象的迷思(神话、传说)](#631-临时性对象的迷思神话传说)
- [7. 站在对象模型的尖端](#7-站在对象模型的尖端)
	- [7.1 Template](#71-template)
		- [7.1.1 Template的"实例化"行为](#711-template的实例化行为)
		- [7.1.2 Template的错误报告](#712-template的错误报告)
		- [7.1.3 Template中的名称决议方式(Name Resolution within a Template)](#713-template中的名称决议方式name-resolution-within-a-template)
		- [7.1.4 Member Function的实例化行为](#714-member-function的实例化行为)
	- [7.2 异常处理](#72-异常处理)
		- [7.2.1 Exception Handling快速检阅](#721-exception-handling快速检阅)
		- [7.2.2 对Exception Handling的支持](#722-对exception-handling的支持)
	- [7.3 运行时类型识别(RTTI)](#73-运行时类型识别rtti)
		- [7.3.1 Type-Safe Downcast（保证安全的向下转型操作）](#731-type-safe-downcast保证安全的向下转型操作)
		- [7.3.2 Type-Safe Dynamic Cast(保证安全的动态转型)](#732-type-safe-dynamic-cast保证安全的动态转型)
		- [7.3.3 References并不是Pointers](#733-references并不是pointers)
		- [7.3.4 Typeid运算符](#734-typeid运算符)
	- [7.4 效率有了，弹性呢](#74-效率有了弹性呢)
		- [7.4.1 动态共享函数库](#741-动态共享函数库)
		- [7.4.2 共享内存](#742-共享内存)

# 0. 导读

中英文对照表↓：

|英文|中文|
|:-:|:-:|
|access level|访问级，即C++中的public、protected、private|
|access section|访问区段，即class中的public、protected、private三种段落|
|alignment|边界调整，调整至某些bytes的倍数，其结果视不同的机器而定，例如32位机器通常调整至4的倍数|
|bind|绑定，将程序中的某个符号真正附着(决议)至一块实体上|
|chain|串链|
|class|类|
|class hierarchy|class体系，class层次结构|
|composition|组合，通常与继承(inheritance)一同讨论|
|concrete inheritance|具体继承(相对于抽象继承)|
|constructor|构造函数|
|data member|数据成员(亦或被称为member variable)|
|declaration, declare|声明|
|definition, define|定义(通常附带"在内存中挖一块空间"的行为)|
|derived|派生|
|destructor|析构函数|
|encapsulation|封装|
|explicit|明确的(通常指C++程序代码中明确出现的)|
|hierarchy|体系，层次结构|
|implement|实现(动词)|
|implementation|实现品、实现物，本书有时候指C++编译器，大部分时候是指class member function的内容|
|implicit|隐含的、暗喻的(通常指未出现在C++程序代码中的)|
|inheritance|继承|
|inline|内联|
|instance|实体|
|invocation|函数调用|
|layout|布局，本书中指object在内存中的数据分布情况|
|mangle|名称切割重组(C++对于函数名称的一种处理方式)|
|member function|成员函数，亦或被称为function member|
|members|成员，泛指data members和member functions|
|object|对象(根据class的声明而完成的一份占有内存的实体)|
|offset|偏移位置|
|operand|操作数|
|operator|运算符|
|overhead|额外负担(因某种设计，而导致的额外成本)|
|overload|重载|
|overload function|重载函数|
|override|重写(对virtual function的重新设计)|
|paradigm|典范|
|pointer|指针|
|polymorphism|多态("面向对象"最重要的一个性质)|
|programming|程序设计、程序化|
|reference|参考、参用(动词)|
|reference|C++的&运算符所代表的东西，当做名词解|
|resolve|决议。函数调用时链接器所进行的一种操作，将符号与函数实体产生关联，如果调用`func()`而链接时找不到`func()`实体，就会出现"unresolved externals"链接错误|
|slot|表格中的一格(一个元素)；条孔；条目；条格|
|subtype|子类型|
|type|类型，类别(指的是int、float等内建类型，或C++ class等自定类型)|
|virtual|虚拟|
|virtual function|虚拟函数|
|virtual inheritance|虚拟继承|
|virtual table|虚拟表格(为实现虚拟机制而实际的一种表格，内放virtual functions的地址)|

# 1. 关于对象

## 1.1 C++对象模型

C++中，数据成员分为`static`与`nonstatic`，函数成员分为`static`、`nonstatic`与`virtual`


* 数据成员：
	* nonstatic data member：被配置于每一个class object之内
	* static data member被存放在所有的class object之外

* 函数成员：
	* static和nonstatic函数成员被存放在所有的class object之外
	* virtual functions使用两个步骤进行支持：
		1. 每一个class产生出一些指向virtual functions的指针，这些指针存放在virtual table(vtbl)中
		2. 每一个class object被添加了一个指针vptr，指向相关的vtbl。vptr的setting和resetting都由每一个class的构造、析构和拷贝运算符自动完成(见[5. 构造、析构、拷贝](#5-构造析构拷贝))，每一个class所关联的_info object(用以支持RTTI)也经由vtbl被指出来，通常是放在表格的第一个slot处

* 优点在于空间和存取时间效率高，缺点在于如果代码没有改变，但是class object中的nonstatic data members发生了改变，则代码同样需要重新编译

在虚拟继承的情况下，base class不管在继承链中被派生多少次，永远只会存在一个实体(称为subobject)


## 1.2 关键词所带来的困扰(struct与class)

C++中凡处于同一个  access section(访问区段)的数据，必定保证以其声明次序出现在内存布局当中，然而被放置在多个  access sections中的各个数据，排列次序就不一定了。同样的道理，基类和派生类的数据成员的内存布局也没有谁先谁后的强制规定([3. Data](#3-data)会更详细的讨论相关的内存布局主题)

## 1.3 对象的差异

* C++程序设计模型直接支持三种程序设计典范：
	1. 程序模型：类似C语言，面向过程编程
	2. 抽象数据类型模型(abstract data type model, ADT)：抽象和一组表达式(public接口)一起提供，而其运算定义并不暴露，如string类
	3. 面向对象模型(object-oriented model, OO)：在此模型中有一些彼此相关的类型，通过一个抽象的base class(用以提供共通接口)被封装起来

* C++支持多态的方法：
	1. 经由一组隐含的转换操作，如把一个派生类指针转换为指向其public base type的指针，`Parent *ptr = new child();`
	2. 经由virtual function机制
	3. 经由dynamic_cast和typeid运算符

* class object所需内存：
	* nonstatic data member的总和大小
	* 由于数据对齐而填补的空间(可能存在于members之间，也可能存在于集合体边界)
	* 为了支持virtual而由内部产生的任何额外负担

### 1.3.1 指针的类型

指向不同类型的各种指针之间的差异主要在于编译器如何解释某个特定地址中的内存内容及其大小。`void*`类型的指针能够含有一个地址，但是无法确定指向的地址的长度(所涵盖的地址空间)，因此不能够通过`void*`指针操作它所指向的object

cast是一种编译器指令，大部分情况下并不改变一个指针所含的真正地址，只影响"被指向的内存的大小和内容"的解释方式

### 1.3.2 加上多态之后

不管是指针还是引用都需要一个字的空间(32位机器上是4bytes)

在派生类的内存空间中，所继承的基类的内存在派生类的内存空间的前面部分。当使用基类指针指向派生类对象时，基类指针所涵盖的地址空间只包含派生类对象中的基类的subobject部分，因此不能使用基类指针操作派生类中的成员。唯一的例外是通过virtual机制

当一个基类对象被直接初始化为一个派生类对象时，派生类对象就会被切割(sliced)，以放入较小的基类内存中

# 2. 构造函数

中英文对照表↓：

|英文|中文|
|:-:|:-:|
|implicit|暗中的，隐含的(通常指并非在程序源代码中出现的)|
|explicit|明确的(通常指程序源代码中所出现的)|
|trivial|没有用的|
|nontrivial|有用的|
|memberwise|对每个member施以...|
|bitwise|对每个bit施以...|
|semantics|语意|

## 2.1 默认构造函数的构造操作

全局对象的内存保证会在程序激活的时候被清为0，局部对象配置于程序的栈中，堆对象配置于自由空间(堆)中，并不一定会被清为0，它们的内容将是内存上次被使用后的遗迹

* class X如果没有任何声明的构造函数，在"必要的"情况下，编译器会进行以下操作：

	1. 如果用户没有声明任何构造函数，那么编译器需要合成默认构造函数

	2. 如果用户已经声明了构造函数，但其中没有默认构造函数，此时编译器不会合成一个新的默认构造函数，而是会扩张现有的每一个构造函数

* 必要的情况包括以下四种情况：
	1. 类内含一个有默认构造函数的成员对象(无论默认构造函数是声明的还是合成的)
	2. 类继承自一个含有默认构造函数的基类(无论默认构造函数是声明的还是合成的)
	3. 类声明或继承了一个或多个虚函数
	4. 类派生自一个继承串链，其中有一个或多个虚基类
	* 默认构造函数的"必要的"情况和拷贝构造函数相同

### 2.1.1 数据成员中包括带有默认构造函数的类成员对象

如果一个class没有任何构造函数，但它内含一个类成员对象，并且类成员对象有默认构造函数，那么编译器将会为这个class合成出一个默认构造函数，不过这个合成操作只有在构造函数真正被调用时才会发生

在C++的各个不同的编译模块(即不同的文件)中，编译器为了避免合成出多个默认构造函数，编译器会把合成的默认构造函数、复制构造函数、析构函数、赋值操作符都以inline方式完成。一个inline函数有静态链接，不会被文件以外者看到。如果函数太复杂，不适合做成inline，编译器就会合成一个explicit non-inline static实体(inline函数将会在[4.5 Inline Functions](#45-inline-functions)节有比较详细的说明)

如果class A内含一个或一个以上的类成员对象，那么class A的每一个构造函数必须调用每一个member class的默认构造函数。编译器会扩张已存在的构造函数，在其中安插一些代码，使得用户编写的代码在被执行之前，先调用必要的默认构造函数

如果有多个类成员对象都要求构造初始化操作，那么编译器在用户编写的代码之前安插代码，按照类成员对象在class中的声明次序，调用每个member所关联的默认构造函数

[2.4 成员们的初始化队伍](#24-成员们的初始化队伍)节将讨论调用隐式默认构造函数和调用明确列于成员初始化列表中的构造函数之间的互动关系

### 2.1.2 派生自带有默认构造函数的基类

如果一个没有任何构造函数的类派生自一个带有默认构造函数的基类，那么编译器会为这个派生类合成默认构造函数，它将按照声明次序调用上一层基类的默认构造函数

如果用户提供多个构造函数，但其中都没有默认构造函数，此时编译器不会合成一个新的默认构造函数，而是会扩张现有的每一个构造函数，将用来调用所有必要的默认构造函数的代码插入其中

如果基类和类成员对象同时带有默认构造函数，那么会先调用基类构造函数，再调用类成员对象构造函数

### 2.1.3 带有虚函数的类或派生自虚基类的类

在用户没有声明任何构造函数时，编译器需要合成默认构造函数；在用户已经声明了构造函数时，编译器扩张用户定义的每一个构造函数

此时编译器会产生一个vtbl(里面放置虚函数的地址)，同时在每一个类对象中，编译器会额外合成vptr(内含类vtbl的地址)

虚基类的实现在不同的编译器之间有极大的差异，然而实现的共同点在于必须使虚基类在每一个派生类对象中的位置，能够在运行时准备妥当。类对象在构造期间完成vptr和vtbl

### 2.1.4 小结

在上述四种情况下，编译器会为没有声明构造函数的类合成一个默认构造函数。被合成出来的构造函数只能满足编译器的需要。它之所以能完成任务，是借着"调用成员对象或基类的默认构造函数"或是"为每一个对象初始化其虚函数或虚基类机制"而完成

在合成出来的默认构造函数中，只有基类的subobject和类成员函数会被初始化，所有其他的非static都不会被初始化，需要用户手动初始化

## 2.2 复制构造函数的构造操作

一个类对象可以从两种方式复制得到，一种是被初始化(本节内容)，另一种是被指定(assignment，[5. 构造、析构、拷贝](#5-构造析构拷贝)讨论)。从概念上而言，这两个操作分别是以拷贝构造函数(copy constructor)和拷贝赋值运算符(copy assignment operator)完成的

* 以下三种情况会以一个对象的内容作为另一个类对象的初始值↓：

	1. 明确地以一个对象的内容作为另一个类对象的初值

	```C++
	class A {...};
	A a;

	// 初始化操作
	A x = a;
	```

	2. 对象被当做参数交给某个函数

	```C++
	extern void fun(A a);

	A a;
	// 以a作为fun()第一个参数的初值
	// 不明显的初始化操作
	fun(a);
	```

	3. 函数传回一个类对象

	```C++
	A fun() {
		A a;
		// ...
		return a;
	}
	```

### 2.2.1 声明拷贝构造函数

类设计者可以明确定义拷贝构造函数(其中一个参数的类型是其class type，可以是多参数的形式，其第二个参数及后继参数需要提供默认值)，代码示例如下↓：

```C++
A::A(const A &a);
B::B(const B &b, int = 0);
```

当一个类对象以另一个同类对象作为初始值时，上述拷贝构造函数就会被调用，这可能导致一个临时对象的产生或者程序代码的变化(或两者都有)

### 2.2.2 合成拷贝构造函数

当一个类对象以同类的另一个对象作为初值时，如果该类没有定义拷贝构造函数，那么编译器在必要的时候会合成拷贝构造函数(与默认构造函数)。必要的情况是指当class不展现bitwise copy semantics的时候。此时合成的拷贝构造函数是以默认逐成员初始化手法完成的，即把每一个内建的或派生的数据成员从某个对象拷贝一份到另一个对象，不过它不会拷贝类成员对象，而是以递归的方式实现逐成员初始化

* 类不展现bitwise copy semantics的情况(即编译器合成拷贝构造函数的"必要的"情况)
	1. 类内含一个有拷贝构造函数的成员对象(无论拷贝构造函数是声明的还是合成的)
	2. 类继承自一个含有拷贝构造函数的基类(无论拷贝构造函数是声明的还是合成的)
	3. 类声明了一个或多个虚函数
	4. 类派生自一个继承串链，其中有一个或多个虚基类
	* 拷贝构造函数的"必要的"情况和默认构造函数相同

* 类内含一个有拷贝构造函数的成员对象或类继承自一个含有拷贝构造函数的基类时，编译器将类成员对象或基类的拷贝构造函数安插到被合成的拷贝构造函数中

* 类声明了一个或多个虚函数时，编译器会进行如下两步扩张操作
	1. 增加一个vtbl，内含每一个有作用的虚函数的地址
	2. 将一个指向vtbl的指针vptr安插在每一个类对象内
	* 编译器对每一个新产生的类对象的vtbl需要正确的设置其初值，该类不再展现bitwise copy semantics，因此编译器需要合成拷贝构造函数，以求将vtbl适当地初始化

* 类派生自一个继承串链，其中有一个或多个虚基类的情况如下↓：

一个类对象如果以另一个有虚基类subobject的对象作为初值，那么也会使bitwise copy semantics失效

每个编译器对虚拟继承的支持承诺，让"derived class object中的virtual base class subobject位置"在运行时就准备妥当。维护"位置的完整性"是编译器的责任，而bitwise copy semantics可能会破坏这个位置，所以编译器必须在它自己合成出来的拷贝构造函数中做出仲裁

注意，问题并不发生于"一个类对象以另一个同类对象作为初值"，而是发生于"一个类对象以其派生类的某个对象作为初值"，参考代码如下↓：

```C++
class A {};
class B : public virtual A {};
class C : public B {};

// 此时bitwise copy足够
// 编译器不需要合成拷贝构造函数
B b1;
B b2 = b1;

// 此时编译器需要合成拷贝构造函数
// 插入代码以设定虚拟基类指针/偏差的初值
// 对每一个成员执行必要的memberwise初始化操作
// 以及执行其他的内存相关工作
B b;
C c = b;
```

[3.4 "继承"与Data Member](#34-继承与data-member)对虚基类有更详细的讨论

## 2.3 程序转化

### 2.3.1 明确的初始化操作

明确的初始化操作会进行程序转化，必要的程序转化有两个阶段，首先重写每一个定义，其中的初始化操作会被剥除，然后类的拷贝构造函数调用操作会被安插进去，代码示例如下↓：

```C++
// 已知
X x0;

// 初始化操作如下
X x1(x0);
X x2 = x0;
X x3 = X(x0);

// 可能的程序转化结果如下
// 首先定义被重写，初始化操作被剥除
X x1;
X x2;
X x3;
// 然后编译器安插类的拷贝构造函数调用操作
x1.X::X(x0);
x2.X::X(x0);
x3.X::X(x0);

// 其中 x1.X::X(x0)就是对拷贝构造函数
// X::X(const X &x) 的调用
```

同时明确的初始化也会带来临时对象的问题，代码示例如下↓：

```C++
X xx0(1024);
X xx1 = X(1024);
X xx2 = (X) 1024;
```

在上述代码中，xx0调用默认构造函数，不会产生临时对象。xx1和xx2都经历了两个步骤的初始化操作，首先是将一个临时对象设以初值1024，然后将临时对象以拷贝构造的方式作为明确初始化的对象的初值，可能的程序代码转化如下↓：

```C++
xx0.X::X(1024);

X __temp0;
__temp0.X::X(1024);
xx1.X::X(__temp0);
__temp0.X::~X();
```

### 2.3.2 函数参数的初始化

C++标准规定将一个类对象当做参数传给一个函数或是作为一个函数的返回值时，相当于`X xx = arg;`形式的初始化操作。其中`xx`代表函数形参或返回值，而`arg`代表真正的参数值

对于函数形参来说，如果已知函数`void fun(X x0);`，则`X xx; fun(xx);`的调用方式将会要就局部实例`x0`以memberwise的方式将`xx`当做初值。在编译器实现技术上，有如下两种实现方式

1. 导入暂时性对象，并调用复制构造函数将它初始化，然后将该暂时性对象交给函数。程序代码转化如下↓：

```C++
// C++伪码

// 编译器产生出来的临时对象
X __temp0;

// 编译器对复制构造函数的调用
__temp0.X::X(xx);

// 重新改写函数调用操作，以便使用上述的临时对象
fun(__temp0);
```

注意函数`fun()`的声明也需要转化，形参从类对象改变为类对象的引用`void fun(X &x0);`

同时会在函数`fun()`完成之后调用类X的析构函数，将临时对象`__temp0`析构

2. 以"拷贝构造(copy construct)"的方式把实参直接构造在其应该的位置上，该位置视函数活动范围的不同记录于程序堆栈中，在函数返回之前，局部对象的析构函数(如果有定义)会被执行

### 2.3.3 函数返回值的初始化

已知函数定义如下↓：

```C++
X fun() {
	X xx;
	// 处理xx...
	return xx;
}
```

一种可能的双阶段程序代码转化是，首先给函数加上一个额外的参数，类型是返回值类对象的一个引用，这个参数将被用来放置被拷贝构造函数而得到的返回值；然后在`return`指令之前安插一个拷贝构造函数调用操作，以便将希望传回的对象的内容当做上述新增参数的初值；最后一个转化操作会重新改写函数，使它不传回任何值。程序代码转化示例如下↓：

```C++
void fun(X &__result) {
	// 添加一个额外参数

	X xx;

	// 编译器所产生的默认构造函数调用操作
	xx.X::X();

	// 处理xx...

	// 编译器所产生的拷贝构造函数调用操作
	__result.X::X(xx);

	return;
}

// 函数结束后会将临时对象xx析构
```

同时对函数`fun()`的调用的操作也必须进行转化，以反映其新定义，转化示例如下↓：

```C++
// 类对象定义
X xx = fun();
// 转化后不必调用默认构造函数
X xx;
fun(xx);

// 执行fun()所传回的类对象的memfunc()
fun().memfunc();
// 转化后为
X __temp0;	// 编译器产生的临时对象
(fun(__temp0), __temp0).memfunc();

// 函数指针
X (*pf)();
pf = fun;
// 转化后为
void (*pf)(X &);
pf = fun;
```

### 2.3.4 在使用者层面做优化

例如如下函数↓：

```C++
X fun(const T &y, const T &z) {
	X xx;
	// 通过y和x来处理xx
	return xx;
}
```

可以通过定义类X使用参数y和z的构造函数来进行优化，代码示例如下↓：

```C++
X::X(const T &y, const T &z) {
	// ... 构造函数
}

X fun(const T &y, const T &z) {
	return X(y, z);
}
```

此时优化后的`fun()`的程序代码转化后的函数如下所示↓：

```C++
void fun(X &__result, const T &y, const T &z) {
	__result.X::X(y, z);
	return;
}
```

在上述优化中，__result被直接构造出来，而不是经由拷贝构造函数拷贝而得。不过这种方法会导致用于特殊计算的构造函数可能会大量扩散

### 2.3.5 在编译器层面做优化

在一个函数中，所有的return指令传回相同的具名数值(named value)，因此编译器有可能进行NRV(Named Return Value)优化，方法是以return参数代替named return value。代码示例如下↓：

```C++
// 原函数
X fun() {
	X xx;
	// 处理xx...
	return xx;
}

// NRV优化后的函数
void fun(X &__result) {
	// 调用默认构造函数
	__result.X::X();

	// 直接处理__result...

	return;
}
```
* NRV优化提供了重要的效率改善，但是缺点如下：
	1. 优化由编译器默默完成，但编译器是否真的完成，并不十分清楚
	2. 一旦函数变得比较复杂，优化也就变得比较难以施行

### 2.3.6 Copy Constructor：要还是不要

当类不含任何由编译器产生的内部成员(如vtbl和vptr)时，可以通过memcpy()或memset()实现拷贝构造函数。如果类声明有虚函数或者内含一个虚基类时，那么使用memcpy()或memset()函数会导致由编译器产生的内成员的初值被改写

### 2.3.7 小结

拷贝构造函数的应用，迫使编译器对程序代码进行部分转化，尤其是当一个函数以传值(by value)的方式传回一个类对象，而该类有一个拷贝构造函数(无论是明确声明的还是合成的)时，将导致程序转化(不论是在函数的定义或使用上)。此外编译器也将拷贝构造函数的调用操作优化，以一个额外的第一参数(数值被直接存放于其中)取代NRV

## 2.4 构造函数成员初始化列表

* 必须使用成员初始化列表的情况：
	1. 初始化一个引用成员
	2. 初始化一个常量成员
	3. 调用一个基类的有参数的构造函数
	4. 调用一个类成员对象的有参数的构造函数

构造函数成员初始化列表可能的程序代码扩张示例如下↓：

```C++
// 低效率构造函数代码
class Word {
public:
	Word() {
		_name = 0;
		_cnt = 0;
	}
private:
	String _name;
	int _cnt;
}

// 低效率构造函数可能的内部扩张如下
Word::Word() {
	// 调用String的默认构造函数
	_name.String::String();

	// 产生临时对象
	String temp = String(0);

	// memberwise地拷贝_name
	_name.String::operator=(temp);

	// 析构临时对象
	temp.String::~String();

	_cnt = 0;
}

// 高效率构造函数代码
Word::Word() : _name(0) {
	_cnt = 0;
}

// 高效率构造函数可能的内部扩张如下
Word::Word() {
	// 调用String(int)构造函数
	_name.String::String(0);

	_cnt = 0;
}
```

成员初始化列表的实现：编译器对按照类中成员的声明次序(而不是在成员初始化列表中的排列次序)，在构造函数之内，用户代码之前，安插初始化操作

在成员初始化列表中，可以使用类的成员函数的返回值作为参数来初始化成员变量，但是要注意成员变量的顺序(类中成员的声明次序)。成员函数的使用是合法的，因为此时与该对象相关的this指针已经构造完毕。构造函数代码扩充示例如下所示↓：

```C++
// 构造函数源代码
class X {
public:
	X(int val) : i(fun(val)), j(val) {}
private:
	int i;
	int j;
};

// 扩充后的构造函数
X::X(int val) {
	i = this->fun(val);
	j = val;
}
```

总结：编译器会对成员初始化列表处理并可能重新排序，以反映出成员的声明次序。它会安插一些代码到构造函数体内，并置于任何用户代码之前。后继章节对于基类和虚基类在成员初始化列表中的初始化程序会有比较详细的说明

# 3. Data

```C++
class X {};
class Y : public virtual X {};
class Z : public virtual X {};
class A : public Y, public Z {};
```

`sizeof(X) == 1`，编译器给类X安插进去一个char，这使得这个类的两个对象得以在内存中配置独一无二的地址

`sizeof(Y) == sizeof(Z) == 8`，Y和Z的大小和机器有关，也和编译器有关，受到三个因素的影响：

1. 语言本身所造成的额外负担：当语言支持虚基类时，就会导致一些额外负担。在派生类中，这个额外负担反映在某种形式的指针上，他或者指向虚基类subobject，或者指向一个相关表格(表格存放着虚基类subobject的地址或者偏移量)，将在[3.4 "继承"与Data Member](#34-继承与data-member)讨论虚基类

2. 编译器对于特殊情况所提供的优化处理：一种较早的方法是将空虚基类分配的字符放在派生列的固定部分的尾端；另一种较新的优化方法是空虚基类被视为派生类对象最开头的一部分，也就是说空虚基类没有花费任何额外空间

3. 数据对齐的限制：数据对齐就是将数值调整到某数的整数倍，在大部分机器上，结构体大小会受到数据对齐的限制，使它们能够更有效率地在内存中被存取

`sizeof(A) == 12`，类A的大小受如下四点决定：

1. 被所有类共享的唯一一个虚基类X的实例，因不同处理方式大小为1(较老)或0(较新)。注意，如果虚基类X含有数据成员，则不管什么处理方法，编译器都会产生完全相同的对象布局

2. 基类Y的大小，减去因虚基类X而配置的大小；基类Z的大小，减去因虚基类X而配置的大小

3. 类A自己的大小：0 bytes

4. 类A的数据对齐的数量

C++标准规范并不强制规定如"基类subobject"或"不同存取层级的数据成员的排列次序"等细节，也不规定虚函数或虚基类的实现细节

C++对象模型把nonstatic数据成员(包括从任意基类继承而来的)直接存放在每一个类对象之中，不过并没有强制定义其间的排列顺序。static数据成员则被放置在静态变量区，不会影响到类对象的大小。在程序之中，不管该类有多少个对象(经由直接定义或者间接派生，甚至没有任何对象实体)，static数据成员永远只存在一份实体。但是一个模板类的static数据成员的行为稍有不同，详见[7.1 Template](#71-template)

一个类对象必须有足够的大小以容纳它所有的nonstatic数据成员，比定义的数据成员内存大小更大，是因为：1、编译器自动加上的额外数据成员，用以支持某些语言特性(主要是各种virtual特性)；2、数据对齐的需要

## 3.1 数据成员的绑定

在类中定义的成员函数，在整个类的声明未被完全看见之前，是不会被评估求值的。即对成员函数本身的分析，会直到整个class的声明都出现了才开始。因此，在成员函数内的一个数据成员的绑定操作，会在整个类声明完成之后才发生

但是类成员函数的形参列表并不是如此。参数列表中的名称还是会在它们第一次遇到时被适当地决议完成。当从上向下读取如下代码时，发生的情况如下↓：

```C++
typedef int length;

class Point {
public:
	void mumble(length val) {_val = val;}
	// length被决议为全局global，即为int
	// _val被决议为Point::_val
private:
	typedef float length;
	// 该局部length必须在"本class对它的第一个参考操作"
	// 之前被看见，因此这样的声明将使之前的参考操作不合法
	length _val;
}
```

因此需要某种防御性程序风格，把所有的嵌套类型声明放在class的起始处

## 3.2 数据成员的布局

nonstatic数据成员在类对象中的排列顺序将和其被声明的顺序一样，任何中间介入的static数据成员都不会被放进对象布局之中，而是存放在程序的静态存储区中，和个别的类对象无关

C++标准要求，在同一个  access section(访问区段，也就是public、protected、private等区段)中，成员的排列只需符合"较晚出现的成员在类对象中有较高的地址"这一条件即可。也就是说，各个成员之间并不一定得连续排列，数据对齐等可能会出现在成员之间

编译器还可能会合成一些内部使用的数据成员，以支持整个对象模型，比如vptr，一般把vptr放在一个类对象的最前端。C++标准允许编译器把那些内部产生出来的成员自由放在任何位置上，甚至放在那些被程序员声明出来的成员之间

C++标准也允许编译器将多个  access sections(访问区段)之中的数据成员自由排列，不必在乎它们出现在类声明中的次序。大部分编译器都是把一个以上的  access sections连锁在一起，依照声明的次序成为一个连续区块，  access sections的多寡并不会招来额外负担，例如在一个section中声明多个成员，或是在多个sections中每个section中声明一个成员，得到的对象大小是一样的

## 3.3 数据成员的访问

本节讨论以不同方式对类对象的数据成员的访问的成本

下文用到如下代码↓：

```C++
class X {
public:
	static int si;
	float f;
}

X origin;
X *pt = &origin;
```

### 3.3.1 Static数据成员

static数据成员被编译器提出于类之外，并被视为一个global变量(但只在class声明范围之内可见)。每一个static数据成员的访问许可(public、protected或private)，以及与类的关联，并不会导致任何空间上或执行时间上的额外负担

* 访问static数据成员

每一个static数据成员之后一个实体，存放在程序的静态存储区之中，每次程序访问static数据成员，都会被内部转化为对该唯一的extern实体的直接参考操作，代码示例如下↓：

```C++
origin.si = 250;
// 转化为
X::si = 250;

pt->si = 250;
// 转化为
X::si = 250;
```

从指令执行的观点来看，这是C++中"通过一个指针和通过一个对象来访问成员，结论完全相同的唯一一种情况"。这是因为经由`.`运算符对一个static数据成员进行访问操作只是语法上的一种方式而已，static数据成员并不在类对象之内，因此存取static数据成员并不需要通过类对象

* static数据成员与继承

无论static数据成员是直接定义而来，还是经过了无论多么复杂的(虚)继承关系而来，程序中对static数据成员还是只有唯一一个实体，并且访问路径仍与上述一样直接

* static数据成员与函数调用

如果通过函数调用(或其他某些语法)的返回值来访问static数据成员，在C++标准中要求函数必须被求值(虽然函数结果可能并无用处)。可能的转化代码如下所示↓：

```C++
// 源代码
fun().si = 250;

// 转化后
(void) fun();
X.si = 250;
```

* 取static数据成员的地址，会得到一个指向其数据类型的指针。如`&X::si`会得到`const int*`类型的指针

* 同名static数据成员处理

如果有两个类，每一个都声明了一个static数据成员i，那么当它们都被放在程序的静态存储区时，就会导致名称冲突。编译器的解决方法是暗中对每一个static数据成员编码，以获得一个独一无二的程序识别代码。不同编译器的改名方式不同，但是有两个要点：1、需要推导出独一无二的名称的算法；2、可以通过修改后的名称推导出原来的名称

### 3.3.2 Nonstatic数据成员

* 成员函数

nonstatic数据成员直接存放在每一个类对象之中，除非经由显式的或隐式的类对象，没有办法直接访问它们。只要用户在一个成员函数中直接处理一个nonstatic数据成员，显式的类对象就会发生。在成员函数中表面上所看到对成员变量的直接存取，实际上是经由一个显式类对象(由this指针表达)完成。代码示例如下↓：

```C++
// 源代码
void X::fun(const X &rhs) {
	x += rhs.x;
	y += rhs.y;
	z += rhs.z;
}

// 成员函数内部转化后
void X::fun(X* const this, const X &rhs) {
	this->x += rhs.x;
	this->y += rhs.y;
	this->z += rhs.z;
}
```

成员函数在[4. Function](#4-function)中有比较详细的讨论

* 偏移量(offset)

想要对一个nonstatic数据成员进行访问操作，编译器需要把类对象的起始地址加上数据成员的偏移量。代码示例如下↓：

```C++
origin._y = 0.0

// 地址&origin._y等于
&origin + (&X::_y - 1);
```

注意其中的-1操作，指向数据成员的指针，其偏移量总是被加上1，这样可以使编译系统区分出"一个指向数据成员的指针，用以指出类的第一个成员"和"一个指向数据成员的指针，没有指出任何成员"两种情况。(为啥-1没看明白)。"指向数据成员的指针"将在[3.6 指向Data Member的指针](#36-指向data-member的指针)有比较详细的讨论(在古老的编译器上，对类数据成员取地址之后，还会在偏移量上加上1，是为了区分没有指向任何数据成员的空指针和指向类第一个数据成员的指针。不过现在的编译器都进行了优化)

* 虚继承

虚继承将为"经由基类subobject访问类成员"导入一层新的间接性。如`pt->_x = 0.0`，其执行效率在_x是一个struct成员、一个class成员、单一继承、多重继承的情况下都完全相同。但如果_x是一个虚基类的成员，访问速度会比较慢一点。[下一节](#34-继承与data-member)会介绍"继承对成员布局的影响"

在如下代码中，当类X是一个派生类，而在其继承结构中有一个虚基类，并且访问的成员(如下文代码中的x)是一个从该虚基类继承而来的成员时，通过对象访问和通过指针访问就会有差异。这时候无法确定指针pt必然指向哪一种类型，因此也就不知道编译时期这个成员(x)真正的偏差位置，所以这个访问操作必须延迟到运行时，经由一个额外的间接导引，才能够解决。但如果通过对象origin访问，就不会有这些问题，其类型是确定的，即使它继承自虚基类，成员的偏差位置也在编译时期就固定了

```C++
origin.x = 0.0;
pt->x = 0.0;
```

## 3.4 "继承"与数据成员

在C++继承模型中，一个派生类对象所表现出来的东西，是其自己的成员加上其基类成员的综合。C++标准中并未指定派生类成员和基类成员的排列次序，理论上编译器可以自由安排。大部分编译器中，基类成员总是先出现，但是虚基类除外

### 3.4.1 只要继承不要多态

具体继承(相对于虚继承)可以共享数据成员本身以及数据的处理方法，并将其局部化，并不会增加空间或访问时间上的额外负担

* 内存布局

```C++
class Point2d {
public:
	Point2d(float x = 0.0, float y = 0.0) :
		_x(x), _y(y) {}

	float x() {return _x;}
	float y() {return _y;}

	void x(float newX) {_x = newX;}
	void y(float newY) {_y = newY;}

	void operator+=(const Point2d &rhs) {
		_x += rhs.x();
		_y += rhs.y();
	}

protected:
	float _x, _y;
};

class Point3d : public Point2d {
public:
	Point3(float x = 0.0, float y = 0.0, float z = 0.0) :
		Point2d(x, y), _z(z) {}

	float z() {return _z;}
	void z(float newZ) {_z = newZ;}

	void operator+=(const Point3d &rhs) {
		Point2d::operator+=(rhs);
		_z += rhs.z();
	}

protected:
	float _z;
};
```

如上代码设计的好处是可以把管理x和y坐标的程序代码局部化。在没有声明virtual接口的时候，Point2d的内存布局为相邻的_x和_y；Point3d的内存布局为对象的前面部分是Point2d的_x和_y，后面紧跟着_z

像这样把两个原本独立不相干的类通过继承进行耦合，容易出现的问题，首先是可能会重复设计一些相同操作的函数，如上述代码中的Point3d::Point3d(float, float, float)和Point3d::operator+=()，它们并没有做成inline函数，并且调用了Point2d相应的函数。第二个容易出现的问题就是，把一个类分解为两层或更多层，有可能会为了表现类体系的抽象化而膨胀所需空间。C++语言保证"基类subobject在派生类中保持完整原样性"

* "基类subobject在派生类中保持完整原样性"

(以下讨论均在32位机器上)

所有的数据成员声明在一个类中，代码如下所示↓：

```C++
class Concrete {
	int val;
	char c1;
	char c2;
	char c3;
};
```

上述代码中，在32位机器内，每个`Concrete`对象的大小都是8 bytes，细分如下：`int val`占用4 bytes、`char c1, c2, c3`各占用1 bytes、数据对其需要1 bytes

数据成员分别声明在不同的类层次结构中，代码示例如下↓：

```C++
class Concrete1 {
	int val;
	char c1;
};

class Concrete2 : public Concrete1 {
	char c2;
};

class Concrete3 : public Concrete2 {
	char c3;
}
```

`Concrete1`对象的大小是8 bytes：`int val`占用4 bytes，`char c1`占用1 bytes，加上数据对齐3 bytes，总共8 bytes

`Concrete2`对象的大小是12 bytes：由于"基类subobject在派生类中保持完整原样性"，所以`Concrete2`对象的内存中首先是`Concrete1`基类的8 bytes，然后是`char c2`占用1 bytes，最后数据对齐占用3 bytes，共12 bytes

`Concrete3`对象的大小是16 bytes：首先是`Concrete2`基类的12 bytes，然后是`char c3`占用1 bytes，最后数据对齐占用3 bytes，共16 bytes

* "出现在派生类中的基类subobject有其完整原样性"的作用

考虑如下指针↓：

```C++
Concrete2 *pc2;
Concrete1 *pc1_1, *pc1_2;
```

操作`*pc1_2 = *pc1_1;`应该执行一个默认的memberwise的复制操作，操作对象是被指向的object的`Concrete1`的那一部分。如果`pc1_1`实际指向一个`Concrete2`或`Concrete3`对象，则上述操作应该将复制内容指定给其`Concrete1`subobject

如果C++语言不保证"基类subobject在派生类中保持完整原样性"，那么`Concrete2::c2`或`Concrete3::c3`将和`Concrete`subobject之间没有数据对齐所用的填补空间，上述语意就无法保证。如果此时`pc1_1`指向一个`Concrete2`对象，那么操作`*pc1_2 = *pc1_1;`执行之后，`Concrete2::c2`将会被覆盖，有一个非预期的值

### 3.4.2 加上多态

```C++
class Point2d {
public:
	Point2d(float x = 0.0, float y = 0.0) :
		_x(x), _y(y) {}

	// x和y的存取函数与3.4.1节中的相同
	// 由于对不同维度的点，这些操作相同
	// 因此不必声明为virtual

	// 加上z的保留空间(当前什么也没做)
	virtual float z() {return 0.0;}
	virtual void z(float) {}

	virtual void operator+=(const Point2d &rhs) {
		_x += rhs.x();
		_y += rhs.y();
	}
protected:
	float _x, _y;
}

void fun(Point2d &p1, Point2d &p2) {
	p1 += p2;
	// 此时p1和p2可能是2d也可能是3d坐标点
};
```

* 引入虚函数之后Point2d带来的空间和访问时间的额外负担：
	1. 导入一个和Point2d有关的vtbl，用来存放它所声明的每一个虚函数的地址，vtbl的元素数目一般而言是被声明的虚函数的数目，再加上一两个元素(用以支持运行时类型识别)
	2. 在每一个类对象中导入一个vptr，提供运行时的链接，使每一个对象都能够找到相应的vtbl
	3. 加强构造函数，使构造函数能够为vptr设定初值，让它指向类所对应的vtbl。这可能意味着在派生类和每一个基类的构造函数中，重新设定vptr的值。具体情况视编译器的优化而定。[5. 构造、析构、拷贝](#5-构造析构拷贝)对此有比较详细的讨论
	4. 加强析构函数，使析构函数能够析构"指向类相关的vtbl"的vptr。注意，析构函数的调用次序是反向的，即从派生类到基类

```C++
class Point3d : public Point2d {
public:
	Point3d(float x = 0.0, float y = 0.0, float z = 0.0) :
		Point2d(x, y), _z(z) {}

	float z() {return _z;}
	void z(float newZ) {_z = newZ;}

	void operator+=(const Point2d &rhs) {
		// 注意，参数为Point2d
		// 可以把+=操作用在一个2d和一个3d对象上
		Point2d::operator+=(rhs);
		_z += rhs.z();
	}

protected:
	float _z;
};
```

在上述Point3d代码中，Point3d::z()、Point3d::z(float)和Point3d::operator+=(const Point2d &)成为了虚函数。每一个Point3d对象内含一个额外的继承自Point2d的vptr，多了一个Point3d vtbl，此外，每一个虚函数的调用也比以前复杂了([4. Function](#4-function)对此有详细的说明)

* vptr在对象中的存放位置

一种较早的方案是将vptr存放在类对象的尾端，这样可以保留基类C struct的对象布局。后来编译器开始把vptr存放在类对象的起始处，这样对于"在多重继承下，通过指向类成员的指针调用虚函数"会带来一些帮助(详见[4.4 指向Member Function的指针](#44-指向member-function的指针))，否则，不仅"从类对象起始点开始量起"的offset必须在运行时准备妥当，甚至与类vptr之间的偏差也必须准备妥当，当然代价就是丧失了C语言的兼容性

### 3.4.3 多重继承

```C++
class Point2d {
public:
	// 定义虚函数
	// Point2d对象中含有vptr
protected:
	float _x, _y;
};

class Point3d : public Point2d {
public:
	// ...
protected:
	float _z;
};

class Vertex {
public:
	// 定义虚函数
	// Vertex对象中含有vptr
protected:
	Vertex *next;
}

class Vertex3d : public Point3d, public Vertex {
public:
	// ...
protected:
	float mumble;
}
```

多重继承的问题主要发生于派生类对象和它的第二个及以后的基类对象之间的转换。不论是直接转换或是由其所支持的虚函数机制做转换。因支持虚函数的调用操作而引发的转换在[4.2 Virtual Member Functions(虚拟成员函数)](#42-virtual-member-functions虚拟成员函数)讨论

对于一个多重派生的对象，将其地址指定给继承列表中的第一个基类的指针，情况将和单一继承时相同，因为二者都指向相同的起始地址(编译器vptr存放于对象末端的情况)，需付出的成本只有地址的指定操作

第二个及后续的基类的地址指定操作时，需要将地址修改：加上(或减去，如果downcast的话)位于中间的基类subobject大小，代码示例如下↓：

```C++
Vertex3d v3d;
Vertex3d *pv3d;
Vertex *pv;
Point2d *p2d;
Point3d *p3d;

// 源代码
pv = &v3d;
// 内部转换如下
pv = (Vertex*)(((char*) &v3d) + sizeof(Point3d));

// 如下两行只需要简单地拷贝地址
p2d = &v3d;
p3d = &v3d;

// 源代码
pv = pv3d;
// 内部转换操作不能简单的转换，下一行为错误示范
pv = (Vertex*)(((char*) pv3d) + sizeof(Point3d));
// 上述代码无法确定pv3d是否为空指针，所以需要条件测试
pv = pv3d ?
	 (Vertex*)(((char*) pv3d) + sizeof(Point3d)) : 0;
```

C++标准并未要求多重继承情况下，派生类的多个基类的排列顺序

如果要访问第二个及其后继的基类的数据成员时，并不需要付出额外的成本，因为成员的位置在编译时就固定了，因此访问成员只是一个简单的offset运算，与单一继承类似(无论是经由一个指针、引用还是对象来访问)

### 3.4.4 虚继承

多重继承的一个副作用就是它必须支持某种形式的"shared subobject继承"。虚继承需要将基类中各自维护的共同的基类subobject折叠成为一个由派生类维护的单一基类subobject，而且还可以保存基类和派生类的指针及引用之间的多态指定操作

一般实现方法如下所述，类如果内含一个或多个虚基类subobject，那就将被分割为两部分，一个不变局部和一个共享局部，不变局部中的数据，不管后继如何衍化，总是拥有固定的offset(从对象开头算起)，所以这一部分数据可以被直接存取；至于共享局部，所表现的就是虚基类subobject。这一部分数据，其位置会因为每次的派生操作而有变化。所以它们只可以被间接存取。不同编译器的实现方法不同，下面说明三种主流策略，下面是是虚继承的层次结构↓：

```C++
class Point2d {
protected:
	float _x, _y;
};

class Vertex : public virtual Point2d {
protected:
	Vertex *next;
};

class Point3d : public virtual Point2d {
protected:
	float _z;
};

class Vertex3d : public Vertex, public Point3d {
protected:
	float mumble;
};
```

* 先安排好派生类的不变部分，然后再建立其共享部分

如何能够访问类的共享部分呢？一种方法是在每一个派生类对象中安插以下指针，每一个指针指向一个虚基类，要访问继承得来的虚基类成员，可以使用相关指针间接完成。转换方式如下↓：

```C++
// 源代码
void Point3d::operator+=(const Point3d &rhs) {
	_x += rhs._x;
	_y += rhs._y;
	_z += rhs._z;
}

// 可能的转换后的代码
// vbc指虚基类virtual base class
__vbcPoint2d->_x += rhs.__vbcPoint2d->_x;
__vbcPoint2d->_y += rhs.__vbcPoint2d->_y;
_z += rhs._z;
```

派生类和虚基类的实例之间的转换如下↓：

```C++
// 源代码
Point2d *p2d = pv3d;
// 可能的转换后的结果
Point2d *p2d = pv3d ? pv3d->__vbcPoint2d : 0;
```

该实现模型的缺点：1、每一个对象必须针对其每一个虚基类添加一个额外的指针；2、由于虚继承串链的加长，导致间接存取层次的增加

上述第一个缺点一般有两种解决方法，一是引入虚基类表，指向虚基类的指针放在虚基类表中，编译器安插一个指针指向虚基类表；二是在虚基类表中放置虚基类的offset，而不是地址

上述第二个缺点的一个解决方法为，经过拷贝取得所有的虚基类的指针，放到派生类对象中，实现了固定访问时间，但是付出了空间上的代价

* 经由一个非多态的类对象来访问一个继承而来的虚基类的成员

```C++
Point3d origin;
origin._x;
```

上述访问操作可以被优化为一个直接访问操作，就好像一个经由对象调用的虚函数调用操作，可以在编译时被决议(resolved)完成一样。在这次访问和下一次访问之间，对象的类型不可以改变，所以"虚基类subobject的位置会变化"的问题在这种情况下就不再存在了

一般而言，虚基类最有效的一种运用形式就是，一个抽象的虚基类，没有任何数据成员

## 3.6 指向数据成员的指针

```C++
class Point3d {
public:
	virtual ~Point3d();
protected:
	static Point3d origin;
	float x, y, z;
};
```

每一个Point3d对象含有三个坐标值以及一个vptr，static数据成员origin，将被放在类对象之外，唯一可能因编译器不同而不同的是vptr的位置。C++标准允许vptr被放在对象中的任何位置，然而实际上，编译器不是把vptr放在对象的头部，就是放在对象的尾部

取类数据成员的地址，将会得到成员在类对象中的偏移量(offset)，代码如下所示↓：

```C++
float Point3d::* px = &Point3d::x;
float Point3d::* py = &Point3d::y;
float Point3d::* pz = &Point3d::z;

// 在64位机器上
// sizeof(int*) == 8
// sizeof(float) == 4
// px = 8
// py = 12
// pz = 16
```

在古老的编译器上，对类数据成员取地址之后，还会在偏移量上加上1，是为了区分没有指向任何数据成员的空指针和指向类第一个数据成员的指针。不过现在的编译器都进行了优化

* 对象地址与指向数据成员的指针之间的相互转换

```C++
Point3d origin;

&origin.z = &origin + &Point3d::z
// 注意，&origin.z的类型为 float *
```

* 多重继承中的指向数据成员的指针

多重继承之下，若要将派生类的继承列表中的第二个及后继基类的指针与派生类对象绑定的成员结合起来，将会因为需要加入偏差值而变得相当复杂

```C++
class Base1 {int val1;};

class Base2 {int val2;};

class Derived :
	public Base1, public Base2 {};

void fun(int Derived::*dmp, Derived *pd) {
	pd->*dmp;
}

int Base2::* bmp = &Base2::val2;
auto *pd = new Derived;
fun(bmp, pd);

// 调用fun()经由编译器内部转换后的代码如下
fun(bmp ? bmp + sizeof(Base1) : 0, pd);
```

# 4. Function

C++支持三种类型的成员函数：static、nonstatic和virtual

static成员函数不允许访问nonstatic数据成员，并且也不能声明为const(后置const)

```C++
float Point3d::magnitude() const {
	return sqrt(_x * _x + _y * _y + _z * _z);
}

Point3d Point3d::normalize() const {
	register float mag = magnitude();
	Point3d normal;

	normal._x = _x / mag;
	normal._y = _y / mag;
	normal._z = _z / mag;

	return normal;
}
```

## 4.1 Member的各种调用方式

### 4.1.1 Nonstatic成员函数

C++的设计准则之一是nonstatic成员函数至少必须和一般的非成员函数有相同的效率。即`float magnitude3d(const Point3d *_this)`和`float Point3d::magnitude3d() const`有相同的效率。

* 编译器会将成员函数实体转换为对等的非成员函数实体，转换操作如下↓：
	* 源代码如下↓：

	```C++
	float Point3d::magnitude() const {
		return sqrt(_x * _x + _y * _y + _z * _z);
	}
	```

	1. 改写函数的signature(函数原型)，以安插一个额外的参数到成员函数中，用以提供一个访问管道，使类对象得以调用该函数，该额外参数被称为this指针
	```C++
	// 源代码
	float Point3d::magnitude() {}
	// 转换后
	float Point3d::magnitude(Point3d* const this) {}

	// 源代码
	float Point3d::magnitude() const {}
	// 转换后
	float Point3d::magnitude(const Point3d* const this) {}
	```

	2. 将每一个对nonstatic数据成员的访问操作改为经由this指针来访问
	```C++
	// 源代码
	return sqrt(_x * _x + _y * _y + _z * _z);
	// 转换后
	return sqrt(
		this->_x * this->_x +
		this->_y * this->_y +
		this->_z * this->_z
	);
	```

	3. 将成员函数重新写成一个外部函数，对函数名称进行mangling(重整)处理，使它在程序中成为独一无二的
	```C++
	// 部分转换后的源代码
	float Point3d::magnitude(Point3d* const this)
	// 转换后
	extern magnitude__7Point3dFv(register Point3d* const this);
	```

	4. 函数的调用操作也需要进行转换
	```C++
	// 源代码
	obj.magnitude();
	ptr->magnitude();
	// 转换后
	magnitude__7Point3dFv(&obj);
	magnitude__7Point3dFv(ptr);
	```

	* 另一个转换的例子(假设已经声明有Point3d的复制构造函数，NRV优化也已施行)
	```C++
	// 源代码
	Point3d Point3d::normalize() const {
		register float mag = magnitude();
		Point3d normal;

		normal._x = _x / mag;
		normal._y = _y / mag;
		normal._z = _z / mag;

		return normal;
	}

	// 转换后
	void normalize__7Point3dFv(
		register const Point3d* const this,
		Point3d &__result) {

		register float mag = this->magnitude();

		// 默认构造函数
		__result.Point3d::Point3d();

		__result._x = this->_x / mag;
		__result._y = this->_y / mag;
		__result._z = this->_z / mag;

		return;
	}
	```
	一个比较有效率的做法是直接构造返回值，这样可以节省默认构造函数初始化所引起的额外负担，代码示例如下所示↓：
	```C++
	// 源代码
	Point3d Point3d::normalize() const {
		register float mag = magnitude();
		return Point3d(_x / mag, _y / mag, _z / mag);
	}

	// 转换后
	void normalize__7Point3dFv(
		register const Point3d* const this,
		Point3d &__result) {

		register float mag = this->magnitude();

		// 默认构造函数
		__result.Point3d::Point3d(
			this->_x_x / mag,
			this->_x_y / mag,
			this->_x_z / mag);
		return;
	}
	```

* 名称的特殊处理(Name Mangling)

一般而言，成员函数的名称前面会加上类的名称，形成独一无二的命名，这样可以在类成员函数的重写中，可以清楚的确定调用的函数

由于成员函数也可以被重载，因此需要在名称上再加上它们的参数链表(可以从函数原型中参考得到)。如果把参数类型也编码进去，就可以制造出独一无二的结果。但是如果声明`extern "C"`，就会压抑非成员函数的mangling效果

同时，如果不同的函数实体都拥有独一无二的name mangling，那么任何不正确的调用操作在链接时期就会因为无法决议(resolved)而失败，这也算是一种确保类型安全的链接行为，但是只能捕捉函数的标记(signature，即函数名+参数数目+参数类型)错误，无法检查返回类型的错误

### 4.1.2 Virtual虚拟成员函数

* 虚函数的转换

```C++
// 源代码
ptr->normalize();
// 转换后
(* ptr->vptr[1])(this);
```

上述代码中，`vptr`表示由编译器产生的指针，指向vtbl。它被安插在每一个含有(声明或继承而来)虚函数的类对象中。并且vptr也会被mangled，因为在一个复杂的类派生体系中，可能存在有多个vptr；`[1]`是vtbl的slot的索引值，关联到normalize()函数；`this`表示this指针

类似的转换例子如下↓：

```C++
// 源代码
register float mag = magnitude();
// 转换后
register float mag = (*this->vptr[2])(this);
```

由于magnitude()是在normalize()中被调用，而normalize()已经由虚拟机制而决议(resolved)妥当，所以明确地调用Point实体(即使用类作用域运算符调用一个)会比较有效率，并因此压制由于虚拟机制而产生的不必要的重复调用操作。使用类作用域运算符调用一个虚函数，其决议方式会和nonstatic成员函数一样。如果magnitude()声明为inline函数会更有效率。代码示例如下↓：

```C++
// 源代码
register float mag = Point3d::magnitude();
// 转换后
register float mag = magnitude__7Point3dFv(this);
```

* 经由类对象调用一个虚函数，编译器会向对待一般nonstatic成员函数一样加以决议(resolved)

虚函数，特别是它们在继承机制下的行为，将在[4.2 Virtual Member Functions(虚拟成员函数)](#42-virtual-member-functions虚拟成员函数)有比较详细的讨论

### 4.1.3 Static静态成员函数

* 静态成员函数会被转换为一般的非成员函数调用，代码示例如下↓：

```C++
// 源代码
obj.normalize();
ptr->normalize();
// 转换后均为
normalize__7Point3dSFv();
```

* static成员函数特性：
	1. 主要特性：没有this指针
	2. 不能直接访问类中的nonstatic成员
	3. 不能被声明为const、volatile或virtual
	4. 不需要经由类对象才能被调用

* 代码转换

经过函数返回值得到的类对象调用static成员函数，该函数仍然会被评估求值(evaluated)

```C++
// 源代码
if(fun().object_count() > 1)
// 转换后
(void) fun();
if (Point3d::object_count() > 1)
```

* mangled

```C++
// 源代码
unsigned int Point3d::object() {
	return _object_count;
}
// 可能的转换后的结果
unsigned int object_count_5Point3dSFv() {
	return _object_count_5Point3d;
}
```

其中的SFv表示它是个static member function，拥有一个空白(void)的参数列表(argument list)

* 取地址

取一个static成员函数地址，得到的是其在内存中的地址。由于static成员函数没有this指针，所以其地址并不是一个"指向类成员函数的指针"，而是一个"非成员函数指针"。即函数`unsigned int Point3d::object()`取地址之后，指针类型为`unsigned int (*)();`，而不是`unsigned int (Point3d::*)();`

* static成员函数缺乏this指针，因此差不多等同于非成员函数，因此它比较适合作为一个回调函数，也比较适合应用在线程函数上

## 4.2 虚成员函数

虚函数的一般实现模型：每一个类有一个vtbl，内含该类中虚函数的地址，然后每个对象有一个vptr，指向vtbl的所在。在本节中将介绍一种可能的实现方法

### 4.2.1 单一继承下的虚函数

为了支持虚函数机制，必须能够对多态对象有某种形式的"运行时类型判断"。RTTI(runtime type identification，运行时类型识别)能够在运行时查询一个多态的指针或引用的类型(RTTI将在[7.3 运行时类型识别(RTTI)](#73-运行时类型识别rtti)讨论)

* RTTI

含有虚函数的类，就需要进行额外的运行时信息，以便实现RTTI

需要存储的额外的运行时信息包括：指针或引用所指对象的真实类型、虚函数实体的位置

* vtbl

vtbl是在编译阶段生成的，vtbl中的虚函数的地址也是编译阶段确定的，并且虚函数的地址是固定不变的，运行时不会新增或者替换。

为了能在运行时找到虚函数的地址，每一个类对象中都被安插上一个由编译器产生的vptr，指向vtbl；并且为了找到函数地址，每一个虚函数按照声明顺序被指派一个表格索引值。这些工作都是编译器完成，运行时要做的只是在记录着虚函数地址的vtbl slot中激活虚函数

一个类只会有一个vtbl，每一个vtbl内含其对应的类对象中所有虚函数实体的地址，包括：1、这个类所定义的函数实体，它会重写(overriding)一个可能存在的基类虚函数实体；2、继承自基类的函数实体(这是在派生类不重写虚函数时才会出现的情况)；3、一个`pure_virtual_called()`函数实体，它既可以扮演纯虚函数的空间保卫者角色，也可以当做运行时异常处理函数(有时候会用到)

每一个虚函数都被指派一个固定的索引值，这个索引在整个继承体系中保持与特定的虚函数的关联，代码示例如下↓：

```C++
class Point {
public:
	virtual ~Point();

	virtual Point& mult(float) = 0;

	float x() const (return _x;)
	virtual float y() const (return 0;)
	virtual float z() const (return 0;)

protected:
	Point(float x = 0.0);
	float _x;
};
```

在上述代码中，在Point中，虚析构函数被赋值slot 1，mult()被赋值slot 2，因为mult()是纯虚函数，所以pure_virtual_called()的函数地址会被放在slot 2中，如果该函数意外地被调用，通常的操作是结束掉这个程序。y()被赋值slot 3，z()倍赋值slot 4，x()不是虚函数，因此并没有被赋予slot

* 当一个类派生自一个基类时的三种可能性：
	1. 派生类可以继承基类所声明的虚函数的实体，该函数实体的地址会被拷贝到派生类的vtbl相对应的slot之中
	2. 派生类可以使用自己的函数实体进行重写，函数实体的地址必须放在对应的slot之中
	3. 派生类可以加入一个新的虚函数，这时候vtbl会在最后增加一个slot，而新的函数实体会被放进该slot之中

```C++
class Point2d : public Point {
public:
	Point2d(float x = 0.0, float y = 0.0) :
		Point(x), _y(y) {}
	~Point2d();

	// 重写基类虚函数
	Point2d& mult(float);
	float y() const {return _y;}
	// ...

protected:
	float _y;
};
```

在上述代码中，在Point2d中的vtbl中，slot 1指向析构函数，slot 2指向mult()(取代pure_virtual_called())，slot 3指向声明的y()，slot 4指向继承自Point的z()

```C++
class Point3d : public Point2d {
public:
	Point3d(float x = 0.0, float y = 0.0, float z = 0.0) :
		Point2d(x, y), _z(z) {}
	~Point3d();

	// 重写基类虚函数
	Point3d& mult(float);
	float z() const {return _z;}
	// ...
protected:
	float _z;
};
```

在上述代码中，在Point3d中的vtbl中，slot 1指向析构函数，slot 2指向Point3d::mult()(取代pure_virtual_called())，slot 3指向继承自Point2d的y()，slot 4指向声明的z()

* 虚函数调用

```C++
// 源代码
ptr->z();
// 转换后
(*ptr->vptr[4])(ptr);
```

在上述代码中，vptr表示编译器所安插的指向vtbl的指针vptr，`[4]`表示z()被复制的slot编号(关联到Point体系的vtbl)，唯一需要在运行时确定的是slot 4指向的z()函数实体

### 4.2.2 多重继承下的虚函数

在多重继承之下，一个派生类会含有n个vtbl，其中n表示该类上一层基类的数目(因此单一继承只含有一个vtbl)，包括一个主要实体，与继承列表中最左侧的基类共享，n-1个次要实体，与继承列表中第二个及后继的基类共享。针对每一个vtbl，派生类对象中都有对应的vptr，vptr将在构造函数中被设立初值

用以支持一个类拥有多个vtbl的传统方法是将每一个vtbl以外部对象的形式产生出来，并赋予独一无二的名字。比如`class Derived : public Base1, public Base2`所关联的两个vtbl可能的名字为`vtbl__Derived`与`vtbl__Base__Derived`。于是当将一个Derived对象地址指定给一个Base1指针或Derived指针时，被处理的vtbl是主要表格`vtbl__Derived`，当将一个Derived对象地址指定给一个Base2指针时，被处理的vtbl是次要表格`vtbl__Base__Derived`

在多重继承中，派生类会继承所有基类的虚函数，如果派生类重写了继承而来的虚函数，那么会在所有的vtbl中更新被重写的虚函数。而如果派生类新增了虚函数，则该虚函数指针会添加到主要表格(即继承列表中第一个基类的vtbl)的最后面

### 4.2.3 虚继承下的虚函数

太复杂书里没讲，建议就是不要在虚基类中声明nonstatic数据成员

## 4.4 指向Member Function的指针

在[3.6 指向数据成员的指针](#36-指向数据成员的指针)可见，取类nonstaitc**数据**成员的地址，将会得到成员在类对象中的偏移量，该偏移量必须绑定于某个类对象的地址上，才能够被访问

使用一个成员函数指针，如果并不用于虚函数、多重继承、虚基类等情况的话，并不会比使用一个非成员函数指针的成本更高

* nonstatic nonvirtual成员函数

取一个nonstatic nonvirtual成员函数的地址，将得到该成员函数在内存中真正的地址。但是如果想调用该成员函数的话，还是需要绑定于某个类对象的地址上，才能够通过该指针调用该函数。所有的nonstatic成员函数都需要对象的地址(以参数this指出)。代码示例如下↓：

```C++
class Point {
public:
	double x() {return _x;}
	double y() {return _y;}
protected:
	double _x, _y;
};

Point origin;
Point *ptr = new Point;

// 指向成员函数的指针声明语法如下
double (Point::* pmf)() = &Point::fun;
pmf = &Point::y;

// 通过指针调用成员函数
(origin.*pmf)();
(ptr->*pmf)();
// 转换后
(pmf)(&origin);
(pmf)(ptr);
```

* static成员函数

指向static成员函数的指针就是普通的函数指针，代码声明如下↓：

```C++
class Point {
public:
	static double fun() {return 0.0;}
};

// 声明
double (*pmf)() = &Point::fun;
// 调用
(*pmf)();
```

### 4.4.1 支持"指向虚函数"的指针

```C++
class Point {
public:
	virtual float z() const (return 0;)
};

class Point2d : public Point {
	// ...
};

class Point3d : public Point2d {
public:
	float z() const {return _z;}
	// ...
protected:
	float _z;
};
```

编译时期虚函数的地址是未知的，对虚成员函数取其地址，将得到该函数在vtbl中的索引值，通过指向虚成员函数的指针来调用虚函数，会在内部转化为一个编译时期的表达式，代码示例如下↓：

```C++
Point *ptr = new Point3d;

// 声明
float (Point::*pmf)() = &Point::z;

// 以下调用均调用Point3d::z()
ptr->z();
(ptr->*pmf)();

// 源代码
(ptr->*pmf)();
// 转化后
// ->优先级比*高
(* ptr->vtbl[(int)pmf])(ptr);
```

对一个"指向成员函数的指针"评估求值(evaluated)，会因为该指针有两种意义而复杂化：该函数指针能够寻址出非虚函数`float Point::x() {return _x;}`和虚函数`float Point::z() {return 0;}`两种成员函数，而且这两个函数还可能有相同的原型，指向非虚函数的指针代表内存地址，指向虚函数的指针代表vtbl中的索引值

### 4.4.2 在多重继承之下，指向成员函数的指针

不同的编译器有不同的实现

* 用结构体实现的方式

通过使用一个结构体，来支持多重继承之下指向成员函数的指针，结构体代码示例如下↓：

```C++
struct __mptr {
	int delta;
	int index;
	union {
		ptrtofunc faddr;
		int v_offset;
	};
};
```

在上述代码中，delta表示this指针的偏差值；v_offset存放的是一个虚基类的vptr位置(如果vptr被编译器放在类对象的前段，v_offset字段就没有必要了，代价是C对象兼容性降低)；这两个字段只在多重继承或虚继承的情况下才有必要

index和faddr分别不同时带有vtbl索引和非虚成员函数地址。当为虚成员函数时，index指向vtbl，当为非虚成员函数时，index为-1，faddr指向非虚成员函数地址。函数调用代码如下所示↓：

```C++
// 源代码
(ptr->*pmf)();
// 转化后
(pmf.index < 0) ?
	(*pmf.faddr)(ptr) :	// 非虚成员函数
	(* ptr->vtbl[pmf.index](ptr));	// 虚函数
```

缺点：每一个调用操作都需要付出上述成本，检查其是否是虚函数；另一个缺点是当传递一个不变值的指针给成员函数时，它需要产生一个临时对象，代码如下所示↓：

```C++
// 源代码
extern Point3d fun(const Point3d&, Point3d (Point3d::*)());
void bar(const Point3d &p) {
	Point3d pt = fun(p, &Point3d::normal);
}

// 此时&Point::normal的值可能为
// {0, -1, 10727417}

// 转化后
__mptr temp = {0, -1, 10727417};
fun(p, temp);
```

## 4.5 Inline Functions

一般而言，处理inline函数有两个阶段：

1. 分析函数定义，以决定函数是否可以被inline。如果函数因为复杂度或其构造问题，被判断不可成为inline，它会被转为一个static函数，并在被编译模块内产生对应的函数定义

2. 真正的inline函数扩展操作是在调用的那一点上，这会带来参数的求值操作以及临时性对象的管理

### 4.5.1 形式参数(Formal Arguments)

在inline扩展期间，每一个形式参数都会被对应的实际参数去掉，但是不可以只是简单的替换程序中出现的形式参数，因为这将会导致对实际参数的多次求值。一般而言，对于"会带来副作用的实参"，通常都需要引入临时对象。换句话说，如果实参是一个常量表达式，可以在替换之前完成其求值操作，后续的inline函数就可以直接使用常量。如果既不是个常量表达式，也不是个带有副作用的表达式，那么就直接替换。代码示例如下↓：

```C++
inline int min(int i, int j) {
	return i < j ? i : j;
}

inline int bar() {
	int minVal;
	int val1 = 1024;
	int val2 = 2048;

	// 源代码
	minVal = min(val1, val2);
	// 转换后
	minVal = val1 < val2 ? val1 : val2;

	// 源代码
	minVal = min(1024, 2048);
	// 转换后
	minVal = 1024;

	// 源代码
	minVal = min(fun(), bar() + 1);
	// 转换后
	int t1, t2;
	minVal = (t1 = fun()), (t2 = bar() + 1),
		t1 < t2 ? t1 : t2;
}

```

### 4.5.2 局部变量(Local Variables)

inline被扩展开后，为了维护其局部变量，每一个局部变量都必须被放在函数调用的一个封闭区段中，拥有一个独一无二的名字。如果inline函数以单一表达式(expression)扩展多次，那么每次扩展都需要自己的一组局部变量。如果inline函数以分离的多个式子(discrete statements)被扩展多次，那么只需一组局部变量，就可以重复使用(因为它们被放在一个封闭区段中，有自己的作用域)。inline函数中的局部变量，在加上有副作用的参数，可能会导致大量临时对象的产生，特别是如果它以单一表达式(expression)扩展多次的话。代码示例如下↓：

```C++
inline int min(int i, int j) {
	int minVal = i < j ? i : j;
	return minVal;
}

int local, minVal;

// 源代码
minVal = min(val1, val2);
// 转换后
int __min_lv_minVal;
minVal = (__min_lv_minVal =
			val1 < val2 ? val1 : val2),
		__min_lv_minVal;

// 源代码
minVal = min(val1, val2) +
	min(fun(), fun() + 1);
// 转换后
// 为局部变量产生临时变量
int __min_lv_minVal__00;
int __min_lv_minVal__01;
// 为放置副作用值而产生临时变量
int t1;
int t2;

minVal =
	((__min_lv_minVal__00 =
		val1 < val2 ? val1 : val2),
	__min_lv_minVal__00) +
	((__min_lv_minVal__01 =
		(t1 = fun()),
		(t2 = fun() + 1),
		t1 < t2 ? t1 : t2),
	__min_lv_minVal__01);
```

inline函数对于封装提供了一种必要的支持，可以有效存取封装于class中的nonpublic数据，它同时也是C程序中大量使用的`#define`(前置处理宏)的一个安全替代品(特别是如果宏中的参数有副作用的话)。然而一个inline函数如果被调用太多次的话，会产生大量的扩展码，使程序的大小暴涨

# 5. 构造、析构、拷贝

一般而言，类的数据成员应该被初始化，并且只在构造函数中或是在类的其他成员函数中指定初值，其他任何操作都将破坏封装性质，使class的维护和修改更加困难

可以静态调用纯虚函数，但不能经由虚拟机制调用。代码示例如下↓：

```C++
class Abstract_base {
public:
	virtual ~Abstract_base() = 0;
	virtual void interface() const = 0;
	virtual const char* mumble() const {
		return _mumble;
	}

protected:
	char* _mumble;
};

class Concrete_derived : public Abstract_base {
public:
	Concrete_derived();
	//...
};

// 静态调用纯虚函数如下
inline void Concrete_derived::interface() const {
	Abstract_base::interface();
}
```

上述代码中的Abstract_base定义较差，优化后代码如下所示↓：

```C++
class Abstract_base {
public:
	virtual ~Abstract_base();
	virtual void interface() = 0;
	const char* mumble() const {
		return _mumble;
	}

protected:
	Abstract_base(char *pc = 0);
	char *_mumble;
};
```

## 5.1 无继承情况下的对象构造

本节考虑如下程序片段↓：

```C++
Point global;	// L1

Point fun() {
	Point local;	// L5
	Point *heap = new Point;	// L6
	*heap = local;	// L7
	// ...
	delete heap;	// L9
	return local;	// L10
}
```

对象产生方式有三种：全局内存配置(L1)、局部内存配置(L5)和堆内存配置(L6)。一个对象的生命周期，是该对象的一个运行时属性。全局对象的生命周期和整个程序的生命周期相同，局部对象的生命周期从定义开始，到当前作用域结束为止，堆对象的生命周期从该对象被new运算符构造开始，到该对象被delete运算符析构为止

下面考虑Point的第一种声明，C++标准称之为Plain Of Data(简单的数据)，代码示例如下↓：

**(第一种声明书上与实际有冲突，下面的看着图一乐)**

```C++
typedef struct {
	float x, y, z;
} Point;
```

C++编译器会为Point声明一个无用的(trivial)构造函数、析构函数、拷贝构造函数与拷贝赋值函数。编译器会分析该声明，并为它贴上Plain Of Data的卷标

当定义全局变量`Point global;`(L1)时，运行了默认构造函数

注意：在C语言中，全局对象被视为一个"临时性的定义"，因为它没有明确的初始化操作，一个"临时性的定义"可以在程序中发生多次，那些示例会被链接器折叠起来，只留下一个单独实体，被放在程序的数据段中一个"特别保留给未初始化的全局对象使用"的空间(即BSS)。C++并不支持"临时性的定义"，全局对象在C++中被视为完全定义(它会阻止第二个或更多个定义)。C和C++的一个差异就在于BSS数据段在C++中相对地不重要，C++的所有全局对象都被当做"初始化过的数据"来对待

定义局部对象`Point local;`(L5)时没有运行构造函数

堆对象`Point *heap = new Point;`(L6)的初始化操作会被转换为对new运算符的调用：`Point *heap = __new(sizeof(Point));`。注意new运算符传回的Point对象上，运行了默认构造函数

赋值操作`*heap = local;`(L7)进行了逐位拷贝操作

delete操作`delete heap;`(L9)会转换为对delete运算符的调用：`__delete(heap);`。同时析构函数要么没有被定义要么就是没有被调用

返回操作`return local;`(L10)会执行简单的逐位拷贝操作

### 5.1.1 抽象数据类型

以下是Point的第二种声明，多了private数据，提供完整的封装性，但没有提供任何虚函数，代码示例如下↓：

```C++
class Point {
public:
	Point(float x = 0.0, float y = 0.0, float z = 0.0) :
		_x(x), _y(y), _z(z) {}
private:
	float _x, _y, _z;
};
```

该经过封装的Point类，大小并没有改变，还是三个连续的float，无论是public、protected、private等访问控制符，还是成员函数的声明，都不会占用额外的对象空间

* 定义全局对象`Point global;`(L1)

运行了默认的构造函数`Point::Point(0.0, 0.0, 0.0);`。由于global被定义在全局范畴中，其初始化操作将延迟到程序激活(startup)时才开始(详见[6.1 对象的构造和析构](#61-对象的构造和析构))

* 对成员设定常量初值(inline扩展)

对类中的所有成员设定常量初值，相比赋值来说，使用显式初始化列表会较好。代码示例如下↓：

```C++
// 较快
Point local1 = Point(0.0, 0.0, 0.0);
// 较慢
Point local2;
local2._x = 0.0;
local2._y = 0.0;
local2._z = 0.0;
```

显式初始化列表的缺点：1、只有当类成员都是public时才有效(为啥?)；2、只能指定常量，因为常量可以在编译时期就可以被评估求值(evaluated)；3、由于编译器并没有自动施行，因此初始化行为失败的可能性会较高一些

* 定义局部对象`Point local;`(L5)

在编译器层面，会有一个优化机制用来识别简单地提供一个逐成员的常量指定操作的inline构造函数，然后编译器会抽取出那些值，并且像显式初始化列表一样进行处理，而不会把构造函数扩展为一系列的赋值指令。代码示例如下↓：

```C++
// 源代码
Point local;
// 转化后
Point local;
local._x = 0.0;
local._y = 0.0;
local._z = 0.0;
```

* 定义堆对象`Point *heap = new Point;`(L6)

```C++
// 源代码
Point *heap = new Point;
// 转化后
Point *heap = __new(sizeof(Point));
if (heap != 0)
	heap->Point::Point();
// 然后进行与定义局部变量相同的inline扩展
```

* 赋值操作`*heap = local;`(L7)：进行逐位拷贝

* delete操作`delete heap;`(L9)：该操作并不会导致析构函数被调用，因为并没有明确地提供一个析构函数实体

* 返回操作`return local;`(L10)：进行逐位拷贝

### 5.1.2 为继承做准备

以下是Point的第三种声明，将为"继承性质"以及某些操作的动态决议(dynamic resolution)做准备，当前限制对z成员的访问操作，代码示例如下↓：

```C++
class Point {
public:
	Point(float x = 0.0, float y = 0.0) :
		_x(x), _y(y) {}
	virtual float z();
private:
	float _x, _y;
};
```

* 虚函数的引入的影响：
	1. 每个Point对象拥有一个指向vtbl的vptr
	2. 定义的构造函数被附加了代码，以便将vptr初始化。这些代码在所有基类构造函数的调用之后，该类的用户代码之前。代码示例如下↓：
	```C++
	// 源代码
	Point(float x = 0.0, float y = 0.0) :
		_x(x), _y(y) {}
	// 转化后
	Point* Point::Point(Point* this, float x, float y) :
		_x(x), _y(y) {
			// 设定对象的vptr
			this->vptr_Point = __vtbl__Point;

			// 扩展成员初始化列表
			this->_x = x;
			this->_y = y;

			// 返回this对象
			return this;
		}
	```
	3. 合成一个有用的复制构造函数与复制赋值函数(但隐式析构函数仍然是无用的)。如果一个Point对象被初始化或以一个派生类对象赋值，那么逐位的操作可能给vptr带来非法设定。代码示例如下↓：
	```C++
	// 合成的复制构造函数
	inline Point* Point::Point(Point *this, const Point &rhs) {
		// 设定对象的vptr
		this->__vptr__Point = __vtbl__Point;

		// 将rhs中的连续位拷贝到this对象
		// 或是经由成员赋值提供一个成员

		return this;
	}
	```
	编译器在优化状态下可能会把对象的连续内容拷贝到另一个对象身上，而不会是按一个精确地逐成员的赋值操作。C++标准要求编译器尽量延迟有用的成员函数的实际合成操作，直到真正遇到其使用场合为止

* 定义全局对象`Point global;`(L1)、定义堆对象`Point *heap = new Point;`(L6)、delete操作`delete heap;`(L9)与上述第二中Point的声明的实现效果相同

运行了默认的构造函数`Point::Point(0.0, 0.0, 0.0);`。由于global被定义在全局范畴中，其初始化操作将延迟到程序激活(startup)时才开始(详见[6.1 对象的构造和析构](#61-对象的构造和析构))


* 定义局部对象`Point local;`(L5)与返回操作`return local;`(L10)：

在编译器层面，会有一个优化机制用来识别简单地提供一个逐成员的常量指定操作的inline构造函数，然后编译器会抽取出那些值，并且像显式初始化列表一样进行处理，而不会把构造函数扩展为一系列的赋值指令。代码示例如下↓：

```C++
// 源代码
Point fun() {
	Point local;	// L5
	return local;	// L10
}
// 转化后
Point fun(Point &__result) {
	Point local;
	local.Point::Point(0.0, 0.0);

	// 拷贝构造函数的应用
	__result.Point::Point(local);

	// local对象的析构函数
	local.Point::~Point();

	return;
}

// 支持NRV优化后的转化
Point fun(Point &__result) {
	__result.Point::Point(0.0, 0.0);

	return;
}
```

## 5.2 继承体系下的对象构造

编译器对构造函数的扩充程度视该类的继承体系而定，一般而言所做的操作如下↓：

1. 调用所有虚基类的构造函数，从左到右，从最深到最浅
	1. 如果虚基类被列于成员初始化列表中，此时如果有任何明确指定的参数，都会传递过去，如果没有列于初始化列表中，而类有一个默认构造函数，也应该调用
	2. 类中的每一个虚基类的subobject的偏移量必须在运行时可以存取
	3. 如果类对象是最底层(most-derived)的类，其构造函数可能被调用；某些用以支持这个行为的机制必须被放进来
2. 所有上一层的基类的构造函数必须被调用，以基类的声明顺序为顺序(与在成员初始化列表中的顺序无关)
	1. 如果基类被列于成员初始化列表中，那么任何明确指定的参数都应该传递过去
	2. 如果基类没有被列于成员初始化列表中，而它有默认构造函数(或默认memberwise拷贝构造函数)，那么就调用该函数
	3. 如果虚基类是多重继承下的第二或后继的基类，那么this指针必须有所调整
3. 如果类对象有vptr，那么为vptr设定初值，指向适当的vtbl
4. 记录在成员初始化列表中的成员函数初始化操作会被放进构造函数的本身，并以成员的声明顺序为顺序
5. 如果有一个成员并没有出现在成员初始化列表中，但它有一个默认构造函数，那么就调用该默认构造函数

含有拷贝构造函数、拷贝赋值操作符、虚析构函数的Point类声明如下↓：

```C++
class Point {
public:
	// 构造函数
	Point(float x= 0.0, float y = 0.0);
	// 拷贝构造函数
	Point(const Point&);
	// 拷贝赋值操作
	Point& operator=(const Point&);

	virtual ~Point();

	virtual float z() {return 0.0;}

protected:
	float _x, _y;
};
```

以下为构造函数扩充的代码示例↓：

```C++
class Line {
public:
	Line(float = 0.0, float = 0.0, float = 0.0, float = 0.0);
	Line(const Point&, const Point&);

	draw();
private:
	Point _begin, _end;
};

// 构造函数源代码
Line::Line(const Point &begin, const Point &end) :
	_end(end), _begin(begin) {}
// 扩充后代码(注意begin与end顺序)
Line* Line::Line(Lint *this, const Point &begin, const Point &end) {
	this->_begin.Point::Point(begin);
	this->end.Point::Point(end);
	return this;
}
```

当定义Line对象时(`Line a;`)，编译器会合成Line的隐式析构函数(因为Line只是内含Point对象而不是继承自Point，因此析构函数不是虚函数)。在析构函数中成员对象的析构函数会按照构造的相反顺序调用。注意，虽然Point的析构函数是虚函数，但是在包含Point类对象的析构函数中，Point析构函数会被静态地决议出来(resolved statically)。代码示例如下↓：

```C++
inline void Line::~Line(Line *this) {
	this->_end.Point::~Point();
	this->_begin.Point::~Point();
}
```

当通过赋值运算符定义Line对象时(`Line b = a;`)，编译器会合成隐式Line拷贝构造函数，成为一个inline public成员

当通过赋值运算符为一个Line对象赋值时(`b = a;`)，编译器会合成隐式Line拷贝赋值操作符函数，成为一个inline public成员

### 5.2.1 虚继承

只有当一个完整的类对象被定义出来时，虚基类的构造函数才会调用；如果某个对象只是另一个完整对象的subobject，则不会调用虚基类的构造函数。也就是说，当一个类含有一个虚基类时，如果该类没有子类对象，那么该类就是最底层的(most derived)，会调用虚基类的构造函数；如果该类还有子类，那么就不会调用虚基类的构造函数。代码示例如下↓：

```C++
// 类声明
class Point3d : public virtual Point {
public:
	Point3d(float x = 0.0, float y = 0.0, float z = 0.0) :
		Point(x, y), _z(z) {}
	Point3d(const Point3d &rhs) :
		Point(rhs), _z(rhs.z) {}
	~Point3d();
	Point3d &operator=(const Point3d&);

	virtual float z() {return _z;}

protected:
	float _z;
};

// 派生情况定义
class Vertex : public virtual Point {...};
class Vertex3d : public Point3d, public Vertex{...};
class PVertex : public Vertex3d {...};

// Point3d扩充后的构造函数
Point3d* Point3d::Point3d(Point3d *this,
	bool __most_derived, float x, float y, float z) {

	if (__most_derived)
		this->Point::Point(x, y);

	this->__vptr_Point3d = __vtbl_Point3d;
	this->__vtbl_Point3d__Point = __vtbl_Point3d__Point;
	this->_z = z;

	return this;
}

// Vertex3d扩充后的构造函数
Vertex3d* Vertex3d::Vertex3d(Vertex3d *this,
	bool __most_derived, float x, float y, float z) {

	if (__most_derived)
		this->Point::Point(x, y);

	// 调用上一层基类的构造函数
	// 设定__most_derived为false
	this->Point3d::Point3d(false, x, y);
	this->Vertex::Vertex(false, x, y);

	// 设定vtprs
	// 安插用户代码
	// ...

	return this;
}
```

### 5.2.2 vptr初始化

当定义一个PVertex对象时(类声明见上一节)，构造函数的调用顺序是：`Point(x, y);`、`Point3d(x, y, z);`、`Vertex(x, y, z);`、`Vertex3d(x, y, z);`、`PVertex(x, y, z);`

在一个类的构造函数和析构函数中，经由构造中的对象来调用一个虚函数，实际调用的函数应该是在该类中有作用的(声明的)那个。构造函数的调用顺序是：自底向上、由内而外。当基类的构造函数执行时，派生类实体还没有构造出来；基类构造函数执行之后，只有基类subobject构造完毕

在构造函数中调用虚函数，会将每一个调用操作以静态方式决议，不会用到虚拟机制。如果构造函数中调用的虚函数中又调用了一个虚函数，这种情况下该虚函数也会被静态决议

vptr的初始化时间：在基类构造函数调用操作之后，在用户代码或是成员初始化列表中所列的成员初始化操作之前

构造函数的执行算法：1、在派生类构造函数中，所有虚基类及上一层基类的构造函数会被调用；2、初始化对象的vptr，指向相关的vtbl；3、如果有成员初始化列表的话，将在构造函数体内扩展开来；4、执行用户代码。代码示例如下↓：

```C++
// 源代码
// 注：继承体系中的每一个类均定义了一个虚函数size()
PVertex::PVertex(float x, float y, float z) :
	_next(0), Vertex3d(x, y, z), Point(x, y) {

	if (spyOn)
		cout << "Within PVertex::PVertex()"
			<< "size: " << size() << endl;
}

// 扩充后
PVertex* PVertex::PVertex(PVertex* this,
	bool __most__derived, float x, float y, float z) {

	// 条件式调用虚基类构造函数
	if (__most__derived)
		this->Point::Point(x, y);

	// 无条件调用上一层基类构造函数
	this->Vertex3d::Vertex3d(x, y, z);

	// 将相关的vptr初始化
	this->__vptr_PVertex = __vtbl_PVertex;
	this->__vptr_Point__PVertex =
		__vtbl_Point__PVertex;

	// 用户代码
	if (spyOn)
		cout << "Within PVertex::PVertex()"
			<< "size: "
			// 经由虚拟机制调用
			<< (*this->__vptr__PVertex[3].faddr)(this)
			<< endl;

	return this;
}
```

vptr必须被设定的情况：1、当一个完整的对象被构造起来时，如果声明一个Point对象，Point的构造函数必须设定其vptr；2、当一个subobject调用了一个虚函数时(无论直接调用还是间接调用)

在类的构造函数的成员初始化列表中调用该类的一个虚函数是安全的，vptr保证能够在成员初始化列表被扩展之前，由编译器正确设定好，但是在语义上这可能是不安全的，因为函数本身可能还需要依赖未被设立初值的成员

## 5.3 拷贝赋值操作符函数

禁止将一个对象赋值给另一个对象的方法：将拷贝赋值操作符声明为private()，并且不提供其定义。声明为private保证了除了成员函数和友函数之外无法进行赋值操作，不提供定义保证了成员函数和友函数如果进行拷贝，程序在链接时就会报错

当类表现出bitwise copy语意时，编译器不会合成拷贝赋值操作符函数，而是会进行逐位拷贝赋值操作。当类不表现出bitwise copy语意时，编译器会合成拷贝赋值操作符函数。编译器需要合成拷贝赋值操作符函数的情况包括如下四种：1、类内含有一个成员对象，该成员对象有拷贝赋值操作符；2、类的基类中有拷贝赋值操作符；3、类声明了虚函数；4、类继承自一个虚基类(此时无论基类是否含有拷贝赋值操作符)

编译器在继承的情况下，合成拷贝赋值操作符的代码示例如下↓：
```C++
// 基类
class Point {
public:
	Point(Point x = 0.0, float y = 0.0);
	// 没有虚函数
protected:
	float _x, _y;
};

inline Point& Point::operator=(const Point &p) {
	_x = p._x;
	_y = p._y;
	return *this;
}

// 派生类
class Point3d : public virtual Point {
public:
	Point3d(float x = 0.0, float y = 0.0, float z = 0.0);

protected:
	float _z;
};
// 编译器合成的派生类拷贝赋值操作符
inline Point3d&
Point3d::operator=(Point3d* const this, const Point3d &p) {
	// 调用基类的函数实体
	this->Point::operator=(p);

	// 逐成员拷贝派生类成员
	_z = p._z;
	return *this;
}

// 源代码
class Vertex : public virtual Point{};
inline Vertex& Vertex::operator=(const Vertex &v) {
	this->Point::operator=(v);
	_next = v._next;
	return *this;
}
class Vertex3d :
	public virtual Point3d, public virtual Vertex{};
// 编译器合成的派生类拷贝赋值操作符
inline Vertex3d&
Vertex3d::operator=(Vertex3d* const this, const Vertex3d &v) {
	this->Point::operator=(v);
	this->Point3d:operator=(v);
	this->Vertex::operator=(v);
	// ...
}
```

C++标准中并没有规定虚基类的subobject是否应该被隐式定义的拷贝赋值操作符赋值内容一次以上

## 5.5 析构

如果类没有定义析构函数，那么只有在类含有的成员对象或者类的基类拥有析构函数的情况下，编译器才自动合成出一个析构函数。编译器是否合成析构函数与虚函数和虚基类无关。所以是否合成构造函数与是否合成析构函数是不对称的

编译器扩展用户定义的析构函数的步骤如下：
1. 执行用户定义的析构函数代码
2. 如果该类含有类成员对象，类成员对象含有析构函数，那么会按照类成员对象声明顺序的相反顺序调用类成员对象的析构函数
3. 如果该类含有vptr，那么会重新设定(reset)，使之指向适当的基类的vtbl
4. 如果有任何直接的(上一层)的基类拥有析构函数，那么它们会以其声明顺序的相反顺序被调用
5. 如果有任何虚基类拥有析构函数，且当前该类是most-derived的类，那么它们会以其原来的构造顺序的相反顺序被调用

# 6. 运行时

代码扩充小例子↓：

```C++
// 类声明
class Y {
public:
	Y();
	~Y();
	bool operator==(const Y&) const;
};

class X {
public:
	X();
	~X();
	// conversion运算符
	operator Y() const;
	X getValue();
};

// 源代码
X xx;
Y yy;
if (yy == xx.getValue())
	// ...
// 按照函数调用扩充后
if (yy.operator==(xx.getValue().operator Y()))
// 实际扩充后
X temp1 = xx.getValue();
Y temp2 = temp1.operator Y();
int temp3 = yy.operator==(temp2);

if (temp3)
	// ...

temp2.Y::~Y();
temp1.X::~X();
```

## 6.1 对象的构造和析构
### 6.1.1 全局对象
### 6.1.2 局部静态对象
### 6.1.3 对象数组
### 6.1.4 Default Constructors和数组

## 6.2 new和delete运算符
### 6.2.1 针对数组的new语意
### 6.2.2 Placement Operator new的语意

## 6.3 临时性对象
### 6.3.1 临时性对象的迷思(神话、传说)

# 7. 站在对象模型的尖端

## 7.1 Template
### 7.1.1 Template的"实例化"行为
### 7.1.2 Template的错误报告
### 7.1.3 Template中的名称决议方式(Name Resolution within a Template)
### 7.1.4 Member Function的实例化行为

## 7.2 异常处理
### 7.2.1 Exception Handling快速检阅
### 7.2.2 对Exception Handling的支持

## 7.3 运行时类型识别(RTTI)
### 7.3.1 Type-Safe Downcast（保证安全的向下转型操作）
### 7.3.2 Type-Safe Dynamic Cast(保证安全的动态转型)
### 7.3.3 References并不是Pointers
### 7.3.4 Typeid运算符

## 7.4 效率有了，弹性呢
### 7.4.1 动态共享函数库
### 7.4.2 共享内存
