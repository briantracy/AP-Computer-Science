

public class Complex {

    public double real, imaginary;

    public Complex(int x, int y)
    {
        real = (double)x;
        imaginary = (double)y;
    }

    public Complex(double x, double y)
    {
        real = x;
        imaginary = y;
    }

    public Complex multiply(Complex other)
    {
        double real = (this.real * other.real)      - (this.imaginary * other.imaginary);
        double imag = (this.real * other.imaginary) + (this.imaginary * other.real);

        this.real = real;
        this.imaginary = imag;
        return this;
    }


    public Complex add(Complex other)
    {
        double real = this.real + other.real;
        double imag = this.imaginary + other.imaginary;

        this.real = real;
        this.imaginary = imag;

        return this;
    }

    /**
     *
     *      f(z) = z^2 + c
     *      compound this formula on itself; f(f(f(z))) to see if a complex
     *      number stays bounded.
     *
     */
    public int escapeIters()
    {
        return escapeIters_();
//        final int kMaxIters = 100;
//        int iters = 1;
//        Complex z = new Complex(0,0);
//
//
//        for (; iters < kMaxIters; ++iters)
//        {
//            z = z.multiply(z).add(this);
//            if (Double.isInfinite(z.distance())) {
//                return iters;
//            }
//        }
//
//
//        return -1;
    }

    private int escapeIters_()
    {
        final int kMaxIters = 255;
        int iters = 1;
        Complex z = new Complex(0,0);


        for (; iters < kMaxIters; ++iters)
        {
            z = z.multiply(z).add(this);
            if (z.distance() > 2) {
                return iters;
            }
        }


        return -1;
    }


    public String toString()
    {
        String _this = this.real == 0 ? "0 " : "" + this.real;
        _this       += this.imaginary == 0 ? "" : "+" + this.imaginary + "i";

        return _this;
    }

    public double distance()
    {
        return Math.sqrt((real * real) + (imaginary * imaginary));
    }



}
