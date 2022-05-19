
// Elena Chekredji - 501133464

import java.lang.reflect.Array;
import java.util.Arrays;

/* A shoe IS A product that has additional information - e.g. stock

*/
public class Shoes extends Product 
{
  
  
  private int shoeStock[][]; 
  // a 2D array
  
  public Shoes(String name, String id, double price, int [][] shoeStock)
  {
  	super(name, id, price, 100000, Product.Category.SHOES);
    this.shoeStock = shoeStock;

    // Make use of the constructor in the super class Product. Initialize additional Shoe instance variables. 
  	 // Set category to SHOES 
  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Black" or "Brown" and if the size is between 6-10
  	// if it is one of these, return true, else return false
    String options[] = productOptions.split(" ");  //splits the product options into an int for size and string for brown or black
    int size = Integer.valueOf(options[0]);
    String color = options[1];
    
    if(size >=6 && size <=10 && (color.equals("Brown") || color.equals("Black")))
    {
      return true;
    }
  	return false;
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "6 Black", "6 Brown" etc
  	// uses the method getIndexOfStock to get the inex of the array with the apropriate stock 
  	// (look at getIndexOfStock for better expliantion of how the method works)
    int pos[] = getIndexOfStock(productOptions);
    return shoeStock[pos[0]][pos[1]];
    //returns the apropriate stock for the color and size given in ProductOptions
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "6 Black", "6 Brown" etc
   	// uses the method getIndexOfStock to get the inex of the array with the apropriate stock 
     int pos[] = getIndexOfStock(productOptions);
     shoeStock[pos[0]][pos[1]] = stockCount;
    //sets the apropriate stock for the color and size given in ProductOptions
	}
	
  
  /*
   * When a shoe is ordered, reduce the stock count for the specific shoe size and color
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "6 Black", "6 Brown" etc
   	// uses the method getIndexOfStock to get the inex of the array with the apropriate stock 
     int pos[] = getIndexOfStock(productOptions);
     shoeStock[pos[0]][pos[1]] -= 1;
     //reduces the apropriate stock for the color and size given in ProductOptions
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
  	// Make use of the super class print() method and append the title and author info.
  	super.print(); 
    System.out.print(" Shoe Stock: " + Arrays.deepToString(shoeStock));
   // prints out the array of stocks for the shoes along with the stuff that comes from the super.print();
  }

  public int[] getIndexOfStock(String productOptions)
  {
    // i split the productOptions into int size and String color
    // the first row is the stock of color "Black"
    // the second row is the stock of color "Brown"
    // as the colums go along the size increases. So [0][1] is "7 Black"
    /*
    once i have size and color, I first check the color and then I check the size 
    For each increasing size I go one to the right in the array list 
    Then i return an array with 2 values that i can use to modify the stock of the shoes as needed
    */

    String options[] = productOptions.split(" ");
    int size = Integer.valueOf(options[0]);
    String color = options[1];
    int arr[] = {9,9};
    if(color.equals("Black"))
    {
      if(size == 6)
      {
        arr[0] = 0;
        arr[1] = 0;
        return arr;
      }
      else if (size == 7)
      {
        arr[0] = 0;
        arr[1] = 1;
        return arr;
      }
      else if(size == 8)
      {
        arr[0] = 0;
        arr[1] = 2;
        return arr;
      }
      else if (size == 9)
      {
        arr[0] = 0;
        arr[1] = 3;
        return arr;
      }
      else if(size == 10)
      {
        arr[0] = 0;
        arr[1] = 4;
        return arr;
      }
    }
    if(color.equals("Brown"))
      {
      if(size == 6)
      {
        arr[0] = 1;
        arr[1] = 0;
        return arr;
      }
      else if (size == 7)
      {
        arr[0] = 1;
        arr[1] = 1;
        return arr;
      }
      else if(size == 8)
      {
        arr[0] = 1;
        arr[1] = 2;
        return arr;
      }
      else if (size == 9)
      {
        arr[0] = 1;
        arr[1] = 3;
        return arr;
      }
      else if(size == 10)
      {
        arr[0] = 1;
        arr[1] = 4;
        return arr;
      }
    }
    return arr;
}
}


