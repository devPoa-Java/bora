package com.pessoal.bora.api.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.pessoal.bora.api.domain.Driver;
import com.pessoal.bora.api.dto.DriverDTO;
import com.pessoal.bora.api.incoming.errorhandling.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Driver API", description = "Manipula dados de motoristas")
public interface DriverSwagger {
	
	    @Operation(description = "Lista de todos os motoristas")
	 	public ResponseEntity<PagedModel<DriverDTO>> findAll(Pageable pageable, PagedResourcesAssembler<Driver> assembler);
	
	    @Operation(description = "Localiza um motorista específico", responses = {
	    		@ApiResponse(responseCode = "200", description = "Caso o motorista tenha sido encontrado na base"),
	    		@ApiResponse(responseCode = "404", description = "Caso o motorista não tenha sido encontrado", 
	    		content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	    })
		public DriverDTO findById( @Parameter(description = "ID do motorista a ser localizado") Long id);
	
	
	    public void saveDriver(@Parameter(description = "Dados do motorista a ser criado")  DriverDTO driver);

	
	

}
