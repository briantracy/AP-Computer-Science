Brian Tracy
AP Computer Science
Kuszmaul

Common Prefix

The task at hand is to write a method that will return the common prefix of two strings. In other words, find the
longest string that they both start with. I decided to take this concept one step farther and support any number of strings
through the use of variable arguments (denoted by <Type>... in java).

The first step to finding the common prefix of a group of strings is to find the smallest string in the group. This serves
as the largest possible common prefix they all share. The next step is to iterate through all this string, building up a
substring and checking to see if is also present in the other strings. If it is not, return what we have built up so far. If
the substring is contained in all of the other strings, repeat the process and add one character to the substring.

Here is a sample output of the test cases found in the main method.

The Greatest Common Prefix of [Hello, Helium, Help] is Hel
The Greatest Common Prefix of [Barry, Barn, Barnicle] is Bar
The Greatest Common Prefix of [Banter, Banner, Bane, Band] is Ban
The Greatest Common Prefix of [Barium, Bard, Barn, Barf, Bart] is Bar
The Greatest Common Prefix of [Mathew, Mathias, Matheo, Mathilial, Mathilde] is Math
The Greatest Common Prefix of [There, Will, Not, Be, A Common Prefix] is