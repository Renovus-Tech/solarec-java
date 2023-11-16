package tech.renovus.solarec.api.rest.controller.administration;

import java.util.Collection;
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
import tech.renovus.solarec.business.LocationService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.entity.Location;

@RestController
public class LocationsController extends BasicController {
	
	//--- Resources -----------------------------
	@Resource LocationService service;
	
	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_LOCATIONS)
	public List<Location> list (
		@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
		@RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
		@RequestParam(name = "name", required = false) String name,
		HttpSession session
	) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			return RestFactory.convertLocations(this.service.findAll(offset, size, name, userData));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_LOCATIONS, exc);
		}
	}
	
	@GetMapping({EndPointFactory.REST_ADMINISTRATION_LOCATIONS + "/{id}", EndPointFactory.REST_ADMINISTRATION_LOCATIONS + "/current"})
	public Location get(@PathVariable (required = false) Integer id, HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			if (id == null) id = userData.getLocId();
			
			LocationVo locVo = this.service.findFullVo(id, userData);
			userData.setLocationVo(locVo);
			return RestFactory.convert(locVo);
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_LOCATIONS, exc);
		}
	}
	
	@PostMapping(EndPointFactory.REST_ADMINISTRATION_LOCATIONS)
	public Location create(@RequestBody Location location, HttpSession session) {
		LocationVo vo = RestFactory.convert(location);
		this.service.create(vo, this.getLoggedUserData(session));
		return RestFactory.convert(vo);
	}
	
	@PutMapping(EndPointFactory.REST_ADMINISTRATION_LOCATIONS)
	public Location update(@RequestBody Location location, HttpSession session) {
		LocationVo vo = RestFactory.convert(location);
		this.service.update(vo, this.getLoggedUserData(session));
		return RestFactory.convert(vo);
	}

	@GetMapping(EndPointFactory.REST_ADMINISTRATION_LOCATIONS + "/estimations")
	public List<Location> getEstimations(HttpSession session) {
		Collection<LocationVo> locations = this.service.getEstimations(this.getLoggedUserData(session));
		return RestFactory.convertLocations(locations);
	}

	@PostMapping(EndPointFactory.REST_ADMINISTRATION_LOCATIONS + "/estimations")
	public List<Location> setEstimations(@RequestBody List<Location> locations, HttpSession session) {
		Collection<LocationVo> vos = this.service.setEstimations(locations, this.getLoggedUserData(session));
		return RestFactory.convertLocations(vos);
	}
	
	
}
