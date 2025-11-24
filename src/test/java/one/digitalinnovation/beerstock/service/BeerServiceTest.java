package one.digitalinnovation.beerstock;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.enums.BeerType;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import one.digitalinnovation.beerstock.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    @Mock
    private BeerMapper beerMapper;

    @InjectMocks
    private BeerService beerService;

    private BeerDTO beerDTO;
    private Beer beer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        beerDTO = new BeerDTO("Colorado Appia", "Colorado", 20, 10, BeerType.LAGER);
        beer = new Beer(1L, "Colorado Appia", "Colorado", 20, 10, BeerType.LAGER);
    }

    @Test
    void whenBeerInformedThenItShouldBeCreated() {
        when(beerMapper.toModel(beerDTO)).thenReturn(beer);
        when(beerRepository.save(beer)).thenReturn(beer);
        when(beerMapper.toDTO(beer)).thenReturn(beerDTO);

        BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

        assertEquals(beerDTO.getName(), createdBeerDTO.getName());
        verify(beerRepository, times(1)).save(beer);
    }

    @Test
    void whenIncrementCalledThenQuantityShouldIncrease() throws BeerNotFoundException {
        when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));
        when(beerRepository.save(beer)).thenReturn(beer);
        when(beerMapper.toDTO(beer)).thenReturn(beerDTO);

        BeerDTO incrementedBeer = beerService.increment(1L, 5);

        assertEquals(15, incrementedBeer.getQuantity());
        verify(beerRepository).save(beer);
    }

    @Test
    void whenBeerNotFoundThenExceptionShouldBeThrown() {
        when(beerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BeerNotFoundException.class, () -> beerService.increment(1L, 5));
    }
}
