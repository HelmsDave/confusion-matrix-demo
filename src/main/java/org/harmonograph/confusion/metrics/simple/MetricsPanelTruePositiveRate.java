/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for True Positive Rate formula.
 * @author Dave
 */
public class MetricsPanelTruePositiveRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelTruePositiveRate() {
        super("Recall");
    }
    
    /**
     * Calculate Recall.
     * @param results Test Results
     * @return Recall
     */
    public static float getTPR(final TestResults results) {
       final float tpr = 
               (float)(results.getTruePositive() /
                ((float)results.getTruePositive() + (float)results.getFalseNegative()));
       
       return tpr;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getTPR(results);
    }    
    
    /** {@inheritDoc} */
    @Override     
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_TRUE_POS,
            InfoGraphic.Elements.DENOM_FALSE_NEG,
            InfoGraphic.Elements.DENOM_TRUE_POS};
    }          
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Recall, aka. Sensitivity, True Positive Rate<p>");
        out.append("Proportion of actual positives which are predicted positive.<br>");
        out.append("Recall = ").append(ColorScheme.HTML_TRUE_POS_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getTPR(results), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
