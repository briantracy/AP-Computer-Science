Brian Tracy
AP Computer Science
Kuszmaul

Vocabulary Through Interpretive (dance) Code

The four topics to be covered on this vocabulary are Inheritance, Delegation, Polymorphism, and Overloading. Each
of these topics has an associated .java file and Class. Each class may have a couple of extras to demonstrate key points.



Inheritance is the act of inheriting behaviours from a separate class. I demonstrated inheritance by making a super class
and a sub class. The super class had protected field that was printed by its only method. In the sub class's constructor,
this protected field was modified (showing that it was inherited). Now when the method was called on the sub class, the appropriate
value of the protected field was printed. This shows inheritance because the sub class inherited the method that the super class
declared.

Delegation is the act of passing off functionality to another object. I demonstrated delegation by creating a class that represented
an english dictionary (for words). I delegated to a Map in the implementation of the various dictionary methods. This was classic
delegation because I was leveraging existing functionality instead of recreating it.

Polymorphism is the way in which a language can call the correct method on an object when that method is also declared in its super
classes. A method is polymorphic when it is re-implemented by a subclass. At runtime, the correct implementation will be called.
This is demonstrated in the three test cases. All three variables are of type SuperClass, but they are all of a different dynamic
type so when their respective polymorphic method is called, all three output different things.

Overloading is creating more than one method with the same name but with different signatures. This is demonstrated in my test
class by declaring three methods name `method`. Each has its own number / type of arguments therefore their method signatures are
different. This uniqueness is what allows the compiler to call the correct method despite all three sharing the same name.

Here is a sample output in the case that you do not want to compile and run the given classes.

╔═══════════════════════════╗
║ Demonstrating Overloading ║
╚═══════════════════════════╝
In method with one argument
The value of argument is 10

In method with two arguments
The values of both arguments are 15, 15

In method with one argument, but its type is String
The value of the string argument is [A String Argument]

╔══════════════════════════╗
║ Demonstrating Delegation ║
╚══════════════════════════╝
Phone = A wireless communication device.
Dog = A small mammal.
Boat = A water vehicle.
The definition of a boat is: A water vehicle
╔════════════════════════════╗
║ Demonstrating Polymorphism ║
╚════════════════════════════╝
In SuperClass method
In DerivedClass method
Very Deep!
╔═══════════════════════════╗
║ Demonstrating Inheritance ║
╚═══════════════════════════╝
default noise
MOOOO

