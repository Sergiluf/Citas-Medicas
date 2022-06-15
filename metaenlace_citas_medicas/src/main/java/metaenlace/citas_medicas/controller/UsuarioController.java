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

import metaenlace.citas_medicas.controller.UsuarioController;
import metaenlace.citas_medicas.entities.Usuario;
import metaenlace.citas_medicas.entities.DTO.UsuarioDTO;
import metaenlace.citas_medicas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
    	this.usuarioService = usuarioService;
    }

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAll() {
		return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getById(@PathVariable Integer id) {
		UsuarioDTO usuario = usuarioService.findById(id);
		if(Objects.nonNull(usuario)) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		if (usuarioService.delete(id)) {
			return new ResponseEntity<>("Usuario eliminado.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Usuario con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
	}

}
