# cmake

## CMakeLists.txt基本语法

* `cmake_minimum_required(VERSION 3.4.1)`：指定需要的最小的cmake版本

* `project(<NAME>)`：设置项目名称

* `add_executable(<NAME> <源文件>)`：生成可执行文件

* `add_library`：添加库
    * `add_library(<库文件> [STATIC | SHARED | MODULE] [EXCLUDE_FROM_ALL] <源文件1> <源文件2> ... <源文件n>)`：添加一个库文件
        * `STATIC | SHARED | MODULE`：库类型，`STATIC`对应静态库，`SHARED`对应共享动态库
        * `EXCLUDE_FROM_ALL`： 如果指定了这一属性，对应的一些属性会在目标被创建时被设置(指明此目录和子目录中所有的目标，是否应当从默认构建中排除, 子目录的IDE工程文件/Makefile将从顶级IDE工程文件/Makefile中排除
    * `add_library(<库文件> [STATIC | SHARED | MODULE | UNKNOWN] IMPORTED)`：导入已经存在的库文件，一般配合`set_target_properties`指令使用

* `set_target_properties(<目标库名称> <要设置的参数> <导入库的路径>)`：指定导入库的路径

* `aux_source_directory(<路径> <变量>)`：在路径下搜索所有源文件并保存到相应的变量中

* `find_library(<变量> <库文件> <路径>)`：查找指定的预编译库，并将它的路径存储在变量中

* `include_directories(<头文件路径>)`：添加头文件位置，可以使用绝对路径，也可以使用自定义的变量值

* `target_link_libraries(<目标库文件> <lib1> <lib2> ... <libn>)`：将lib链接到目标库文件上，若lib1依赖lib2，则lib1应在lib2前面

* `set(<变量名> <变量值1> <变量值2> ... <变量值n>)`：设置CMake变量
    * `set(SRC_LIST main.cpp test.cpp)`：直接设置变量的值
    * `set(SRC_LIST ${SRC_LIST} test.cpp)`：追加设置变量的值
    * 常用变量如下：
    * `PROJECT_SOURCE_DIR`：工程的根目录
    * `PROJECT_BINARY_DIR`：运行cmake命令的目录，通常为${PROJECT_SOURCE_DIR}/build
    * `PROJECT_NAME`：返回通过 project 命令定义的项目名称
    * `CMAKE_CURRENT_SOURCE_DIR`：当前处理的 CMakeLists.txt 所在的路径
    * `CMAKE_CURRENT_BINARY_DIR`：target 编译目录
    * `CMAKE_CURRENT_LIST_DIR`：CMakeLists.txt 的完整路径
    * `EXECUTABLE_OUTPUT_PATH`：重新定义目标二进制可执行文件的存放位置
    * `LIBRARY_OUTPUT_PATH`：重新定义目标链接库文件的存放位置
    * `CMAKE_INSTALL_PREFIX`：cmake执行install目标时安装的路径前缀

* `add_subdirectory(<子目录位置> [输出路径])`：如果当前目录下还有子目录时可以使用add_subdirectory，子目录中也需要包含有CMakeLists.txt

* `file`：文件操作
    * `file(WRITE <文件名> "message")`：将message写入文件中，会覆盖原内容
    * `file(APPEND <文件名> "message")`：在文件末尾追加内容
    * `file(READ <文件名> <变量> [LIMIT numBytes] [OFFSET offset] [HEX])`：从文件中读取内容并存储到变量中
        * `offset`：开始读取的偏移量
        * `numBytes`：最多读取字节数
        * `HEX`：以16进制读取
    * `file(RENAME <原文件名> <新文件名>)`：重命名文件
    * `file(REMOVE <文件>)`：删除文件
    * `file(REMOVE_RECURSE <文件>)`：递归删除文件
    * `file(DOWNLOAD <url> <文件> [TIMEOUT timeout] [STATUS status] [LOG log] [EXPECTED_MD5 sum] [SHOW_PROGRESS])`：根据指定的url下载文件
        * `timeout`：超时时间;
        * `status`：下载的状态保存路径
        * `log`：下载日志保存路径
        * `sum`：所下载文件预期的MD5值,如果指定会自动进行比对，如果不一致，则返回一个错误
        * `SHOW_PROGRESS`：进度信息会以状态信息的形式被打印出来
    * `file(MAKE_DIRECTORY <目录>)`：创建目录
    * `file(TO_CMAKE_PATH <路径> <变量>)`：将路径转换为以Unix的`/`开头的cmake风格路径，保存在变量中
    * `file(TO_NATIVE_PATH <路径> <变量>)`：将cmake风格的路径转换为本地路径风格
    * `file(GLOB <变量> [RELATIVE path] [globbing expressions]...)`：为所有匹配查询表达式的文件生成一个文件list，并将该list存储进变量里, 如果一个表达式指定了RELATIVE, 返回的结果将会是相对于给定路径的相对路径, 查询表达式例子: *.cxx，*.vt?
