package com.pessoal.bora.api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoal.bora.api.domain.Driver;
import com.pessoal.bora.api.dto.DriverDTO;
import com.pessoal.bora.api.services.DriverAssembler;
import com.pessoal.bora.api.services.DriverService;

@RestController
@RequestMapping(value = "/drivers", produces = "application/json")
public class DriverController implements DriverSwagger {
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DriverAssembler driverAssembler;

	@GetMapping
	public CollectionModel<DriverDTO> findAll(@RequestParam(defaultValue = "0") int page) {
		
		Page<Driver> driverPage = driverService.findAll(PageRequest.of(page, PAGE_SIZE));
		CollectionModel<Driver> collectionModel = CollectionModel.of(driverPage.getContent());
		
		Link lastPageLink = linkTo(methodOn(DriverController.class).findAll(driverPage.getTotalPages() - 1)).withRel("lastPage");
		return driverAssembler.toCollectionModel(collectionModel).add(lastPageLink);
	}
 
	@GetMapping(value = "/{id}")
	public DriverDTO findById(@PathVariable Long id) {
		return driverService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public void delet(@PathVariable Long id) {
		driverService.delete(id);
	}

	@PostMapping
	public void saveDriver(@RequestBody DriverDTO driver) {
		driverService.saveDriver(driver);
	}

	@PatchMapping(value = "/{id}")
	public void updateDriver(@PathVariable Long id, @RequestBody DriverDTO driverDto) {
		DriverDTO driver = driverService.findById(id);
		driver.setName(driverDto.getName());
		driver.setBirthDate(driverDto.getBirthDate());
		driverService.updateDriver(id, driver);

	}

}
