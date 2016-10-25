/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;

/**
 * MetricsPanel for Positive Predicted Value formula.
 * @author Dave
 */
public class MetricsPanelPositivePredictedValue extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelPositivePredictedValue() {
        super("Positive Predicted Value");
    }
    
    /**
     * Calculate PPV.
     * @param results Test Results
     * @return PPV
     */
    protected static float getPPV(final TestResults results) {
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
        out.append("Positive Predicted Value (PPV), aka. Precision<p>");
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
