- [本地修改](#本地修改)
- [commit历史](#commit历史)
- [分支](#分支)
- [标签](#标签)
- [远程仓库](#远程仓库)
- [撤销](#撤销)
- [别名](#别名)
- [初始化设置](#初始化设置)
- [git报错](#git报错)

# 本地修改
* `git status`
	* 检查工作目录下文件的状态
	* `-s`：状态简览
		* `??`：新添加的未跟踪文件
		* `A`：新添加到暂存区的文件
		* `M`：修改过的文件，靠左表示修改并存入暂存区，靠右表示被修改但未存入暂存区
* `git diff`
	* 比较工作目录和暂存区快照之间的差异，即尚未暂存的改动
	* `--staged`：查看已暂存的将要添加到下次提交的内容
	* `--cached`：显示staged状态与之前commi状态的不同
	* `HEAD`：同时显示staged和unstaged状态与之前commit状态的差别
	* `commit-id-old commit-id-new \> d:/diff.txt`：在d:/diff.tx中显示两个commit的差异，-为id-new相对id-old减少的，+为id-new相对id-old增加的
	* `--stat`：仅显示有修改的文件名
* `git add`
	* `.`：添加所有改动到下次commit
	* `-p <file>`：添加<file\>中的改动到下次commit
* `git rm --cached -r .`
  * 清除缓存，作用与`git add .`相反
* `git commit`
	* 启动文本编辑器以输入本次提交的说明
	* commit以前的staged变动
	* `-m <string>`：添加说明文字
	* `-a`：把所有跟踪过的文件暂存并提交
	* `--amend`：尝试重新提交，修改上次提交信息
* `git rm`
	* 从暂存区移除文件
	* `-f`：强制删除，用于删除改动已经添加到暂存区的文件
* `git <old-name> <new-name>`
	* 对文件改名
# commit历史
* `git log`
	* 显示所有commit，从最新处开始
	* `-p`：按补丁格式显示每个更新之间的差异
	* `-p <file-name>`：显示一个特定文件随时间的改动
	* `--stat`：显示每次更新的文件修改统计信息（简略模式）
	* `--pretty`：指定使用不同于默认格式的方式展示提交历史	oneline、short、full、fuller
	* `--graph`：显示 ASCII 图形表示的分支合并历史
	* 骚操作：`git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)\<%an\>%Creset' --abbrev-commit"`
* `git blame <file>`
	* 谁在何时修改了\<file\>的什么
# 分支
* `git branch`
	* 列出当前所有分支
	* `<branch-name>`：在当前commit对象上创建分支指针
	* `<branch-name> <commit>`：基于commit创建分支指针
	* `-d <branch-name>`：删除分支		-D为强制删除
	* `-v`：查看每一个分支的最后一次提交
	* `-vv`：查看所有跟踪分支
	* `--merged`：查看哪些分支已经合并到当前分支
	* `--no-merged`：查看所有包含未合并工作的分支
	* `--set-upstream-to=origin/<remote-branch-name> <branch-name>`：将分支追踪远程分支
* `git checkout`
	* `<branch-name>`：切换到其它分支并修改工作目录中的文件到其他分支
	* `-b <branch-name>`：新建并切换到该分支
	* `<branch-name> <remote-name/branch-name>`：基于一个远程分支创建一个追踪分支
	* `--track <remote-name/branch-name>`：基于一个远程分支创建一个新的追踪分支
* `git merge <branch-name>`
	* 合并\<beanch-name\>到当前分支
		* `fast-forward`：被合并的分支是当前分支的直接下游，没有需要解决的分歧
		* 如果两个分支没有修改同一个地方，那么就会自动生成一次合并commit
		* 合并冲突(conflict)时，使用 git status 查看unmerged的文件并修改，最后add标记为冲突已解决
* `git mergetool`
	* 启动合适的可视化合并工具，并一步一步解决冲突
* `git rebase`
	* `<branch-name>`：将当前分支的修改变基到\<beanch-name\>上
	* `--onto <b1> <b2> <b3>`：将在b3中但不在b2中的修改在b1上重放一遍
	* 不要对在仓库外有副本的分支进行变基
# 标签
* `git tag`
	* 列出已有标签
	* `<tag-name>`：创建轻量标签（`git show <tag-name>`只显示提交信息）
	* `-a <tag-name> -m "string"`：创建附注标签（可用`git show <tag-name>`查看各种信息）
	* `-a <tag-name> <校验和>`：对过去的提交打标签
	* `-d <tag-name>`：删除本地仓库的标签
* `git push`
	* `<remote-name> <tag-name>`：推送标签到远程仓库
	* `<remote-name> --tags`：将所有不在远程仓库上的标签全部推送
	* `<remote-name> :refs/tags/<tag-name>`：更新远程仓库标签
	* `<remote-name> HEAD --force`：强制推送
# 远程仓库
* `git remote`
	* 列出指定的远程服务器的简写
	* `-v`：显示需要读写远程仓库使用的 Git 保存的简写与其对应的 URL
	* `add <short-name> <URL>`：添加一个新的远程Git仓库并指定简写
	* `rm <short-name>`：删除远程仓库
* `git push`
	* `<remote-name> <branch-name>`：将分支推送到远程仓库
	* `<remote-name> --delete <branch-name>`：删除远程分支
	* `<remote-name> <tag-name>`：推送标签到远程仓库
	* `<remote-name> --tags`：将所有不在远程仓库上的标签全部推送
	* `<remote-name> :refs/tags/<tag-name>`：更新远程仓库标签
	* `<remote-name> <commits-name> --force`：强制更新远程仓库
* `git fetch <remote-name>`
	* 访问远程仓库并拉取没有的数据和所有分支
* `git pull`
	* `<remote-name> <remote-branch>:<local-branch>`：拉取远程分支并在本地创建对应的新分支
	* 抓取然后合并远程分支到当前分支，相当于fetch+merge
* `git remote`
	* `show <remote-name>`：查看远程仓库信息
	* `rename <old-name> <new-name>`：修改远程仓库的简写
	* `rm <remote-name>`：移除远程仓库
# 撤销
* `git reset HEAD <file>`
	* 取消暂存file文件
* `git checkout -- <file>`
	* 还原文件到 上次提交/刚克隆完/刚放入工作目录 的时候
* `git checkout HEAD <file>`
* `git revert <commit>`
* `git reset --hard <commit>`
	* 版本回退	  HEAD为当前版本、HEAD^为上一版本
	* HEAD^^为上上版本、HEAD~100为上100版本
	* 也可以只写版本号的前几位来确定版本
* `git reset HEAD <file>`
	* 将暂存区的修改撤销掉，重新放回工作区
* `git reset <commit>`
* `git reset --keep <commit>`
# 别名
* `git config --global alias.aaa bbb`
	* 将bbb设置为aaa的别名，即用bbb代替aaa

# 初始化设置

```shell
git config --global user.name "LiuShenLan"
git config --global user.email "liushenlan1997@gmail.com"
git config --global core.quotepath false # 解决终端乱码
```


代码片段url

la1n370rtxcsk8umhi54w50

私人令牌

4022cb75b4247bec000fa3963556d8d4

# git报错

* `WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!`
	1. `ssh-keygen -l -f ~/.ssh/known_hosts`或`ssh-keygen -l -f C:\Users\liush\.ssh\known_hosts`
	2. `ssh-keygen -R 服务器端的ip地址`
	3. 重新`git pull`，输入yes
