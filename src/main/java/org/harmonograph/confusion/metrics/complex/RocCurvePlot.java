/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.complex;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.messages.Threshold;
import org.harmonograph.confusion.metrics.simple.MetricsPanelFalsePositiveRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTruePositiveRate;
import org.harmonograph.confusion.threshold.CalculateResultsUtil;

/**
 *
 * @author Dave
 */
public class RocCurvePlot extends JPanel {
    
    /** Test Results. */
    protected TestResults m_results = TestResults.DEFAULT;
    
    /** 
     * Simple Constructor. 
     */
    public RocCurvePlot() {
        super();
        setPreferredSize(new Dimension(200, 200));
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
            final float FPR = MetricsPanelFalsePositiveRate.getFPR(r);

            g2d.drawRect((int) (FPR * getWidth()),
                    (int) (getHeight() - TPR * getHeight()), 1, 1);
        }

        final float TPR = MetricsPanelTruePositiveRate.getTPR(m_results);
        final float FPR = MetricsPanelFalsePositiveRate.getFPR(m_results);

        g2d.drawRect((int) (FPR * getWidth() - 3),
                (int) (getHeight() - TPR * getHeight()) - 3, 7, 7);
    }
}
