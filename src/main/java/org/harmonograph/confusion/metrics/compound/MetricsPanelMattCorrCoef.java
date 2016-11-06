/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Matthews Correlation Coefficient formula.
 * @author Dave
 */
public class MetricsPanelMattCorrCoef extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelMattCorrCoef() {
        super("<html>Matthews<br>Correlation Coefficient</html>");
    }
    
    
    /**
     * Calculate Matthews Correlation Coefficient.
     * @param results Test Results
     * @return Matthews Correlation Coefficient
     */
    protected static float getMattCorrCoef(final TestResults results) {
       final float mcc = 
               (float)(results.getTruePositive() * results.getTrueNegative() -
                       results.getFalsePositive() * results.getFalseNegative()) / 
                 (float)Math.sqrt(
                         (float)(results.getTruePositive() + results.getFalsePositive())*
                         (float)(results.getTruePositive() + results.getFalseNegative())*
                         (float)(results.getTrueNegative() + results.getFalsePositive())*
                         (float)(results.getTrueNegative() + results.getFalseNegative()));

       return mcc;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return (getMattCorrCoef(results)+1f) / 2f;
    }      
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Matthews Correlation Coefficient, MCC = ")
                .append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("*")
                .append(ColorScheme.HTML_TRUE_NEG_TEXT_SHORT).append(" - ")
                .append(ColorScheme.HTML_FALSE_POS_TEXT_SHORT).append("*")
                .append(ColorScheme.HTML_FALSE_NEG_TEXT_SHORT).append(" /<br>")
                .append("sqrt((")  
                .append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("*")
                .append(ColorScheme.HTML_FALSE_POS_TEXT_SHORT).append(")(")
                .append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("*")
                .append(ColorScheme.HTML_FALSE_NEG_TEXT_SHORT).append(")(")
                .append(ColorScheme.HTML_TRUE_NEG_TEXT_SHORT).append("*")
                .append(ColorScheme.HTML_FALSE_POS_TEXT_SHORT).append(")(")
                .append(ColorScheme.HTML_TRUE_NEG_TEXT_SHORT).append("*")
                .append(ColorScheme.HTML_FALSE_NEG_TEXT_SHORT).append(")<br>");                
                
               
        out.append(String.format("%.3f = (%s*%s + %s*%s) /<br>sqrt((%s + %s)(%s + %s)<br>(%s + %s)(%s + %s))<p>",
               getMattCorrCoef(results), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getFalsePositive()),
               TestResults.format(results.getFalseNegative()),
               
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalsePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getFalsePositive()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getFalseNegative())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
