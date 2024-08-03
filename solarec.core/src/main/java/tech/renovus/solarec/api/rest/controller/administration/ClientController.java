package tech.renovus.solarec.api.rest.controller.administration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.renovus.solarec.RestFactory;
import tech.renovus.solarec.UserData;
import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.business.ClientService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.rest.entity.Client;

@CrossOrigin
@RestController
public class ClientController extends BasicController {

	//--- Resources -----------------------------
	@Resource ClientService service;
	@Resource RestFactory restFactory;
	
	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_CLIENTS + "/current")
	public Client current(HttpSession session) throws CoreException {
		UserData userData = this.getLoggedUserData(session);
		ClientVo vo = this.service.getCurrent(userData);
		return this.restFactory.convert(vo, userData);
	}

	@PostMapping(EndPointFactory.REST_ADMINISTRATION_CLIENTS + "/current")
	public Client setToCurrent(@RequestBody Client client, HttpSession session) throws CoreException {
		UserData userData = this.getLoggedUserData(session);
		this.service.saveToCurrent(client, userData);
		return this.current(session);
	}

}
