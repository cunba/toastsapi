package com.sanvalero.toastsapi.model.dto;

import java.util.UUID;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    @AssertFalse
    @AssertTrue
    @NotNull
    private boolean inMenu;
    @PositiveOrZero
    private float price;
    @NotNull
    @Min(value = 0)
    @Max(value = 5)
    private float punctuation;
    private UUID typeId;
    private UUID menuId;
    @NotNull
    @NotBlank
    @NotEmpty
    private UUID publicationId;
}
