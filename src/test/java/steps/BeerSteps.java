package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeerSteps {

    private int stock;
    private int increment;

    @Given("the beer stock is {int}")
    public void given_the_beer_stock(int quantity) {
        this.stock = quantity;
    }

    @When("I increment the stock by {int}")
    public void when_i_increment_the_stock(int quantity) {
        this.increment = quantity;
        this.stock += increment;
    }

    @Then("the stock should be {int}")
    public void then_the_stock_should_be(int expected) {
        assertEquals(expected, this.stock);
    }
}
