/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.messages.TestResultsListener;

/**
 * MetricsPanel for Accuracy formula.
 * @author Dave
 */
public class MetricsPanelAccuracy implements MetricsPanel, TestResultsListener {
 
    /** Main Panel for Confusion Matrix. */
    protected final JPanel m_panel;      
    
    /** Scratch label to show something. */
    protected final JLabel m_label1;
    protected final JLabel m_label2;
    
    /** Current Test Results. */
    protected TestResults m_testResults = TestResults.DEFAULT;
    
    /** Simple Constructor. */
    public MetricsPanelAccuracy() {
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        
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
    public void processTestResults(final TestResults testResults) {
       m_testResults = testResults;
       
       final float a = 
               (float)(testResults.getTruePositive() + testResults.getTrueNegative()) /
                       (float)TestResults.POPULATION_SIZE;
       m_label2.setText(String.format("%.3f = (%s + %s) / %s",
               a, 
               TestResults.format(testResults.getTruePositive()),
               TestResults.format(testResults.getTrueNegative()),
               TestResults.format(TestResults.POPULATION_SIZE)));
    }
    
    /** {@inheritDoc} */
    @Override
    public JPanel getPanel() {
        return m_panel;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Accuracy";
    }
}
