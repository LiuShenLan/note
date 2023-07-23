- [创建工作空间](#创建工作空间)
- [Launch文件](#launch文件)
- [录制与回放数据(rosbag)](#录制与回放数据rosbag)
- [常用可视化工具](#常用可视化工具)
- [参数命令行](#参数命令行)
- [节点实现流程](#节点实现流程)
- [自定义话题消息](#自定义话题消息)
- [自定义服务数据](#自定义服务数据)
- [TF坐标系](#tf坐标系)
- [坐标系系统](#坐标系系统)
- [工具](#工具)
- [杂项](#杂项)
	- [报错](#报错)
- [坐标系](#坐标系)
	- [rviz中坐标轴颜色对应关系](#rviz中坐标轴颜色对应关系)
	- [静态坐标转换关系发布](#静态坐标转换关系发布)

# 创建工作空间

* 创建工作空间：`mkdir -p .../catkin_ws/src`、`cd .../catkin_ws/src`、`catkin_init_workspace`
* 编译工作空间：`cd .../catkin_ws`、`catkin_make`
* 设置环境变量：`source devel/setup.bash`

* 创建功能包：`cd .../catkin_ws/src`、`catkin_create_pkg <pkg_make> [depend1] [depend2]...`
* 编译功能包：`cd .../catkin_ws`、`catkin_make`、`source devel/setup.bash`

# Launch文件

功能：通过XML文件实现多节点的配置和启动（可自动启动ROS Master）

Launch文件语法如下↓：

* `<launch>`：根元素
* `<node>`：指定希望启动的节点，roslaunch不提供任何关于节点启动顺序的保证
	* `pkg`：所在的功能包名称
	* `type`：可执行文件名称
	* `name`：运行时的名称
	* 以下为可选参数
	* `args`：传递给节点的参数
	* `machine`：在指定机器上启动节点
	* `respawn`：若为true，则如果节点退出，它会重新启动
	* `respawn_delay`：如果`respawn`为ture，则在检测到节点故障后等待respawn_delay秒，然后再尝试重新启动
	* `required`：若为ture，则如果节点死亡，会杀死整个roslaunch
	* `ns`：命名空间
	* `clear_params`：启动前是否删除节点私有命名空间中的所有参数
	* `output`：若为screen，则stdout/stderr将被发送到屏幕；若为log，则stdout/stderr将被发送到$ORS_HOME/log中的日志文件，stderr将继续发送到屏幕
	* `cwd`：若为node，则节点的工作目录将设置为与节点的可执行文件相同的目录，默认值为ROS_HOME
	* `launch-prefix`：附加到节点的启动参数的命令/参数
	* `if`：节点是否启动
* `<param>`：设置ROS系统运行中的参数，存储在参数服务器中
	* `name`：参数名称。名称空间可以包含在参数名称中，但应避免全局指定名称
	* `value`：定义参数的值。如果省略此属性，则必须指定binfile、textfile或command
	* `type`：指定参数的类型。如果不指定类型，roslaunch 将尝试自动确定类型
	* `textfile`：文件的内容将被读取并存储为字符串。该文件必须可在本地访问，最好使用相对地址
	* `binfile`：文件的内容将被读取并存储为 base64 编码的 XML-RPC 二进制对象。该文件必须可在本地访问，最好使用相对地址
	* `command`：命令的输出将被读取并存储为字符串，最好使用相对地址
* `<rosparam>`：加载参数文件中的多个参数
	* `command`：同rosparam指令，load/dump/delete
	* `file`：rosparam文件名称
	* `param`：参数名称
	* `ns`：将参数范围限定为指定的命名空间
	* `subst_value`：为ture则允许在YAML文本中使用替换参数(substitution args)
* `<arg>`：aunch文件内部的局部变量，仅限于launch文件使用
	* `name`：参数名
	* 以下为可选参数
	* `default`：默认值
	* `value`：参数值，不能与`default`一起使用
	* `doc`：参数的描述，可以通过`roslaunch`命令中的`--ros-args`来获取它
	* 声明示例：
		* `<arg name="arg-name" />`：声明arg-name的存在。arg-name必须作为命令行参数（如果是顶级）或通过`<include>`传递（如果包含）传入
		* `<arg name="arg-name" default="1" />`：用默认值声明arg-name 。arg-name可以被命令行参数（如果是顶级）或通过`<include>`传递（如果包括）覆盖
		* `<arg name="arg-name" value="bar" />`：用常量值声明arg-name 。arg-name的值不能被覆盖。这种用法支持启动文件的内部参数化，而无需在更高级别公开该参数化
	* 调用示例：
		* `<param name="foo" value="$(arg arg-name)" />`
		* `<node name="node" pkg="package" type="type " args="$(arg arg-name)" />`
* `<remap>`：重映射ros计算图资源的命名
	* `from`：原始名称
	* `to`：目标名称
* `<include>`：嵌套，包含其他launch文件，类似C语言中的头文件包含
	* `file`：要包含的文件的名称
	* `ns`：导入与指定的命名空间相关的文件
	* `clear_params`：在启动前删除include的命名空间中的所有参数
	* `pass_all_args`：如果为true，则将当前上下文中设置的所有参数都添加到为处理包含文件而创建的子上下文中
* `<env>`：在启动的节点上设置环境变量，只能在`<launch>`、`<include>`、`<node>`或`<machine>`的范围内使用
	* `name`：环境变量名
	* `value`：环境变量值
* `group`：设置一组节点，可以将节点组推送到相应的命名空间之中，也可以在组中使用`<remap>`
	* `<group>`标签等同于顶级`<launch>`标签，只是充当其中标签的容器。因此可以像通常在`<launch>`标记中使用它一样使用任何标记
	* `ns`：将节点组分配给指定的命名空间
	* `clear_params`：若为true则启动前删除组命名空间中的所有参数

# 录制与回放数据(rosbag)

* `rosbag record`：录制发布的话题数据
	* `-a`：录制所有话题
	* `-O <文件名>`：指定保存文件
	* `<话题1> <话题2> ... <话题n>`：只录制指定话题
	* `-e <正特表达式>`：使用正则表达式匹配录制话题
	* `--node=<节点名>`：录制特定节点订阅的所有话题

* `rosbag info <.bag文件>`：查看bag文件中所包含的话题名称、类型和消息数量

* `rosbag play <.bag文件>`：回放数据
	* `-d <?秒>`：回访数据前的等待时间
	* `-i`：立即播放所有消息
	* `-s`：跳过bag文件初始部分后再真正开始回放
	* `-r`：改变消息发布速率
	* `-l`：循环播放
	* `-k`：在消息结束后保持存活
	* `--topics <话题1> <话题2> ... <话题n>`：指定要播放的主题
	* `--clock`：使用录制数据时的时间
	* `/original_name:=/new_name`：将topic进行映射

# 常用可视化工具

* QT工具箱：
	* `ros_console`：日志输出工具
	* `rqt_graph`：计算图可视化工具
	* `rqt_plot`：数据绘图工具
	* `rqt_image_view`：图像渲染工具

* rviz：三维可视化工具

* gazebo：三维物理仿真平台

# 参数命令行

* `rosparam`
	* `list`：列出当前所有参数
	* `get <param_key>`：显示某个参数值
	* `set <param_key> <param_value>`：设置某个参数值
	* `dump <file_name>`：保存参数到文件
	* `load f<ile_name>`：从文件读取参数
	* `delete <param_key>`：删除参数

* 编程方法
	1. 初始化ros节点
	2. get函数获取参数
	3. set函数设置参数

# 节点实现流程

* 发布者
	1. 初始化ros节点
	2. 向ros Master注册节点信息，包括发布的话题名和话题中的消息类型
	3. 创建消息数据
	4. 按照一定频率循环发布消息

* 接受者
	1. 初始化ros节点
	2. 订阅需要的话题
	3. 循环等待话题消息，接收到消息后进入回调函数
	4. 在回调函数中完成消息处理

* 服务器
	1. 初始化ros节点
	2. 创建Server实例
	3. 循环等待服务请求，进入回调函数
	4. 在回调函数中完成服务功能的处理，并反馈应答数据

# 自定义话题消息

1. 定义msg文件
2. 在package.xml中添加功能包依赖
	`<build_depend>message_generation</build_depend>`
	`<exec_depend>message_runtime</exec_depend>`
3. 在CMakeLists.txt添加编译选项
	* `find_package( …… message_generation)`
	* `add_message_files(FILES Person.msg)`
	`generate_messages(DEPENDENCIES std_msgs)`
	* `catkin_package(…… message_runtime)`
	* 设置需要编译的代码和生成的可执行文件
	* 设置链接库
	* 添加依赖项
4. 编译生成语言相关文件

# 自定义服务数据

1. 定义srv文件
2. 在package.xml中添加功能包依赖
	`<build_depend>message_generation</build_depend>`
	`<exec_depend>message_runtime</exec_depend>`
3. 在CMakeLists.txt添加编译选项
	* `find_package( …… message_generation)`
	* `add_service_files(FILES Person.srv)`
	`generate_messages(DEPENDENCIES std_msgs)`
	* `catkin_package(…… message_runtime)`
	* 设置需要编译的代码和生成的可执行文件
	* 设置链接库
	* 添加依赖项
4. 编译生成语言相关文件

# TF坐标系

* tf广播器
	1. 定义tf广播器(TransformBroadcaster)
	2. 创建坐标变换值
	3. 发布坐标变换

* tf监听器
	1. 定义tf监听器(TransformListener)
	2. 查找坐标变换(waitForTransform、lookupTransform)

* 查看tf树：`rosrun rqt_tf_tree rqt_tf_tree`

# 坐标系系统

* base_link

base_link坐标系和机器人的底盘直接连接。其具体位置和方向都是任意的。对于不同的机器人平台，底盘上会有不同的参考点。不过ROS也给了推荐的坐标系取法：x 轴指向机器人前方、y 轴指向机器人左方、z 轴指向机器人上方

* odom

odom是一个固定在环境中的坐标系也就是world-fixed。它的原点和方向不会随着机器人运动而改变。但是odom的位置可以随着机器人的运动漂移。漂移导致odom不是一个很有用的长期的全局坐标。然而机器人的odom坐标必须保证是连续变化的。也就是在odom坐标系下机器人的位置必须是连续变化的，不能有突变和跳跃

在一般使用中odom坐标系是通过里程计信息计算出来的。比如轮子的编码器或者视觉里程计算法或者陀螺仪和加速度计。odom是一个短期的局域的精确坐标系。但是却是一个比较差的长期大范围坐标

* map

map和odom一样是一个固定在环境中的世界坐标系。map的z轴是向上的。机器人在map坐标系下的坐标不应该随着时间漂移。但是map坐标系下的坐标并不需要保证连续性。也就是说在map坐标系下机器人的坐标可以在任何时间发生跳跃变化

一般来说map坐标系的坐标是通过传感器的信息不断的计算更新而来。比如激光雷达，视觉定位等等。因此能够有效的减少累积误差，但是也导致每次坐标更新可能会产生跳跃

map坐标系是一个很有用的长期全局坐标系。但是由于坐标会跳跃改变，这是一个比较差的局部坐标系（不适合用于避障和局部操作）

* 坐标系变换

odom到base_link的变换由里程计数据源中的一个发布

map到base_link通过定位组件计算得出。但是定位组件并不发布从map到base_link的变换。它首先获取odom到base_link的变换然后利用定位信息计算出map到odom的变换

# 工具

* 查看节点与话题：`rosrun rqt_graph rqt_graph`
	* 挂后台：`nohup rosrun rqt_graph rqt_graph > temp.txt 2>&1 &`

* 查看tf树：`rosrun rqt_tf_tree rqt_tf_tree`
	* 挂后台：`nohup rosrun rqt_tf_tree rqt_tf_tree > temp.txt 2>&1 &`

# 杂项

* ros安装好的包无法使用tab补齐时，在终端中使用`rospack list`，该指令会显示电脑上所有安装好的ros包，包括官方的功能包和自建的功能包。 执行过该命令之后，就可以自动补齐了

* [rosdep连不上](https://zhuanlan.zhihu.com/p/398754989)

## 报错

* `No module named 'rospy'`：将`/opt/ros/<ros版本>/lib/python3/dist-packages`目录下的catkin、genmsg、genpy、geometry_msgs、roscpp、rosgraph、rosgraph_msgs、roslib、rospy、sensor_msgs、std_msgs文件夹复制到`<anaconda3>/envs/<envName>/lib/python3.10/site-packages/`目录下

* `substitution args not supported:  No module named 'rospkg'`：`conda install -c conda-forge rospkg`


# 坐标系

## rviz中坐标轴颜色对应关系

|坐标轴|颜色|
|:-:|:-:|
|x|红色|
|y|绿色|
|z|蓝色|

## 静态坐标转换关系发布

`<node pkg="tf" type="static_transform_publisher" name="broadcaster" args="x y z yaw pitch rool frame_id child_frame_id period_in_ms" />`

参数

* x、y、z：对应坐标轴的平移，单位米
* yaw、pitch、rool：绕坐标轴的转动，顺着坐标轴的方向，顺时针为正，单位弧度
	* yaw：绕z轴旋转
	* pitch：绕y轴旋转
	* rool：绕x轴旋转
* frame_id、child_frame_id：父坐标系与子坐标系
* period_in_ms：发布频率，单位为毫秒，一般取100
