package checkout.kata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;

public class Checkout 
{
	public static void populateItemsFromPricePlan(BufferedReader pricePlanFile, Map<String, Item> items) throws IOException, DataFormatException {
		boolean hasNext = true;
		while(hasNext) {
			String currentLine = pricePlanFile.readLine();
			if(currentLine == null) {
				hasNext = false;
				break;
			}
			List<String> itemList = Arrays.asList(currentLine.split("\\s*,\\s*"));
			switch (itemList.size()) {
			case 2:
				items.put(itemList.get(0), new Item(itemList.get(0), Integer.valueOf(itemList.get(1))));
				break;
			case 4:
				items.put(itemList.get(0), new Item(itemList.get(0), Integer.valueOf(itemList.get(1)),
						new int[]{Integer.valueOf(itemList.get(2)), Integer.valueOf(itemList.get(3))}));
				break;
			default :
				throw new DataFormatException();
			}
		}
		pricePlanFile.close();
	}

	public static int getTotalPrice(Map<String, Item> items) {
		int totalPrice = 0;
		Set<String> keys = items.keySet();
		for(String key : keys) {
			totalPrice += items.get(key).getTotalPrice();
		}

		System.out.println(totalPrice);
		return totalPrice;
	}

	public static void calculateTotals(String[] letters, Map<String, Item> items) {
		for (int i = 0; i < letters.length; i++) {
			Item item = items.get(letters[i]);
			item.increaseTotal();
		}
	}

	public static void main( String[] args ) throws IOException, DataFormatException
	{
		Map<String, Item> items = new HashMap<String, Item>();
		BufferedReader pricePlanFile = new BufferedReader(new FileReader("D:/Documents/Java/ITV/kata/priceplan.txt"));
		populateItemsFromPricePlan(pricePlanFile, items);		
		calculateTotals(args, items);
		getTotalPrice(items);
	}
}
