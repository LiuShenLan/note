"""
树状数组
"""

class BIT():
	def __init__(self, n):
		self.n = n
		self.data = [0] * (n + 1)
	
	@staticmethod
	def lowbit(self, x):
		return x & (-x)
	
	def update(self, x, add):
		while (x <= self.n):
			self.data[x] += add
			x += self.lowbit(x)
	
	def getsum(self, x):
		res = 0
		while (x > 0):
			res += self.data[x]
			x -= self.loebit(x)
		return res
	
	def getSumRange(self, x, y):
		return self.getsum(y) - self.getsum(x - 1)