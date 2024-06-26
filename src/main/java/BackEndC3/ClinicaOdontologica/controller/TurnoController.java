package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.entity.Turno;
import BackEndC3.ClinicaOdontologica.exception.BadRequestException;
import BackEndC3.ClinicaOdontologica.exception.ResourceNotFoundException;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import BackEndC3.ClinicaOdontologica.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@Tag(name = "Controller de Turnos", description = "Permite registrar, eliminar, listar y actualizar")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    @Operation(summary = "Nos permite agregar un turno", description = "Enviar turno sin id")
    @ApiResponse(responseCode = "200", description = "Turno creado con exito")
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if(pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()){
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.status(200).body(turnoService.guardarTurno(turno));
        } else if (pacienteBuscado.isEmpty()){
            throw new BadRequestException("Paciente no existe");
        } else {
            throw new BadRequestException("Odontologo no existe");
        }

    }

    @GetMapping("/{id}")
    @Parameter(description = "Nos permite obtener un turno por id")
    @ApiResponse(responseCode = "200", description = "Turno encontrado por id con éxito")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.status(200).body(turnoBuscado);
        }else{
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }

    @GetMapping
    @Parameter(description = "Nos permite listar todos los turnos")
    @ApiResponse(responseCode = "200", description = "Lista de todos los turnos obtenida con éxito")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.status(200).body(turnoService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    @Parameter(description = "Nos permite eliminar un turno por id")
    @ApiResponse(responseCode = "200", description = "Turno eliminado con éxito")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws BadRequestException{
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.status(200).body("Turno eliminado");
        }else{
            throw new BadRequestException("Turno no encontrado");
        }
    }

    @PutMapping
    @Parameter(description = "Nos permite actualizar un turno por id")
    @ApiResponse(responseCode = "200", description = "Turno actualizado con éxito")
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(turno.getId());
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.status(200).body("Turno actualizado con exito");
        }else{
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }

}
