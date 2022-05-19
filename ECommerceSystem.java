// Elena Chekredji - 501133464
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.List;

//import Product.Category;

import java.io.*;


/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    //private ArrayList<Product>  products = new ArrayList<Product>();
    private TreeMap<String, Product> productsMap = new TreeMap<String, Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = null;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
    	 	
      try
      {
        ArrayList<Product> productList = readProductsFile("products.txt");
        for(Product p:productList)
        {
          productsMap.put(p.getId(), p);
        }
        
      }
      catch (IOException e)
      {
        System.out.println(e.getMessage());
      }

    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));

          	// Create some products. Notice how generateProductId() method is used
      // int stock[][]={{15,15,15,15,15},{15,15,15,15,15}};
    	// products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
    	// products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
    	// products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney"));
    	// products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
    	// products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
    	// products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
    	// products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast"));
    	// products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive"));
    	// products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney"));
    	// products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
      // products.add(new Shoes("Shoes", generateProductId(), 65.0, stock));
    }
    
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    public String getErrorMessage()
    {
    	return errMsg;
    }
    
    public void printAllProducts()
    {
    	Set<String> keySet = productsMap.keySet();
      for (String key : keySet)
      {
        productsMap.get(key).print();
      }
      // for (Product p : products)
    	// 	p.print();
        // traverses every product in productsMap and calls the Product print method on the product value of the map
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
      Set<String> keySet = productsMap.keySet();
      for (String key : keySet)
      {
        if(productsMap.get(key).getCategory()==Product.Category.BOOKS)
        {
          productsMap.get(key).print();
          //traverses all the values in the map products, and prints the ones with the category books
        }
      }
    }
    
    // Print all current orders
    public void printAllOrders()
    {
    	for (ProductOrder o : orders)
    		o.print();
        // traverses every ProductOrder in orders and calls the Product print method
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for (ProductOrder allO : shippedOrders)
      allO.print();
        // traverses every ProductOrder in shippedOrders and calls the Product print method
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c : customers)
    		c.print();
        // traverses every Customer in customers and calls the Customer print method
    }
    /*  
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId)
    {
      // Make sure customer exists - check using customerId
      // create a boolean variable that will check if the customer exists
      boolean customerExists = false;
    	for (Customer c : customers)
      {
        if(c.getId().equals(customerId))
          customerExists = true;
          // it will traverse customers and if any customers id is equal to tge given customer if
          //set customerExists to true
      }
    	if (customerExists == false)
      {
        throw new UnknownCustomerException("Customer " + customerId + " Not Found");
      }
    	// Print current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
    	for (ProductOrder o : orders)
    	{
        if(o.getCustomer().getId().equals(customerId))
        {
          o.print();
          //prints out the orders of the products that have the same customerId as the given one
        }
      }
    	// Print shipped orders of this customer 
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
    	for (ProductOrder s : shippedOrders)
    	{
        if(s.getCustomer().getId().equals(customerId))
        {
          s.print();
          //prints out the orders of the customer that have the same customerId as the given one and are in the arrayList shippedOrders
        }
      }
    }
    
    public String orderProduct(String productId, String customerId, String productOptions)
    {
    	// First check to see if customer object with customerId exists in array list customers
      int index = customers.indexOf(new Customer(customerId, "", ""));
      // gets the index of the customer with the customerId
      if(index == -1)  //if index is -1, no such customer exists, so set an error message
      {
        throw new UnknownCustomerException("Customer " + customerId + " Not Found");
      }
      Customer newCust = customers.get(index);
    	// Check to see if product object with productId exists in array list of products
    	// else get the Product object 
      if(!productsMap.containsKey(productId))//if it doesnt contain the key, throw an eception
      {
        throw new UnknownProductException("Product " + productId + " Not Found");
      }
      Product newProd = productsMap.get(productId);//get the value with key productId
      newProd.hasBeenOreded();
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
    	// If options are not valid, set errMsg string and return null;
    	if (newProd.validOptions(productOptions) == false)
      // calls valid options to see if the options are valid
      {
        throw new InvalidProductOptionsException(" Invalid product options -> " + productOptions);
      }
    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
    	if (newProd.getStockCount(productOptions) == 0)
      // calls get stock and checks if its 0; if it is set error message cause there is no stock 
      {
        throw new ProductOutOfStockException("Product out of stock");
      }
      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
      ProductOrder newOrder = new ProductOrder(generateOrderNumber(), newProd, newCust, productOptions);
      newProd.reduceStockCount(productOptions);
      orders.add(newOrder);
      return newOrder.getOrderNumber();
    }
    
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address) throws InvalidCustomerNameException 
    {
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	// Repeat this check for address parameter
    	if(name.equals("") || name == null)
      // check for the validity of the name and sets an appropriate error message
      {
        throw new InvalidCustomerNameException("Invalid Customer Name!");
      }
      if(address.equals("") || address == null)
      // check for the validity of the address and sets an appropriate error message
      {
        throw new InvalidCustomerAddressException("Invalid Customer Address!");
      }
    	// Create a Customer object and add to array list
      // if its all good i create a new customer with the name and adress
      Customer newCust = new Customer(generateCustomerId(), name, address);
      customers.add(newCust);
    }
    
    public ProductOrder shipOrder(String orderNumber) 
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
      int index = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));
      // gets the index of the order with orderNumber
      if(index == -1)
      //if index is -1, no such order exists, so set an error message
      {
        throw new UnknownOrderNumberException( "Order " + orderNumber + " Not Found");
      }
      
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order
      ProductOrder orderRef = orders.get(index);
      orders.remove(index);
      shippedOrders.add(orderRef);
      
    	return orderRef;
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
      int index = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));
      // gets the index of the order with orderNumber
      if(index == -1)
      //if index is -1, no such order exists, so set an error message
      {
        throw new UnknownOrderNumberException( "Order " + orderNumber + " Not Found");
      }
      orders.remove(index);
    }
    
    // Sort products by increasing price
    public void printByPrice()
    {
    ArrayList<Product> temp = new ArrayList<Product>();	

    	Set<String> keySet = productsMap.keySet();
      for (String key : keySet)
      {
        temp.add(productsMap.get(key));
      }

  	  Collections.sort(temp, new ProductPriceComparator());
      for(Product p: temp)
      {
        p.print();
      }
      
    }
    // Sort products alphabetically by product name
    public void printByName()
    {
      ArrayList<Product> temp = new ArrayList<Product>();	

    	Set<String> keySet = productsMap.keySet();
      for (String key : keySet)
      {
        temp.add(productsMap.get(key));
      }
  	  Collections.sort(temp, new ProductNameComparator());
      for(Product p: temp)
      {
        p.print();
      }

    }
       
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
  	  Collections.sort(customers);
    }

    public void addToCart(String productId, String customerID, String productOptions)
    {
      int index = customers.indexOf(new Customer(customerID, "", ""));
      if(index == -1)  //if index is -1, no such customer exists, so set an error message
      {
        throw new UnknownCustomerException("Customer " + customerId + " Not Found");
      }
      Customer newCust = customers.get(index);

      if(!productsMap.containsKey(productId))//if it doesnt contain the key, throw an eception
      {
        throw new UnknownProductException("Product " + productId + " Not Found");
      }
      Product newProd = productsMap.get(productId);//get the value with key productId

    	if (newProd.validOptions(productOptions) == false)
      {
        throw new InvalidProductOptionsException(" Invalid product options -> " + productOptions+ "\n>");
      }
    	if (newProd.getStockCount(productOptions) == 0)
      {
        throw new ProductOutOfStockException("Product out of stock");
      }
      CartItem newItem = new CartItem(newProd, productOptions);
      Cart c = newCust.getCart();
      c.addItem(newItem);//method in class Cart
    }

    public void removeFromCart(String productId, String customerID)
    {
      int index = customers.indexOf(new Customer(customerID, "", ""));
      // gets the index of the customer with the customerId
      if(index == -1)  //if index is -1, no such customer exists, so set an error message
      {
        throw new UnknownCustomerException("Customer " + customerId + " Not Found");
      }
      Customer newCust = customers.get(index);
    	// Check to see if product object with productId exists in map of products
    	// else get the Product object 
      if(!productsMap.containsKey(productId))//if it doesnt contain the key, throw an eception
      {
        throw new UnknownProductException("Product " + productId + " Not Found");
      }
      Product newProd = productsMap.get(productId);//get the value with key productId
    	

      Cart c = newCust.getCart();
      c.removeItem(newProd);//method in class Cart
    }

    public void printCart(String customerId)
    {
      int index = customers.indexOf(new Customer(customerId, "", ""));
      // gets the index of the customer with the customerId
      if(index == -1)  //if index is -1, no such customer exists, so set an error message
      {
        throw new UnknownCustomerException("Customer " + customerId + " Not Found");
      }
      Customer newCust = customers.get(index);
      newCust.getCart().print();//calls the print in cart which calls the print in cartitem
    }

    public void orderCartItems(String customerId)
    {
      int index = customers.indexOf(new Customer(customerId, "", ""));
      // gets the index of the customer with the customerId
      if(index == -1)  //if index is -1, no such customer exists, so set an error message
      {
        throw new UnknownCustomerException("Customer " + customerId + " Not Found");
      }
      Customer newCust = customers.get(index);
      Cart c = newCust.getCart();
      ArrayList<CartItem>  cart = c.getCartItemsList();
      for(CartItem item : cart)
      {
        orderProduct(item.getProduct().getId(), customerId, item.getProductOptions());
      }
      c.resetCart();//method in class cart
    }

    public ArrayList<Product> readProductsFile(String filename) throws FileNotFoundException 
    {
      ArrayList<Product> productList = new ArrayList<>();
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      String name = "";
      String price = "";
      String stock = "";
      int papaerbackStock = 0;
      int hardcoverStock = 0;
      String line4 = "";
      String line5 = "";
      String author = "";
      String title = "";
      String year = "";

      while(scanner.hasNextLine())
      {
      String category  = scanner.nextLine();
      if(category.equalsIgnoreCase("BOOKS") && scanner.hasNextLine())
      {
        if(scanner.hasNextLine())
          name=scanner.nextLine();

        if(scanner.hasNextLine())
        price=scanner.nextLine();
       
        if(scanner.hasNextLine())
        {
          line4 = scanner.nextLine();
          Scanner s4 = new Scanner(line4);
          s4.useDelimiter(" ");
          if(s4.hasNextInt())
            papaerbackStock = s4.nextInt();
          if(s4.hasNextInt())
            hardcoverStock = s4.nextInt();
        } 
        if(scanner.hasNextLine())
        {
          line5 = scanner.nextLine();
          Scanner s5 = new Scanner(line5);
          s5.useDelimiter(":");
          if(s5.hasNext())
          title = s5.next();
          if(s5.hasNext())
            author = s5.next();
          if(s5.hasNext())
            year = s5.next();
        }
        productList.add(new Book(name, generateProductId(), Double.parseDouble(price), papaerbackStock, hardcoverStock, title, author, Integer.parseInt(year)));
      }
      else if(scanner.hasNextLine())
      {
        if(scanner.hasNextLine())
          name=scanner.nextLine();

        if(scanner.hasNextLine())
          price=scanner.nextLine();

        if(scanner.hasNextLine())
          stock = scanner.nextLine();
        
        if(scanner.hasNextLine())
          line5 = scanner.nextLine();

        productList.add(new Product(name, generateProductId(), Double.parseDouble(price), Integer.parseInt(stock), Product.Category.valueOf(category)));

      }
    }
    return productList;
  }

  public String giveProducOptions(String productId)
  {
    if(productsMap.get(productId).getCategory() ==  Product.Category.BOOKS)
    {
      return "The valid options include: Paperback, Hardcover and EBook";
    }
    else if (productsMap.get(productId).getCategory()==  Product.Category.SHOES)
    {
      return "The valid options include: Black 6-10 or Brown 6-10";
    }
    else
    {
      return "Product oprions are not needed for this product";
    }
  }

  public void getStats()
  {
    Map<String, Integer> stats = new HashMap<String, Integer>();
    Set<String> keySet = productsMap.keySet();
    for (String key : keySet)
     {
       String s = String.format("Name: %-20s ProductId: %3s ",productsMap.get(key).getName(), productsMap.get(key).getId());
       stats.put(s, productsMap.get(key).getTimesOrdered());
     }
		
     Map<String, Integer> sortedByValueDesc = stats.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
     .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

     Set<String> key = sortedByValueDesc.keySet();
    for (String k : key)
     {
       System.out.printf("%-50s Times Ordered: %1d %n", k, sortedByValueDesc.get(k));
     }
  }

}

class UnknownCustomerException extends RuntimeException
{
  public UnknownCustomerException(String message)
  {
    super(message);
  }
}

class UnknownProductException extends RuntimeException
{
  public UnknownProductException(String message)
  {
    super(message);
  }
}

class InvalidProductOptionsException extends RuntimeException
{
  public InvalidProductOptionsException(String message)
  {
    super(message);
  }
}

class ProductOutOfStockException extends RuntimeException
{
  public ProductOutOfStockException(String message)
  {
    super(message);
  }
}

class InvalidCustomerNameException extends RuntimeException
{
  public InvalidCustomerNameException(String message)
  {
    super(message);
  }
}

class InvalidCustomerAddressException extends RuntimeException
{
  public InvalidCustomerAddressException(String message)
  {
    super(message);
  }
}
class UnknownOrderNumberException extends RuntimeException
{
  public UnknownOrderNumberException(String message)
  {
    super(message);
  }
}


