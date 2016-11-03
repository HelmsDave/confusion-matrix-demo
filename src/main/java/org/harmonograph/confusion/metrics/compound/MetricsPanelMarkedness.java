/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;
import org.harmonograph.confusion.metrics.simple.MetricsPanelNegativePredictiveValue;
import org.harmonograph.confusion.metrics.simple.MetricsPanelPositivePredictiveValue;

/**
 * MetricsPanel for Markedness formula.
 * @author Dave
 */
public class MetricsPanelMarkedness extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelMarkedness() {
        super("Markedness");
    }
    
    /**
     * Calculate Markedness.
     * @param results Test Results
     * @return Markedness
     */
    protected static float getMarkedness(final TestResults results) {
       final float markedness = 
               MetricsPanelPositivePredictiveValue.getPPV(results) +
               MetricsPanelNegativePredictiveValue.getNPV(results) - 1;
       
       return markedness;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getMarkedness(results);
    }
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Markedness<p>");
        out.append("Markedness = PPV + NPV - 1<p>");
        
        out.append(String.format("%.3f = %.3f + %.3f - 1<p>",
               getMarkedness(results), 
               MetricsPanelPositivePredictiveValue.getPPV(results),
               MetricsPanelNegativePredictiveValue.getNPV(results)));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
