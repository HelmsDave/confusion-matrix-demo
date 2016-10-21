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

/**
 * Canvas for Plot of PDF and thresholds.
 * @author Dave
 */
public class ThresholdCanvas {
    
    /** Main Panel for Metrics Tabs. */
    protected final JPanel m_canvas;       
    
    /** Arbitrary maximum value, for visual plot. */
    protected final static float MAX = 100f;
    /** Noise mean. */
    protected final float m_noiseMean = 20f;
    /** Noise Standard Deviation. */
    protected final float m_noiseStdDev = 15f;
    /** Signal Mean. */
    protected final float m_signalMean = 50f;
    /** Signal Standard Deviation. */
    protected final float m_signalStdDev = 20f;
    /** Threshold. */
    protected final float m_threshold = 40f;
    
    /** Max height of PDF. */
    private static final float MAX_PDF_HEIGHT = 
            1f / (float)Math.sqrt(2f*(float)Math.PI);
    
    /** Simple constructor. */
    public ThresholdCanvas() {
        
        m_canvas = new JPanel() {
            /** {@inheritDoc} */
            @Override            
            public void paint(final Graphics g) {
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
       final float x = p * (float)MAX / (float)m_canvas.getHeight();
       final float prob = GaussUtil.pdf(x, m_noiseMean, m_noiseStdDev);    
       return (int)((1f/MAX_PDF_HEIGHT) * m_noiseStdDev * prob * (float)m_canvas.getWidth());
    }
    
    /** 
     * Get signal probability at value x along curve.
     * @param x Pixel x along curve
     * @return probability scaled to pixel value
     */
    private int signalAt(final float p) {
       final float x = p * (float)MAX / (float)m_canvas.getHeight();
       final float prob = GaussUtil.pdf(x, m_signalMean, m_signalStdDev);    
       return (int)((1f/MAX_PDF_HEIGHT) * m_signalStdDev * prob * (float)m_canvas.getWidth());
    }    
    
    /** 
     * Get the pixel location of the threshold. 
     * 
     * @return pixel location of the threshold
     */
    private int threshPixel() {
        return (int)(m_threshold * (float)m_canvas.getHeight() / (float)MAX); 
    }
    
    /**
     * Implementation of canvas paint.
     * @param g Graphics context
     */
    public void paint(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        
        final int thresh = threshPixel();
        final int h = m_canvas.getHeight();
        
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
        
        
        g2d.setColor(ColorScheme.FALSE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(0, h-y, signalAt(y), h-y);
        }         
        
        g2d.setColor(ColorScheme.TRUE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(0, h-y, noiseAt(y), h-y);
        }
        
        g2d.setColor(ColorScheme.FALSE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(signalAt(y), h-y, signalAt(y), h-y);
        }  
       
        
        g2d.setColor(ColorScheme.THRESH_COLOR);
        g2d.drawLine(0, h-thresh, m_canvas.getWidth(), h-thresh);         
    }
   
}
