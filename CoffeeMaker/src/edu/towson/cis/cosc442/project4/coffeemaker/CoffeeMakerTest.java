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
		r1.setAmtChocolate(3);
		
		//Will define a recipe that requires no ingredients
		r2 = new Recipe();
		r2.setName("Water");
		r2.setPrice(-1);
		r2.setAmtCoffee(-1);
		r2.setAmtMilk(-1);
		r2.setAmtSugar(-1);
		r2.setAmtChocolate(-1);
	}

	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
		//Should not be able to add the same recipe 
		assertFalse(cm.addRecipe(r1));
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
		
		//Testing the equality of recipes to satisfy mutation
		Recipe eq1 = new Recipe();
		Recipe eq2 = new Recipe();
		eq2.setName("MangoChoc");
		assertFalse(eq1.equals(eq2));
		eq1.setName("VanillaBean");
		assertFalse(eq1.equals( eq2));
		
		//Testing the toString()
		assertEquals(eq1.toString(),"VanillaBean");
		
		
	}
	
	public void testAddInventory1() {
		
		//The first inventory checker
		assertEquals(cm.checkInventory().getCoffee(), 0);
		assertEquals(cm.checkInventory().getMilk(), 0);
		assertEquals(cm.checkInventory().getSugar(), 0);
		assertEquals(cm.checkInventory().getChocolate(), 0);
		//The first add checker
		assertTrue(cm.addInventory(50, 30, 20, 40));
		
		//Should return an error if trying to use negative inventory value
		assertFalse(cm.addInventory(-1, 30, 20, 40));
		
		//Should return an error if the a single inventory value is not correct
		assertEquals(cm.checkInventory().getCoffee(), 50);
		assertEquals(cm.checkInventory().getMilk(), 30);
		assertEquals(cm.checkInventory().getSugar(), 20);
		assertEquals(cm.checkInventory().getChocolate(), 40);
		
		cm.checkInventory().setChocolate(35);
		assertEquals(cm.checkInventory().getChocolate(), 35);
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
		cm2.addRecipe(frappe);
		assertFalse(cm2.checkInventory().enoughIngredients(frappe));
		
		//Testing the tostring method to see if it shows the correct inventory values
		
		assertEquals("Coffee: 0%nMilk: 0%nSugar: 0%nChocolate: 0%n", cm2.checkInventory().toString());

		
		
	}
	public void testPurchaseBeverage1() {
		cm.addInventory(50, 30, 20, 40);
		cm.addRecipe(r1);
		
		assertEquals(cm.makeCoffee(cm.getRecipeForName("Black Coffee"), 70), 20);
		Inventory inv = cm.checkInventory();
		assertEquals(inv.getCoffee(), 44);
		assertEquals(inv.getMilk(), 29);
		assertEquals(inv.getSugar(), 19);
		assertEquals(inv.getChocolate(), 37);
		
		//Test the case when the price is more than what was offered to pay with
		CoffeeMaker cm2 = new CoffeeMaker();
		Recipe frappe = new Recipe();
		frappe.setName("Frappe");
		frappe.setPrice(50);
		frappe.setAmtCoffee(50);
		frappe.setAmtMilk(50);
		frappe.setAmtSugar(50);
		frappe.setAmtChocolate(50);
		cm2.addRecipe(frappe);
		assertEquals(cm2.makeCoffee(cm2.getRecipeForName("Frappe"), 4), 4);
		
		//Test the enough ingredients helper methods
		cm2.checkInventory().getCoffee();
		assertFalse(cm2.checkInventory().enoughIngredients(frappe));
		
		//Satisfies mutation testing for is enough
		assertTrue(cm2.checkInventory().enoughIngredients(r2));
		
		//Tests to ensure that the setters kill mutants
		Inventory invSet = cm2.checkInventory();
		//For sugar
		invSet.setSugar(30);
		assertEquals(invSet.getSugar(), 30);
		invSet.setSugar(0);
		assertEquals(invSet.getSugar(), 0);
		invSet.setSugar(-1);
		assertEquals(invSet.getSugar(), 0);
		//For coffee
		invSet.setCoffee(40);
		assertEquals(invSet.getCoffee(), 40);
		invSet.setCoffee(0);
		assertEquals(invSet.getCoffee(), 0);
		invSet.setCoffee(-1);
		assertEquals(invSet.getCoffee(), 0);
		
		//For chocolate
				invSet.setChocolate(50);
				assertEquals(invSet.getChocolate(), 50);
				invSet.setChocolate(0);
				assertEquals(invSet.getChocolate(), 0);
				invSet.setChocolate(-1);
				assertEquals(invSet.getChocolate(), 0);
	 // For milk
				invSet.setMilk(70);
				assertEquals(invSet.getMilk(), 70);
				invSet.setMilk(0);
				assertEquals(invSet.getMilk(), 0);
				invSet.setMilk(-1);
				assertEquals(invSet.getMilk(), 0);	
		
	}
}