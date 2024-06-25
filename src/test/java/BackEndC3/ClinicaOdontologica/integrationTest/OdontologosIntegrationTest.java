package BackEndC3.ClinicaOdontologica.integrationTest;


import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
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

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologosIntegrationTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void agregarOdontologoTest() throws Exception {
        Odontologo odontologo = new Odontologo(678, "Leonardo", "Caceres");

        String odontologoJson = objectMapper.writeValueAsString(odontologo);

        MvcResult respuesta = mockMvc.perform(
                        MockMvcRequestBuilders.post("/odontologos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(odontologoJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


    @Test
    public void listarTodosLosOdontologosTest() throws Exception{
        MvcResult respuesta=
                mockMvc.perform(MockMvcRequestBuilders.get("/odontologos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

    }

}
