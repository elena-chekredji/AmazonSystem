// Elena Chekredji - 501133464
import java.util.ArrayList;

public class Cart
{
  private ArrayList<CartItem>  cart = new ArrayList<CartItem>();;  

  public Cart()//default constructor
  {
  	ArrayList<CartItem>  cart = new ArrayList<CartItem>();
  }
  
  public Cart(ArrayList<CartItem>  c)//constructor with variable
  {
  	cart = c;
  }
    
  public void addItem(CartItem i)//adds item to list cart
  {
    this.cart.add(i);
  }

  public void removeItem(Product i)//removes item from list cart
  {
    for(CartItem c : this.cart)
    {
      if(c.getProduct().getId() == i.getId())
      {
      this.cart.remove(c);
      }
    }
  }
  public ArrayList<CartItem> getCartItemsList()//returns the list of items in cart
  {
    return this.cart;
  }

  public void resetCart()//resets cart to being empty
  {
    this.cart = new ArrayList<CartItem>();;
  }

  public void print()//prints all the elemnts in the cart
  {
    System.out.println("The items in your cart currently are: ");
    for(CartItem c: cart)
    {
      c.print();
      //calls the print method in CartItem
    }
    // I add the title and the author to the end of the super.print()
  }

}
