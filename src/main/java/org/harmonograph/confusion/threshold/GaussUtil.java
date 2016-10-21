package org.harmonograph.confusion.threshold;

/**
 * Helper methods for probability distributions.
 * @author Dave
 */
public class GaussUtil {
    
    /** Private constructor for utility class. */
    private GaussUtil() { 
    }
    
    // return pdf(x) = standard Gaussian pdf
    public static float pdf(float x) {
        return (float)Math.exp(-x*x / 2f) / (float)Math.sqrt(2f * (float)Math.PI);
    }

    // return pdf(x, mu, signma) = Gaussian pdf with mean mu and stddev sigma
    public static float pdf(float x, float mu, float sigma) {
        return pdf((x - mu) / sigma) / sigma;
    }

    // return cdf(z) = standard Gaussian cdf using Taylor approximation
    public static float cdf(float z) {
        if (z < -8f) return 0f;
        if (z >  8f) return 1f;
        float sum = 0f, term = z;
        for (int i = 3; sum + term != sum; i += 2) {
            sum  = sum + term;
            term = term * z * z / i;
        }
        return 0.5f + sum * pdf(z);
    }

    // return cdf(z, mu, sigma) = Gaussian cdf with mean mu and stddev sigma
    public static float cdf(float z, float mu, float sigma) {
        return cdf((z - mu) / sigma);
    } 
}
