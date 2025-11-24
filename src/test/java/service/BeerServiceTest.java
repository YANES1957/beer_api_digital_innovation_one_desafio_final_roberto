package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.enums.BeerType;
import one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerService beerService;

    private BeerDTO beerDTO;
    private Beer beer;

    @BeforeEach
    void setUp() {
        beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        beer = Beer.builder()
                .id(beerDTO.getId())
                .name(beerDTO.getName())
                .brand(beerDTO.getBrand())
                .max(beerDTO.getMax())
                .quantity(beerDTO.getQuantity())
                .type(beerDTO.getType())
                .build();
    }

    @Test
    void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(any(Beer.class))).thenReturn(beer);

        BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

        assertThat(createdBeerDTO.getName(), is(beerDTO.getName()));
        assertThat(createdBeerDTO.getBrand(), is(beerDTO.getBrand()));
        assertThat(createdBeerDTO.getQuantity(), is(beerDTO.getQuantity()));

        verify(beerRepository, times(1)).findByName(beerDTO.getName());
        verify(beerRepository, times(1)).save(any(Beer.class));
    }

    @Test
    void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown() {
        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.of(beer));

        assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(beerDTO));

        verify(beerRepository, times(1)).findByName(beerDTO.getName());
        verify(beerRepository, never()).save(any(Beer.class));
    }

    @Test
    void whenValidBeerNameIsGivenThenReturnABeer() throws BeerNotFoundException {
        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.of(beer));

        BeerDTO foundBeerDTO = beerService.findByName(beerDTO.getName());

        assertThat(foundBeerDTO.getName(), is(beerDTO.getName()));
        assertThat(foundBeerDTO.getBrand(), is(beerDTO.getBrand()));
    }

    @Test
    void whenNotRegisteredBeerNameIsGivenThenThrowException() {
        when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());

        assertThrows(BeerNotFoundException.class, () -> beerService.findByName(beerDTO.getName()));
    }

    @Test
    void whenListBeersIsCalledThenReturnListOfBeers() {
        when(beerRepository.findAll()).thenReturn(Collections.singletonList(beer));

        List<BeerDTO> beerDTOList = beerService.listAll();

        assertThat(beerDTOList, is(not(empty())));
        assertThat(beerDTOList.get(0).getName(), is(beerDTO.getName()));
    }

    @Test
    void whenListBeersIsCalledThenReturnEmptyList() {
        when(beerRepository.findAll()).thenReturn(Collections.emptyList());

        List<BeerDTO> beerDTOList = beerService.listAll();

        assertThat(beerDTOList, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenBeerShouldBeDeleted() throws BeerNotFoundException {
        when(beerRepository.findById(beerDTO.getId())).thenReturn(Optional.of(beer));

        beerService.deleteById(beerDTO.getId());

        verify(beerRepository, times(1)).findById(beerDTO.getId());
        verify(beerRepository, times(1)).deleteById(beerDTO.getId());
    }

    @Test
    void whenExclusionIsCalledWithInvalidIdThenThrowException() {
        when(beerRepository.findById(beerDTO.getId())).thenReturn(Optional.empty());

        assertThrows(BeerNotFoundException.class, () -> beerService.deleteById(beerDTO.getId()));
    }
}

