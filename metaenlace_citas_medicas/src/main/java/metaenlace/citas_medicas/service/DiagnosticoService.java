package metaenlace.citas_medicas.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import metaenlace.citas_medicas.entities.DTO.CitaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metaenlace.citas_medicas.entities.Cita;
import metaenlace.citas_medicas.entities.Diagnostico;
import metaenlace.citas_medicas.entities.DTO.DiagnosticoDTO;
import metaenlace.citas_medicas.repositories.DiagnosticoRepository;

@Service
public class DiagnosticoService implements IService<DiagnosticoDTO> {
	
	private final DiagnosticoRepository diagnosticoRepository;
	private final CitaService citaService;
	private ModelMapper mapper = new ModelMapper();

    @Autowired
    public DiagnosticoService(DiagnosticoRepository diagnosticoRepository, CitaService citaService) {
    	this.diagnosticoRepository = diagnosticoRepository;
		this.citaService = citaService;
	}

	//@Override
	public List<DiagnosticoDTO> findAll() {
		return diagnosticoRepository.findAll().stream().map(d -> mapper.map(d, DiagnosticoDTO.class)).collect(Collectors.toList());
	}

	//@Override
	public DiagnosticoDTO findById(Integer id) {
		return diagnosticoRepository.findById(id).map(d -> mapper.map(d, DiagnosticoDTO.class)).orElse(null);
	}

	//@Override
	public DiagnosticoDTO save(DiagnosticoDTO nuevoDiagnostico) {
		if (Objects.nonNull(nuevoDiagnostico)) {
			Diagnostico diagnostico = new Diagnostico(0, nuevoDiagnostico.getDiagnosticoValoracionEspecialista(),
					nuevoDiagnostico.getDiagnosticoEnfermedad(), null);
			if (Objects.nonNull(nuevoDiagnostico.getDiagnosticoCitaCitaID())) {
				CitaDTO cita = citaService.findById(nuevoDiagnostico.getDiagnosticoCitaCitaID());
				if (Objects.nonNull(cita)) {
					cita.setDiagnosticoDiagnosticoID(nuevoDiagnostico.getDiagnosticoDiagnosticoID());
					//citaService.save(cita);
					diagnostico.setCita(mapper.map(cita, Cita.class));
					nuevoDiagnostico.setDiagnosticoDiagnosticoID(diagnosticoRepository.save(diagnostico).getDiagnosticoID());
				}
			}
			return nuevoDiagnostico;
		}
		return null;
	}

	//@Override
	public boolean delete(Integer id) {
		if (diagnosticoRepository.findById(id).isPresent()) {
			diagnosticoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
