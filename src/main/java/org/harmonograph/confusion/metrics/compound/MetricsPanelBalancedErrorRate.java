/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Balanced Error Rate formula.
 * @author Dave
 */
public class MetricsPanelBalancedErrorRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelBalancedErrorRate() {
        super("Balanced Error Rate");
    }
    
    
    /**
     * Calculate Balanced Error Rate Score.
     * @param results Test Results
     * @return Balanced Error Rate
     */
    protected static float getBalancedErrorRate(final TestResults results) {
       return 1f - MetricsPanelBalancedAccuracy.getBalancedAccuracy(results);
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getBalancedErrorRate(results);
    }      
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Balanced Error Rate = 1 - Balanced Accuracy<br>");
                
        
        out.append(String.format("%.3f = 1 - %.3f<p>",
               getBalancedErrorRate(results), 
               MetricsPanelBalancedAccuracy.getBalancedAccuracy(results)));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
