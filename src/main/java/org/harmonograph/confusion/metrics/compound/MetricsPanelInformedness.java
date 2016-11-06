/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTrueNegativeRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTruePositiveRate;

/**
 * MetricsPanel for Informedness formula.
 * @author Dave
 */
public class MetricsPanelInformedness extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelInformedness() {
        super("Informedness");
    }
    
    /**
     * Calculate Informedness.
     * @param results Test Results
     * @return Informedness
     */
    //  Sensitivity + Specificity ? 1
    protected static float getInformedness(final TestResults results) {
       final float informedness = 
               MetricsPanelTruePositiveRate.getTPR(results) +
               MetricsPanelTrueNegativeRate.getTNR(results) - 1;       
       
       
       return informedness;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getInformedness(results);
    }
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Informedness<p>");
        out.append("Informedness = Sensitivity + Specificity - 1<p>");
        
        out.append(String.format("%.3f = %.3f + %.3f - 1<p>",
               getInformedness(results),                
               MetricsPanelTruePositiveRate.getTPR(results),
               MetricsPanelTrueNegativeRate.getTNR(results)));

        out.append("</html>");
        return out.toString();        
    }
    
}
