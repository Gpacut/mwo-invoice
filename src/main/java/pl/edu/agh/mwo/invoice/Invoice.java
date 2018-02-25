package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	
	private static int nextNumber=1;
	private final int invoiceNumber = nextNumber++;
	
	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		products.put(product, quantity);
	}

	public BigDecimal getNetTotal() { 
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;
	}

	public int getNumber() {
		return invoiceNumber;
	}

	public String printInvoice() {
		return String.valueOf(invoiceNumber);
	}
	
	public String printInvoice2() {
		String print =  String.valueOf(invoiceNumber);
		for (Product product : products.keySet()) {
			print += "\n";
			print += product.getName();
			print += " " + products.get(product);
			print += " " + product.getPrice();
		}

		
		return print;
	}
	
	public String printInvoice3() {
		String print =  String.valueOf(invoiceNumber);
/*		for (Product product : products.keySet()) {
			print += "\n";
			print += product.getName();
			print += " " + products.get(product);
			print += " " + product.getPrice();
		}
*/		
		print += products.keySet().stream()
//				.map(product -> "\n" + product.getName() + " " + products.get(product) + " " + product.getPrice())
				.map(product -> String.format("\n%s %d %s", product.getName(),products.get(product),product.getPrice()))
				.collect(Collectors.toList());
		print += "\nLiczba pozycji: " + products.size();
		
		return print;
	}
	
	public String printInvoice4() {
		String print =  String.valueOf(invoiceNumber);
		
		return print;
	}
}
