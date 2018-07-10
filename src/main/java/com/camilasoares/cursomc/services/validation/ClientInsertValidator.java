package com.camilasoares.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.camilasoares.cursomc.domain.enums.ClientType;
import com.camilasoares.cursomc.dto.ClientNewDTO;
import com.camilasoares.cursomc.resouces.exception.handler.FieldMessage;
import com.camilasoares.cursomc.services.validation.ultils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>{

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
		
		
		for(FieldMessage e : list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
		}
		return list.isEmpty();
	}

}
