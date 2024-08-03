package tech.renovus.solarec.api.rest.controller.administration;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.renovus.solarec.RestFactory;
import tech.renovus.solarec.UserData;
import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.business.DataDefinitionService;
import tech.renovus.solarec.vo.rest.entity.DataDefinition;
import tech.renovus.solarec.vo.rest.entity.DataDefinitionTrigger;

@RestController
public class DataDefinitionsController extends BasicController {
	
	//--- Resources -----------------------------
	@Resource DataDefinitionService service;
	@Resource RestFactory restFactory;
	
	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DATA_DEFINITIONS)
	public List<DataDefinition> list ( HttpSession session ) {
		UserData userData = this.getLoggedUserData(session);
		return this.restFactory.convertDataDefinitions(this.service.findAll(userData));
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DATA_DEFINITIONS_TRIGGERS)
	public List<DataDefinitionTrigger> listTriggers ( HttpSession session ) {
		UserData userData = this.getLoggedUserData(session);
		return this.restFactory.convertTriggers(this.service.findAllTriggers(userData), userData);
	}
}
