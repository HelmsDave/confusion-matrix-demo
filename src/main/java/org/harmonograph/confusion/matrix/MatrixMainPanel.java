/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.matrix;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    protected final JLabel m_labelTruePos = new JLabel("True Pos");
    /** Label for False Negative. */
    protected final JLabel m_labelFalseNeg = new JLabel("False Neg");
    /** Label for False Positive. */
    protected final JLabel m_labelFalsePos = new JLabel("False Pos");
    /** Label for True Negative. */
    protected final JLabel m_labelTrueNeg = new JLabel("True Neg");
    /** Label for Marginal Actual True. */
    protected final JLabel m_labelMargActTrue = new JLabel("Marg Actual True");
    /** Label for Marginal Actual False. */
    protected final JLabel m_labelMargActFalse = new JLabel("Marg Actual False");
    /** Label for Marginal Predicted True. */
    protected final JLabel m_labelMargPredTrue = new JLabel("Marg Pred True");
    /** Label for Marginal Predicted False. */
    protected final JLabel m_labelMargPredFalse = new JLabel("Marg Pred False");
    /** Label for Marginal Total. */
    protected final JLabel m_labelMargTotal = new JLabel("Marg Total");    
    
    /** Simple Constructor. */
    public MatrixMainPanel() {
        
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        
        // Axis labels
        {
            final JLabel label = new JLabel("Predicted");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 1;           
            c.gridwidth = 2;          
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }           
        {
            final JLabel label = new JLabel("Pos");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 2;               
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }     
        {
            final JLabel label = new JLabel("Neg");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 2;               
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }           
        
        {
            final JLabel label = new JLabel("True");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 3;
            c.gridheight = 2;         
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }           
        {
            final JLabel label = new JLabel("Pos");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 3;                 
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }     
        {
            final JLabel label = new JLabel("Neg");
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 4;               
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(label, c);
        }          
        
        // Main Matrix
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 3;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelTruePos, c);
        }            
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 4;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelFalseNeg, c);
        }  
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 3;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelFalsePos, c);
        }  
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 4;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelTrueNeg, c);
        }          
        
        // Margin
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 5;
            c.gridy = 3;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargActTrue, c);
        }             
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 5;
            c.gridy = 4;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargActFalse, c);
        }              
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 5;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargPredTrue, c);
        }             
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 4;
            c.gridy = 5;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargPredFalse, c);
        }             
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 5;
            c.gridy = 5;     
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_labelMargTotal, c);
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

        m_labelTruePos.setText(
                String.format("<html>True Pos<p>%s</html>",
                    TestResults.format(m_testResults.getTruePositive())));

        m_labelFalseNeg.setText(
                String.format("<html>False Neg<p>%s</html>",
                    TestResults.format(m_testResults.getFalseNegative())));

        m_labelFalsePos.setText(
                String.format("<html>False Pos<p>%s</html>",
                    TestResults.format(m_testResults.getFalsePositive())));

        m_labelTrueNeg.setText(
                String.format("<html>True Neg<p>%s</html>",
                    TestResults.format(m_testResults.getTrueNegative())));

        m_labelMargActTrue.setText(
                String.format("<html>Marg Actual True<p>%s</html>",
                    TestResults.format(m_testResults.getActualTrue())));

        m_labelMargActFalse.setText(
                String.format("<html>Marg Actual False<p>%s</html>",
                    TestResults.format(m_testResults.getActualFalse())));

        m_labelMargPredTrue.setText(
                String.format("<html>Marg Pred True<p>%s</html>",
                    TestResults.format(m_testResults.getPredictedTrue())));

        m_labelMargPredFalse.setText(
                String.format("<html>Marg Pred False<p>%s</html>",
                    TestResults.format(m_testResults.getPredictedFalse())));

        m_labelMargTotal.setText(
                String.format("<html>Marg Total<p>%s</html>", 
                    TestResults.format(m_testResults.getPopulationTotal())));
    }
}
