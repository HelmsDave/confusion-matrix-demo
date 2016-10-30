/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;


import org.harmonograph.confusion.messages.Threshold;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.messages.TestResultsDistributor;


/**
 * Canvas for Plot of PDF and thresholds.
 * @author Dave
 */
public class ThresholdCanvas implements MouseMotionListener, MouseListener {
    
    /** Main Panel for Metrics Tabs. */
    protected final JPanel m_canvas;       
    
    /** Current threshold data. */
    protected Threshold m_thresh = Threshold.DEFAULT;
     
    /** Max height of PDF. */
    private static final float MAX_PDF_HEIGHT = 
            1f / (float)Math.sqrt(2f*(float)Math.PI);

    /** Type of hot-spot. */
    protected enum HotspotType {
        THRESH, SKEW, NOISE_MEAN, NOISE_SIGMA,
        SIGNAL_MEAN, SIGNAL_SIGMA};    
    
    /** Current hotspot drag, null unless drag in progress. */
    protected HotspotType m_currentDragHotspot = null;
    
    
    /** Half-size of control hot-spot. */
    final static protected int HOTSPOT_HALFSIZE = 5;    
    
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
        
        m_canvas.setMinimumSize(new Dimension(100, 400));
        m_canvas.setPreferredSize(new Dimension(100, 400));
        m_canvas.setToolTipText(
                "<html>Measurement Space<p>with Noise and Signal PDF<p>" + 
                        "Adjust controls to change PDF and Threshold</html>");
        
        m_canvas.addMouseMotionListener(this);
        m_canvas.addMouseListener(this);
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
       final float prob = GaussUtil.pdf(x, m_thresh.getNoiseMean(), m_thresh.getNoiseStdDev());    
       return (int)((1f/MAX_PDF_HEIGHT) * m_thresh.getNoiseStdDev() * prob * (float)m_canvas.getWidth());
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
     * Get the Y pixel location of the threshold. 
     * @return Y pixel location of the threshold
     */
    private int threshPixel() {
        return (int)(m_thresh.getThreshold() * (float)m_canvas.getHeight() / 
                (float)Threshold.MAX); 
    }
    /** 
     * Get the Y pixel location of the noise mean. 
     * @return Y pixel location of the noise mean
     */
    private int noiseMeanPixel() {
        return (int)(m_thresh.getNoiseMean() * (float)m_canvas.getHeight() / 
                (float)Threshold.MAX); 
    }  
    /** 
     * Get the Y pixel location of the signal mean. 
     * @return Y pixel location of the signal mean
     */
    private int signalMeanPixel() {
        return (int)(m_thresh.getSignalMean() * (float)m_canvas.getHeight() / 
                (float)Threshold.MAX); 
    }        
    /** 
     * Get the Y pixel location of the noise sigma. 
     * @return Y pixel location of the noise sigma
     */
    private int noiseSigmaPixel() {
        return (int)((m_thresh.getNoiseMean()-m_thresh.getNoiseStdDev()) * (float)m_canvas.getHeight() / 
                (float)Threshold.MAX); 
    }  
    /** 
     * Get the Y pixel location of the signal sigma. 
     * @return Y pixel location of the signal sigma
     */
    private int signalSigmaPixel() {
        return (int)((m_thresh.getSignalMean()+m_thresh.getSignalStdDev()) * (float)m_canvas.getHeight() / 
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
        
        // Below threshold, true neg, solid
        g2d.setColor(ColorScheme.TRUE_NEG_COLOR);
        for (int y = 0; y < thresh; ++y) {
            g2d.drawLine(0, h-y, noiseAt(y), h-y);
        }
        // Accents
        g2d.setColor(ColorScheme.TRUE_NEG_COLOR.darker());
        {
            final int mean = 
                (int)((float)m_thresh.getNoiseMean() * (float)m_canvas.getHeight() /
                    (float)Threshold.MAX);                      
            g2d.drawLine(0, h-mean, noiseAt(mean), h-mean);
            final int s1 = 
                (int)((float)(m_thresh.getNoiseMean()+m_thresh.getNoiseStdDev()) * (float)m_canvas.getHeight() /
                    (float)Threshold.MAX);         
            g2d.drawLine(0, h-s1, noiseAt(s1), h-s1);
            final int s2 = 
                (int)((float)(m_thresh.getNoiseMean()-m_thresh.getNoiseStdDev()) * (float)m_canvas.getHeight() /
                    (float)Threshold.MAX);                
            g2d.drawLine(0, h-s2, noiseAt(s2), h-s2);
        }
        // Below threshold, false neg, solid
        g2d.setColor(ColorScheme.FALSE_NEG_COLOR);
        for (int y = 0; y < thresh; ++y) {
            g2d.drawLine(0, h-y, signalAt(y), h-y);
        }      
        // Below threshold, true neg, outline
        g2d.setColor(ColorScheme.TRUE_NEG_COLOR);
        for (int y = 0; y < thresh; ++y) {
            g2d.drawLine(noiseAt(y), h-y, noiseAt(y), h-y);
        }        
        
        // Above threshold, true pos, solid
        g2d.setColor(ColorScheme.TRUE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(0, h-y, signalAt(y), h-y);
        }   
        // Accents
        g2d.setColor(ColorScheme.TRUE_POS_COLOR.darker());
        {
            final int mean = 
                (int)((float)m_thresh.getSignalMean() * (float)m_canvas.getHeight() /
                    (float)Threshold.MAX);                      
            g2d.drawLine(0, h-mean, signalAt(mean), h-mean);
            final int s1 = 
                (int)((float)(m_thresh.getSignalMean()+m_thresh.getSignalStdDev()) * (float)m_canvas.getHeight() /
                    (float)Threshold.MAX);         
            g2d.drawLine(0, h-s1, signalAt(s1), h-s1);
            final int s2 = 
                (int)((float)(m_thresh.getSignalMean()-m_thresh.getSignalStdDev()) * (float)m_canvas.getHeight() /
                    (float)Threshold.MAX);                
            g2d.drawLine(0, h-s2, signalAt(s2), h-s2);
        }        
        // Above threshold, false pos, solid
        g2d.setColor(ColorScheme.FALSE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(0, h-y, noiseAt(y), h-y);
        }
        // Above threshold, true pos, outline
        g2d.setColor(ColorScheme.TRUE_POS_COLOR);
        for (int y = thresh; y < m_canvas.getHeight(); ++y) {
            g2d.drawLine(signalAt(y), h-y, signalAt(y), h-y);
        }  
          
        // Threshold propoer
        g2d.setColor(ColorScheme.THRESH_COLOR);
        g2d.fillRect(0, (h-thresh)-1, m_canvas.getWidth(), 3);         
        
        // Hotspots
        for (final HotspotType hotspot : HotspotType.values()) {
            final Point hotT = getHotspot(hotspot);
            g2d.drawRect(
                    hotT.x-HOTSPOT_HALFSIZE, (h-hotT.y)-HOTSPOT_HALFSIZE,
                    2*HOTSPOT_HALFSIZE, 2*HOTSPOT_HALFSIZE);    
        }
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
        
        final TestResults results = CalculateResultsUtil.calculateResults(thresh);
        TestResultsDistributor.DISTRIBUTOR.processTestResults(results);    
    }
    

    
    /** Get hot-spot center for specified hot-spot type.
     * @param type hot-spot type
     * @return hot-spot center
     */    
    protected Point getHotspot(final HotspotType type) {
        
        final int x;   
        final int y;
        switch (type) {
            case THRESH:
                y = threshPixel();
                x = 3*m_canvas.getWidth()/4;                
                break;
            case SKEW:
                y = signalMeanPixel();
                x = signalAt(y);                  
                break;
            case NOISE_MEAN:
                y = noiseMeanPixel();
                x = noiseAt(y)/2;                
                break;
            case NOISE_SIGMA:
                y = noiseSigmaPixel();
                x = noiseAt(y)/2;                  
                break;
            case SIGNAL_MEAN:
                y = signalMeanPixel();
                x = signalAt(y)/2;                    
                break;
            case SIGNAL_SIGMA:
                y = signalSigmaPixel();
                x = signalAt(y)/2;                 
                break;        
            default:
                y = 0;
                x = 0;
        }
        return new Point(x, y);        
    }
  
    /** Test hot-spot for threshold control.
     * @param point Point to evaluate
     * @param type hot-spot type
     * @return true if hot-spot for threshold control
     */
    protected boolean isHotspot(final Point point, final HotspotType type) {
        final Point hot = getHotspot(type);
        return 
                Math.abs(point.getX() - hot.getX()) <= HOTSPOT_HALFSIZE && 
                Math.abs((m_canvas.getHeight()-point.getY()) - hot.getY()) <= HOTSPOT_HALFSIZE;
    }
   
    
    /** {@inheritDoc} */
    @Override     
    public void mouseMoved(final MouseEvent e) {
    }
     /** {@inheritDoc} */
    @Override     
    public void mouseDragged(final MouseEvent e) {
        if (m_currentDragHotspot == null) {
            return;
        }
        final float sampleValue = (float)(m_canvas.getHeight()-e.getY()) * (float)Threshold.MAX / 
                (float)m_canvas.getHeight();        
        switch (m_currentDragHotspot) {
            case THRESH: {
                    final Threshold threshold = new Threshold(
                        m_thresh.getNoiseMean(), m_thresh.getNoiseStdDev(),
                        m_thresh.getSignalMean(), m_thresh.getSignalStdDev(),
                        sampleValue, m_thresh.getSkew());
                    setThresholdData(threshold); 
                }
                break;
            case SKEW: {
                    final float skew = (float)m_canvas.getWidth() / (float)e.getX();
                    final Threshold threshold = new Threshold(
                        m_thresh.getNoiseMean(), m_thresh.getNoiseStdDev(),
                        m_thresh.getSignalMean(), m_thresh.getSignalStdDev(),
                        m_thresh.getThreshold(), skew*skew);
                    setThresholdData(threshold);                 
                }
                break;
            case NOISE_MEAN: {               
                    final Threshold threshold = new Threshold(
                        sampleValue, m_thresh.getNoiseStdDev(),
                        m_thresh.getSignalMean(), m_thresh.getSignalStdDev(),
                        m_thresh.getThreshold(), m_thresh.getSkew());
                    setThresholdData(threshold);  
                }
                break;
            case NOISE_SIGMA: {
                    final float noiseSigma = m_thresh.getNoiseMean() - sampleValue;
                    final Threshold threshold = new Threshold(
                        m_thresh.getNoiseMean(), noiseSigma,
                        m_thresh.getSignalMean(), m_thresh.getSignalStdDev(),
                        m_thresh.getThreshold(), m_thresh.getSkew());
                    setThresholdData(threshold);    
                }
                break;
            case SIGNAL_MEAN: {               
                    final Threshold threshold = new Threshold(
                        m_thresh.getNoiseMean(), m_thresh.getNoiseStdDev(),
                        sampleValue, m_thresh.getSignalStdDev(),
                        m_thresh.getThreshold(), m_thresh.getSkew());
                    setThresholdData(threshold); 
                }
                break;
            case SIGNAL_SIGMA: {
                    final float signalSigma = sampleValue - m_thresh.getSignalMean();
                    final Threshold threshold = new Threshold(
                        m_thresh.getNoiseMean(), m_thresh.getNoiseStdDev(),
                        m_thresh.getSignalMean(), signalSigma,
                        m_thresh.getThreshold(), m_thresh.getSkew());
                    setThresholdData(threshold);   
                }
                break;        
            default:
        }
    }
     /** {@inheritDoc} */
    @Override
    public void mouseClicked(final MouseEvent e) {
    }
     /** {@inheritDoc} */
    @Override    
    public void mouseEntered(final MouseEvent e) {
    }
     /** {@inheritDoc} */
    @Override    
    public void mouseExited(final MouseEvent e) {    
    }
     /** {@inheritDoc} */
    @Override    
    public void mousePressed(final MouseEvent e) {
        for (final HotspotType hotspot : HotspotType.values()) {
            if (isHotspot(e.getPoint(), hotspot)) {
                m_currentDragHotspot = hotspot;
                break;
            }
        }
    }
     /** {@inheritDoc} */
    @Override    
    public void mouseReleased(final MouseEvent e) {
        m_currentDragHotspot = null;
    }    
}
