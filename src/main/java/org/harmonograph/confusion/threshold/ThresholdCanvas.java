/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.messages.TestResultsDistributor;


/**
 * Canvas for Plot of PDF and thresholds.
 * @author Dave
 */
public class ThresholdCanvas {
    
    /** Main Panel for Metrics Tabs. */
    protected final JPanel m_canvas;       
    
    /** Current threshold data. */
    protected Threshold m_thresh = Threshold.DEFAULT;
     
    /** Max height of PDF. */
    private static final float MAX_PDF_HEIGHT = 
            1f / (float)Math.sqrt(2f*(float)Math.PI);
    
    /** Simple constructor. */
    public ThresholdCanvas() {
        
        m_canvas = new JPanel() {
            /** {@inheritDoc} */
            @Override            
            public void paint(final Graphics g) {
               super.paint(g);
               ThresholdCanvas.this.paint(g);
            }
        };
        
        m_canvas.setMinimumSize(new Dimension(50, 400));
    }
    
    
    
    /**
     * Get JPanel
     * @return JPanel
     */
    public JPanel getPanel() {
        return m_canvas;
    }        
    
    /** 
     * Get noise probability at value x along curve.
     * @param x Pixel x along curve
     * @return probability scaled to pixel value
     */
    private int noiseAt(final float p) {
       final float x = p * (float)Threshold.MAX / (float)m_canvas.getHeight();
       final float prob = GaussUtil.pdf(x, m_thresh.m_noiseMean, m_thresh.m_noiseStdDev);    
       return (int)((1f/MAX_PDF_HEIGHT) * m_thresh.m_noiseStdDev * prob * (float)m_canvas.getWidth());
    }
    
    /** 
     * Get signal probability at value x along curve.
     * @param x Pixel x along curve
     * @return probability scaled to pixel value
     */
    private int signalAt(final float p) {
        final float slewScale = Math.max(
                (float)Math.sqrt(1f / m_thresh.getSkew()), 0.1f);
       final float x = p * (float)Threshold.MAX / (float)m_canvas.getHeight();
       final float prob = GaussUtil.pdf(x, m_thresh.getSignalMean(), m_thresh.getSignalStdDev());    
       return (int)((1f/MAX_PDF_HEIGHT) * m_thresh.getSignalStdDev() * prob * slewScale * (float)m_canvas.getWidth());
    }    
    
    /** 
     * Get the pixel location of the threshold. 
     * 
     * @return pixel location of the threshold
     */
    private int threshPixel() {
        return (int)(m_thresh.getThreshold() * (float)m_canvas.getHeight() / 
                (float)Threshold.MAX); 
    }
    
    /**
     * Implementation of canvas paint.
     * Draw bell curves.
     * 
     * @param g Graphics context
     */
    public void paint(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        
        final int thresh = threshPixel();
        final int h = m_canvas.getHeight();
        
        // Draw bell curves, in this order:
        //    Below threshold:
        //       Solid bell curve
        //       Outline of bell curve
        //       Threshold line
        //    Above threshold:
        //       Solid bell curve
        //       Outline of bell curve
        //       Threshold line
        
        g2d.setColor(ColorScheme.TRUE_NEG_COLOR);
        for (int y = 0; y < thresh; ++y) {
            g2d.drawLine(0, h-y, noiseAt(y), h-y);
        }
        
        g2d.setColor(ColorScheme.FALSE_NEG_COLOR);
        for (int y = 0; y < thresh; ++y) {
            g2d.drawLine(0, h-y, signalAt(y), h-y);
        }      
        
        g2d.setColor(ColorScheme.TRUE_NEG_COLOR);
        for (int y = 0; y < thresh; ++y) {
            g2d.drawLine(noiseAt(y), h-y, noiseAt(y), h-y);
        }        
        
        
        g2d.setColor(ColorScheme.TRUE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(0, h-y, signalAt(y), h-y);
        }         
        
        g2d.setColor(ColorScheme.FALSE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(0, h-y, noiseAt(y), h-y);
        }
        
        g2d.setColor(ColorScheme.TRUE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(signalAt(y), h-y, signalAt(y), h-y);
        }  
       
        
        g2d.setColor(ColorScheme.THRESH_COLOR);
        g2d.drawLine(0, h-thresh, m_canvas.getWidth(), h-thresh);         
    }
   
    /** 
     * Update threshold data in canvas, and 
     * calculate results and send results to 
     * the rest of the GUI.
     * @param thresh Threshold data
     */
    public void setThresholdData(final Threshold thresh) {
        m_thresh = thresh;
        m_canvas.repaint();

        final float proportionOfNoiseBelowThresh = 
            GaussUtil.cdf(thresh.getThreshold(), 
                    thresh.getNoiseMean(), thresh.getNoiseStdDev());
        final float proportionOfSignalBelowThresh = 
            GaussUtil.cdf(thresh.getThreshold(), 
                    thresh.getSignalMean(), thresh.getSignalStdDev());
        
        final float skew = Math.max(thresh.getSkew(), 1f);
        final int signalPopulation = 
                (int)((float)TestResults.POPULATION_SIZE * .5f / skew);
        final int noisePopulation = TestResults.POPULATION_SIZE - signalPopulation;
        
        final int trueNegative = (int)(noisePopulation * proportionOfNoiseBelowThresh);
        final int falsePositive = noisePopulation - trueNegative;
        
        final int falseNegative = (int)(signalPopulation * proportionOfSignalBelowThresh);
        final int truePositive = signalPopulation - falseNegative;
        
        final TestResults results = new TestResults(
            truePositive, trueNegative, falsePositive, falseNegative);   
        
        TestResultsDistributor.DISTRIBUTOR.processTestResults(results);    
    }
      
}
