package com.bins.designpattern.strategy.chapterone;

import java.math.BigDecimal;

public class ShippingInfo
{
    public BigDecimal CalculateShippingAmount(State shipToState)
    {
        switch (shipToState)
        {
            case Alaska:
                return getAlaskaShippingAmount();
            case NewYork:
                return getNewYorkShippingAmount();
            case Florida:
                return getFloridaShippingAmount();
            default:
                return new BigDecimal(0);
        }
    }

    private BigDecimal getAlaskaShippingAmount()
    {
        return new BigDecimal(15);
    }

    private BigDecimal getNewYorkShippingAmount()
    {
        return new BigDecimal(10);
    }

    private BigDecimal getFloridaShippingAmount()
    {
        return new BigDecimal(3);
    }

    public enum State
    {
        Alaska,
        NewYork,
        Florida
    }
}

