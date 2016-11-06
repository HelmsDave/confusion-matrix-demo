/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

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
     * @return NPV
     */
    public static float getNPV(final TestResults results) {
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
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_TRUE_NEG,
            InfoGraphic.Elements.DENOM_TRUE_NEG,
            InfoGraphic.Elements.DENOM_FALSE_NEG};
    }     
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
       
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Negative Predictive Value, NPV<p>");
        out.append("Proportion of predicted negative which are actual negative.<br>");
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
