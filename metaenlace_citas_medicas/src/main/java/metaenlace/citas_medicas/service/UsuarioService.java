package metaenlace.citas_medicas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metaenlace.citas_medicas.entities.DTO.UsuarioDTO;
import metaenlace.citas_medicas.repositories.UsuarioRepository;

@Service
public class UsuarioService implements IService<UsuarioDTO> {

	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
    	this.usuarioRepository = usuarioRepository;
    }

	//@Override
	public List<UsuarioDTO> findAll() {
		return usuarioRepository.findAll().stream().map(u -> modelMapper.map(u, UsuarioDTO.class)).collect(Collectors.toList());
	}

	//@Override
	public UsuarioDTO findById(Integer id) {
		return usuarioRepository.findById(id).map(u -> modelMapper.map(u, UsuarioDTO.class)).orElse(null);
	}

	//@Override
	public UsuarioDTO save(UsuarioDTO nuevoUsuario) {
		return null;
	}

	//@Override
	public boolean delete(Integer id) {
		if (usuarioRepository.findById(id).isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
