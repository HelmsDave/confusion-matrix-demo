/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.metrics.simple.MetricsPanelTruePositiveRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelFalsePositiveRate;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Positive Likelihood Ratio formula.
 * @author Dave
 */
public class MetricsPanelPositiveLikelihoodRatio extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelPositiveLikelihoodRatio() {
        super("Positive Likelihood Ratio");
    }
    
    /**
     * Calculate Precision.
     * @param results Test Results
     * @return Precision
     */
    protected static float PosLR(final TestResults results) {
       final float PosLR = 
               MetricsPanelTruePositiveRate.getTPR(results) /
               MetricsPanelFalsePositiveRate.getFPR(results);  
       
       return PosLR;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return PosLR(results);
    }
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Positive Likelihood Ratio (LR+)<p>");
        out.append("(LR+) = TPR / FPR<p>");
        
        out.append(String.format("%.3f = %s / %s<p>",
               PosLR(results), 
               MetricsPanelTruePositiveRate.getTPR(results) ,
               MetricsPanelFalsePositiveRate.getFPR(results)));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
