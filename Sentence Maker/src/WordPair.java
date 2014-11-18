public class WordPair
{
    static int longest = 0;
    String a;
    String b;

    public WordPair() {}

    public WordPair(String a, String b)
    {
        this.a = a;
        this.b = b;

        longest = Math.max(longest, this.a.length());
    }

    public boolean equals(Object other)
    {
        if (!(other instanceof WordPair)) return false;

        WordPair that = (WordPair)other;

        boolean x = (this.a.equals(that.a) && this.b.equals(that.b));
        boolean y = (this.a.equals(that.b) && this.b.equals(that.a));

        return x || y;

    }

    @Override
    public String toString()
    {
        String format = "%-" + longest + "s %s";


        return String.format(format, a, b);
    }

}
