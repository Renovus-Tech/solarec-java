package tech.renovus.solarec.api.rest.controller.administration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.api.rest.controller.RestFactory;
import tech.renovus.solarec.business.DocumentService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.DocumentVo;
import tech.renovus.solarec.vo.rest.entity.DocType;
import tech.renovus.solarec.vo.rest.entity.Document;
import tech.renvous.solarec.util.BooleanUtils;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.FileUtil;
import tech.renvous.solarec.util.StringUtil;

@RestController
public class DocumentsController extends BasicController {
	
	//--- Resources -----------------------------
	@Resource DocumentService service;
	
	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS_CATEGORIES)
	public List<DocType> listCategories(HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		return RestFactory.convertDocTypes(this.service.getAllDocTypes(userData));
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS)
	public List<Document> list (
		@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
		@RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
		@RequestParam(name = "name", required = false) String name,
		@RequestParam(name = "term", required = false) String term,
		@RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
		@RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to,
		@RequestParam(name = "period", required = false) String period,
		@RequestParam(name = "isOpen", required = false) String isOpen,
		HttpSession session
	) {
		UserData userData = this.getLoggedUserData(session);
		
		if (size.intValue() == -1) size = null;
		
		try {
			Boolean openValue = StringUtil.isEmpty(isOpen) || "null".equals(isOpen) ? null : Boolean.valueOf(BooleanUtils.isTrue(isOpen));
			return RestFactory.convertDocuments(CollectionUtil.subCollection(this.service.findAllLocation(userData.getLocId(), name, term, from, to, period, openValue, userData), offset, size));
			//return RestFactory.convertDocuments(this.service.findAll(offset, size, name, term, from, to, period, userData));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_DOCUMENTS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS + "/generator/{id}")
	public List<Document> listForGenerator(
			@PathVariable Integer id, 
			@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
			@RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "term", required = false) String term,
			@RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
			@RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to,
			@RequestParam(name = "period", required = false) String period,
			@RequestParam(name = "isOpen", required = false) String isOpen,
			HttpSession session ) {
		UserData userData = this.getLoggedUserData(session);
		
		if (size.intValue() == -1) size = null;
		
		try {
			Boolean openValue = StringUtil.isEmpty(isOpen) || "null".equals(isOpen) ? null : Boolean.valueOf(BooleanUtils.isTrue(isOpen));
			return RestFactory.convertDocuments(CollectionUtil.subCollection(this.service.findAllGenerator(id, name, term, from, to, period, openValue, userData), offset, size));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_DOCUMENTS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS + "/location/{id}")
	public List<Document> listForLocation(
			@PathVariable Integer id, 
			@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
			@RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "term", required = false) String term,
			@RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
			@RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to,
			@RequestParam(name = "period", required = false) String period,
			@RequestParam(name = "isOpen", required = false) String isOpen,
			HttpSession session ) {
		UserData userData = this.getLoggedUserData(session);
		
		if (size.intValue() == -1) size = null;
		
		try {
			Boolean openValue = StringUtil.isEmpty(isOpen) || "null".equals(isOpen) ? null : Boolean.valueOf(BooleanUtils.isTrue(isOpen));
			return RestFactory.convertDocuments(CollectionUtil.subCollection(this.service.findAllLocation(id, name, term, from, to, period, openValue, userData), offset, size));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_DOCUMENTS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS + "/station/{id}")
	public List<Document> listForStation(
			@PathVariable Integer id, 
			@RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
			@RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "term", required = false) String term,
			@RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
			@RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to,
			@RequestParam(name = "period", required = false) String period,
			@RequestParam(name = "isOpen", required = false) String isOpen,
			HttpSession session ) {
		UserData userData = this.getLoggedUserData(session);
		
		if (size.intValue() == -1) size = null;
		
		try {
			Boolean openValue = StringUtil.isEmpty(isOpen) || "null".equals(isOpen) ? null : Boolean.valueOf(BooleanUtils.isTrue(isOpen));
			return RestFactory.convertDocuments(CollectionUtil.subCollection(this.service.findAllStation(id, name, term, from, to, period, openValue, userData), offset, size));
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_DOCUMENTS, exc);
		}
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS + "/{id}")
	public Document get(@PathVariable Integer id, HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		
		try {
			DocumentVo vo = this.service.findVo(id, userData);
			Document result = RestFactory.convert(vo);
			if (result != null && vo != null) result.setMetadata(vo.getDocFileContent());
			return result;
		} catch (CoreException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error at: " + EndPointFactory.REST_ADMINISTRATION_DOCUMENTS, exc);
		}
	}
	
	@PostMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS)
	public Collection<Document> create(
			@RequestParam(name = "file", required = true) MultipartFile filePart,
			@RequestParam(name = "request", required = true) String json,
			HttpSession session
	) throws CoreException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Document document = objectMapper.readValue(json, Document.class);
	
			DocumentVo vo = RestFactory.convert(document);
			vo.setFilePart(filePart);
			Collection<DocumentVo> result = this.service.create(vo, this.getLoggedUserData(session));
			return RestFactory.convertDocuments(result);
		} catch (JsonProcessingException e) {
			throw new CoreException(e);
		}
	}
	
	@PutMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS)
	public Document update(@RequestBody Document document, HttpSession session) {
		DocumentVo vo = RestFactory.convert(document);
		this.service.update(vo, this.getLoggedUserData(session));
		return RestFactory.convert(vo);
	}
	
	@DeleteMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS + "/{id}")
	public void delete(@PathVariable Integer id, HttpSession session) {
		this.service.delete(id, this.getLoggedUserData(session));
	}
	
	@GetMapping(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS + "/download/{id}")
	public ResponseEntity<InputStreamResource> download(@PathVariable Integer id, HttpSession session) throws CoreException {
		UserData userData = this.getLoggedUserData(session);
		
		DocumentVo docVo	= this.service.findVo(id, userData);
		File filePath		= this.service.getFilePath(docVo);
		
		if (filePath == null) return null;
		
		try {
			InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath));
			
			String fileExtension = FileUtil.getExtension(docVo.getDocFileName());
			String fileName = docVo.getDocName();
			if (! fileName.endsWith(fileExtension)) fileName += fileExtension;
			
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Content-Disposition", "attachment; filename=\"" + fileName  + "\"");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");
			
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(filePath.length())
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(resource);
		} catch (IOException e) {
			throw new CoreException(e);
		}
	}
}
