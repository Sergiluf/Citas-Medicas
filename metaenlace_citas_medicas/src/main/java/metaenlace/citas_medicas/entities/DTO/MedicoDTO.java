package metaenlace.citas_medicas.entities.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MedicoDTO{
	
	private Integer medicoUserID;
	private String medicoUsuario;
	private String medicoNombre;
	private String medicoApellidos;
	private String medicoClave;
	private String medicoNumColegiado;
	private List<Integer> medicoPacientesUserID;
	private List<Integer> medicoCitasCitaID;

}
