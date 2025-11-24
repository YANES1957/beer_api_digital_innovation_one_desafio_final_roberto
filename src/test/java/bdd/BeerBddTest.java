package one.digitalinnovation.beerstock.bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BeerBddTest {

    private MockMvc mockMvc;

    @Mock
    private BeerService beerService;

    @InjectMocks
    private one.digitalinnovation.beerstock.controller.BeerController beerController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void bddListBeers() throws Exception {
        when(beerService.listAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/beers")
                        .header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(beerService, times(1)).listAll();
    }

    @Test
    void bddCreateBeer() throws Exception {
        BeerDTO beerDTO = new BeerDTO("Colorado appia", "Colorado", 20, 10, "LAGER");
        when(beerService.createBeer(any(BeerDTO.class))).thenReturn(beerDTO);

        mockMvc.perform(post("/api/v1/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Colorado appia"));
    }

    @Test
    void bddIncrementBeer() throws Exception {
        BeerDTO beerDTO = new BeerDTO("Colorado appia", "Colorado", 20, 12, "LAGER");
        when(beerService.incrementStock(1L, 2)).thenReturn(beerDTO);

        mockMvc.perform(patch("/api/v1/beers/1/increment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"quantity\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(12));
    }
}
