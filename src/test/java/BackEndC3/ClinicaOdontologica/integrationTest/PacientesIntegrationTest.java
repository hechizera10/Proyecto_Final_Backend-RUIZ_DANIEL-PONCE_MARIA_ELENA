package BackEndC3.ClinicaOdontologica.integrationTest;


import BackEndC3.ClinicaOdontologica.entity.Domicilio;
import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.entity.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacientesIntegrationTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void agregarPacienteTest() throws Exception {
        Paciente paciente= new Paciente("Amanda","Mayor","5143", LocalDate.of(2024,6,20),new Domicilio("calle falsa",
                123,"La Rioja","Argentina"),"amandita@digitalhouse.com");


        String pacienteJson = objectMapper.writeValueAsString(paciente);

        MvcResult respuesta = mockMvc.perform(
                        MockMvcRequestBuilders.post("/pacientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(pacienteJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


    @Test
    public void listarTodosLosPacientesTest() throws Exception{
        MvcResult respuesta=
                mockMvc.perform(MockMvcRequestBuilders.get("/pacientes").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

    }

}
