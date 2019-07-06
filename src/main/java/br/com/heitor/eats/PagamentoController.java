package br.com.heitor.eats;


import br.com.heitor.eats.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
public class PagamentoController {

	private PagamentoRepository pagamentoRepo;
	//private PedidoRepository pedidoRepo;
	//private SimpMessagingTemplate websocket;

	@GetMapping("/{id}")
	public PagamentoDto detalha(@PathVariable Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new PagamentoDto(pagamento);
	}

	@PostMapping
	public ResponseEntity<PagamentoDto> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		URI path = uriBuilder.path("/pagamentos/{id}").buildAndExpand(salvo.getId()).toUri();
		return ResponseEntity.created(path).body(new PagamentoDto(salvo));
	}

	@PutMapping("/{id}")
	public PagamentoDto confirma(@PathVariable Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		pagamentoRepo.save(pagamento);
		//Long pedidoId = pagamento.getPedido().getId();
		//Pedido pedido = pedidoRepo.porIdComItens(pedidoId);
		//pedido.setStatus(Pedido.Status.PAGO);
		//pedidoRepo.atualizaStatus(Pedido.Status.PAGO, pedido);
		//websocket.convertAndSend("/parceiros/restaurantes/" + pedido.getRestaurante().getId() + "/pedidos/pendentes",
		//		new PedidoDto(pedido));
		return new PagamentoDto(pagamento);
	}

	@DeleteMapping("/{id}")
	public PagamentoDto cancela(@PathVariable Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

}
