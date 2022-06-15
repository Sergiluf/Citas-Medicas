package metaenlace.citas_medicas.entities.DTO;

import lombok.Data;

@Data
public class DiagnosticoDTO {
	
	private Integer diagnosticoDiagnosticoID;
	private String diagnosticoEnfermedad;
	private String diagnosticoValoracionEspecialista;
	private Integer diagnosticoCitaCitaID;

}
