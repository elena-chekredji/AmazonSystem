
// Elena Chekredji - 501133464
import java.util.Comparator;


public class ProductNameComparator implements Comparator<Product>
{
	public int compare(Product a, Product b)
	{
		return a.getName().compareTo(b.getName());
	}
}	

// comapres the products based on their names  