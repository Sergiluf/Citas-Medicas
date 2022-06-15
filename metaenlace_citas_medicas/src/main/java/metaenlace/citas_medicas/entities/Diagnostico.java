package metaenlace.citas_medicas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIAGNOSTICO")
public class Diagnostico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DIAGNOSTICO_ID")
	private Integer diagnosticoID;
	@Column(name = "VALORACION_ESPECIALISTA")
	private String valoracionEspecialista;
	@Column(name = "ENFERMEDAD")
	private String enfermedad;
	@OneToOne
	@JoinColumn(name = "CITA")
	private Cita cita;

}
