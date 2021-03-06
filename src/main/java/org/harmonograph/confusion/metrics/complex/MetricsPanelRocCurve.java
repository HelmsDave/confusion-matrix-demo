/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.complex;

import org.harmonograph.confusion.metrics.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.harmonograph.confusion.messages.TestResults;

/**
 * Metrics panel for ROC Curve.
 * 
 * @author Dave
 */
public class MetricsPanelRocCurve implements MetricsPanel {

    /** Main Panel for Confusion Matrix. */
    protected final JPanel m_panel;      
    
    /** This label holds a multi-line html string, 
     * which is the contents of this panel. */
    protected final JLabel m_label;    
    
    /** Plot for ROC Curve.  */
    protected final RocCurvePlot m_plot;
    
    /** Name of this panel. */
    protected String m_name;
    
    /** 
     * Simple Constructor.
     * 
     */
    public MetricsPanelRocCurve() {
        m_name = "<html>ROC<br>Curve</html>";
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        
        {
            m_label = new JLabel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1f;
            c.weighty = 1f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_label, c);
        }       
           
   
        {
            m_plot = new RocCurvePlot();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 1;
            c.anchor = GridBagConstraints.CENTER;
            m_panel.add(m_plot, c);
        }          
        
        processTestResults(TestResults.DEFAULT);
    }
    
    /** {@inheritDoc} */
    @Override    
    public void processTestResults(final TestResults testResults) {
       final String labelText = updateLabelString(testResults);
       m_label.setText(labelText);
       
       m_plot.processTestResults(testResults);
       
    }
    
    /** 
     * Perform any update for new test results.
     * 
     * @param testResults Test results for update
     * @return formatted string for label
     */
    public String updateLabelString(final TestResults testResults) {
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Receiver Operating Characteristic (ROC)<p>");
        out.append("Plot of True Positive Rate(TPR)<p>");
        out.append("vs. False Positive Rate (FPR)<p>");
        out.append("</html>");
        return out.toString(); 
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
