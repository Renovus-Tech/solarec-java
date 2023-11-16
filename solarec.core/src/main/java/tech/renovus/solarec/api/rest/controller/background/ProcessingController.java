package tech.renovus.solarec.api.rest.controller.background;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.api.rest.controller.RestFactory;
import tech.renovus.solarec.business.ProcessingService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.rest.background.Processing;
import tech.renovus.solarec.vo.rest.entity.DataDefinitionTrigger;
import tech.renvous.solarec.util.JsonUtil;

@RestController
public class ProcessingController extends BasicController {

	//--- Resources -----------------------------
	@Resource ProcessingService service;

	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_BACKGROUND_PROCESSING)
	public List<Processing> list (
		HttpSession session
	) {
		UserData userData = this.getLoggedUserData(session);
		return RestFactory.convertProcessings(this.service.findAllClient(userData.getCliId(), userData));
	}
	
	@PostMapping(EndPointFactory.REST_BACKGROUND_PROCESSING)
	public Collection<Processing> process(
		@RequestParam(name = "file", required = false) MultipartFile filePart,
		@RequestParam(name = "request", required = false) String json,
		HttpSession session,
		HttpServletResponse response
	) throws CoreException {
		
		UserData userData = this.getLoggedUserData(session);
		
		try {
			Processing request = JsonUtil.toObject(json, Processing.class);
			
			request.setFilePart(filePart);
			
			Collection<DataProcessingVo> result = this.service.process(request, userData);
			
			response.setStatus(HttpServletResponse.SC_OK);
			
			return RestFactory.convertProcessings(result);
		} catch (JsonProcessingException e) {
			throw new CoreException(e);
		}
	}
	
	@PostMapping(EndPointFactory.REST_BACKGROUND_TRIGGER)
	public Collection<Processing> trigger(
		@RequestParam(name = "file", required = false) MultipartFile filePart,
		@RequestParam(name = "request", required = false) String json,
		HttpSession session,
		HttpServletResponse response
	) throws CoreException {
		
		UserData userData = this.getLoggedUserData(session);
		
		try {
			DataDefinitionTrigger request				= JsonUtil.toObject(json, DataDefinitionTrigger.class);
			
			request.setFilePart(filePart);
			
			Collection<DataProcessingVo> result = this.service.process(request, userData);
			
			response.setStatus(HttpServletResponse.SC_OK);
			
			return RestFactory.convertProcessings(result);
		} catch (JsonProcessingException e) {
			throw new CoreException(e);
		}
	}
}
