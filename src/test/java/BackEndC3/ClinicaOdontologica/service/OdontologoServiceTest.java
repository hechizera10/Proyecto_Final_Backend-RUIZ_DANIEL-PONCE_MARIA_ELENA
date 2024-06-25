package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo= new Odontologo(12345,"German", "Gonzalez");
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);
        assertEquals(3L,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId(){
        Long id= 1L;
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Long id= 1L;
        Odontologo odontologo= new Odontologo(id,123456,"Jose", "Gonzalez");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoPorId(id);
        assertEquals("Jose", odontologoBuscado.get().getNombre());
            }

   @Test
   @Order(4)
   public void ListarTodos(){
        List<Odontologo> listaOdontologos= odontologoService.buscarTodos();
        assertEquals(3,listaOdontologos.size());
   }
   @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(3L);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarOdontologoPorId(3L);
        assertFalse(odontologoEliminado.isPresent());
   }
}
