package br.com.digitalhouse.oficina.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digitalhouse.oficina.model.OrdemServico;
import br.com.digitalhouse.oficina.service.OrdemServicoService;

@RestController
@RequestMapping("/ordensdeservico")
public class OrdemServicoResource {

	private final OrdemServicoService ordemServicoService;
	
	@Autowired
	public OrdemServicoResource (OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemServico>> findAll() {
		
		List<OrdemServico> ordensDeServico = this.ordemServicoService.findAll();
		
		return ResponseEntity.ok(ordensDeServico);
	}
	
	@PostMapping
	public ResponseEntity<Void> create( @RequestBody OrdemServico ordemServico) {
		
		ordemServico = this.ordemServicoService.create(ordemServico);
		
		URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(ordemServico.getId())
				 .toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdemServico> findById(@PathVariable Long id) {
		
		OrdemServico ordemServico = this.ordemServicoService.findById(id);
		
		return ResponseEntity.ok(ordemServico);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrdemServico> findById(@PathVariable Long id, @RequestBody OrdemServico ordemServico) {
		ordemServico.setId(id);
		
		this.ordemServicoService.update(ordemServico);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.ordemServicoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
