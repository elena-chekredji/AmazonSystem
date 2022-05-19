
// Elena Chekredji - 501133464
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">"); 
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		try
		{
		
			String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				amazon.createCustomer(name, address);
				
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
        
					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();
					// Ship order to customer (see ECommerceSystem for the correct method to use
					ProductOrder check = amazon.shipOrder(orderNumber); // Call shipOrder here and store it in check
					if(check == null)
					{
						System.out.println(amazon.getErrorMessage());
						// if the function return null I call the get error message method
						// which is set with the reason I cant call the shipOrder method; ex: Invalid order number
					}
					else
					{
						check.print();
						//else print out the Product order using its print method
					}
					
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
				customerId = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
				amazon.printOrderHistory(customerId);
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
			  // Get product Id from scanner
			  if (scanner.hasNextLine())
			  productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
			  if (scanner.hasNextLine())
			  customerId = scanner.nextLine();
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				String check = amazon.orderProduct(productId, customerId, ""); // Call orderproduct here and store it in check
				System.out.println("Order #" + check);
				// Print Order Number string returned from method in ECommerceSystem
				
			}	
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
			    customerId = scanner.nextLine();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book forma and store in options string
				if (scanner.hasNextLine())
				options = scanner.nextLine();
				
				// Order product. Check for error mesage set in ECommerceSystem
				// Print order number string if order number is not null
				String check = amazon.orderProduct(productId, customerId, options); // Call orderproduct here and store it in check
				System.out.println("Order # "+ check);
					// if it is valid i print out the order#
				

			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productId = "";
				String customerId = "";
				String options = "";
				
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
				customerId = scanner.nextLine();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				if (scanner.hasNextLine())
				options = scanner.nextLine() + " ";
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if (scanner.hasNextLine())
				options += scanner.nextLine();
				//order shoes

				String check = amazon.orderProduct(productId, customerId, options); // Call orderproduct here and store it in check
				System.out.println("Order # "+ check);
					// if it is valid i print out the order#
				
					

			}			
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				if (scanner.hasNextLine())
				orderNumber = scanner.nextLine();
				// cancel order. Check for error
			amazon.cancelOrder(orderNumber);	//I call cancel order here and store it in check 
	
				
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
			{
				amazon.printByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.printByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			else if (action.equalsIgnoreCase("ADDTOCART")) 
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
			    customerId = scanner.nextLine();
				System.out.println(amazon.giveProducOptions(productId));
				System.out.print("\n Add the product options(if neccesry): ");
				// get book forma and store in options string
				if (scanner.hasNextLine())
				options = scanner.nextLine();

				amazon.addToCart(productId, customerId, options);
				
			}
			else if (action.equalsIgnoreCase("REMCARTITEM")) 
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
			    customerId = scanner.nextLine();

				amazon.removeFromCart(productId, customerId);
			}
			else if (action.equalsIgnoreCase("PRINTCART")) 
			{
				String customerId = "";
			
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
			    customerId = scanner.nextLine();
				amazon.printCart(customerId);
			}
			else if (action.equalsIgnoreCase("ORDERITEMS")) 
			{
				String customerId = "";
			
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
			    customerId = scanner.nextLine();
				amazon.orderCartItems(customerId);
			}
			else if (action.equalsIgnoreCase("STATS")) 
			{
				amazon.getStats();
			}

			
		
		// I call each appropriate sort method here
	    }
		
		catch(RuntimeException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			System.out.print("\n>");
		}
	}
	
}
