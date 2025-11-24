package one.digitalinnovation.beerstock.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;

    public Beer createBeer(Beer beer) {
        if (beer.getName() == null || beer.getBrand() == null || beer.getType() == null) {
            throw new IllegalArgumentException("Name, Brand e Type são obrigatórios.");
        }
        return beerRepository.save(beer);
    }

    public List<Beer> listAll() {
        return beerRepository.findAll();
    }

    public Beer getById(Long id) {
        return beerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Beer not found with id: " + id)
        );
    }

    public void delete(Long id) {
        beerRepository.deleteById(id);
    }
}
