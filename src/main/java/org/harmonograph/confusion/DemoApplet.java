/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion;

import java.applet.Applet;
import javax.swing.SwingUtilities;

/**
 * Applet entrypoint for demo.
 * @author Dave
 */
public class DemoApplet extends Applet {
    
    @Override
    public void init() {
        
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    final MainPanel m_mainPanel = new MainPanel();
                    add(m_mainPanel.getPanel());
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }          
    }
    
}
