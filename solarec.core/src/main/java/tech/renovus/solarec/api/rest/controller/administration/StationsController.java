package tech.renovus.solarec.api.rest.controller.administration;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.api.rest.controller.RestFactory;
import tech.renovus.solarec.business.StationService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.vo.rest.entity.Station;

@RestController
public class StationsController extends BasicController {
	
	//--- Resources -----------------------------
	@Resource StationService service;
	@Resource RestFactory restFactory;
	
	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_STATIONS)
	public List<Station> list (
		@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
		@RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
		@RequestParam(name = "name", required = false) String name,
		HttpSession session
	) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			return this.restFactory.convertStations(this.service.findAll(offset, size, name, userData));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_STATIONS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_STATIONS + "/{id}")
	public Station get(@PathVariable Integer id, HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			return this.restFactory.convert(this.service.findVo(id, userData));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_STATIONS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_STATIONS + "/current/location")
	public List<Station> getForLocation(HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			return this.restFactory.convertStations(this.service.findAllForLocation(userData.getLocId(), userData));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_GENERATORS, exc);
		}
	}
	
	@PostMapping(EndPointFactory.REST_ADMINISTRATION_STATIONS)
	public Station create(@RequestBody Station Station, HttpSession session) {
		StationVo vo = this.restFactory.convert(Station);
		this.service.create(vo, this.getLoggedUserData(session));
		return this.restFactory.convert(vo);
	}
	
	@PutMapping(EndPointFactory.REST_ADMINISTRATION_STATIONS)
	public Station update(@RequestBody Station Station, HttpSession session) {
		StationVo vo = this.restFactory.convert(Station);
		this.service.update(vo, this.getLoggedUserData(session));
		return this.restFactory.convert(vo);
	}
		
}
