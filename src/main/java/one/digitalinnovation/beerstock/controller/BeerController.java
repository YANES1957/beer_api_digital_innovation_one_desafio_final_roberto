package one.digitalinnovation.beerstock.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.service.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @PostMapping
    public ResponseEntity<BeerDTO> createBeer(@RequestBody BeerDTO beerDTO) {
        Beer beer = BeerMapper.INSTANCE.toModel(beerDTO);
        Beer savedBeer = beerService.createBeer(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(BeerMapper.INSTANCE.toDTO(savedBeer));
    }

    @GetMapping
    public ResponseEntity<List<BeerDTO>> listAll() {
        List<BeerDTO> beers = beerService.listAll()
                .stream()
                .map(BeerMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(beers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDTO> getById(@PathVariable Long id) {
        Beer beer = beerService.getById(id);
        return ResponseEntity.ok(BeerMapper.INSTANCE.toDTO(beer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        beerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
