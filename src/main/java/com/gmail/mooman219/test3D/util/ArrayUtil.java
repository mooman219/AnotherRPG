package com.gmail.mooman219.test3D.util;

import java.util.Arrays;

public class ArrayUtil {
    public static float[] combine(float[] a, float[] b) {
        float[] ret = Arrays.copyOf(a, a.length+b.length);
        System.arraycopy(b, 0, ret, a.length, b.length);
        return ret;
    }
}
