/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for Prevalence formula.
 * @author Dave
 */
public class MetricsPanelPrevalence extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelPrevalence() {
        super("Prevalence");
    }
    
    
    /**
     * Calculate Prevalence.
     * @param results Test Results
     * @return Precision
     */
    protected static float getPrevalence(final TestResults results) {
       final float prevalence = 
               (float)(results.getTruePositive() + results.getFalseNegative()) /
                       (float)TestResults.POPULATION_SIZE;     
       
       return prevalence;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getPrevalence(results);
    }    
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Prevalence = (").append(ColorScheme.HTML_TRUE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT).append(") / Total Population<p>");
        
        out.append(String.format("%.3f = (%s + %s) / %s<p>",
               getPrevalence(results), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative()),
               TestResults.format(TestResults.POPULATION_SIZE)));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
