import org.junit.jupiter.api.*;
import java.time.LocalTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    @BeforeEach
    private void setup2(){
        LocalTime spiedOpeningTime = LocalTime.parse("12:00:00");
        LocalTime spiedClosingTime = LocalTime.parse("14:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Lucknow", spiedOpeningTime, spiedClosingTime);

    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {

        Restaurant restaurant = service.findRestaurantByName("Amelie's cafe");
        assertNotNull(restaurant);
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class,()-> {

            service.findRestaurantByName("The Cherry Free Cafe");
        });
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<< ADMIN: ADDING & REMOVING RESTAURANTS >>>>>>>>>>>>>>>>>>>>>>>>>>
}