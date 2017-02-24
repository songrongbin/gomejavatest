package com.bins.designpattern.strategy.chaptertwo;

import java.math.BigDecimal;

public class AlaskShippingCalculation implements IShippingCalculation {

    @Override
    public State getState() {
        return null;
    }
    @Override
    public BigDecimal calculate()
    {
        return new BigDecimal(15);
    }
}