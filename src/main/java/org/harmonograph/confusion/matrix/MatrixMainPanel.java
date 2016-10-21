/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.matrix;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.messages.TestResultsDistributor;
import org.harmonograph.confusion.messages.TestResultsListener;

/**
 * The Matrix panel shows a simple confusion matrix 
 * with margins and labels.
 * @author Dave
 */
public class MatrixMainPanel implements TestResultsListener {
   
    /** Main Panel for Confusion Matrix. */
    protected final JPanel m_panel;    
    
    /** Current Test Results. */
    protected TestResults m_testResults = TestResults.DEFAULT;
        
    /** Label for True Positive. */
    protected final GaugeLabel m_labelTruePos = 
            new GaugeLabel("True Pos", true, false, false, false);
    /** Label for False Negative. */
    protected final GaugeLabel m_labelFalseNeg = 
            new GaugeLabel("False Neg", false, false, false, true);
    /** Label for False Positive. */
    protected final GaugeLabel m_labelFalsePos = 
            new GaugeLabel("False Pos", false, false, true, false);
    /** Label for True Negative. */
    protected final GaugeLabel m_labelTrueNeg = 
            new GaugeLabel("True Neg", false, true, false, false);
    /** Label for Marginal Actual True. */
    protected final GaugeLabel m_labelMargActTrue = 
            new GaugeLabel("Marg Actual True", true, false, false, true);
    /** Label for Marginal Actual False. */
    protected final GaugeLabel m_labelMargActFalse = 
            new GaugeLabel("Marg Actual False", false, true, true, false);
    /** Label for Marginal Predicted True. */
    protected final GaugeLabel m_labelMargPredTrue = 
            new GaugeLabel("Marg Pred True", true, false, true, false);
    /** Label for Marginal Predicted False. */
    protected final GaugeLabel m_labelMargPredFalse = 
            new GaugeLabel("Marg Pred False", false, true, false, true);
    /** Label for Marginal Total. */
    protected final GaugeLabel m_labelMargTotal = 
            new GaugeLabel("Marg Total", false, false, false, false);    
    
    /** Simple Constructor. */
    public MatrixMainPanel() {
        
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        
        // Axis labels          
        {
            final JLabel label = new JLabel("<html>Predicted<p>Positive</html>");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 2;               
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }     
        {
            final JLabel label = new JLabel("<html>Predicted<p>Negative</html>");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 6;
            c.gridy = 2;               
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }           
                
        {
            final JLabel label = new JLabel("<html>True<p>Positive</html>");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 4;                 
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }     
        {
            final JLabel label = new JLabel("<html>True<p>Negative</html>");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 6;               
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }          
        
        // Main Matrix
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 4;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelTruePos, c);
        }            
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 6;
            c.gridy = 4;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelFalseNeg, c);
        }  
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 6;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelFalsePos, c);
        }  
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 6;
            c.gridy = 6;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelTrueNeg, c);
        }          
        
        // Margin
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 8;
            c.gridy = 4;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargActTrue, c);
        }             
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 8;
            c.gridy = 6;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargActFalse, c);
        }              
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 8;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargPredTrue, c);
        }             
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 6;
            c.gridy = 8;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargPredFalse, c);
        }             
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 8;
            c.gridy = 8;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargTotal, c);
        }      
        
        // Make a pretty grid
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 3;   
            c.gridwidth = 8;
            c.weightx = 1f;
            c.fill = GridBagConstraints.HORIZONTAL;
            m_panel.add(new JSeparator(JSeparator.HORIZONTAL), c);
        } 
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 5;   
            c.gridwidth = 8;
            c.weightx = 1f;
            c.fill = GridBagConstraints.HORIZONTAL;
            m_panel.add(new JSeparator(JSeparator.HORIZONTAL), c);
        }         
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 7;   
            c.gridwidth = 8;
            c.weightx = 1f;
            c.fill = GridBagConstraints.HORIZONTAL;
            m_panel.add(new JSeparator(JSeparator.HORIZONTAL), c);
        }   
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 1;   
            c.gridheight = 8;
            c.weighty = 1f;
            c.fill = GridBagConstraints.VERTICAL;
            m_panel.add(new JSeparator(JSeparator.VERTICAL), c);
        }     
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 5;
            c.gridy = 1;   
            c.gridheight = 8;
            c.weighty = 1f;
            c.fill = GridBagConstraints.VERTICAL;
            m_panel.add(new JSeparator(JSeparator.VERTICAL), c);
        }    
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 7;
            c.gridy = 1;   
            c.gridheight = 8;
            c.weighty = 1f;
            c.fill = GridBagConstraints.VERTICAL;
            m_panel.add(new JSeparator(JSeparator.VERTICAL), c);
        }            
        
        
        update();
        TestResultsDistributor.DISTRIBUTOR.addListener(this);
    }
    
    /**
     * Get JPanel
     * @return JPanel
     */
    public JPanel getPanel() {
        return m_panel;
    }    
    
    /** {@inheritDoc} */
    @Override    
    public void processTestResults(final TestResults testResults) {
       m_testResults = testResults;
       update();
    }    
    
    /**
     * Update labels based on current test results.
     */
    protected void update() {

        m_labelTruePos.setData(
                String.format("<html>True Pos<p>%s</html>",
                    TestResults.format(m_testResults.getTruePositive())),
                m_testResults);

        m_labelFalseNeg.setData(
                String.format("<html>False Neg<p>%s</html>",
                    TestResults.format(m_testResults.getFalseNegative())),
                m_testResults);

        m_labelFalsePos.setData(
                String.format("<html>False Pos<p>%s</html>",
                    TestResults.format(m_testResults.getFalsePositive())),
                m_testResults);

        m_labelTrueNeg.setData(
                String.format("<html>True Neg<p>%s</html>",
                    TestResults.format(m_testResults.getTrueNegative())),
                m_testResults);

        m_labelMargActTrue.setData(
                String.format("<html>Marg Actual True<p>%s</html>",
                    TestResults.format(m_testResults.getActualTrue())),
                m_testResults);

        m_labelMargActFalse.setData(
                String.format("<html>Marg Actual False<p>%s</html>",
                    TestResults.format(m_testResults.getActualFalse())),
                m_testResults);

        m_labelMargPredTrue.setData(
                String.format("<html>Marg Pred True<p>%s</html>",
                    TestResults.format(m_testResults.getPredictedTrue())),
                m_testResults);

        m_labelMargPredFalse.setData(
                String.format("<html>Marg Pred False<p>%s</html>",
                    TestResults.format(m_testResults.getPredictedFalse())),
                m_testResults);

        m_labelMargTotal.setData(
                String.format("<html>Marg Total<p>%s</html>", 
                    TestResults.format(m_testResults.getPopulationTotal())),
                m_testResults);
        m_panel.repaint();
    }
}
