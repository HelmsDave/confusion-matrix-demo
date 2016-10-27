/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for False Omission Rate formula.
 * @author Dave
 */
public class MetricsPanelFalseOmissionRate extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelFalseOmissionRate() {
        super("False Omission Rate");
    }
    
    /**
     * Calculate FOR.
     * @param results Test Results
     * @return FOR
     */
    public static float getFOR(final TestResults results) {
       final float falseOmissionTate = 
               (float)(results.getFalseNegative() /
                ((float)results.getTrueNegative() + (float)results.getFalseNegative()));
       return falseOmissionTate;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getFOR(results);
    }     
    
   /** {@inheritDoc} */
    @Override     
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {
            InfoGraphic.Elements.NUM_FALSE_NEG,
            InfoGraphic.Elements.DENOM_TRUE_NEG,
            InfoGraphic.Elements.DENOM_FALSE_NEG};
    }        
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("False Omission Rate, aka. FDR<p>");
        out.append("FOR = ").append(ColorScheme.HTML_FALSE_NEG_TEXT).append(" / (")
                   .append(ColorScheme.HTML_TRUE_NEG_TEXT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT).append(")<p>");
        
        out.append(String.format("%.3f = %s / (%s + %s)<p>",
               getFOR(results), 
               TestResults.format(results.getFalseNegative()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getFalseNegative())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
