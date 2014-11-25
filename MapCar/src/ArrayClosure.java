import java.lang.reflect.Array;

class ArrayClosure {

    private ICarMappable[] objects;

    public ArrayClosure(int size) {
        objects = new ICarMappable[size];
    }


    public ArrayClosure(ICarMappable[] objects)
    {
        this.objects = objects;
    }

    public ArrayClosure() {}


    public void execClosure()
    {

    }







}


