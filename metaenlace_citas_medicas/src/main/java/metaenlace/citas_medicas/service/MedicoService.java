package metaenlace.citas_medicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import metaenlace.citas_medicas.entities.DTO.PacienteDTO;
import metaenlace.citas_medicas.entities.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metaenlace.citas_medicas.entities.Medico;
import metaenlace.citas_medicas.entities.DTO.MedicoDTO;
import metaenlace.citas_medicas.repositories.MedicoRepository;

@Service
public class MedicoService implements IService<MedicoDTO> {

	private final MedicoRepository medicoRepository;
	private ModelMapper mapper = new ModelMapper();

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
    	this.medicoRepository = medicoRepository;
	}
    
	@Override
	public List<MedicoDTO> findAll() {
		return medicoRepository.findAll().stream().map(m -> { MedicoDTO dto = mapper.map(m, MedicoDTO.class);
			dto.setMedicoPacientesUserID(m.getPacientes().stream().map(p -> p.getUserID()).collect(Collectors.toList()));
			dto.setMedicoCitasCitaID(m.getCitas().stream().map(c -> c.getCitaID()).collect(Collectors.toList()));
			return dto;}).collect(Collectors.toList());
	}

	@Override
	public MedicoDTO findById(Integer id) {
		return medicoRepository.findById(id).map(m -> {	MedicoDTO dto = mapper.map(m, MedicoDTO.class);
			dto.setMedicoPacientesUserID(m.getPacientes().stream().map(p -> p.getUserID()).collect(Collectors.toList()));
			dto.setMedicoCitasCitaID(m.getCitas().stream().map(c -> c.getCitaID()).collect(Collectors.toList()));
			return dto;}).orElse(null);
	}

	@Override
	public MedicoDTO save(MedicoDTO nuevoMedico) {
		if (Objects.nonNull(nuevoMedico)) {
			Medico medico = new Medico(0, nuevoMedico.getMedicoUsuario(),
					nuevoMedico.getMedicoNombre(), nuevoMedico.getMedicoApellidos(),
					nuevoMedico.getMedicoClave(), nuevoMedico.getMedicoNumColegiado(),
					new ArrayList<>(), new ArrayList<>());
			nuevoMedico.setMedicoUserID(medicoRepository.save(medico).getUserID());
			return nuevoMedico;
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		if (Objects.nonNull(medicoRepository.findById(id))) {
			medicoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
