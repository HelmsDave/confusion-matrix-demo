/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for False Negative Rate Value formula.
 * @author Dave
 */
public class MetricsPanelFalseNegativeRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelFalseNegativeRate() {
        super("False Negative Rate");
    }
    
    /**
     * Calculate FalsePositiveRate.
     * @param results Test Results
     * @return Precision
     */
    protected static float getFNR(final TestResults results) {
       final float fnr = 
               (float)(results.getFalseNegative() /
                ((float)results.getTruePositive() + (float)results.getFalseNegative()));
    
       return fnr;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getFNR(results);
    }       
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
       
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("False Negative Rate, aka. FNR, Miss Rate<p>");
        out.append("FPR = ").append(ColorScheme.HTML_FALSE_NEG_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getFNR(results), 
               TestResults.format(results.getFalseNegative()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}