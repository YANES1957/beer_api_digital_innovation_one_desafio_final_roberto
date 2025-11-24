package one.digitalinnovation.beerstock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuantityDTO {

    @NotNull(message = "O id da cerveja não pode ser nulo")
    private Long id;

    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    private Integer quantity;
}
