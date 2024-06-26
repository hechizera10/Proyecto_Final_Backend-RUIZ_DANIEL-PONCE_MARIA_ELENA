package BackEndC3.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Valor unico por entidad, auto-incremental, por ejemplo = 1")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Matricula debe llevar un valor numerico", required = false)
    private Integer matricula;

    @Column
    private String nombre;

    @Column
    private String apellido;


    public Odontologo(Long id, Integer matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Odontologo( Integer matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Odontologo() {
    }


}
