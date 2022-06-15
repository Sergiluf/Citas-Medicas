package metaenlace.citas_medicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import metaenlace.citas_medicas.entities.DTO.MedicoDTO;
import metaenlace.citas_medicas.entities.Medico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metaenlace.citas_medicas.entities.Paciente;
import metaenlace.citas_medicas.entities.DTO.PacienteDTO;
import metaenlace.citas_medicas.repositories.PacienteRepository;

@Service
public class PacienteService implements IService<PacienteDTO> {
	
	private final PacienteRepository pacienteRepository;
	private final MedicoService medicoService;

	private ModelMapper mapper = new ModelMapper();

	
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, MedicoService medicoService) {
    	this.pacienteRepository = pacienteRepository;
		this.medicoService = medicoService;
	}
	
	@Override
	public List<PacienteDTO> findAll() {
		return pacienteRepository.findAll().stream().map(p -> {PacienteDTO dto = mapper.map(p, PacienteDTO.class);
			dto.setPacienteMedicosUserID(p.getMedicos().stream().map(m -> m.getUserID()).collect(Collectors.toList()));
			dto.setPacienteCitasCitaID(p.getCitas().stream().map(c -> c.getCitaID()).collect(Collectors.toList()));
			return dto;}).collect(Collectors.toList());
	}

	@Override
	public PacienteDTO findById(Integer id) {
		return pacienteRepository.findById(id).map(p -> {PacienteDTO dto = mapper.map(p, PacienteDTO.class);
			dto.setPacienteMedicosUserID(p.getMedicos().stream().map(m -> m.getUserID()).collect(Collectors.toList()));
			dto.setPacienteCitasCitaID(p.getCitas().stream().map(c -> c.getCitaID()).collect(Collectors.toList()));
			return dto;}).orElse(null);
	}

	@Override
	public PacienteDTO save(PacienteDTO nuevoPaciente) {
		if (Objects.nonNull(nuevoPaciente)) {
			List<Medico> medicos = new ArrayList<>();
			if (Objects.nonNull(nuevoPaciente.getPacienteMedicosUserID())) {
				for (int medicoID : nuevoPaciente.getPacienteMedicosUserID()) {
					MedicoDTO medico = medicoService.findById(medicoID);
					if (Objects.nonNull(medico)) {
						medico.getMedicoPacientesUserID().add(nuevoPaciente.getPacienteUserID());
						medicos.add(mapper.map(medico, Medico.class));
					}
				}
			}
			Paciente paciente = new Paciente(0, nuevoPaciente.getPacienteUsuario(),
					nuevoPaciente.getPacienteNombre(), nuevoPaciente.getPacienteApellidos(),
					nuevoPaciente.getPacienteClave(), nuevoPaciente.getPacienteNSS(), nuevoPaciente.getPacienteNumTarjeta(),
					nuevoPaciente.getPacienteTelefono(), nuevoPaciente.getPacienteDireccion(), medicos, new ArrayList<>());
			nuevoPaciente.setPacienteUserID(pacienteRepository.save(paciente).getUserID());
			return nuevoPaciente;
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		if (Objects.nonNull(pacienteRepository.findById(id))) {
			pacienteRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
