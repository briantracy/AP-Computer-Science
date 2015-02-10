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


def meth(args):
	a = args.split(",");
	
	print "%-16s|%-16s|%-16s|%-16s|%-16s|%-16s" % ("", a[0], a[1], a[2], a[3], a[4])

	for i in a:
		print "\n" + i

	print "\n\n"


meth("""0.1M Ba(NO3)2,0.1M BaCl2,0.2M K2CrO4,0.1M K2SO4,0.4M KCl""")
meth("""0.2M Na2SO4,0.14M Al2(SO4)3,0.1M Sr(NO3)2,0.2M MgCl2,0.1M Ba(NO3)2,0.07M AlCl3""")
meth("""0.1M Co(NO3)2,0.2M KOH,0.2M NaNO3,0.5M NaOH,0.1M FeCl3,0.1M CoCl2""")
meth("""0.5M NaOH,0.1M NiCl2,0.14M Ba(OH)2,0.2M MgCl2,0.2M MgSO4,0.2M Na2SO4""")
meth("""0.1M Sr(NO3)2,0.1M BaCl2,0.14M Al2(SO4)3,0.2M K2CrO4,0.05M AgNO3""")








