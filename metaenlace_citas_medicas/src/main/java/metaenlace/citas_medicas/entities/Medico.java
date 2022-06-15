package metaenlace.citas_medicas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEDICO")
@PrimaryKeyJoinColumn(referencedColumnName = "USER_ID")
public class Medico extends Usuario {

	@Column(name = "NUM_COLEGIADO")
	private String numColegiado;
	@ManyToMany(mappedBy = "medicos", cascade = CascadeType.ALL)
	private List<Paciente> pacientes;
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<Cita> citas;

	public Medico(Integer userID, String usuario, String nombre, String apellidos, String clave, String numColegiado,
			List<Paciente> pacientes, List<Cita> citas) {
		super(userID, usuario, nombre, apellidos, clave);
		this.numColegiado = numColegiado;
		this.pacientes = pacientes;
		this.citas = citas;
	}

}
