import sys 

n = int(sys.stdin.readline())
data = list(map(int, sys.stdin.readline().split()))
data.sort() 

print(data[(n-1)//2])