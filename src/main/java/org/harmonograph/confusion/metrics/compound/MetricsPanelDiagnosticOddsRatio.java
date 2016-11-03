/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Diagnostic Odds Ratio formula.
 * @author Dave
 */
public class MetricsPanelDiagnosticOddsRatio extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelDiagnosticOddsRatio() {
        super("<html>Diagnostic<br>Odds Ratio</html>");
    }
    
    /**
     * Calculate Diagnostic Odds Ratio.
     * @param results Test Results
     * @return Diagnostic Odds Ratio
     */
    protected static float getDOR(final TestResults results) {
       final float dor = 
               MetricsPanelPositiveLikelihoodRatio.PosLR(results) /
               MetricsPanelNegativeLikelihoodRatio.NegLR(results);  
       
       return dor;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getDOR(results);
    }
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Diagnostic Odds Ratio (DOR)<p>");
        out.append("DOR = (LR+) / (LR-)<p>");
        
        out.append(String.format("%.3f = %s / %s<p>",
               getDOR(results), 
               MetricsPanelPositiveLikelihoodRatio.PosLR(results) ,
               MetricsPanelNegativeLikelihoodRatio.NegLR(results)));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
