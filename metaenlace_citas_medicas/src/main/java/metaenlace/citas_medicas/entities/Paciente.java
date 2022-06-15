package metaenlace.citas_medicas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PACIENTE")
@PrimaryKeyJoinColumn(referencedColumnName = "USER_ID")
public class Paciente extends Usuario {

	@Column(name = "NSS")
	private String NSS;
	@Column(name = "NUM_TARJETA")
	private String numTarjeta;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "DIRECCION")
	private String direccion;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PACIENTE_MEDICO", joinColumns = { @JoinColumn(name = "ID_PACIENTE") }, inverseJoinColumns = {
	@JoinColumn(name = "ID_MEDICO") })
	private List<Medico> medicos;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Cita> citas;

	public Paciente(Integer userID, String usuario, String nombre, String apellidos, String clave, String nSS,
			String numTarjeta, String telefono, String direccion, List<Medico> medicos, List<Cita> citas) {
		super(userID, usuario, nombre, apellidos, clave);
		NSS = nSS;
		this.numTarjeta = numTarjeta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.medicos = medicos;
		this.citas = citas;
	}

}
