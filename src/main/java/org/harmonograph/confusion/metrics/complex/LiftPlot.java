/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.complex;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.messages.Threshold;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTruePositiveRate;
import org.harmonograph.confusion.threshold.CalculateResultsUtil;

/**
 * Plot proper for Lift Chart.
 * @author Dave
 */
public class LiftPlot extends JPanel {
    
    /** Test Results. */
    protected TestResults m_results = TestResults.DEFAULT;
   
    
    /** 
     * Simple Constructor. 
     */
    public LiftPlot() {
        super();
        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));
        setMaximumSize(new Dimension(200, 200));
        setSize(new Dimension(200, 200));
    }
    
    /** 
     * Set test results an repaint. 
     * 
     * @param results Test Results to set
     */
    public void processTestResults(final TestResults results) {
        m_results = results;
        repaint();
    }    
    
    /** {@inheritDoc} */
    @Override      
    public void paint(final Graphics g) {
        super.paint(g);
        final Graphics2D g2d = (Graphics2D) g;

        g2d.drawString("0", 0, getHeight() - g2d.getFontMetrics().getHeight());
        g2d.drawString("TPR", 0, g2d.getFontMetrics().getHeight());
        g2d.drawString("Predicted Positive", 
                getWidth() - g2d.getFontMetrics().stringWidth("Predicted Positive"), 
                getHeight()- g2d.getFontMetrics().getHeight()/2);
             
        int lastX = getWidth();
        int lastY = 0;
        for (int testThresh = 0; testThresh <= Threshold.MAX; ++testThresh) {
            final Threshold t = new Threshold(
                    m_results.getThreshold().getNoiseMean(),
                    m_results.getThreshold().getNoiseStdDev(),
                    m_results.getThreshold().getSignalMean(),
                    m_results.getThreshold().getSignalStdDev(),
                    (float) testThresh,
                    m_results.getThreshold().getSkew());
            final TestResults r = CalculateResultsUtil.calculateResults(t);

            final float TPR = MetricsPanelTruePositiveRate.getTPR(r);

            final float PredictedTrue = (float)r.getPredictedTrue()/
                                        (float)r.getPopulationTotal();

            final int x = (int) (PredictedTrue * getWidth());
            final int y = (int) (getHeight() - TPR * getHeight());
            g2d.drawLine(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        }

        final float TPR = MetricsPanelTruePositiveRate.getTPR(m_results);
        final float PredictedTrue = (float)m_results.getPredictedTrue()/
                                    (float)m_results.getPopulationTotal();

        g2d.drawRect((int) (PredictedTrue * getWidth() - 3),
                (int) (getHeight() - TPR * getHeight()) - 3, 7, 7);
        
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);
        final int tics = 5;
        for (int index = 1; index < tics; ++index) { 
            g2d.drawLine(0, index*getHeight()/tics, getWidth(), index*getHeight()/tics);
            g2d.drawLine(index*getWidth()/tics, 0, index*getWidth()/tics, getHeight());
        }
        
    }
}
