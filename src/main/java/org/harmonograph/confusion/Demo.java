/**
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 * Released under the GPL 3.
 */
package org.harmonograph.confusion;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Simple Java demo of Confusion Matrix and related concepts.
 * Standalone entrypoint for demo.
 * All logic in demo is confined to AWT Event thread.
 * @author Dave Helms
 */
public class Demo implements ActionListener {
    
    /** Top level frame for demo application. */
    protected final JFrame m_frame;    
    
    /** Main content Panel. */
    protected final MainPanel m_mainPanel;
    
    protected final static String ABOUT_TEXT = 
            "<html><body>" 
            + "David Helms<br>"
            + "<a href=\"https://www.linkedin.com/in/helmsdave\">LinkedIn</a><br>" 
            + "<a href=\"https://github.com/HelmsDave\">GitHub</a><br>" 
            + "</body></html>";
    
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
        
        final JMenuBar m_menuBar = new JMenuBar();
        final JMenu m_menu = new JMenu("About");
        m_menuBar.add(m_menu);
        final JMenuItem m_menuItem = new JMenuItem("Author");
        m_menu.add(m_menuItem);
        m_menuItem.addActionListener(this);
        m_frame.setJMenuBar(m_menuBar);
       
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
   
    /** {@inheritDoc} */
    @Override        
    public void actionPerformed(ActionEvent e) {
        try {
            final JEditorPane ep = new JEditorPane("text/html", ABOUT_TEXT);   
            ep.setEditable(false);
            ep.setBackground(new Label().getBackground());

            ep.addHyperlinkListener(new HyperlinkListener()
            {
                /** {@inheritDoc} */
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e)
                {
                    if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                        openWebpage(e.getURL());
                    }
                }
            });            
            
           JOptionPane.showMessageDialog(null, ep);        
        } catch (final Exception ex) {
            
        }
    }

    private static void openWebpage(final URI uri) {
        final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void openWebpage(final URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }    
}
