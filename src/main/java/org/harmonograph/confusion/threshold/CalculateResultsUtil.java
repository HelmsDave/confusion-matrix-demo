/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;

import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.messages.Threshold;

/**
 * Utility to calculate Results from Thresholds.
 * @author Dave
 */
public class CalculateResultsUtil {
    
    /** Private constructor. */
    private CalculateResultsUtil() {
    }
    
   /** 
     * Update threshold data in canvas, and 
     * calculate results and send results to 
     * the rest of the GUI.
     * @param thresh Threshold data
     * @return Calculated Test Results
     */
    public static TestResults calculateResults(final Threshold thresh) {

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
            truePositive, trueNegative, falsePositive, falseNegative, thresh);   
        
        return results;
    }
    
}
