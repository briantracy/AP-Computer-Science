/**
 *  The following file demonstrates polymorphic methods. Here is the class hierarchy.
 *
 *      SuperClass - declares method()
 *          |
 *      DerivedClass - declares method()
 *          |
 *      EvenMoreDerivedClass - declares method()
 *
 *      Notice how all three classes declare method().
 */

class SuperClass {
    public void method()
    {
        System.out.println("In SuperClass method");
    }
}

class DerivedClass extends SuperClass {
    @Override
    public void method() {
        System.out.println("In DerivedClass method");
    }
}

class EvenMoreDerivedClass extends DerivedClass {
    @Override
    public void method() {
        System.out.println("Very Deep!");
    }
}


/**
 *  This class uses the above classes to demonstrate polymorphism. Each of the above classes declares method() and has its
 *  own implementation.
 *
 *  Static vs Dynamic Type:
 *      Each object has both a static and dynamic type. Its static type is is type written just before its name during
 *      declaration. In all three examples, the static type of the object is SuperClass.
 *
 *      Each object also has a dyncamic type, or its actual type during assignment. Each object in the following examples
 *      has a different dynamic type. The first is SuperClass, the second is DerivedClass, the third is EvenMoreDerivedClass.
 *
 *      The dynamic type of each of the examples dictates which version of method() is called. In the case of the second and
 *      third examples, there are many possible method()s that could be called. This is what constitutes method() as polymorphic.
 *
 *      In each example, polymorphic methods are in action when the correct function is called despite the static type of each
 *      instance.
 *
 */
public class Polymorphism {

    public void demonstrate()
    {
        Main.boxtext("Demonstrating Polymorphism");

        /// Static Type = SuperClass. Dynamic Type = SuperClass
        SuperClass staticSuperDynamicSuper = new SuperClass();
        staticSuperDynamicSuper.method();

        /// Static Type = SuperClass. Dynamic Type = DerivedClass
        SuperClass staticSuperDynamicDerived = new DerivedClass();
        staticSuperDynamicDerived.method();

        /// Static Type = SuperClass. Dynamic Type = EvenMoreDerivedClass`
        SuperClass staticSuperDynamicEvenMoreDerived = new EvenMoreDerivedClass();
        staticSuperDynamicEvenMoreDerived.method();
    }
}
