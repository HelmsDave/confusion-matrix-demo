/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for False Discovery Rate formula.
 * @author Dave
 */
public class MetricsPanelFalseDiscoveryRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelFalseDiscoveryRate() {
        super("False Discovery Rate");
    }
    
    /**
     * Calculate FDR.
     * @param results Test Results
     * @return FDR
     */
    protected static float getFDR(final TestResults results) {
       final float fdr = 
               (float)(results.getFalsePositive() /
                ((float)results.getTruePositive() + (float)results.getFalsePositive()));
       return fdr;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getFDR(results);
    }       
    
    /** {@inheritDoc} */
    @Override     
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_FALSE_POS,
            InfoGraphic.Elements.DENOM_TRUE_POS,
            InfoGraphic.Elements.DENOM_FALSE_POS};
    }     
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("False Discovery Rate, FDR<p>");
        out.append("Proportion of predicted positive which are actual negative.<br>");
        out.append("FDR = ").append(ColorScheme.HTML_FALSE_POS_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_POS_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getFDR(results), 
               TestResults.format(results.getFalsePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalsePositive())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
