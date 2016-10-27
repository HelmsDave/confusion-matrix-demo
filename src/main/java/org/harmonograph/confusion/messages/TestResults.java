/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.messages;

import org.harmonograph.confusion.threshold.CalculateResultsUtil;

/**
 * Data class representing test results from a threshold test.
 * The majority of the GUI is updated from to this message.
 * 
 * For simplicity, this assumes a fixed population size
 * POPULATION_SIZE is split into the four buckets of 
 * the confusion matrix. 
 * 
 * @author Dave
 */
public class TestResults {
   
    /** For ease of formatting, assume a fixed size population. */
    public static final int POPULATION_SIZE = 1000000;
    
    /** Number of population tested as True Positive. */
    private final int m_truePositive;
    
    /** Number of population tested as True Negative. */
    private final int m_trueNegative;
    
    /** Number of population tested as False Positive. */
    private final int m_falsePositive;
    
    /** Number of population tested as False Negative. */
    private final int m_falseNegative;
    
    /** Threshold data, used to calculate these results. */
    private final Threshold m_thresh;
    
    /** Default instance, just for kicks. */
    public static final TestResults DEFAULT = 
            CalculateResultsUtil.calculateResults(Threshold.DEFAULT);
    
    /** Simple Constructor. 
     *  Sum of parameters should be POPULATION_SIZE.
     * 
     * @param truePositive Number of population tested as True Positive
     * @param trueNegative Number of population tested as True Negative
     * @param falsePositive Number of population tested as False Positive
     * @param falseNegative Number of population tested as False Negative
     * @param thresh Threshold Data
     */
    public TestResults(
        final int truePositive,
        final int trueNegative,
        final int falsePositive,
        final int falseNegative,
        final Threshold thresh) {
        
        m_truePositive = truePositive;
        m_trueNegative = trueNegative;
        m_falsePositive = falsePositive;
        m_falseNegative = falseNegative;
        m_thresh = thresh;
    }

    /**
     * Get the number of population tested as True Positive
     * @return number of population tested as True Positive
     */
    public int getTruePositive() {
        return m_truePositive;
    }

    /**
     * Get the number of population tested as True Negative
     * @return number of population tested as True Negative
     */
    public int getTrueNegative() {
        return m_trueNegative;
    }

    /**
     * Get the number of population tested as False Positive
     * @return number of population tested as False Positive
     */
    public int getFalsePositive() {
        return m_falsePositive;
    }

    /**
     * Get the number of population tested as False Negative
     * @return number of population tested as False Negative
     */
    public int getFalseNegative() {
        return m_falseNegative;
    }
    
    /**
     * Get the number of population actual true.
     * @return number of population actual true
     */
    public int getActualTrue() {
        return getTruePositive() + getFalseNegative();
    }    
    
    /**
     * Get the number of population actual false.
     * @return number of population actual false
     */
    public int getActualFalse() {
        return getTrueNegative() + getFalsePositive();
    }        
    
    /**
     * Get the number of population predicted true.
     * @return number of population predicted true
     */
    public int getPredictedTrue() {
        return getTruePositive() + getFalsePositive();
    }    
    
    /**
     * Get the number of population predicted false.
     * @return number of population predicted false
     */
    public int getPredictedFalse() {
        return getTrueNegative() + getFalseNegative();
    }           
    
    /**
     * Get the number of population.
     * @return number of population
     */
    public int getPopulationTotal() {
        return getActualTrue() + getActualFalse();
    }           
    
    /** 
     * Get Threshold used to calculate results.
     * 
     * @return Threshold used to calculate results
     */
    public Threshold getThreshold() {
        return m_thresh;
    }
    
    /** 
     * Helper to format population value as a pretty 
     * string representing percentage relative
     * to POPULATION_SIZE.
     * 
     * @param val value to format
     * @return Formatted value
     */
    public static String format(final int val) {
        return String.format("%3.3f%%", (float)val * 100f / (float)POPULATION_SIZE);
    }
    
//    /** {@inheritDoc} */
//    @Override
//    public boolean equals(final Object otherObj) {
//        if (!(otherObj instanceof TestResults)) {
//            return false;
//        }
//        if (otherObj == this) {
//            return true;
//        }
//        
//        final TestResults other = (TestResults)otherObj;
//        
//        if (m_truePositive != other.m_truePositive) {
//            return false;
//        }
//        if (m_trueNegative != other.m_trueNegative) {
//            return false;
//        }
//        if (m_falsePositive != other.m_falsePositive) {
//            return false;
//        }
//        if (m_falseNegative != other.m_falseNegative) {
//            return false;
//        }        
//        
//        return true;
//     }
//    
//    /** {@inheritDoc} */
//    @Override
//    public int hashCode() {
//        return m_truePositive + 3*m_trueNegative + 
//                7*m_falsePositive + 13*m_falseNegative;
//    }
//    
//    /** {@inheritDoc} */
//    @Override
//    public String toString() {
//        return String.format(
//                "TP: %s TN: %s FP: %s FN: %s",
//                format(m_truePositive),
//                format(m_trueNegative),
//                format(m_falsePositive),
//                format(m_falseNegative));
//    }
    
}
