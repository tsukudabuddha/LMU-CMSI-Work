package java.math;

import java.util.Random;
import java.io.*;
public class BigInteger{

    int signum;
    int[] mag;
    private int bitCount =  -1;
    private int bitLength = -1;
		
		public static final BigInteger ZERO = new BigInteger(new int[0], 0);
    public static final BigInteger ONE = valueOf(1);
    public static final BigInteger TEN = valueOf(10);

    //Constructors
    public BigInteger(byte[] val) {
        if (val.length == 0)
            throw new NumberFormatException("Zero length BigInteger");

        if (val[0] < 0) {
            mag = makePositive(val);
            signum = -1;
        } else {
            mag = stripLeadingZeroBytes(val);
            signum = (mag.length == 0 ? 0 : 1);
        }
    }

    public BigInteger(int signum, byte[] magnitude) {
        this.mag = stripLeadingZeroBytes(magnitude);

        if (signum < -1 || signum > 1)
            throw(new NumberFormatException("Invalid signum value"));

        if (this.mag.length==0) {
            this.signum = 0;
        } else {
            if (signum == 0)
                throw(new NumberFormatException("signum-magnitude mismatch"));
            this.signum = signum;
        }
    }

    public BigInteger(String val) {
        this(val, 10);
    }

    public BigInteger(int numBits, Random rnd) {
        this(1, randomBits(numBits, rnd));
    }

    private BigInteger(byte[] magnitude, int signum) {
        this.signum = (magnitude.length==0 ? 0 : signum);
        this.mag = stripLeadingZeroBytes(magnitude);
    }

    /**
     * This private constructor is for internal use in converting
     * from a MutableBigInteger object into a BigInteger.
     */
    BigInteger(MutableBigInteger val, int sign) {
        if (val.offset > 0 || val.value.length != val.intLen) {
            mag = new int[val.intLen];
            for(int i=0; i<val.intLen; i++)
                mag[i] = val.value[val.offset+i];
        } else {
            mag = val.value;
        }

        this.signum = (val.intLen == 0) ? 0 : sign;
    }

    public static final BigInteger ZERO = new BigInteger(new int[0], 0);

    public static final BigInteger ONE = valueOf(1);

    private static final BigInteger TWO = valueOf(2);

    public static final BigInteger TEN = valueOf(10);

    // Arithmetic Operations

    public BigInteger add(BigInteger val) {
        int[] resultMag;
        if (val.signum == 0)
            return this;
        if (signum == 0)
            return val;
        if (val.signum == signum)
            return new BigInteger(add(mag, val.mag), signum);

        int cmp = intArrayCmp(mag, val.mag);
        if (cmp==0)
            return ZERO;
        resultMag = (cmp>0 ? subtract(mag, val.mag)
                           : subtract(val.mag, mag));
        resultMag = trustedStripLeadingZeroInts(resultMag);

        return new BigInteger(resultMag, cmp*signum);
    }

    /**
     * Adds the contents of the int arrays x and y. This method allocates
     * a new int array to hold the answer and returns a reference to that
     * array.
     */
    private static int[] add(int[] x, int[] y) {
        // If x is shorter, swap the two arrays
        if (x.length < y.length) {
            int[] tmp = x;
            x = y;
            y = tmp;
        }

        int xIndex = x.length;
        int yIndex = y.length;
        int result[] = new int[xIndex];
        long sum = 0;

        // Add common parts of both numbers
        while(yIndex > 0) {
            sum = (x[--xIndex] & LONG_MASK) +
                  (y[--yIndex] & LONG_MASK) + (sum >>> 32);
            result[xIndex] = (int)sum;
        }

        // Copy remainder of longer number while carry propagation is required
        boolean carry = (sum >>> 32 != 0);
        while (xIndex > 0 && carry)
            carry = ((result[--xIndex] = x[xIndex] + 1) == 0);

        // Copy remainder of longer number
        while (xIndex > 0)
            result[--xIndex] = x[xIndex];

        // Grow result if necessary
        if (carry) {
            int newLen = result.length + 1;
            int temp[] = new int[newLen];
            for (int i = 1; i<newLen; i++)
                temp[i] = result[i-1];
            temp[0] = 0x01;
            result = temp;
        }
        return result;
    }

    /**
     * Returns a BigInteger whose value is {@code (this - val)}.
     *
     * @param  val value to be subtracted from this BigInteger.
     * @return {@code this - val}
     */
    public BigInteger subtract(BigInteger val) {
        int[] resultMag;
        if (val.signum == 0)
            return this;
        if (signum == 0)
            return val.negate();
        if (val.signum != signum)
            return new BigInteger(add(mag, val.mag), signum);

        int cmp = intArrayCmp(mag, val.mag);
        if (cmp==0)
            return ZERO;
        resultMag = (cmp>0 ? subtract(mag, val.mag)
                           : subtract(val.mag, mag));
        resultMag = trustedStripLeadingZeroInts(resultMag);
        return new BigInteger(resultMag, cmp*signum);
    }

    /**
     * Subtracts the contents of the second int arrays (little) from the
     * first (big).  The first int array (big) must represent a larger number
     * than the second.  This method allocates the space necessary to hold the
     * answer.
     */
    private static int[] subtract(int[] big, int[] little) {
        int bigIndex = big.length;
        int result[] = new int[bigIndex];
        int littleIndex = little.length;
        long difference = 0;

        // Subtract common parts of both numbers
        while(littleIndex > 0) {
            difference = (big[--bigIndex] & LONG_MASK) -
                         (little[--littleIndex] & LONG_MASK) +
                         (difference >> 32);
            result[bigIndex] = (int)difference;
        }

        // Subtract remainder of longer number while borrow propagates
        boolean borrow = (difference >> 32 != 0);
        while (bigIndex > 0 && borrow)
            borrow = ((result[--bigIndex] = big[bigIndex] - 1) == -1);

        // Copy remainder of longer number
        while (bigIndex > 0)
            result[--bigIndex] = big[bigIndex];

        return result;
    }

    /**
     * Returns a BigInteger whose value is {@code (this * val)}.
     *
     * @param  val value to be multiplied by this BigInteger.
     * @return {@code this * val}
     */
    public BigInteger multiply(BigInteger val) {
        if (val.signum == 0 || signum == 0)
            return ZERO;

        int[] result = multiplyToLen(mag, mag.length,
                                     val.mag, val.mag.length, null);
        result = trustedStripLeadingZeroInts(result);
        return new BigInteger(result, signum*val.signum);
    }

    /**
     * Multiplies int arrays x and y to the specified lengths and places
     * the result into z.
     */
    private int[] multiplyToLen(int[] x, int xlen, int[] y, int ylen, int[] z) {
        int xstart = xlen - 1;
        int ystart = ylen - 1;

        if (z == null || z.length < (xlen+ ylen))
            z = new int[xlen+ylen];

        long carry = 0;
        for (int j=ystart, k=ystart+1+xstart; j>=0; j--, k--) {
            long product = (y[j] & LONG_MASK) *
                           (x[xstart] & LONG_MASK) + carry;
            z[k] = (int)product;
            carry = product >>> 32;
        }
        z[xstart] = (int)carry;

        for (int i = xstart-1; i >= 0; i--) {
            carry = 0;
            for (int j=ystart, k=ystart+1+i; j>=0; j--, k--) {
                long product = (y[j] & LONG_MASK) *
                               (x[i] & LONG_MASK) +
                               (z[k] & LONG_MASK) + carry;
                z[k] = (int)product;
                carry = product >>> 32;
            }
            z[i] = (int)carry;
        }
        return z;
    }

    public BigInteger divide(BigInteger val) {
        MutableBigInteger q = new MutableBigInteger(),
                          r = new MutableBigInteger(),
                          a = new MutableBigInteger(this.mag),
                          b = new MutableBigInteger(val.mag);

        a.divide(b, q, r);
        return new BigInteger(q, this.signum * val.signum);
    }

    public BigInteger remainder(BigInteger val) {
        MutableBigInteger q = new MutableBigInteger(),
                          r = new MutableBigInteger(),
                          a = new MutableBigInteger(this.mag),
                          b = new MutableBigInteger(val.mag);

        a.divide(b, q, r);
        return new BigInteger(r, this.signum);
    }

		public String toString(int radix) {
        if (signum == 0)
            return "0";
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
            radix = 10;

        // Compute upper bound on number of digit groups and allocate space
        int maxNumDigitGroups = (4*mag.length + 6)/7;
        String digitGroup[] = new String[maxNumDigitGroups];

        // Translate number to string, a digit group at a time
        BigInteger tmp = this.abs();
        int numGroups = 0;
        while (tmp.signum != 0) {
            BigInteger d = longRadix[radix];

            MutableBigInteger q = new MutableBigInteger(),
                              r = new MutableBigInteger(),
                              a = new MutableBigInteger(tmp.mag),
                              b = new MutableBigInteger(d.mag);
            a.divide(b, q, r);
            BigInteger q2 = new BigInteger(q, tmp.signum * d.signum);
            BigInteger r2 = new BigInteger(r, tmp.signum * d.signum);

            digitGroup[numGroups++] = Long.toString(r2.longValue(), radix);
            tmp = q2;
        }
			}

		public int compareTo(BigInteger val) {
        return (signum==val.signum
                ? signum*intArrayCmp(mag, val.mag)
                : (signum>val.signum ? 1 : -1));
    }

		  public boolean equals(Object x) {
	        // This test is just an optimization, which may or may not help
	        if (x == this)
	            return true;

	        if (!(x instanceof BigInteger))
	            return false;
	        BigInteger xInt = (BigInteger) x;

	        if (xInt.signum != signum || xInt.mag.length != mag.length)
	            return false;

	        for (int i=0; i<mag.length; i++)
	            if (xInt.mag[i] != mag[i])
	                return false;

	        return true;
	    }

			public static BigInteger valueOf(long val) {
        // If -MAX_CONSTANT < val < MAX_CONSTANT, return stashed constant
        if (val == 0)
            return ZERO;
        if (val > 0 && val <= MAX_CONSTANT)
            return posConst[(int) val];
        else if (val < 0 && val >= -MAX_CONSTANT)
            return negConst[(int) -val];

        return new BigInteger(val);
    }

    /**
     * Constructs a BigInteger with the specified value, which may not be zero.
     */
    private BigInteger(long val) {
        if (val < 0) {
            signum = -1;
            val = -val;
        } else {
            signum = 1;
        }

        int highWord = (int)(val >>> 32);
        if (highWord==0) {
            mag = new int[1];
            mag[0] = (int)val;
        } else {
            mag = new int[2];
            mag[0] = highWord;
            mag[1] = (int)val;
        }
    }

    /**
     * Returns a BigInteger with the given two's complement representation.
     * Assumes that the input array will not be modified (the returned
     * BigInteger will reference the input array if feasible).
     */
    private static BigInteger valueOf(int val[]) {
        return (val[0]>0 ? new BigInteger(val, 1) : new BigInteger(val));
    }
	}
