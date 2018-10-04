package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.Cidade;
import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.domain.Endereco;
import com.camilasoares.cursomc.domain.enums.ClientType;
import com.camilasoares.cursomc.domain.enums.Perfil;
import com.camilasoares.cursomc.dto.ClientDTO;
import com.camilasoares.cursomc.dto.ClientNewDTO;
import com.camilasoares.cursomc.repositories.AddressRepository;
import com.camilasoares.cursomc.repositories.ClientRepository;
import com.camilasoares.cursomc.security.UserSS;
import com.camilasoares.cursomc.services.exception.AuthorizationException;
import com.camilasoares.cursomc.services.exception.DataIntegrityException;
import com.camilasoares.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value ( "${img.prefix.client.profile}" )
	private String prefix;

	@Value ("${img.profile.size}")
	private Integer size;

	
	public Client find(Integer id) {

		UserSS user = UserService.authenticated ();
		if(user==null || !user.hasRole ( Perfil.ADMIN ) && !id.equals ( user.getId () )){
			throw new AuthorizationException ("Acesso Negado");
		}

		Optional<Client> obj = clientRepository.findById (id);
		return obj.orElseThrow ( () -> new ObjectNotFoundException (
				"Objeto não encontrado! id:" + id + ", Tipo:"+ Client.class.getName ()
		) );
	}

	@Transactional
	public  Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll (obj.getEnderecos());
		return obj;
	}

	public Client update(Client obj) {
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
		clientRepository.deleteById (id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir  Cliente o mesmo possui pedidos");
		}
		
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}


	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	public Client findByEMail(String email){
		UserSS user = UserService.authenticated ();
		if(user == null  && !email.equals ( user.getPassword () )){
			throw new AuthorizationException ( "Acesso negado" );
		}

		Client obj = clientRepository.findByEmail ( email );
		if(obj == null ){
			throw new ObjectNotFoundException (
					"Objeto não encontrado! Id:" + user.getId () + ", Tipo:" + Client.class.getName ()
			);
		}
		return obj;
	}

	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
		
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), ClientType.toEnum(objDTO.getTipo()), pe.encode ( objDTO.getSenha () ));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco (null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null){
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null){
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) throws  FilerException {
		UserSS user = UserService.authenticated ();
		if(user == null){
			throw new AuthorizationException ( "Acesso negado" );
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile ( multipartFile );
		jpgImage = imageService.cropSquare ( jpgImage );
		jpgImage = imageService.resize (jpgImage, size );
		String fileName = prefix + user.getId () + ".jpg";

		return s3Service.uploadFile ( imageService.getInputStream ( jpgImage, "jpg"), fileName, "image" );
		}

}
