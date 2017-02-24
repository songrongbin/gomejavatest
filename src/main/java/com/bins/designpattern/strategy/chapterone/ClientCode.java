package com.bins.designpattern.strategy.chapterone;

import java.math.BigDecimal;

public class ClientCode
{
    public BigDecimal CalculateShipping()
    {
        ShippingInfo shippingInfo = new ShippingInfo();
        return shippingInfo.CalculateShippingAmount(ShippingInfo.State.Alaska);
    }
}



