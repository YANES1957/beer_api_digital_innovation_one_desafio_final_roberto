package one.digitalinnovation.beerstock.config;

import lombok.AllArgsConstructor;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.enums.BeerType;
import one.digitalinnovation.beerstock.service.BeerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BeerService beerService;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
    }

    private void loadBeerData() {
        Beer beer1 = Beer.builder().name("Heineken").brand("Heineken").max(50).quantity(10).type(BeerType.LAGER).build();
        Beer beer2 = Beer.builder().name("Budweiser").brand("Budweiser").max(60).quantity(20).type(BeerType.PILSNER).build();
        Beer beer3 = Beer.builder().name("Guinness").brand("Guinness").max(30).quantity(5).type(BeerType.STOUT).build();
        Beer beer4 = Beer.builder().name("Paulaner").brand("Paulaner").max(40).quantity(15).type(BeerType.WHEAT).build();
        Beer beer5 = Beer.builder().name("Sierra Nevada").brand("Sierra Nevada").max(35).quantity(8).type(BeerType.ALE).build();

        beerService.createBeer(beer1);
        beerService.createBeer(beer2);
        beerService.createBeer(beer3);
        beerService.createBeer(beer4);
        beerService.createBeer(beer5);
    }
}

