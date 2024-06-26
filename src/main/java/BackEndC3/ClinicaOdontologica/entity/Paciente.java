package BackEndC3.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "pacientes")

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Valor unico por entidad, auto-incremental, por ejemplo = 1")
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true, nullable = false)
    private String cedula;

    @Column
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Paciente(Long id, String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente() {
    }
    public Paciente( String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                ", email='" + email + '\'' +
                '}';
    }

}
