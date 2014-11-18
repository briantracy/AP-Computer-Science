import java.lang.reflect.Array;

class ArrayClosure {

    private Object[] objects;

    public ArrayClosure(int size) {
        objects = new Object[size];
    }


    public ArrayClosure(Object[] objects)
    {
        this.objects = objects;
    }

    public ArrayClosure() {}


    public void execClosure(java.lang.reflect.Method closure)
    {
        for (Object o : objects)
        {
            try {
                closure.invoke(o, null);
            } catch (Exception e) {
                System.out.println("could not invoke");
            }

        }
    }





}


