package com.example.youngforyou.commom.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathUtils {

    /**
     *      * @Title: objectConvertBigDecimal
     *      * @Description: Object类型的数字转BigDecimal
     *      * @author: ZXM
     *      * @param value
     *      * @return
     *     
     */
    public static BigDecimal objectConvertBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }
}

