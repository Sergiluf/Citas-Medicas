package metaenlace.citas_medicas.controller;

import java.util.List;
import java.util.Objects;

import metaenlace.citas_medicas.entities.DTO.CitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metaenlace.citas_medicas.entities.DTO.PacienteDTO;
import metaenlace.citas_medicas.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
    	this.pacienteService = pacienteService;
    }
 
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAll() {
		return new ResponseEntity<>(pacienteService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getById(@PathVariable Integer id) {
		PacienteDTO paciente = pacienteService.findById(id);
		if(Objects.nonNull(paciente)) {
			return new ResponseEntity<>(paciente, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity add(@RequestBody PacienteDTO nuevoPaciente) {
		PacienteDTO paciente = pacienteService.save(nuevoPaciente);
		if(Objects.nonNull(paciente)) {
			return new ResponseEntity<>(paciente, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		if (pacienteService.delete(id)) {
			return new ResponseEntity<>("Paciente eliminado.",HttpStatus.OK);
		}
		return new ResponseEntity<>("Paciente con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
	}

}
