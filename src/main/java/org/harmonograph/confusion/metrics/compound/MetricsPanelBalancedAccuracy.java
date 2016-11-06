/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Balanced Accuracy formula.
 * @author Dave
 */
public class MetricsPanelBalancedAccuracy extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelBalancedAccuracy() {
        super("Balanced Accuracy");
    }
    
    
    /**
     * Calculate Balanced Accuracy Score.
     * @param results Test Results
     * @return Balanced Accuracy
     */
    // ½ (TP / (TP + FN) + TN / (TN + FP)
    protected static float getBalancedAccuracy(final TestResults results) {
       final float bacc1 = 
               (float)(results.getTruePositive()) / 
                 (float)(results.getTruePositive() + results.getFalseNegative());
       final float bacc2 = 
               (float)(results.getTrueNegative()) / 
                 (float)(results.getTrueNegative() + results.getFalsePositive());
   
       final float bacc = (bacc1 + bacc2) / 2f;
       return bacc;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getBalancedAccuracy(results);
    }      
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Balanced Accuracy (BACC), Balanced Classification Rate<br>");
        out.append("Arithmetic average of Sensitivity and Specificity.<br>");
        out.append("BACC = (").append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("/(")
                   .append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("+")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT_SHORT).append(") + ")
                
                .append(ColorScheme.HTML_TRUE_NEG_TEXT_SHORT).append("/(")
                   .append(ColorScheme.HTML_TRUE_NEG_TEXT_SHORT).append("+")
                   .append(ColorScheme.HTML_FALSE_POS_TEXT_SHORT).append("))/2<br>");
                
        
        out.append(String.format("%.3f = (%s/(%s+%s) + %s/(%s+%s))/2<p>",
               getBalancedAccuracy(results), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative()),
               TestResults.format(results.getTrueNegative()),
               TestResults.format(results.getTrueNegative()),               
               TestResults.format(results.getFalsePositive())));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
