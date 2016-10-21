/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for Accuracy formula.
 * @author Dave
 */
public class MetricsPanelAccuracy extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelAccuracy() {
        super("Accuracy");
    }
    
    
    /**
     * Calculate Accuracy.
     * @param results Test Results
     * @return Precision
     */
    protected static float getAccuracy(final TestResults results) {
       final float accuracy = 
               (float)(results.getTruePositive() + results.getTrueNegative()) /
                       (float)TestResults.POPULATION_SIZE;     
       
       return accuracy;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getAccuracy(results);
    }    
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Accuracy = (").append(ColorScheme.HTML_TRUE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_TRUE_NEG_TEXT).append(") / Total Population<p>");
        
        out.append(String.format("%.3f = (%s + %s) / %s<p>",
               getAccuracy(results), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(TestResults.POPULATION_SIZE)));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
