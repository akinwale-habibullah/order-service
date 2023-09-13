package com.akinwalehabib.orderservice.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record OrderRequest(

  @NotBlank
  String isbn,

  @NotNull(message = "The book quantity must be defined.")
  @Min(value = 1, message = "You must order at least one item.")
  @Max(value = 5, message = "You cannot order more than 5 items.")
  Integer quantity
) {}
