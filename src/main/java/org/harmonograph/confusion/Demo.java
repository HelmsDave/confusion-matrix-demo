/**
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 * Released under the GPL 3.
 */
package org.harmonograph.confusion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Simple Java demo of Confusion Matrix and related concepts.
 * Standalone entrypoint for demo.
 * All logic in demo is confined to AWT Event thread.
 * @author Dave Helms
 */
public class Demo {
    
    /** Top level frame for demo application. */
    protected final JFrame m_frame;    
    
    /** Main content Panel. */
    protected final MainPanel m_mainPanel;
    
    /** Simple constructor. Simply holds main panels. */
    public Demo() {
        
        m_frame = new JFrame("Confusion Matrix Demo");
        m_frame.setMinimumSize(new Dimension(400, 400));
        m_frame.getContentPane().setLayout(new GridBagLayout());
        
        {
            m_mainPanel = new MainPanel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 0.5f;
            c.weighty = 0.5f;
            c.fill = GridBagConstraints.BOTH;
            m_frame.getContentPane().add(m_mainPanel.getPanel(), c);
        }
        
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.pack();
        m_frame.setVisible(true);      
    }
        
 
    /**
     * Entry point for demo.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            /** {@inheritDoc} */
            @Override            
            public void run() {
                new Demo();
            }
        });

    }
}
