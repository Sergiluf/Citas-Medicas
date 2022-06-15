package metaenlace.citas_medicas.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import metaenlace.citas_medicas.entities.DTO.CitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metaenlace.citas_medicas.controller.DiagnosticoController;
import metaenlace.citas_medicas.entities.DTO.DiagnosticoDTO;
import metaenlace.citas_medicas.service.DiagnosticoService;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {
	
	private final DiagnosticoService diagnosticoService;

    @Autowired
    public DiagnosticoController(DiagnosticoService diagnosticoService) {
    	this.diagnosticoService = diagnosticoService;
    }
    
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAll() {
		return new ResponseEntity<>(diagnosticoService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getById(@PathVariable Integer id) {
		DiagnosticoDTO diagnostico = diagnosticoService.findById(id);
		if(Objects.nonNull(diagnostico)) {
			return new ResponseEntity<>(diagnostico, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity add(@RequestBody DiagnosticoDTO nuevoDiagnostico) {
		DiagnosticoDTO diagnostico = diagnosticoService.save(nuevoDiagnostico);
		if(Objects.nonNull(diagnostico)) {
			return new ResponseEntity<>(diagnostico, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		if (diagnosticoService.delete(id)) {
			return new ResponseEntity<>("Diagnóstico eliminado.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Diagnóstico con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
	}

}
