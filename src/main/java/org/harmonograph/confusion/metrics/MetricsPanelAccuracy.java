/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for Accuracy formula.
 * @author Dave
 */
public class MetricsPanelAccuracy extends AbstractMetricsPanel {
      
    /** Scratch label to show something. */
    protected final JLabel m_label1;
    /** Scratch label to show something. */
    protected final JLabel m_label2;
    
    /** Simple Constructor. */
    public MetricsPanelAccuracy() {
        super("Accuracy");
        
        {
            m_label1 = new JLabel("A = (TruePositive + TrueNegative) / Total");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_label1, c);
        }        
        
        {
            m_label2 = new JLabel(m_label1.getText());
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 2;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_label2, c);
        }     
    }
    
    /** {@inheritDoc} */
    @Override    
    public void update() {
       
       final float a = 
               (float)(m_testResults.getTruePositive() + m_testResults.getTrueNegative()) /
                       (float)TestResults.POPULATION_SIZE;
       m_label2.setText(String.format("%.3f = (%s + %s) / %s",
               a, 
               TestResults.format(m_testResults.getTruePositive()),
               TestResults.format(m_testResults.getTrueNegative()),
               TestResults.format(TestResults.POPULATION_SIZE)));
    }
    
}
