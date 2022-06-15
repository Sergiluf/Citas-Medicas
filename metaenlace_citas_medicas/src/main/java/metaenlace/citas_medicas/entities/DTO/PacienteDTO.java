package metaenlace.citas_medicas.entities.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PacienteDTO{

	private Integer pacienteUserID;
	private String pacienteUsuario;
	private String pacienteNombre;
	private String pacienteApellidos;
	private String pacienteClave;
	private String pacienteNSS;
	private String pacienteNumTarjeta;
	private String pacienteTelefono;
	private String pacienteDireccion;
	private List<Integer> pacienteMedicosUserID;
	private List<Integer> pacienteCitasCitaID;
	
}
