/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;
import org.harmonograph.confusion.metrics.simple.InfoGraphic.Elements;

/**
 * MetricsPanel for Error Rate formula.
 * @author Dave
 */
public class MetricsPanelErrorRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelErrorRate() {
        super("Error Rate");
    }
    
    
    /**
     * Calculate Error Rate.
     * @param results Test Results
     * @return Error Rate
     */
    public static float getErrorRate(final TestResults results) {
       final float errorRate = 
               (float)(results.getFalsePositive() + results.getFalseNegative()) /
                       (float)TestResults.POPULATION_SIZE;     
       
       return errorRate;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getErrorRate(results);
    }    
    
    /** {@inheritDoc} */
    @Override     
    public Elements[] updateGraphic() {
        return new Elements[] {
            Elements.NUM_FALSE_POS, Elements.NUM_FALSE_NEG,
            Elements.DENOM_FALSE_NEG, Elements.DENOM_FALSE_POS,
            Elements.DENOM_TRUE_NEG, Elements.DENOM_TRUE_POS};
    }    
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Proportion of total population which are classified incorrectly.<br>");
        out.append("Error Rate = (").append(ColorScheme.HTML_FALSE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT).append(") / Total Population<p>");
        
        out.append(String.format("%.3f = (%s + %s) / %s<p>",
               getErrorRate(results), 
               TestResults.format(results.getFalsePositive()),
               TestResults.format(results.getFalseNegative()),
               TestResults.format(TestResults.POPULATION_SIZE)));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
