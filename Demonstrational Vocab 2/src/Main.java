
public class Main {
    public static void main(String[] args) {

        new PublicInterfaceOfClass()    .demonstrate();
        new Interface()                 .demonstrate();
        new Encapsulation()             .demonstrate();
        new Invariant()                 .demonstrate();
    }

    public static void header(String str)
    {
        /// ANSI escape sequence for underlined blue font
        System.out.println(String.format("\033[4m\033[94m%s\033[0m", str));
    }
}
