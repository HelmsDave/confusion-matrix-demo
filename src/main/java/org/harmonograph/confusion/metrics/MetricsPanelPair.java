/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import org.harmonograph.confusion.messages.TestResults;

/**
 * Metrics panel for matched pair of metrics.
 * 
 */
public class MetricsPanelPair implements MetricsPanel {

    /** Main Panel. */
    protected final JPanel m_panel;      
    
    /** Accuracy panel. */
    protected final MetricsPanel m_primaryMetricPanel;
    
    /** Error Rate panel. */
    protected final MetricsPanel m_complementMetricPanel;
        
    /** Name of this panel. */
    protected final String m_name;
        

    /** 
     * Simple Constructor.
     * 
     * @param primary Primary Metric 
     * @param complement Metric for complementary concept.
     * @param name Name for concept pair
     */
    public MetricsPanelPair(final MetricsPanel primary, final MetricsPanel complement,
            final String name) {
        m_name = name;
        m_primaryMetricPanel = primary;
        m_complementMetricPanel = complement;
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_primaryMetricPanel.getPanel(), c);
        }
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 2;   
            c.gridwidth = 1;
            c.weightx = 1f;
            c.fill = GridBagConstraints.HORIZONTAL;
            m_panel.add(new JSeparator(JSeparator.HORIZONTAL), c);
        }            
   
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = 1f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_complementMetricPanel.getPanel(), c);
        }          

    }

    /** 
     * Simple Constructor, default name.
     * 
     * @param primary Primary Metric 
     * @param complement Metric for complementary concept.
     */
    public MetricsPanelPair(final MetricsPanel primary, final MetricsPanel complement) {
        this(primary, complement, 
                String.format("<html>%s<br>%s</html>", primary.getName(), complement.getName()));
    }    
        
    
    /** {@inheritDoc} */
    @Override    
    public void processTestResults(final TestResults testResults) {
       m_primaryMetricPanel.processTestResults(testResults);
       m_complementMetricPanel.processTestResults(testResults);
    }
    

    /** {@inheritDoc} */
    @Override
    public JPanel getPanel() {
        return m_panel;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return m_name;
    }    
}
