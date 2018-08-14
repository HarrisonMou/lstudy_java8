package com.harrison.study.lambda.math.impl;

import com.harrison.study.lambda.math.MathOperation;

public class MathOperationImpl implements MathOperation{
    @Override
    public int add(int x, int y) {
        return x + y;
    }
}
