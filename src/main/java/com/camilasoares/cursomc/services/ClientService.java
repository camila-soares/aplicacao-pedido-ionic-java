package com.camilasoares.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.dto.ClientDTO;
import com.camilasoares.cursomc.repositories.ClientRepository;
import com.camilasoares.cursomc.services.exception.DataIntegrityException;
import com.camilasoares.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	
	
	public Client find(Integer id) throws ObjectNotFoundException{
		Client obj = clientRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Client.class.getName());
		}
		return obj;
	}

	public  Client insert(Client obj) {
		obj.setId(null);
		return clientRepository.save(obj);
	}

	public Client update(Client obj) throws ObjectNotFoundException {
		Client newObj  = find(obj.getId());
		updateData(newObj, obj);
		return clientRepository.save(newObj);
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try{
		clientRepository.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir  Cliente o mesmo possui pedidos");
		}
		
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Page<Client> findPage(Integer page,Integer linesForpage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesForpage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
		
	}
}
