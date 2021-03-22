import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    private void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        LocalTime mockedTime = LocalTime.parse("13:40:00");
        Restaurant mockedRestaurant = Mockito.spy(restaurant);
        Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(mockedTime);

        assertTrue(mockedRestaurant.isRestaurantOpen());
    }


    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        LocalTime mockedTime = LocalTime.parse("23:40:00");
        Restaurant mockedRestaurant = Mockito.spy(restaurant);
        Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(mockedTime);

        assertFalse(mockedRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void getOrderValue_should_return_total_amount_of_all_items_selected() {
        List<String> itemsSelected = new ArrayList<String>();
        itemsSelected.add("Sweet corn soup");
        itemsSelected.add("Vegetable lasagne");
        int expectedAmount = 388;
        assertEquals(restaurant.getOrderValue(itemsSelected),expectedAmount);
    }
    //>>>>>>>>>>>>>>>>>>>>>>ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}