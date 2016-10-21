/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for Recall formula.
 * @author Dave
 */
public class MetricsPanelRecall extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelRecall() {
        super("Recall");
    }
    
    /**
     * Calculate Recall.
     * @param results Test Results
     * @return Recall
     */
    protected static float getRecall(final TestResults results) {
       final float recall = 
               (float)(results.getTruePositive() /
                ((float)results.getTruePositive() + (float)results.getFalseNegative()));
       
       return recall;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getRecall(results);
    }    
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Recall, aka. Sensitivity, True Positive Rate<p>");
        out.append("Recall = ").append(ColorScheme.HTML_TRUE_POS_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getRecall(results), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
