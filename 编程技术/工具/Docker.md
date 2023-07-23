- [容器生命周期管理](#容器生命周期管理)
- [容器操作](#容器操作)
- [容器rootfs指令](#容器rootfs指令)
- [镜像仓库](#镜像仓库)
- [本地镜像管理](#本地镜像管理)
- [info|version](#infoversion)
- [vscode连接docker](#vscode连接docker)
- [使用图形界面](#使用图形界面)

`docker run -d --restart=on-failure --name <容器名称> --cap-add=SYS_PTRACE --gpus all --shm-size=1024m -v /tmp/.X11-unix:/tmp/.X11-unix:rw -p 10022:22 -p 14000:4000 <镜像名称>`

# 容器生命周期管理
* `docker run (OPTIONS) 镜像名称 (COMMAND)`：创建一个新的容器并运行一个命令
	* `-d`：后台运行容器，并返回容器ID
	* `-i`：以交互模式运行容器，通常与 -t 同时使用
	* `-t`：为容器重新分配一个伪输入终端，通常与 -i 同时使用
	* `-v 主机绝对路径:容器内路径:rw`：挂载文件夹
	* `-p 主机端口:容器端口`：端口映射
	* `--name 容器名称`：指定容器名称
	* `--device 设备文件路径`：挂载设备文件路径
	* `--rm`：容器退出时自动删除容器内部的文件系统
	* `--restart`：容器重启策略
		* `no`：默认策略，容器退出时不重启容器
		* `on-failure`：容器非正常退出时重启容器
		* `on-failure:n`：容器非正常退出时重启容器，最多重启n次
		* `always`：在容器退出时总是重启容器
		* `unless-stopped`：在容器退出时总是重启容器，但是不考虑在Docker守护进程启动时就已经停止了的容器
	* `--cap-add 权限`：添加容器Linux权限
	* `--cap-drop 权限`：关闭容器Linux权限
	* `--shm-size 大小`：设置共享内存
	* `--privileged`：启动root权限
	* `--net`：设置网络模式
		* `=host`：比较好用
	* `docker run -it 镜像名称 /bin/bash`: 使用交互模式创建一个容器
* `docker exec (OPTIONS) 容器名称 COMMAND`：在运行的容器中执行命令
	* `-d`：分离模式: 在后台运行
	* `-i`：即使没有附加也保持STDIN 打开
	* `-t`：分配一个伪终端
	* `docker exec -it CONTAINER /bin/bash`：使用一个新的终端连接容器
* `docker start/stop/restart 容器名称`：启动/停止/重启容器
* `docker kill (OPTIONS) 容器名称`：杀掉一个运行中的容器
	* -s：向容器发送一个信号
* `docker rm (OPTIONS) 容器名称`：删除容器
	* `-f`：通过SIGKILL信号强制删除一个运行中的容器
	* `-l`：移除容器间的网络连接，而非容器本身
	* `-v`：删除与容器关联的卷
* `docker pause/unpause 容器名称`：暂停/恢复容器中所有的进程
* `docker create (OPTIONS) 镜像名称 (COMMAND)`：创建一个新的容器但不启动它，用法同docker run

# 容器操作
* `docker ps (OPTIONS)`：列出容器
	* `-a`：显示所有的容器，包括未运行的
* `docker attach 容器名称`：连接到正在运行中的容器

# 容器rootfs指令
* `docker commit (OPTIONS) 容器名称 (REPOSITORY(:TAG))`：从容器创建一个新的镜像
	* `-a`：提交的镜像作者
	* `-c`：使用Dockerfile指令来创建镜像
	* `-m`：提交时的说明文字
	* `-p`：在commit时，将容器暂停
* `docker cp (OPTIONS) 容器名称:原路径 目标路径`：用于容器与主机之间的数据拷贝
	* 两个路径中一个为主机路径，一个为容器路径，容器路径表示为`容器名称:路径`
	* `-L`：保持源目标中的链接
* `docker diff 容器名称`：检查容器里文件结构的更改

# 镜像仓库
* `docker login/logout (OPTIONS)`：登陆/登出一个Docker镜像仓库，如果未指定镜像仓库地址，默认为官方仓库 Docker Hub
	* `-u`：登陆的用户名
	* `-p`：登陆的密码
* `docker pull (OPTIONS) NAME<:TAG|@DIGEST>`：从镜像仓库中拉取或者更新指定镜像
	* `-a`：拉取所有 tagged 镜像
* `docker push (OPTIONS) NAME<:TAG>`：将本地的镜像上传到镜像仓库,要先登陆到镜像仓库
	* 推送时首先打TAG，然后推送
* `docker search TERM`：从Docker Hub查找镜像

# 本地镜像管理
* `docker images (OPTIONS) (REPOSITORY(:TAG))`：列出本地镜像
	* `-a`：列出本地所有的镜像(含中间映像层，默认情况下，过滤掉中间映像层)
* `docker rmi (OPTIONS) 镜像名称`：删除本地镜像
	* `-f`：强制删除
* `docker tag 镜像名称<:TAG> <REGISTRYHOST/><USERNAME/>NAME<:TAG>`：标记本地镜像，将其归入某一仓库
* `docker build (OPTIONS) PATH/URL/-`：使用 Dockerfile 创建镜像
	* `-f`：指定要使用的Dockerfile路径
	* `--tag`、`-t`：镜像的名字及标签，通常为`name:tag`或者`name`格式；可以在一次构建中为一个镜像设置多个标签
* `docker save (OPTIONS) 镜像名称 (镜像名称...)`：将指定镜像保存成 tar 归档文件
	* `-o filename.tar:`输出到的文件
* `docker load (OPTIONS)`：导入使用 docker save 命令导出的镜像
	* `--input`、`-i`：指定导入的文件，代替`STDIN`
* `docker import (OPTIONS) file|URL|- (REPOSITORY(:TAG))`：从归档文件中创建镜像
	* `-c`：应用docker 指令创建镜像
	* `-m`：提交时的说明文字
* 删除镜像
	* `docker stop $(docker ps -a -q)`
	* `docker rm $(docker ps -a -q)`
	* `docker rmi <image id>`

# info|version
* `docker info (OPTIONS)`：显示 Docker 系统信息，包括镜像和容器数
* `docker version (OPTIONS)`：显示 Docker 版本信息

# vscode连接docker

[vscode连接容器太慢](https://zhuanlan.zhihu.com/p/136537715)

# 使用图形界面

[docker使用图形界面](https://blog.csdn.net/hyj53/article/details/118633272)
