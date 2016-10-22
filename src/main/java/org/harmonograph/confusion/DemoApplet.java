/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion;

import java.applet.Applet;

/**
 * Applet entrypoint for demo.
 * @author Dave
 */
public class DemoApplet extends Applet {

    /** Main content Panel. */
    protected MainPanel m_mainPanel;   
    
    
    @Override
    public void init() {
        m_mainPanel = new MainPanel();
        add(m_mainPanel.getPanel());   
    }
    
}
