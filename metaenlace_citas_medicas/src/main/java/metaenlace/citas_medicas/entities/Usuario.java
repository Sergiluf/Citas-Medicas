package metaenlace.citas_medicas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	protected Integer userID;
	@Column(name = "USUARIO")
	protected String usuario;
	@Column(name = "NOMBRE")
	protected String nombre;
	@Column(name = "APELLIDOS")
	protected String apellidos;
	@Column(name = "CLAVE")
	protected String clave;
	
}
