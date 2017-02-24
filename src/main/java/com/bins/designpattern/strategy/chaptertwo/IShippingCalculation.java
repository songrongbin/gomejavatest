package com.bins.designpattern.strategy.chaptertwo;

import java.math.BigDecimal;

public interface IShippingCalculation
{
    State getState();
    BigDecimal calculate();
}