package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.exception.BadRequestException;
import BackEndC3.ClinicaOdontologica.exception.ResourceNotFoundException;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller //<-- es controller pq vamos a usar una tecnologia de vista
@RestController
@RequestMapping("/pacientes")
@Tag(name = "Controller de Pacientes", description = "Permite registrar, eliminar, listar y actualizar")

public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}") //--> nos permite buscar un paciente por id
    @Parameter(description = "Nos permite obtener un paciente por id")
    @ApiResponse(responseCode = "200", description = "Paciente encontrado por id con éxito")
    public ResponseEntity<Optional<Paciente>> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.status(200).body(pacienteBuscado);
        }

        throw new ResourceNotFoundException("Paciente no encontrado");
    }

    @GetMapping
    @Parameter(description = "Nos permite listar todos los pacientes")
    @ApiResponse(responseCode = "200", description = "Lista de todos los pacientes obtenida con éxito")
    public ResponseEntity<List<Paciente>> buscarTodosLosPacientes(){
        return ResponseEntity.status(200).body(pacienteService.buscarTodosLosPacientes());
    }

    @GetMapping("/cedula/{cedula}")
    @Parameter(description = "Nos permite buscar un paciente por cedula")
    @ApiResponse(responseCode = "200", description = "Paciente encontrado por cedula con éxito")
    public ResponseEntity<Optional<Paciente>> buscarPorCedula(@PathVariable String cedula) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorCedula(cedula);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.status(200).body(Optional.of(pacienteBuscado.get()));
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    @Operation(summary = "Nos permite agregar un paciente", description = "Enviar paciente sin id")
    @ApiResponse(responseCode = "200", description = "Paciente creado con exito")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.status(200).body(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    @Parameter(description = "Nos permite actualizar un paciente por id")
    @ApiResponse(responseCode = "200", description = "Paciente actualizado con éxito")
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {

        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.status(200).body("paciente actualizado con exito");
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }

    }

    @DeleteMapping("/{id}")
    @Parameter(description = "Nos permite eliminar un paciente por id")
    @ApiResponse(responseCode = "200", description = "Paciente eliminado con éxito")
    public ResponseEntity<String> eliminarPaciente(@PathVariable("id") Long id) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(200).body("paciente eliminado con exito");
        }else{
            throw new BadRequestException("Paciente no encontrado");
        }
    }

    @GetMapping("/buscar/{email}")
    @Parameter(description = "Nos permite buscar un paciente por email")
    @ApiResponse(responseCode = "200", description = "Paciente encontrado por email con éxito")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.status(200).body(pacienteBuscado);
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

}
