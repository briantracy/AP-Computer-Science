import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
//
//        JFrame fr = new JFrame();
//        fr.setVisible(true);
//        fr.setSize(100, 100);
//
//        JButton button = new JButton("HELLO");
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                System.out.println("yoski");
//            }
//        });
//
//        fr.add(button);

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
