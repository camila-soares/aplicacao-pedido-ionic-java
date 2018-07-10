package com.camilasoares.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.dto.ClientDTO;
import com.camilasoares.cursomc.repositories.ClientRepository;
import com.camilasoares.cursomc.resouces.exception.handler.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository clientRepository;
	@Override
	public void initialize(ClientUpdate ann) {
	}

	
	@Override
	public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));		
		
		List<FieldMessage> list = new ArrayList<>();
		
		Client aux = clientRepository.findByEmail(objDTO.getEmail());
		if(aux != null && !aux.getId().equals(uriId)){
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for(FieldMessage e : list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
		}
		return list.isEmpty();
	}
}
