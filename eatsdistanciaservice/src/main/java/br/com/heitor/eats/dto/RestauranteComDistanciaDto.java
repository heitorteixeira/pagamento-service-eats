package br.com.heitor.eats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteComDistanciaDto {

	private Long restauranteId;

	private BigDecimal distancia;

}
