/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for F1 Score formula.
 * @author Dave
 */
public class MetricsPanelF1Score extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelF1Score() {
        super("<html>F1<br>Score</html>");
    }
    
    
    /**
     * Calculate F1 Score.
     * @param results Test Results
     * @return F1 Score
     */
    protected static float getF1Score(final TestResults results) {
       final float f1 = 
               (float)(2*results.getTruePositive()) / 
                 ((float)(2*results.getTruePositive() + 
                         results.getFalsePositive() + 
                              results.getFalseNegative()));
   
       
       return f1;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getF1Score(results);
    }      
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("F1 Score = 2*").append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append(" / ")
                   .append("(2*").append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_POS_TEXT_SHORT).append(" + ")
                   .append(ColorScheme.HTML_FALSE_NEG_TEXT_SHORT).append(")<br>");
        
        out.append(String.format("%.3f = 2*%s / (2*%s + %s + %s)<p>",
               getF1Score(results), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalsePositive()),
               TestResults.format(results.getFalseNegative())));
       
        out.append("</html>");
        return out.toString();        
    }
    
}
