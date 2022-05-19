

// Elena Chekredji - 501133464
import java.util.Comparator;


public class ProductPriceComparator implements Comparator<Product>
{
	public int compare(Product a, Product b)
	{
		if (a.getPrice() < b.getPrice()) return -1;
		if (a.getPrice() > b.getPrice()) return  1;
		return 0;
	}
}	

// comapres the products based on their price 