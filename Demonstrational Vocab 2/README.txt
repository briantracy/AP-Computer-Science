Brian Tracy
AP Computer Science
Kuszmaul

Demonstrational Vocabulary 2


Public Interface of a Class
Interface
Encapsulation
Invariant

Each of the above topics has a related .java file and one or more classes. The purpose of these is to explain the concept
and to demonstrate it.


The public interface of a class is how that class is seen from the outside. It is composed of anything marked as public
such as methods or fields. The public interfaces of classes are the only way they can interact with each other. In my
demonstration, I use Reflection to textualize the public interface of a class. Only the public methods of that class
show up, thus printing the public interface.

Interfaces are augmentations to the public interfaces of other classes. An interface is a set of declarations of fields
(must be initialized) and methods. All declarations are public and must be implemented. Interfaces guarantee that any objects
that implements them will share some sort of interface. By allowing objects of different classes to communicate in a mutually
understood way, interfaces in java are a very powerful feature.

Encapsulation is the ability to hide functionality from the public interface of a class. Encapsulation behaviour makes it
so only the class itself can perform some functions. Most encapsulated behaviour is not needed by the client class, and due
to its encapsulation, is not available. In my demonstration, I created a class with a public and private variable. The private
variable had a getter and setter. Each time the getter was called, the public variable was incremented. By hiding away the
behaviour of the getter, encapsulation is used.

An invariant is some constant that is introduced during the execution of a program. In my demonstration, I set up a
loop that modified two variables each iteration. The invariant was that at any given time, both variables added up to ten.
To prove this, each iteration the sum of both variables is printed.


Here is a sample output in the case that you do not want to compile and run all 5 classes.



Demonstrating The Public Interface of a Class
The following methods and fields are the public interface of this class
public void PublicInterfaceOfClass.demonstrate()
public void PublicInterfaceOfClass.publicInterface1()
public void PublicInterfaceOfClass.publicInterface2(java.lang.String)
public final void java.lang.Object.wait() throws java.lang.InterruptedException
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
public boolean java.lang.Object.equals(java.lang.Object)
public java.lang.String java.lang.Object.toString()
public native int java.lang.Object.hashCode()
public final native java.lang.Class java.lang.Object.getClass()
public final native void java.lang.Object.notify()
public final native void java.lang.Object.notifyAll()
public java.lang.String PublicInterfaceOfClass.publicString

Demonstrating An Interface
The string had a bigger myCount

Demonstrating Encapsulation
The value of secret is 10
Number of requests we have made for the secret variable: 4

Demonstrating An Invariant
10
10
10
10
10
10
10
10
10
10