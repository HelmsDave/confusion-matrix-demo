/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;

/**
 * Helper methods for probability distributions.
 * It would be better to use a third party 
 * library, but we don't want a dependency for
 * this one function.
 * 
 * @author Dave
 */
public class GaussUtil {
    
    /** Private constructor for utility class. */
    private GaussUtil() { 
    }
    
    /** 
     * Function to calculate Gaussian PDF,
     * for Mean and Standard Deviation of one.
     * 
     * @param x x
     * @return pdf(x)
     */
    public static float pdf(final float x) {
        return (float)Math.exp(-x*x / 2f) / 
                (float)Math.sqrt(2f * (float)Math.PI);
    }

    /** 
     * Function to calculate Gaussian PDF,
     * with specified Mean and Standard Deviation.
     * 
     * @param x x
     * @param m Mean
     * @param sig Standard Deviation
     * @return pdf(x)
     */
    public static float pdf(final float x, final float m, final float sig) {
        return pdf((x - m) / sig) / sig;
    }

    /** 
     * Function to calculate Gaussian CDF,
     * for Mean and Standard Deviation of one.
     * 
     * @param z z
     * @return pdf(z)
     */    
    public static float cdf(final float z) {
        if (z < -8f) return 0f;
        if (z >  8f) return 1f;
        float sum = 0f;
        float term = z;
        for (int i = 3; sum + term != sum; i += 2) {
            sum  = sum + term;
            term = term * z * z / i;
        }
        return 0.5f + sum * pdf(z);
    }

    /** 
     * Function to calculate Gaussian CDF,
     * with specified Mean and Standard Deviation.
     * 
     * @param z z
     * @param m Mean
     * @param sig Standard Deviation
     * @return pdf(z)
     */    
    public static float cdf(final float z, final float m, final float sig) {
        return cdf((z - m) / sig);
    } 
}
