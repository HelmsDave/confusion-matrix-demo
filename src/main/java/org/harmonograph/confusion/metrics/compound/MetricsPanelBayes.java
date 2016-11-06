/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.ColorScheme;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;

/**
 * MetricsPanel for Bayes' Theorem.
 * @author Dave
 */
public class MetricsPanelBayes extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelBayes() {
        super("<html>Bayes'<br>Theorem</html>");
    }
    
    /** Get term. */
    private static float getB1(final TestResults results) { 
        return (float)results.getTruePositive() /
            (float)(results.getTruePositive() + results.getFalseNegative());
    }
    /** Get term. */
    private static float getB2(final TestResults results) { 
        return (float)(results.getTruePositive() + results.getFalseNegative());
    }
    /** Get term. */
    private static float getB3(final TestResults results) { 
        return (float)(results.getTruePositive() + results.getFalsePositive()); 
    }    
    
    /**
     * Calculate Bayes Score.
     * @param results Test Results
     * @return Bayes Score
     */
    protected static float getBayes(final TestResults results) {
        
       // = (TP / (TP+FN)) * (TP+FN) / (TP+FP) 
       final float bayes = getB1(results) * getB2(results) / getB3(results);
       
       return bayes;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getBayes(results);
    }      
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
             
        final int pop = results.getPopulationTotal();
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Bayes' Theorem, Bayes' Rule<br>");
        out.append("General form, P(A|B) = P(B|A) * P(A) / P(B)<br>");
        out.append("Read P(A) as \"Probablity of A\"<br>");
        out.append("Read P(A|B) as \"Probablity of A given B\"<br><br>");
        out.append("Specific Example, A=Actual True Condition, B=Positive Test Result.<br>");

        // TP / (TP+FP) = (TP / (TP+FN)) * (TP+FN) / (TP+FP) 
        out.append("(Probablity of True Condition given Positive Test Result) = ");
        out.append("(Probablity of Positive Test Result given True Condition) * ");
        out.append("(Probablity of True Condition) / ");
        out.append("(Probablity of Positive Test Result)<br><br>");
        
        out.append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("/(");
        out.append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("+");
        out.append(ColorScheme.HTML_FALSE_POS_TEXT_SHORT).append(") = (");
        out.append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("/(");
        out.append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("+");
        out.append(ColorScheme.HTML_FALSE_NEG_TEXT_SHORT).append(")) * (");
        out.append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("+");
        out.append(ColorScheme.HTML_FALSE_NEG_TEXT_SHORT).append(") / (");        
        out.append(ColorScheme.HTML_TRUE_POS_TEXT_SHORT).append("+");
        out.append(ColorScheme.HTML_FALSE_POS_TEXT_SHORT).append(")<br><br>");         
        

        
        out.append(String.format("%s = (%s / (%s+%s)) * (%s+%s) / (%s+%s)<p>",
               TestResults.format((int)(getBayes(results)*pop)), 
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalseNegative()),
               TestResults.format(results.getTruePositive()),
               TestResults.format(results.getFalsePositive())));
        
        out.append(String.format("%s = %s * %s / %s <br><br>",
               TestResults.format((int)(getBayes(results)*pop)), 
               TestResults.format((int)(getB1(results)*pop)), 
               TestResults.format((int)(getB2(results))), 
               TestResults.format((int)(getB3(results)))));
     
        out.append(String.format("Probablity of True Condition given Positive Test Result: %s<br>",
                TestResults.format((int)(getBayes(results)*pop))));
        out.append(String.format("Probablity of Positive Test Result given True Condition: %s<br>",
                TestResults.format((int)(getB1(results)*pop))));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
