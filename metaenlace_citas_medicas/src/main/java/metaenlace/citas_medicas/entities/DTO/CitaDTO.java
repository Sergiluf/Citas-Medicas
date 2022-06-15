package metaenlace.citas_medicas.entities.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CitaDTO {

	private Integer citaCitaID;
	private Date citaFechaHora;
	private String citaMotivoCita;
	private Integer citaPacienteUserID;
	private Integer citaMedicoUserID;
	private Integer diagnosticoDiagnosticoID;

}
