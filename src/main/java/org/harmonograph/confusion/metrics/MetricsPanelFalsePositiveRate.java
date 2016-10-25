/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for False Positive Rate formula.
 * @author Dave
 */
public class MetricsPanelFalsePositiveRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelFalsePositiveRate() {
        super("False Positive Rate");
    }
    
    /**
     * Calculate Positive Likelihood Ratio.
     * @param results Test Results
     * @return Positive Likelihood Ratio
     */
    protected static float getFPR(final TestResults results) {
       final float fpr = 
               (float)(results.getFalsePositive() /
                ((float)results.getTrueNegative() + (float)results.getFalsePositive()));
    
       return fpr;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getFPR(results);
    }       
    
    /** {@inheritDoc} */
    @Override     
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_TRUE_POS,
            InfoGraphic.Elements.DENOM_TRUE_NEG,
            InfoGraphic.Elements.DENOM_FALSE_POS};
    }     
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
       
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("False Positive Rate, aka. FPR, Fall-out<p>");
        out.append("FPR = ").append(ColorScheme.HTML_FALSE_POS_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_NEG_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_POS_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getFPR(results), 
               TestResults.format(results.getFalsePositive()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getFalsePositive())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
