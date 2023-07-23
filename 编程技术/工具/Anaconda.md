- [conda操作](#conda操作)
- [环境管理](#环境管理)
- [修改path](#修改path)
- [库安装](#库安装)
- [环境操作](#环境操作)
- [源](#源)

# conda操作

* 取消自动启动base环境：`conda config --set auto_activate_base false`

# 环境管理
* conda create
	* -n \<new_env\> python=3.6: 创建新环境并指定python版本
	* -n \<nem_env\> --clone \<old_env\>: 克隆旧环境
	* --name \<env\> --file \<file\>: 根据txt文件创建环境
* conda env create -f \<name\>.yaml
    * 用配置文件\<name\>.yaml创建新的虚拟环境
* conda list -e \> requirements.txt: 导出环境信息到txt文件
* conda env export \> \<name\>.yaml: 导出当前环境的包信息到\<name\>.yaml
* conda remove -n \<env\> --all: 删除\<env\>环境及下属所有包

# 修改path
import sys
把sys.path的结果粘贴到相应的anaconda3/envs/\<env_name\>/lib/python3.6/site-packages/\<env_name\>.pth下

# 库安装
* conda install --yes --file requirements.txt: 根据txt文件安装包
* conda install --use-local ***.tar.bz2： 安装手动下载的包
* conda install -c local rdkit： 安装依赖


# 环境操作
* conda activate: 切换到base环境
* conda activate \<name\>: 切换到\<name\>环境
* conda env list: 列出conda管理的所有环境
* conda list: 列出当前环境的所有包
* conda install \<package\>: 安装\<package\>包
* conda remove \<package\>: 卸载\<package\>包
* conda update \<package\>: 更新\<package\>包

# 源

* [conda清华源](https://mirror.tuna.tsinghua.edu.cn/help/anaconda/)

* [pip清华源](https://mirrors.tuna.tsinghua.edu.cn/help/pypi/)

* 添加conda-forge源

```
conda config --add channels conda-forge
```

* 添加清华源
```
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/msys2/
conda config --set show_channel_urls yes
```

* 修改.condarc添加清华源
```
channels:
  - defaults
show_channel_urls: true
channel_alias: https://mirrors.tuna.tsinghua.edu.cn/anaconda
default_channels:
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/r
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/pro
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/msys2
custom_channels:
  conda-forge: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  msys2: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  bioconda: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  menpo: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  pytorch: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  simpleitk: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
```
