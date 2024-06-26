package BackEndC3.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Valor unico por entidad, auto-incremental, por ejemplo = 1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @JsonIgnoreProperties("odontologo")
    @Schema(description = "Cedula del paciente", required = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    @JsonIgnoreProperties("pacientes")
    @Schema(description = "Matricula del odontologo", required = false)
    private Odontologo odontologo;

    @Column
    private LocalDate fecha;

    public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }
    public Turno( Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno() {
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}
