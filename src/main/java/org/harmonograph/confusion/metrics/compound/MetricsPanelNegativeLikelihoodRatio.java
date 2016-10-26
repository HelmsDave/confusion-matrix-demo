/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.metrics.simple.MetricsPanelFalseNegativeRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTrueNegativeRate;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Negative Likelihood Ratio formula.
 * @author Dave
 */
public class MetricsPanelNegativeLikelihoodRatio extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelNegativeLikelihoodRatio() {
        super("Negative Likelihood Ratio");
    }
    
    /**
     * Calculate Negative Likelihood Ratio.
     * @param results Test Results
     * @return Negative Likelihood Ratio
     */
    protected static float NegLR(final TestResults results) {
       final float NegLR = 
               MetricsPanelFalseNegativeRate.getFNR(results) /
               MetricsPanelTrueNegativeRate.getTNR(results);  
       
       return NegLR;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return NegLR(results);
    }
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Negative Likelihood Ratio (LR-)<p>");
        out.append("(LR-) = FNR / TNR<p>");
        
        out.append(String.format("%.3f = %s / %s<p>",
               NegLR(results), 
               MetricsPanelFalseNegativeRate.getFNR(results) ,
               MetricsPanelTrueNegativeRate.getTNR(results)));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
