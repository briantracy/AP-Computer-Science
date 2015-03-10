import random
import math
import inspect

offset = 100000
rangevalues = 100000

# Hash Tester



def test_hash_func(func):

	rand = [x + offset for x in range(rangevalues)]
	random.shuffle(rand)

	final = []

	for i in rand:
		val = func(i)
		final.append(val)




	maxval = max(final)
	minval = min(final)

	print "Testing Hash Function", inspect.getsourcelines(func)
	print "Min value", minval
	print "Max value", maxval
	print "Range    ", maxval - minval
	


def hashA(num):
	return math.ceil(math.sin(num) + 100 * math.cos(num))

def hashB(num):
	return (num % 1000) / 1000

def hashC(num):
	return (num / 1000) % 1000

def hashD(num):
	return math.cos(1000 * num)


test_hash_func(hashA)




