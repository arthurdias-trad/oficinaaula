package br.com.digitalhouse.oficina.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.digitalhouse.oficina.model.OrdemServico;
import br.com.digitalhouse.oficina.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	private OrdemServicoRepository ordemServicoRepository;
	
	public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
		this.ordemServicoRepository = ordemServicoRepository;
	}
	
	public OrdemServico create(OrdemServico ordemServico) {
		ordemServico.setId(null);
		return this.ordemServicoRepository.save(ordemServico);
	}
	
	public OrdemServico update(OrdemServico ordemServico) {
		OrdemServico antiga = this.findById(ordemServico.getId());
		
		antiga.setCliente(ordemServico.getCliente());
		antiga.setVeiculo(ordemServico.getVeiculo());
		
		return this.ordemServicoRepository.save(antiga);
	}
	
	public OrdemServico findById(Long id) {
		Optional
			.ofNullable(id)
			.orElseThrow( () -> new RuntimeException("O id não pode ser nulo"));
		
		return this.ordemServicoRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Não foi possivel encontrar um objeto com id: " + id));
	}
	
	public List<OrdemServico> findAll() {
		return this.ordemServicoRepository.findAll();
	}
	
	public void deleteById(Long id) {
		this.ordemServicoRepository.deleteById(id);
	}

}
