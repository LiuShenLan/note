"""
B树
"""
# B树:一个节点可以有许多子节点,有n个节点的B树的高度为lgn
# 如果B树的节点包含n个关键字,则该节点有n+1个子节点,节点中的关键字就是子节点关键字的分隔点
# 叶节点有相同的深度,即树的高度h
# B树的最小度数t:每个节点最多包含2t-1个关键字(即2t个子节点),若包含2他t-1个关键字则称该节点是满的
#			  除根节点外每个节点最少包含t-1个关键字(即t个子节点)

# import BTrees   # 不知道咋用

class NODE():
	def __init__(self,n=0,key=[],leaf=False,child=[],p=None):
		self.n=n			# 关键字数目
		self.key=key.copy() # 关键字列表
		self.leaf=leaf	  # 是否为叶节点标志
		self.child=child.copy() # 子节点列表
		self.p=p
	def free(self):
		self.n=None
		self.key=None
		self.leaf=None
		self.child=None
		self.p=None

class TREE():
	# 左子节点>=父节点>=右子节点
	def __init__(self,t=2,data=None):
		self.root=NODE(n=0,leaf=True)
		self.t=t	# 最小度数
		if data!=None:
			for i in range(len(data)):
				self.insert(data[i])

	def search(self,root,key):  # 查找关键字,返回关键字节点与下标
		i=0
		while i<root.n and key>root.key[i]:
			i+=1
		if i<root.n and key==root.key[i]:   # 找到关键字
			return root,i
		elif root.leaf:	 # 节点为叶节点
			return None
		else:
			return self.search(root.child[i],key)

	def insert(self,key):   # 插入新关键字
		r=self.root
		if r.n==2*self.t-1:	 # 根节点已满
			s=NODE()
			self.root=s
			s.leaf=False
			s.n=0
			s.child.append(r)
			r.p=s
			self.split_child(s,0)
			self.insert_nonfull(s,key)
		else:
			self.insert_nonfull(r,key)
	def insert_nonfull(self,root,key):
		i=root.n-1
		if root.leaf:
			root.key.append('')
			root.n+=1
			while i>=0 and key<root.key[i]:
				root.key[i+1]=root.key[i]
				i-=1
			root.key[i+1]=key
		else:
			while i>=0 and key<root.key[i]:
				i-=1
			i+=1
			if root.child[i].n==2*self.t-1:
				self.split_child(root,i)
				if key>root.key[i]:
					i+=1
			self.insert_nonfull(root.child[i],key)
	def split_child(self,root,i):
		# x为不满的节点,x.child[i]为满的子节点
		# 将子节点取中位关键字分裂,中位关键字加入x,分裂出的新树作为x的新子节点
		z=NODE()		# 将来作为右侧的新子节点
		y=root.child[i] # 将来作为左侧的新子节点,占据root.child[i]的位置
		y.p=root
		z.p=root
		z.leaf=y.leaf
		z.n=self.t-1
		# 将root.child[i]的后t-1个(t~2t-2)key赋值给z
		for count in range(self.t,2*self.t-1):
			z.key.append(y.key[count])
		del(y.key[self.t:2*self.t-1])
		# 若root.chile[i]不是叶节点,将root.child[i]的后t个子节点赋值给z
		if not y.leaf:
			for count in range(self.t,2*self.t):
				z.child.append(y.child[count])
			for count in range(len(z.child)):
				z.child[count].p=z
			del(y.child[self.t:2*self.t])
		y.n=self.t
		# 将父节点第i个子节点之后的子节点后移一位
		root.child.append(root.child[root.n])
		for j in range(root.n-1,i,-1):
			root.child[j+1]=root.child[j]
		root.child[i+1]=z
		for count in range(len(root.child)):
			root.child[count].p=root
		# 将父节点的第i个关键字之后的关键字后移一位
		if root.n!=0:
			root.key.append(root.key[root.n-1])
			for j in range(root.n-2,i-1,-1):
				root.key[j+1]=root.key[j]
			root.key[i]=y.key[self.t-1]
		else:
			root.key.append(y.key[self.t-1])
		del(y.key[self.t-1])
		y.n-=1
		root.n+=1

	def find_min(self,root):	# 返回关键字最小值的节点与下标
		if root==None:
			return None
		elif root.leaf: # 输入为叶节点
			return root,0
		else:
			return self.find_min(root.child[0])
	def min_print(self):		# 打印关键字最小值
		result=tree.find_min(tree.root)
		if result!=None:
			print("最小值为:",result[0].key[result[1]])
		else:
			print("树为空")

	def find_max(self,root):	# 返回关键字最大值的节点与下标
		if root==None:
			return None
		elif root.leaf: # 输入为叶节点
			return root,len(root.key)-1
		else:
			return self.find_max(root.child[-1])
	def max_print(self):		# 打印关键字最大值
		result=tree.find_max(tree.root)
		if result!=None:
			print("最大值为:",result[0].key[result[1]])
		else:
			print("树为空")

	def predecessor(self,node,i):   # 返回节点node.key[i]的前继关键字的节点与下标
		if not node.leaf:  # node不是叶节点
			return self.find_max(node.child[i])
		elif i>0:   # node为叶节点并且输入不为第一个关键字
			return node,i-1
		else:   # node为叶节点并且输入为第一个关键字
			z=node
			while True:
				if z.p==None:
					return None
				y=z.p
				j=0
				while y.child[j]!=z:
					j+=1
				if j==0:
					z=y
				else:
					return y,j-1
	def predecessor_print(self,key):# 打印关键字的前继
		result=tree.search(tree.root,key)
		if result!=None:
			result_p=tree.predecessor(result[0],result[1])
			if result_p!=None:
				print("{}的前继为:{}".format(key,result_p[0].key[result_p[1]]))
			else:
				print("{}的前继不存在".format(key))
		else:
			print("关键字{}不存在".format(key))

	def successor(self,node,i):	 # 返回节点node.key[i]的后继关键字的节点与下标
		if not node.leaf:  # node不是叶节点
			return self.find_min(node.child[i+1])
		elif i<node.n-1:   # node为叶节点并且输入不为最后一个关键字
			return node,i+1
		else:   # node为叶节点并且输入为最后一个关键字
			z=node
			while True:
				if z.p==None:
					return None
				y=z.p
				j=0
				while y.child[j]!=z:
					j+=1
				if j==y.n:
					z=y
				else:
					return y,j
	def successor_print(self,key):  # 打印关键字的后继
		result=tree.search(tree.root,key)
		if result!=None:
			result_s=tree.successor(result[0],result[1])
			if result_s!=None:
				print("{}的后继为:{}".format(key,result_s[0].key[result_s[1]]))
			else:
				print("{}的后继不存在".format(key))
		else:
			print("关键字{}不存在".format(key))

	def delete(self,node,index):	# 删除node.key[index]
		if node.leaf:   # 为叶节点
			if node.n>self.t-1:	 # 该叶节点关键字足够,直接删除输入关键字
				del(node.key[index])
				node.n-=1
			else:   # 该叶节点关键字为t-1,无法直接删除
				node_p=node.p
				index_child=0   # 确定输入节点为父节点的child的下标
				while node_p.child[index_child]!=node:
					index_child+=1

				if index_child==0:
					index_brother=index_child+1 # 确定输入节点的兄弟节点的下标
					if node_p.child[index_brother].n>self.t-1:
						# 若兄弟节点中关键字较多,则将父节点中的一个关键字移动到倍删除位置
						# 将兄弟节点中的一个关键字补充到父节点中
						node.key[index]=node_p.key[index_child]
						node_p.key[index_child]=node_p.child[index_brother].key[0]
						del(node_p.child[index_brother].key[0])
						node_p.child[index_brother].n-=1
					else:
						# 若兄弟节点中关键字较少,则将输入节点删除.将输入节点与兄弟节点合并
						# 将父节点中相应的关键字移动到新合并节点中,作为中间关键字
						# 将父节点.child中的兄弟节点信息删除
						"""
						此处有一个bug,如果移动父节点关键字导致父节点中关键字过少
						可能无法满足B树的性质,待修改
						"""
						del(node.key[index])
						node.n-=1
						node.key.append(node_p.key[index_child])
						del(node_p.key[index_child])
						for count in range(node_p.child[index_brother].n):
							node.key.append(node_p.child[index_brother].key[count])
							node.n+=1
						node_p.child[index_brother].free()
						del(node_p.child[index_brother])
						node_p.n-=1
				else:   # 同上,只不过是换了一下兄弟节点的位置
					index_brother=index_child-1
					if node_p.child[index_brother].n>self.t-1:
						node.key[index]=node_p.key[index_child-1]
						node_p.key[index_child-1]=node_p.child[index_brother].key[-1]
						del(node_p.child[index_brother].key[-1])
						node_p.child[index_brother].n-=1
					else:
						del(node.key[index])
						node.n-=1
						node_p.child[index_brother].key.append(node_p.key[index_child-1])
						del(node_p.key[index_child-1])
						for count in range(node.n):
							node_p.child[index_brother].key.append(node.key[count])
							node_p.child[index_brother].n+=1
						node.free()
						del(node_p.child[index_child])
						node_p.n-=1
		else:		   # 不为叶节点
			node_p,index_p=self.predecessor(node,index) # 前继
			node_s,index_s=self.successor(node,index)   # 后继
			if node_p.n>self.t-1:
				node.key[index]=node_p.key[index_p]
				self.delete(node_p,index_p)
			elif node_s.n>self.t-1:
				node.key[index]=node_s.key[index_s]
				self.delete(node_s,index_s)
			else:
				del(node.key[index])
				del(node.child[index+1])
				node.n-=1
				for count in range(node_s.n):
					node_p.key.append(node_s.key[count])
					node_p.child.append(node_s.child[count])
					node_p.n+=1
				node_p.child.append(node_s.child[node_s.n])
				node_s.free()


if __name__ == '__main__':
	print("B树")
	data=['F','S','Q','K','C','L','H','T','V','W','M','R','N','P','A','B','X','Y','D','Z','E']
	tree=TREE(data=data)

	tree.predecessor_print('A')
	tree.predecessor_print('B')
	tree.predecessor_print('F')
	tree.predecessor_print('R')
	tree.predecessor_print('G')
	tree.successor_print('Z')
	tree.successor_print('W')
	tree.successor_print('T')
	tree.successor_print('H')

	result=tree.search(tree.root,'N')
	tree.delete(result[0],result[1])
	result=tree.search(tree.root,'P')
	tree.delete(result[0],result[1])
	print("")

"""
					 K , Q
			 /		 |		 \
		B , F		  M		  T , W
	  /   |   \	  /   \	  /   |   \
	 A  C,D,E  H	L   N,P	R,S  V  X,Y,Z
"""
