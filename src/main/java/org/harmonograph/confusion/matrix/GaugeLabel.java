/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.matrix;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * Label for confusion matrix element.
 * Shows gas gauge color box behind label.
 * @author Dave
 */
public class GaugeLabel extends JLabel {
    
    /** Show True Positive. */
    private final boolean m_truePositive;
    
    /** Show True Negative. */
    private final boolean m_trueNegative;
    
    /** Show False Positive. */
    private final boolean m_falsePositive;
    
    /** Show Negative. */
    private final boolean m_falseNegative;
    
    /** Current Test Results. */
    protected TestResults m_testResults = TestResults.DEFAULT;
        
    /**
     * Constructor.
     * @param text Text for label 
     * @param truePositive Show True Positive
     * @param trueNegative Show True Negative
     * @param falsePositive Show False Positive
     * @param falseNegative Show False Negative
     */
    public GaugeLabel(final String text,
        final boolean truePositive,
        final boolean trueNegative,
        final boolean falsePositive,
        final boolean falseNegative) {
        
        super(text);
        
        m_truePositive = truePositive;
        m_trueNegative = trueNegative;
        m_falsePositive = falsePositive;
        m_falseNegative = falseNegative;                    
    }
    
    /**
     * Set text and data. 
     * @param text Text for label
     * @param results Test Results for label
     */
    public void setData(final String text, final TestResults results) {
        m_testResults = results;
        super.setText(text);
    }
    
    /** {@inheritDoc} */
    @Override            
    public void paint(final Graphics g) {
       //this.getBackground();
       final Graphics2D g2d = (Graphics2D) g; 
       
       g2d.setColor(getBackground());
       g2d.fillRect(0, 0, getWidth(), getHeight());

       int fill = 0;
       if (m_truePositive) { 
           final int height = (int)(getHeight() * 
                ((float)m_testResults.getTruePositive() / 
                   (float)m_testResults.getPopulationTotal()));
           fill += height;
           g2d.setColor(ColorScheme.TRUE_POS_COLOR);
           g2d.fillRect(0, getHeight()-fill, getWidth(), height);
           
           
       }
       if (m_trueNegative) {    
           final int height = (int)(getHeight() * 
                ((float)m_testResults.getTrueNegative()/ 
                   (float)m_testResults.getPopulationTotal()));
           fill += height;
           g2d.setColor(ColorScheme.TRUE_NEG_COLOR);
           g2d.fillRect(0, getHeight()-fill, getWidth(), height);
       }
       if (m_falsePositive) {    
           final int height = (int)(getHeight() * 
                ((float)m_testResults.getFalsePositive() / 
                   (float)m_testResults.getPopulationTotal()));
           fill += height;
           g2d.setColor(ColorScheme.FALSE_POS_COLOR);
           g2d.fillRect(0, getHeight()-fill, getWidth(), height);
       }
       if (m_falseNegative) {    
           final int height = (int)(getHeight() * 
                ((float)m_testResults.getFalseNegative() / 
                   (float)m_testResults.getPopulationTotal()));
           fill += height;
           g2d.setColor(ColorScheme.FALSE_NEG_COLOR);
           g2d.fillRect(0, getHeight()-fill, getWidth(), height);
       }     
       
       super.paint(g);
    }
}
