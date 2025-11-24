package builder;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.enums.BeerType;

public class BeerDTOBuilder {

    private static final String DEFAULT_NAME = "Barney";
    private static final String DEFAULT_BRAND = "XPTO";
    private static final int DEFAULT_MAX = 50;
    private static final int DEFAULT_QUANTITY = 10;
    private static final BeerType DEFAULT_TYPE = BeerType.LAGER;

    private String name;
    private String brand;
    private int max;
    private int quantity;
    private BeerType type;
}

