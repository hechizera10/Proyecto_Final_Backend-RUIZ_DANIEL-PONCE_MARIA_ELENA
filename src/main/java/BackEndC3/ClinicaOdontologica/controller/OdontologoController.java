package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;


import BackEndC3.ClinicaOdontologica.exception.BadRequestException;
import BackEndC3.ClinicaOdontologica.exception.ResourceNotFoundException;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;

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
@RequestMapping("/odontologos")
@Tag(name = "Controller de Odontologos", description = "Permite registrar, eliminar, listar y actualizar")

public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.

    @GetMapping("/{id}")
    @Parameter(description = "Nos permite obtener un odontologo por id")
    @ApiResponse(responseCode = "200", description = "Odontólogo encontrado por id con éxito")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = odontologoService.buscarOdontologoPorId(id);
        if (odontologoOptional.isPresent()) {
            Odontologo odontologo = odontologoOptional.get();
            return ResponseEntity.status(200).body(odontologo);
        }

        throw new ResourceNotFoundException("Odontólogo no encontrado");
    }


    @PostMapping
    @Operation(summary = "Nos permite agregar un odontologo", description = "Enviar odontologo sin id")
    @ApiResponse(responseCode = "200", description = "Odontologo creado con exito")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.status(200).body(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    @Parameter(description = "Nos permite actualizar un odontologo por id")
    @ApiResponse(responseCode = "200", description = "Odontólogo actualizado con éxito")
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {

        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.status(200).body("Odontologo actualizado con exito");
        }else{
            throw new ResourceNotFoundException("Odontólogo no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @Parameter(description = "Nos permite eliminar un odontologo por id")
    @ApiResponse(responseCode = "200", description = "Odontólogo eliminado con éxito")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.status(200).body("Odontologo eliminado con exito");
        }else{
            throw new BadRequestException("Odontologo no encontrado");
        }
    }

    @GetMapping
    @Parameter(description = "Nos permite listar todos los odontologos")
    @ApiResponse(responseCode = "200", description = "Lista de todos los odontologos obtenida con éxito")
    public ResponseEntity<List<Odontologo>> buscarTodosLosOdontologos(){
        return ResponseEntity.status(200).body(odontologoService.buscarTodos());
    }

    @GetMapping("/matricula/{matricula}")
    @Parameter(description = "Nos permite buscar un odontologo por matricula")
    @ApiResponse(responseCode = "200", description = "Odontólogo encontrado por matricula con éxito")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologoPorMatricula(@PathVariable Integer matricula) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoService.buscarPorMatricula(matricula);
        if (odontologo.isPresent()) {
            return ResponseEntity.status(200).body(Optional.of(odontologo.get()));
        } else {
            throw new ResourceNotFoundException("Odontólogo no encontrado");

        }
    }

}
