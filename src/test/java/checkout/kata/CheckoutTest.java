package checkout.kata;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CheckoutTest {
	
	Map<String, Item> items = new HashMap<String, Item>();
	
	@Before
	public void doSetUp() {
		Item a = new Item("A", 50, new int[]{3, 130});
		Item b = new Item("B", 30, new int[]{2, 45});
		Item c = new Item("C", 20);
		Item d = new Item("D", 15);
		
		items.put("A", a);
		items.put("B", b);
		items.put("C", c);
		items.put("D", d);
	}

	@Test
	public void sameItemNoOfferTest() {
		String[] input = {"C", "C", "C", "C", "C", "C", "C"};
		Checkout.calculateTotals(input, items);
		assertEquals(Checkout.getTotalPrice(items), 140);
	}
	
	@Test
	public void sameItemWithOfferTest() {
		String[] input = {"A", "A", "A", "A", "A", "A", "A"};
		Checkout.calculateTotals(input, items);
		assertEquals(Checkout.getTotalPrice(items), 310);
	}
	
	@Test
	public void mixOfOfferAndNoOfferTest() {
		String[] input = {"B", "A", "A", "C", "A", "B", "C", "B", "A", "D", "D", "C", "C", "C"};
		Checkout.calculateTotals(input, items);
		assertEquals(Checkout.getTotalPrice(items), 385);
	}
	
	@Test
	public void tooFewItemsDoesNotTriggerOfferTest() {
		String[] input = {"A", "A"};
		Checkout.calculateTotals(input, items);
		assertEquals(Checkout.getTotalPrice(items), 100);
	}
}
