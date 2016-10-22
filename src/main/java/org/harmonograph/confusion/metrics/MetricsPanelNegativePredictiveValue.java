/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for Negative Predictive Value formula.
 * @author Dave
 */
public class MetricsPanelNegativePredictiveValue extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelNegativePredictiveValue() {
        super("Negative Predictive Value");
    }
    
    /**
     * Calculate NPV.
     * @param results Test Results
     * @return Precision
     */
    protected static float getNPV(final TestResults results) {
       final float npv = 
               (float)(results.getTrueNegative() /
                ((float)results.getTrueNegative() + (float)results.getFalseNegative()));
    
       return npv;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getNPV(results);
    }       
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
       
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Negative Predictive Value, aka. NPV<p>");
        out.append("NPV = ").append(ColorScheme.HTML_TRUE_NEG_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_NEG_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getNPV(results), 
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getFalseNegative())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}