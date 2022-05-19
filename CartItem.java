// Elena Chekredji - 501133464

public class CartItem 
{
  private Product prod;
  private String ProductOptions;


  public CartItem()//constructor without parameters
  {
    this.prod = new Product();
    this.ProductOptions = "";
  }

  public CartItem(Product p, String ProdOpt)//constructor with parameters
  {
  	 prod = p; 
     ProductOptions = ProdOpt;
  }

  public Product getProduct()
  {
    return prod;
    //rerurns the product
  }
  public String getProductOptions()
  {
    return ProductOptions;
    //rerurns the productOptions

  }

  public void print()
  {
  	this.prod.print();
    //calls the print method in prouct 
  }
}
