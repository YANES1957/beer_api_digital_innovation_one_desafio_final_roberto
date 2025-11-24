package one.digitalinnovation.beerstock;

import com.fasterxml.jackson.databind.ObjectMapper;
import one.digitalinnovation.beerstock.controller.BeerController;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.enums.BeerType;
import one.digitalinnovation.beerstock.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService beerService;

    private BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = new BeerDTO("Colorado Appia", "Colorado", 20, 10, BeerType.LAGER);
    }

    @Test
    void whenPOSTBeerThenReturnCreated() throws Exception {
        Mockito.when(beerService.createBeer(any(BeerDTO.class))).thenReturn(beerDTO);

        mockMvc.perform(post("/api/v1/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(beerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(beerDTO.getName()));
    }

    @Test
    void whenGETBeersThenReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/beers"))
                .andExpect(status().isOk());
    }
}
