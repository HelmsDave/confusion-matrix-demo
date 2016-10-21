/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Panel for all controls for Threshold panel.
 * 
 * @author Dave
 */
public class ThresholdControls implements ChangeListener {
    
    /** Canvas, for updating threshold data. */
    protected final ThresholdCanvas m_canvas;
    
    /** Main Panel for Threshold Controls. */
    protected final JPanel m_panel;  
    
    /** Slider for noise mean. */
    protected final JSlider m_noiseMean;
    
    /** Slider for noise standard deviation. */
    protected final JSlider m_noiseSigma;  
    
    /** Slider for signal mean. */
    protected final JSlider m_signalMean;
    
    /** Slider for signal standard deviation. */
    protected final JSlider m_signalSigma;      
    
    /** Slider for threshold. */
    protected final JSlider m_threshold;     
    
    /** Slider for skew (imbalance) between 
     * noise and signal population. */
    protected final JSlider m_skew;   
    
    /** Current threshold data. */
    protected Threshold m_thresh = Threshold.DEFAULT;    
    
    /** 
     * Simple Constructor.
     * 
     * @param canvas Threshold Canvas, for updating threshold data 
     */
    public ThresholdControls(final ThresholdCanvas canvas) {
        m_canvas = canvas;
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
   
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;                 
            m_panel.add(new JLabel("Noise Mean"), c);
        }        
        {
            m_noiseMean = new JSlider(JSlider.HORIZONTAL, 
                    0, (int)Threshold.MAX, (int)m_thresh.getNoiseMean());

            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 0.5f;         
            c.fill = GridBagConstraints.HORIZONTAL;            

            m_panel.add(m_noiseMean, c);
            m_noiseMean.addChangeListener(this);
        }
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 3;                 
            m_panel.add(new JLabel("Noise Sigma"), c);
        }           
        {
            m_noiseSigma = new JSlider(JSlider.HORIZONTAL, 
                    0, (int)Threshold.MAX, (int)m_thresh.getNoiseStdDev());

            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 4;
            c.weightx = 0.5f;         
            c.fill = GridBagConstraints.HORIZONTAL;            

            m_panel.add(m_noiseSigma, c);
            m_noiseSigma.addChangeListener(this);
        }        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 5;                 
            m_panel.add(new JLabel("Signal Mean"), c);
        }         
        {
            m_signalMean = new JSlider(JSlider.HORIZONTAL, 
                    0, (int)Threshold.MAX, (int)m_thresh.getSignalMean());

            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 6;
            c.weightx = 0.5f;         
            c.fill = GridBagConstraints.HORIZONTAL;            

            m_panel.add(m_signalMean, c);
            m_signalMean.addChangeListener(this);
        }
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 7;                 
            m_panel.add(new JLabel("Signal Sigma"), c);
        }          
        {
            m_signalSigma = new JSlider(JSlider.HORIZONTAL, 
                    0, (int)Threshold.MAX, (int)m_thresh.getSignalStdDev());

            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 8;
            c.weightx = 0.5f;         
            c.fill = GridBagConstraints.HORIZONTAL;            

            m_panel.add(m_signalSigma, c);
            m_signalSigma.addChangeListener(this);

        }          
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 9;                 
            m_panel.add(new JLabel("Threshold"), c);
        }         
        {
            m_threshold = new JSlider(JSlider.HORIZONTAL, 
                    0, (int)Threshold.MAX, (int)m_thresh.getThreshold());
            m_threshold.setMinorTickSpacing(2);
            m_threshold.setMajorTickSpacing(10);
            m_threshold.setPaintTicks(true);
            m_threshold.setPaintLabels(true);

            m_threshold.setLabelTable(m_threshold.createStandardLabels(10));
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 10;
            c.weightx = 0.5f;         
            c.fill = GridBagConstraints.HORIZONTAL;            

            m_panel.add(m_threshold, c);
            m_threshold.addChangeListener(this);
        }      
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 11;                 
            m_panel.add(new JLabel("Skew"), c);
        }           
        {
            m_skew = new JSlider(JSlider.HORIZONTAL, 
                    1, (int)Threshold.MAX, (int)m_thresh.getSkew());

            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 12;
            c.weightx = 0.5f;         
            c.fill = GridBagConstraints.HORIZONTAL;            

            m_panel.add(m_skew, c);
            m_skew.addChangeListener(this);
        }            
           
    }
 
    /**
     * Get JPanel. 
     * @return JPanel for MetricPanel
     */
    public JPanel getPanel() {
        return m_panel;
    }    
    
    /** {@inheritDoc} */
    @Override
    public void stateChanged(final ChangeEvent e) {
        final Threshold thresh = new Threshold(
           m_noiseMean.getValue(),
           m_noiseSigma.getValue(),
           m_signalMean.getValue(),
           m_signalSigma.getValue(),
           m_threshold.getValue(),
           m_skew.getValue());
        
        m_canvas.setThresholdData(thresh);
    }    
}
