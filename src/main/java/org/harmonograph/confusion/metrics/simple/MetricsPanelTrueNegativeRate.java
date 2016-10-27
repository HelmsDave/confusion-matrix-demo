/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for True Negative Rate formula.
 * @author Dave
 */
public class MetricsPanelTrueNegativeRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelTrueNegativeRate() {
        super("True Negative Rate");
    }
    
    /**
     * Calculate True Negative Rate.
     * @param results Test Results
     * @return True Negative Rate
     */
    public static float getTNR(final TestResults results) {
       final float tnr = 
               (float)(results.getTrueNegative() /
                ((float)results.getTrueNegative() + (float)results.getFalsePositive()));
    
       return tnr;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getTNR(results);
    }       
    
    /** {@inheritDoc} */
    @Override     
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_TRUE_NEG,
            InfoGraphic.Elements.DENOM_TRUE_NEG,
            InfoGraphic.Elements.DENOM_FALSE_POS};
    }          
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
       
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("True Negative Rate, aka. TNR, Sensitivity, Specificity<p>");
        out.append("FPR = ").append(ColorScheme.HTML_TRUE_NEG_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_NEG_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_POS_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getTNR(results), 
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getFalsePositive())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
