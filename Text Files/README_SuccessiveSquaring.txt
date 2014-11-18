Brian Tracy
AP Computer Science
Kuszmaul

Successive Squaring

This assignment was to implement the Exponentiation by Successive Squaring algorithm. This algorithm is preferred over a brute force, repetitive multiplication because it has a lower runtime. The most interesting part about my implementation of the successive squaring algorithm was the way that I stored the numbers that were being operated on. At first, I wrote the algorithm to deal with ints. The problem with ints is that with only 32 (on my machine) bits of precision, high exponents overflowed very quickly. This obviously lead to disastrous results. My next idea was to beef up my algorithm to accommodate longs. Now I had (on my machine) 64 bits of precision. Unfortunately, this was not enough for even mild exponents. The solution was to have an (almost) infinite precision number to calculate arbitrary powers such as 2 ^ 1000. The class for this job was the Java.math.BigInteger. 

The Oracle standards state that the BigIntegers are "Immutable arbitrary-precision integers". They come complete with a full set of arithmetic operations such as +-*/ and even %. This makes working with BigIntegers just like standard ints with the exception of how these operators are applied. 

					BigInteger x = new BigInteger("12345");
					BigInteger y = x.multiply(new BigInteger("6789")).mod(new BigInteger("13579")); // 617

Operations on BigIntegers are instance methods that return BigIntegers, allowing them to be chained. By using the BigInteger, my algorithm is able to calculate any exponents thrown at it, including those that would normally overflow even the biggest scalars.


Testing

Now that I had an algorithm that could calculate exponents through successive squaring, I had to test its accuracy. For this I created a gauntlet of test cases to run the algorithm through. These test cases utilized the assertions to prove that the algorithm was accurate. There were two sets of test cases.

	1. Computed
		This series of test cases compared the algorithm to its counterpart, Math.pow(). Unfortunately, Math.pow() operates on doubles, and can only handle a certain number of exponents. For this reason, the first series of test cases checks the numbers

							[0-9) ^ [0-9)

	2. Internet Found Constants.
		This series of test cases picks up where the automated series left off. To see if the algorithm would remain accurate past the limits of a double, I created a series of large exponent tests. This next array of assertions tested the algorithms accuracy in the range of 2^20 all the way up to 2^95.

		The final test was an extreme edge case to see how far the algorithm and indirectly, BigIntegers could go. The highest power of two I could find in text was 2^512. 

			13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096

		Even this insane outlier could be accurately computed by the algorithm, a true testament to the capacity and precision of the BigInteger.

Assertions Note

	Note: Assertions are not enabled by default in Java. In order to enable them, pass the -ea flag to the JVM. This can be done 
		1. In Eclipse) in the run > Edit Configurations 
		2. In Intellij) also in the run > Edit Configurations

	Assertions are not a compile time check, instead they are enforced at runtime.

		assert <#condition#>;

	If condition is true, the program continues. If condition is false, an exception is raised and program execution continues.

	Through the use of assertions, I was able to prove the accuracy of my implementation of Exponentiation through Successive Squaring.




