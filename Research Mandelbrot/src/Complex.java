import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Complex
{

    public BigDecimal real, imag;

    public Complex(String real, String imag)
    {
        this.real = new BigDecimal(real);
        this.imag = new BigDecimal(imag);
    }

    public Complex(BigDecimal real, BigDecimal imag)
    {
        this.real = real;
        this.imag = imag;
    }

    private double distance()
    {
        double _real = real.doubleValue();
        double _imag = imag.doubleValue();

        return Math.sqrt((_real * _real) + (_imag * _imag));
    }

    private Complex add(Complex other)
    {
        //double real = this.real + other.real;
        //double imag = this.imaginary + other.imaginary;

        this.real = this.real.add(other.real);
        this.imag = this.imag.add(other.imag);

        return this;
    }

    private Complex multiply(Complex other)
    {
        //double real = (this.real * other.real)      - (this.imaginary * other.imaginary);
        //double imag = (this.real * other.imaginary) + (this.imaginary * other.real);

        BigDecimal _real = (this.real.multiply(other.real)).subtract(this.imag.multiply(other.imag));
        BigDecimal _imag = (this.real.multiply(other.imag)).add     (this.imag.multiply(other.real));

        real = _real;
        imag = _imag;

        return this;
    }

    public int escapeIters()
    {
        final int kMaxIters = 256;
        int iters = 0;
        Complex z = new Complex(BigDecimal.ZERO, BigDecimal.ZERO);



        for (; iters < kMaxIters; iters++)
        {
            z.real = z.real.setScale(70, RoundingMode.CEILING);
            z = z.multiply(z).add(this);

            System.out.println("iter: " +  iters + z);

            if (z.distance() > 2.0) {
                return iters;
            }
        }

        return -1;
    }

    public String toString()
    {
        return real + " + " + imag + "i";
    }

}
