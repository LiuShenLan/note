import random
import math
"""
根据random(0,1)计算random(a,b)
"""
# 时间复杂度为O(lg(b-a))
def ex5_1_2(a,b):
    bit=int(math.log((b-a),2))
    result=0
    for i in range(bit):
        result+=random.random() * 2**i
    if result>(b-a):
        return ex5_1_2(a,b)
    else:
        return a+result

if __name__ == '__main__':
    print("雇用问题")

    # 习题5.1-2
    a=1
    b=5
    print("生成{}到{}之间的随机数{}".format(a,b,ex5_1_2(a,b)))
