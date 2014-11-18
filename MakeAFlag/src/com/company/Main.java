/*
    This is the driver class for the application. It contains the `main()` method and simply transfers control to FlagFrame.java
 */

package com.company;        /* I messed up some setting in IntelliJ, so now we have to use a package!? */

public class Main {

    /**
     * <summary>Entry point for application, set up the flag frame and let if fly. </summary>
     * @param args Command Line Arguments (were not expecting any!)
     */
    public static void main(String[] args) {
        FlagFrame flag = new FlagFrame();                   /* Initialize, show, allow to be closed, set title */
        flag.setVisible(true);
        flag.setTitle("The American Flag");

    }

}





