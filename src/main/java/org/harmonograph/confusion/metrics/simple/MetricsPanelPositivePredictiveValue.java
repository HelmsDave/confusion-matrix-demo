/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Positive Predictive Value formula.
 * @author Dave
 */
public class MetricsPanelPositivePredictiveValue extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelPositivePredictiveValue() {
        super("Positive Predictive Value");
    }
    
    /**
     * Calculate PPV.
     * @param results Test Results
     * @return PPV
     */
    public static float getPPV(final TestResults results) {
       final float ppv = 
               (float)(results.getTruePositive() /
                ((float)results.getTruePositive() + (float)results.getFalsePositive()));  
       
       return ppv;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getPPV(results);
    }
    
    /** {@inheritDoc} */
    @Override     
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_TRUE_POS,
            InfoGraphic.Elements.DENOM_TRUE_POS,
            InfoGraphic.Elements.DENOM_FALSE_POS};
    }     
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Positive Predicted Value (PPV), Precision<p>");
        out.append("Proportion of predicted positive which are actual positive.<br>");

        out.append("PPV = ").append(ColorScheme.HTML_TRUE_POS_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_POS_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_POS_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getPPV(results),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalsePositive())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
