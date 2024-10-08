package tech.renovus.solarec.api.rest.controller.administration;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

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

import tech.renovus.solarec.RestFactory;
import tech.renovus.solarec.UserData;
import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.business.GeneratorService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.comparator.GeneratorGenCodeAsNumberComparator;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.entity.Generator;
import tech.renovus.solarec.vo.rest.entity.Location;

@RestController
public class GeneratorsController extends BasicController {
	
	//--- Private constants --------------------
	private static final String TEXT_ERROR_AT = "Error at: ";
	
	//--- Resources -----------------------------
	@Resource GeneratorService service;
	@Resource RestFactory restFactory;
	
	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_GENERATORS)
	public List<Generator> list (
		@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
		@RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
		@RequestParam(name = "name", required = false) String name,
		HttpSession session
	) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			return this.restFactory.convertGenerators(this.service.findAll(offset, size, name, userData));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, TEXT_ERROR_AT + EndPointFactory.REST_ADMINISTRATION_GENERATORS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_GENERATORS + "/{id}")
	public Generator get(@PathVariable Integer id, HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			return this.restFactory.convert(this.service.findFullVo(id, userData));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, TEXT_ERROR_AT + EndPointFactory.REST_ADMINISTRATION_GENERATORS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_GENERATORS + "/current/location")
	public List<Generator> getForLocation(HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			Collection<GeneratorVo> generators = new TreeSet<>(GeneratorGenCodeAsNumberComparator.getInstance());
			CollectionUtil.addAll(generators, this.service.findAllForLocation(userData.getLocId(), userData));
			return this.restFactory.convertGenerators(generators);
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, TEXT_ERROR_AT + EndPointFactory.REST_ADMINISTRATION_GENERATORS, exc);
		}
	}
	
	@PostMapping(EndPointFactory.REST_ADMINISTRATION_GENERATORS)
	public Generator create(@RequestBody Generator Generator, HttpSession session) {
		GeneratorVo vo = this.restFactory.convert(Generator);
		this.service.create(vo, this.getLoggedUserData(session));
		return this.restFactory.convert(vo);
	}
	
	@PutMapping(EndPointFactory.REST_ADMINISTRATION_GENERATORS)
	public Generator update(@RequestBody Generator Generator, HttpSession session) {
		GeneratorVo vo = this.restFactory.convert(Generator);
		vo = this.service.update(vo, this.getLoggedUserData(session));
		return this.restFactory.convert(vo);
	}

	@GetMapping(EndPointFactory.REST_ADMINISTRATION_GENERATORS + "/neighbors")
	public List<Location> getEstimations(HttpSession session) {
		Collection<LocationVo> locations = this.service.getNeighbors(this.getLoggedUserData(session));
		return this.restFactory.convertLocations(locations);
	}

	@PostMapping(EndPointFactory.REST_ADMINISTRATION_GENERATORS + "/neighbors")
	public List<Location> setEstimations(@RequestBody List<Location> generators, HttpSession session) {
		Collection<LocationVo> vos = this.service.setNeighbors(generators, this.getLoggedUserData(session));
		return this.restFactory.convertLocations(vos);
	}

}
