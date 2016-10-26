package org.harmonograph.confusion.messages;

/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */

/**
 * Representation of threshold data, between 
 * the Threshold controls and canvas.
 * 
 * @author Dave
 */
public class Threshold {
    
    /** Arbitrary maximum value, for visual plot. (assume min of 0).*/
    public final static float MAX = 100f;    
    /** Noise Mean. */
    protected final float m_noiseMean;
    /** Noise Standard Deviation. */
    protected final float m_noiseStdDev;
    /** Signal Mean. */
    protected final float m_signalMean;
    /** Signal Standard Deviation. */
    protected final float m_signalStdDev;
    /** Threshold. */
    protected final float m_threshold;
    /** Skew as a ratio between noise and signal. */
    protected final float m_skew;     
    /** Default threshold. */
    public final static Threshold DEFAULT = 
            new Threshold(18f, 8f, 70f, 8f, 50f, 1f);
    /** 
     * Simple constructor.
     * 
     * @param noiseMean Noise Mean
     * @param noiseStdDev Noise Standard Deviation
     * @param signalMean Signal Mean
     * @param signalStdDev Signal Standard Deviation
     * @param threshold Threshold
     * @param skew Skew
     */
    public Threshold(final float noiseMean, final float noiseStdDev, 
            final float signalMean, final float signalStdDev, 
            final float threshold, final float skew) {
        
        m_noiseMean = noiseMean;
        m_noiseStdDev = noiseStdDev;
        m_signalMean = signalMean;
        m_signalStdDev = signalStdDev;
        m_threshold = threshold;
        m_skew = Math.max(skew, 1f);
    }

    /**
     * Get Noise Mean
     * @return Noise mean
     */
    public float getNoiseMean() {
        return m_noiseMean;
    }

    /**
     * Get Noise Standard Deviation.
     * @return Noise Standard Deviation
     */
    public float getNoiseStdDev() {
        return m_noiseStdDev;
    }

    /**
     * Get Signal Mean.
     * @return Get Signal Mean
     */
    public float getSignalMean() {
        return m_signalMean;
    }

    /**
     * Get Signal Standard Deviation.
     * @return Signal Standard Deviation
     */
    public float getSignalStdDev() {
        return m_signalStdDev;
    }

    /**
     * Get Threshold.
     * @return Threshold
     */
    public float getThreshold() {
        return m_threshold;
    }

    /**
     * Get Skew.
     * @return Skew
     */
    public float getSkew() {
        return m_skew;
    }
}
