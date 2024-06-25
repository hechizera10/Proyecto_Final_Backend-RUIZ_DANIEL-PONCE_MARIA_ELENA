package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.entity.Domicilio;
import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.entity.Turno;
import BackEndC3.ClinicaOdontologica.repository.OdontologoRepository;
import BackEndC3.ClinicaOdontologica.repository.PacienteRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Test
    @Order(1)
    public void guardarTurno(){
       Paciente paciente1 = new Paciente(1L,"Analia","Fraire","11111", LocalDate.of(2024,6,20),new Domicilio("calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com");
       Odontologo odontologo1 = new Odontologo(1L,12345,"Juana", "Gonzalez");

        Turno turno= new Turno(paciente1,odontologo1, LocalDate.of(2024,6,20));
        Turno turnoGuardado= turnoService.guardarTurno(turno);
        assertEquals(2L,turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId(){
        Long id= 2L;
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno() {
        Long id = 1L;

        // Crear y guardar el paciente inicial
        Paciente paciente1 = new Paciente("Franco", "Fraire", "11111", LocalDate.of(2024, 6, 20),
                new Domicilio("calle falsa", 123, "La Rioja", "Argentina"), "jorge.pereyra@digitalhouse.com");
        paciente1 = pacienteRepository.save(paciente1);

        // Crear y guardar el odontólogo inicial
        Odontologo odontologo1 = new Odontologo(12345, "Juana", "Gonzalez");
        odontologo1 = odontologoRepository.save(odontologo1);

        // Crear y guardar el turno inicial
        Turno turno = new Turno(paciente1, odontologo1, LocalDate.of(2024, 6, 20));
        turno = turnoService.guardarTurno(turno);

        // Actualizar el paciente en el turno
        paciente1.setNombre("NOMBRECAMBIADO");
        pacienteRepository.save(paciente1);

        // Crear y actualizar el turno con el paciente actualizado
        Turno turnoActualizado = new Turno(turno.getId(), paciente1, odontologo1, LocalDate.of(2024, 6, 20));
        turnoService.actualizarTurno(turnoActualizado);

        // Verificar que el turno fue actualizado
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
        assertTrue(turnoBuscado.isPresent());
        assertEquals("NOMBRECAMBIADO", turnoBuscado.get().getPaciente().getNombre());
    }


   @Test
   @Order(4)
   public void ListarTodos(){
        List<Turno> listaTurnos= turnoService.buscarTodos();
        assertEquals(3,listaTurnos.size());
   }
   @Test
    @Order(5)
    public void eliminarTurno(){
        turnoService.eliminarTurno(3L);
        Optional<Turno> turnoEliminado= turnoService.buscarPorId(3L);
        assertFalse(turnoEliminado.isPresent());
   }
}
