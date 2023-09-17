package com.akinwalehabib.orderservice.order.event;

public record OrderAcceptedMessage(
  Long orderId
) {}
