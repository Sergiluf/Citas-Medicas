package metaenlace.citas_medicas.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metaenlace.citas_medicas.entities.DTO.CitaDTO;
import metaenlace.citas_medicas.service.CitaService;

@RestController
@RequestMapping("/citas")
public class CitaController {

	private final CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
    	this.citaService = citaService;
    }

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAll() {
		return new ResponseEntity<>(citaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getById(@PathVariable Integer id) {
		CitaDTO cita = citaService.findById(id);
		if(Objects.nonNull(cita)) {
			return new ResponseEntity<>(cita, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity add(@RequestBody CitaDTO nuevaCita) {
		CitaDTO cita = citaService.save(nuevaCita);
		if(Objects.nonNull(cita)) {
			return new ResponseEntity<>(cita, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		if (citaService.delete(id)) {
			return new ResponseEntity<>("Cita eliminada.",HttpStatus.OK);
		}
		return new ResponseEntity<>("Cita con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
	}

}