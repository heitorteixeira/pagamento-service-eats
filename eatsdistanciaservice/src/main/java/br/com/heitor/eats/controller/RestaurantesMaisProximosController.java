package br.com.heitor.eats.controller;

import br.com.heitor.eats.dto.RestauranteComDistanciaDto;
import br.com.heitor.eats.service.DistanciaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
class RestaurantesMaisProximosController {

	private DistanciaService distanciaService;

	@GetMapping("/restaurantes/mais-proximos/{cep}")
	public List<RestauranteComDistanciaDto> maisProximos(@PathVariable("cep") String cep) {
		return distanciaService.restaurantesMaisProximosAoCep(cep);
	}

	@GetMapping("/restaurantes/mais-proximos/{cep}/tipos-de-cozinha/{tipoDeCozinhaId}")
	public List<RestauranteComDistanciaDto> maisProximos(@PathVariable("cep") String cep,
			@PathVariable("tipoDeCozinhaId") Long tipoDeCozinhaId) {
		return distanciaService.restaurantesDoTipoDeCozinhaMaisProximosAoCep(tipoDeCozinhaId, cep);
	}

	@GetMapping("/restaurantes/{cep}/restaurante/{restauranteId}")
	public RestauranteComDistanciaDto comDistanciaPorId(@PathVariable("cep") String cep,
			@PathVariable("restauranteId") Long restauranteId) {
		return distanciaService.restauranteComDistanciaDoCep(restauranteId, cep);
	}

}
