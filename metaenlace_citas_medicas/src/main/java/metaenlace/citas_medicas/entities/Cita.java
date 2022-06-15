package metaenlace.citas_medicas.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CITA")
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CITA_ID")
	private Integer citaID;
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	@Column(name = "MOTIVO_CITA")
	private String motivoCita;
	@ManyToOne
	@JoinColumn(name = "PACIENTE")
	private Paciente paciente;
	@ManyToOne
	@JoinColumn(name = "MEDICO")
	private Medico medico;
	@OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
	private Diagnostico diagnostico;

}
