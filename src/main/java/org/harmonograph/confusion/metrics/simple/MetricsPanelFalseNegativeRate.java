/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

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
     * Calculate False Positive Rate.
     * @param results Test Results
     * @return False Positive Rate
     */
    public static float getFNR(final TestResults results) {
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
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_FALSE_NEG,
            InfoGraphic.Elements.DENOM_TRUE_POS,
            InfoGraphic.Elements.DENOM_FALSE_NEG};
    }     
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
       
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("False Negative Rate, FNR, Miss Rate<p>");
        out.append("Proportion of acutal positive which are predicted negative.<br>");
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
