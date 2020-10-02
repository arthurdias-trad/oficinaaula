package br.com.digitalhouse.oficina.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.oficina.model.Cliente;
import br.com.digitalhouse.oficina.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente create(Cliente cliente) {
		cliente.setId(null);
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente clienteNovo) {
		Cliente antigo = this.findById(clienteNovo.getId());
		
		antigo.setNome(clienteNovo.getNome());
		antigo.setVeiculos(clienteNovo.getVeiculos());
		
		return this.clienteRepository.save(antigo);
	}
	
	public Cliente findById(Long id) {
		Optional
			.ofNullable(id)
			.orElseThrow( () -> new RuntimeException("O id não pode ser nulo"));
		
		return this.clienteRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Não foi possivel encontrar um objeto com id: " + id));
	}
	
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
	
	public void deleteById(Long id) {
		this.clienteRepository.deleteById(id);
	}
	
	
}
