package com.akinwalehabib.orderservice.order.event;

public record OrderDispatchedMessage(
  Long orderId
) {}
