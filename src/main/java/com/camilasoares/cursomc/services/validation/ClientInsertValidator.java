package com.camilasoares.cursomc.services.validation;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.domain.enums.ClientType;
import com.camilasoares.cursomc.dto.ClientNewDTO;
import com.camilasoares.cursomc.repositories.ClientRepository;
import com.camilasoares.cursomc.resources.exception.FieldMessage;
import com.camilasoares.cursomc.services.validation.ultils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>{

	@Autowired
	private ClientRepository clientRepository;
	@Override
	public void initialize(ClientInsert ann) {
	}

	
	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipo().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())){
			list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));
		}
		
		if(objDTO.getTipo().equals(ClientType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())){
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
		}
		

		Client aux = clientRepository.findByEmail(objDTO.getEmail());
		if(aux != null){
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for(FieldMessage e : list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
		}
		return list.isEmpty();
	}

}
