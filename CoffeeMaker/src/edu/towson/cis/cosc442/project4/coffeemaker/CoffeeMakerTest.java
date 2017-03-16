package edu.towson.cis.cosc442.project4.coffeemaker;

import junit.framework.TestCase;

/**
 *
 */
public class CoffeeMakerTest extends TestCase {
	private CoffeeMaker cm;
	//private Inventory i;
	private Recipe r1;
	private Recipe r2;
	public void setUp() {
		cm = new CoffeeMaker();
		//i = cm.checkInventory();

		r1 = new Recipe();
		r1.setName("Black Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
		
		//Will define a recipe that requires no ingredients
		r1 = new Recipe();
		r1.setName("Water");
		r1.setPrice(-1);
		r1.setAmtCoffee(-1);
		r1.setAmtMilk(-1);
		r1.setAmtSugar(-1);
		r1.setAmtChocolate(-1);
	}

	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}

	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}
	
	public void testAddInventory1() {
		assertTrue(cm.addInventory(50, 30, 20, 40));
		
	}
	
	public void testCheckInventory1() {
		cm.addInventory(50, 30, 20, 40);
		Inventory tempInv = cm.checkInventory();
		assertEquals(tempInv.getCoffee(),50);
		assertEquals(tempInv.getMilk(),30);
		assertEquals(tempInv.getSugar(),20);
		assertEquals(tempInv.getChocolate(),40);
		
		//Testing the false cases of the inventory
		CoffeeMaker cm2 = new CoffeeMaker();
		Recipe frappe = new Recipe();
		frappe.setName("Frappe");
		frappe.setPrice(50);
		frappe.setAmtCoffee(50);
		frappe.setAmtMilk(50);
		frappe.setAmtSugar(50);
		frappe.setAmtChocolate(50);
		assertFalse(cm2.checkInventory().enoughIngredients(frappe));
		
		//Testing the tostring method to see if it shows the correct inventory values
		
		assertEquals("Coffee: 0%nMilk: 0%nSugar: 0%nChocolate: 0%n", cm2.checkInventory().toString());

		
		
	}
	public void testPurchaseBeverage1() {
		cm.addInventory(50, 30, 20, 40);
		cm.addRecipe(r1);

		assertEquals(cm.makeCoffee(cm.getRecipeForName("Black Coffee"), 65), 15);
		
		
	}
}