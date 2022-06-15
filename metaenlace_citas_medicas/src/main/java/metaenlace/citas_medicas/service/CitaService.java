package metaenlace.citas_medicas.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import metaenlace.citas_medicas.entities.DTO.MedicoDTO;
import metaenlace.citas_medicas.entities.DTO.PacienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metaenlace.citas_medicas.entities.Cita;
import metaenlace.citas_medicas.entities.Medico;
import metaenlace.citas_medicas.entities.Paciente;
import metaenlace.citas_medicas.entities.DTO.CitaDTO;
import metaenlace.citas_medicas.repositories.CitaRepository;

@Service
public class CitaService implements IService<CitaDTO> {
	
	private final CitaRepository citaRepository;
	private final PacienteService pacienteService;
	private final MedicoService medicoService;
	private ModelMapper mapper = new ModelMapper();

    @Autowired
    public CitaService(CitaRepository citaRepository, PacienteService pacienteService, MedicoService medicoService) {
    	this.citaRepository = citaRepository;
		this.pacienteService = pacienteService;
		this.medicoService = medicoService;
	}
	
	@Override
	public List<CitaDTO> findAll() {
		return citaRepository.findAll().stream().map(u -> mapper.map(u, CitaDTO.class)).collect(Collectors.toList());
	}

	@Override
	public CitaDTO findById(Integer id) {
		return citaRepository.findById(id).map(c -> mapper.map(c, CitaDTO.class)).orElse(null);
	}

	@Override
	public CitaDTO save(CitaDTO nuevaCita) {
		if (Objects.nonNull(nuevaCita)) {
			if (Objects.nonNull(nuevaCita.getCitaMedicoUserID()) && Objects.nonNull(nuevaCita.getCitaPacienteUserID())) {
				PacienteDTO paciente = pacienteService.findById(nuevaCita.getCitaPacienteUserID());
				MedicoDTO medico = medicoService.findById(nuevaCita.getCitaMedicoUserID());
				if (Objects.nonNull(paciente) && Objects.nonNull(medico)) {
					Cita cita = new Cita(0, nuevaCita.getCitaFechaHora(), nuevaCita.getCitaMotivoCita(), null, null, null);
					cita.setPaciente(mapper.map(paciente, Paciente.class));
					cita.setMedico(mapper.map(medico, Medico.class));
					nuevaCita.setCitaCitaID(citaRepository.save(cita).getCitaID());
					paciente.getPacienteCitasCitaID().add(nuevaCita.getCitaCitaID());
					//pacienteService.save(paciente);
					medico.getMedicoCitasCitaID().add(nuevaCita.getCitaCitaID());
					//medicoService.save(medico);
				}
			}
			return nuevaCita;
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		if (citaRepository.findById(id).isPresent()) {
			citaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
