package metaenlace.citas_medicas.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import metaenlace.citas_medicas.entities.DTO.PacienteDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metaenlace.citas_medicas.controller.MedicoController;
import metaenlace.citas_medicas.entities.DTO.MedicoDTO;
import metaenlace.citas_medicas.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
    	this.medicoService = medicoService;
    }
    
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAll() {
		return new ResponseEntity<>(medicoService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getById(@PathVariable Integer id) {
		MedicoDTO medico = medicoService.findById(id);
		if(Objects.nonNull(medico)) {
			return new ResponseEntity<>(medico, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity add(@RequestBody MedicoDTO nuevoMedico) {
		MedicoDTO medico = medicoService.save(nuevoMedico);
		if(Objects.nonNull(medico)) {
			return new ResponseEntity<>(medico, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		if (medicoService.delete(id)) {
			return new ResponseEntity<>("Médico eliminado.",HttpStatus.OK);
		}
		return new ResponseEntity<>("Médico con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
	}

}
