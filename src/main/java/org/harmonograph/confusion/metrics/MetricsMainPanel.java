/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.harmonograph.confusion.messages.TestResultsDistributor;

/**
 * The Metrics panel contains a collection of tabs,
 * where each tap represents some common concept 
 * such as sensitivity or specificity.
 * @author Dave
 */
public class MetricsMainPanel {
   
    /** Main Panel for Metrics Tabs. */
    protected final JPanel m_panel;    
    
    /** Tabbed Pane. */
    protected final JTabbedPane m_tabbedPane;
    
    /** List of subpanels. */
    protected final List<MetricsPanel> m_subPanels;
    
    /** Simple Constructor. */
    public MetricsMainPanel() {
        
        m_subPanels = new ArrayList<>();
        m_subPanels.add(new MetricsPanelAccuracy());
        // TODO add panels
        
        m_tabbedPane = new JTabbedPane();
        for (final MetricsPanel p : m_subPanels) {
            m_tabbedPane.add(p.getName(), p.getPanel());
            TestResultsDistributor.DISTRIBUTOR.addListener(p);
        }
        
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.gridheight = 2;
            c.weightx = 0.5f;
            c.weighty = 0.5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_tabbedPane, c);        
        }
    }
    
    /**
     * Get JPanel
     * @return JPanel
     */
    public JPanel getPanel() {
        return m_panel;
    }    
}
