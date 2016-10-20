/**
 * Simple Java demo of Confusion Matrix and related concepts.
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 * Released under the GPL 3.
 */
package org.harmonograph.confusion;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Entrypoint for demo.
 * All logic in demo is confined to AWT Event thread.
 * @author Dave Helms
 */
public class Demo {
    public static void main( String[] args ) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final MainFrame mainFrame = new MainFrame();
                final JFrame frame = mainFrame.getFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

    }
}
