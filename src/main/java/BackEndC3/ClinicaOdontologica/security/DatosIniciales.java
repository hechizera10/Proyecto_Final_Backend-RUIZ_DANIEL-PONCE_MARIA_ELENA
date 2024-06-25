package BackEndC3.ClinicaOdontologica.security;

import BackEndC3.ClinicaOdontologica.entity.*;
import BackEndC3.ClinicaOdontologica.repository.OdontologoRepository;
import BackEndC3.ClinicaOdontologica.repository.PacienteRepository;
import BackEndC3.ClinicaOdontologica.repository.UsuarioRepository;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import BackEndC3.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*Crear Usuarios*/
        String passSinCifrar= "user";
        String passSinCifrar2= "admin";
        String passCifrado2=  passwordEncoder.encode(passSinCifrar2);
        String passCifrado=  passwordEncoder.encode(passSinCifrar);
        Usuario usuario= new Usuario("jorgito","jpereryradh","user@user.com",passCifrado, UsuarioRole.ROLE_USER);
        Usuario usuario2= new Usuario("admin","admin","admin@admin.com",passCifrado2, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);

         // Crear Pacientes
        Domicilio domicilio = new Domicilio("Calle Falsa", 123, "Springfield", "Provincia Ejemplo");

        Paciente paciente = new Paciente( "Jorge", "Pereyra", "123", LocalDate.parse("2024-06-10"), domicilio, "user@user.com");

        pacienteRepository.save(paciente);

        // Crear Odontologos
        Odontologo odontologo1 = new Odontologo( 1234, "Ana", "García");
        Odontologo odontologo2 = new Odontologo( 5678, "Luis", "Martínez");

        odontologoRepository.save(odontologo1);
        odontologoRepository.save(odontologo2);


        // Crear Turnos
        Turno turno1 = new Turno(paciente, odontologo1, LocalDate.parse("2021-06-10"));

        turnoService.guardarTurno(turno1);
    }

}
