package com.lti.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lti.dto.Cart;
import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.repository.EcommerceRepository;

public class EcommerceTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
	EcommerceRepository erepo = context.getBean(EcommerceRepository.class);

	@Test
	public void addAnAdmin() {
		Admin admin=new Admin();
		admin.setAdminName("Ram");
		admin.setEmailId("ram@123");
		admin.setPassword("123");
		erepo.addAnAdmin(admin);
	}
	@Test
	public void addACustomer() {
		Customer customer=new Customer();
		customer.setCustomerName("Priyatosh");
		customer.setDateOfBirth(LocalDate.now());;
		customer.setEmailId("priyat@gmail.com");
		customer.setGender("male");
		customer.setMobileNumber("45632789");
		customer.setPassword("2345");
		customer.setAddress("Hyderabad");
		customer.setApproved(false);
		erepo.addACustomer(customer);
	}
	
	@Test
	public void updateACustomer() {
		Customer customer=erepo.findCustomerById(101);
		customer.setApproved(true);
		erepo.updateACustomer(customer);
	}
	
	@Test
	public void viewAllCustomers() {
		List<Customer> customers=erepo.viewAllCustomers();
		for(Customer c:customers) {
			System.out.println(c.getCustomerId());
		}
	}
	
	@Test
	public void authenticateWithEmailAndPassword() {
		System.out.println(erepo.authenticateWithEmailAndPassword("priyat@gmail.com", "23451"));
	}
	
	@Test
	public void updatePasswordwithEmail() {
		System.out.println(erepo.updatePasswordwithEmail("priyat@gmail.com", "pwd"));
	}
	
	@Test
	public void addOrUpdateProduct() {
		Product product=new Product();
		product.setProductName("Reebok Shoes");
		product.setProductPrice(1234);
		product.setProductImg("dfgshd");
		product.setBrand("Rbk");
		product.setStock(17);
		product.setApproved(false);
		product.setCategoryName("Shoes");
		product.setDescriptionText("These shoes are very nice");
		erepo.addOrUpdateProduct(product);
//	    Retailer retailer=erepo.findRetailerById(601);
//	    OrderItem oi=erepo.findOrderItemById(402);
//	    Product product=erepo.findProductById(503);
//	    product.setRetailer(retailer);
//	    product.setOrderitem(oi);
//	    List<Product> products=retailer.getProduct();
//	    products.add(product);
//	    retailer.setProduct(products);
//	    oi.setProduct(product);
//		erepo.addOrUpdateProduct(product);
	}
	
	@Test
	public void findProductById() {
		Product product=erepo.findProductById(501);
		System.out.println(product.getProductId());
	}
	
	@Test
	public void viewAllProducts() {
		List<Product> products=erepo.viewAllProducts();
		for(Product p:products) {
			System.out.println(p.getProductId());
		}
	}
	
	@Test
	public void updateStockOfProduct() {
		Product product=erepo.updateStockOfProduct(501,20);
		System.out.println(product.getProductId()+" "+product.getStock());
	}
	
	@Test
	public void removeProduct() {
		erepo.removeProduct(502);
	}
	
	@Test
	public void addOrUpdateRetailer() {
		Retailer retailer=new Retailer();
		retailer.setRetailerName("Naveen");
		retailer.setPassword("54267");
		retailer.setEmailId("Naveen@123.com");
		retailer.setMobileNumber("8724982932");
		retailer.setApproved(false);
		erepo.addOrUpdateRetailer(retailer);
	}
	
	@Test
	public void addProductByRetailer() {
		Retailer retailer=erepo.findRetailerById(621);
		Product product=new Product();
		product.setProductName("Puma Shoes");
		product.setProductPrice(3000);
		product.setProductImg("znbxcnz");
		product.setBrand("Puma");
		product.setStock(20);
		product.setApproved(false);
		product.setCategoryName("Shoes");
		product.setDescriptionText("Stylish shoes");
		erepo.addProductByRetailer(retailer, product);
	}
	
	@Test
	public void findRetailerById() {
		Retailer retailer=erepo.findRetailerById(601);
		System.out.println(retailer.getRetailerId()+" "+retailer.getRetailerName());
	}
	
	@Test
	public void viewAllRetailers() {
		List<Retailer> retailers=erepo.viewAllRetailers();
		for(Retailer r:retailers) {
			System.out.println(r.getRetailerId());
		}
	}
	
	@Test
	public void revenueGeneratedByRetailer() {
		System.out.println(erepo.revenueGeneratedByRetailer(601));
	}
	
	@Test
	public void displayRetailerProducts() {
		List<Product> products=erepo.displayRetailerProducts(601);
		for(Product p:products) {
			System.out.println(p.getProductId());
		}
	}
	
	@Test
	public void addOrUpdateOrder() {
		Order order=new Order();
		erepo.addorUpdateOrder(order);
	}
	
	@Test
	public void findOrderById() {
		Order order=erepo.findOrderById(301);
		System.out.println(order.getOrderId());
	}
	
	@Test
	public void viewAllOrders() {
		List<Order> orders=erepo.viewAllOrders();
        for(Order o:orders) {
    		System.out.println(o.getOrderId());
        }
	}
	
	@Test
	public void addOrUpdateOrderItems() {
		OrderItem oi=new OrderItem();
		oi.setQuantity(5);
		erepo.addOrUpdateOrderItem(oi);		
	}
	
	@Test
	public void viewAllOrderItems() {
		List<OrderItem> orderItems=erepo.viewAllOrderItems();
		for(OrderItem oi:orderItems) {
			System.out.println(oi.getOrderItemId());
		}
	}
	
	@Test
	public void findOrderItemById() {
		OrderItem oi=erepo.findOrderItemById(401);
		System.out.println(oi.getOrderItemId());
	}
	
	@Test
	public void createCart() {
		erepo.createCart(101);
		Cart cart= erepo.getCart();
		erepo.addToCart(521);
		erepo.addToCart(522);
		System.out.println(cart.getCustomerId());
		List<Product> products=cart.getProducts();
		List<Integer> quantity=cart.getQuantity();
		erepo.increaseProductQuantityinCart(erepo.findProductById(521), 10);
		erepo.increaseProductQuantityinCart(erepo.findProductById(522), 10);
		erepo.addIntoOrderAndOrderItemByCart(cart);
		System.out.println(erepo.calculateTotalPrice(cart.getProducts(), cart.getQuantity()));
		int i=-1;
		for(Product p:products) {
			i=i+1;
			System.out.println(p.getProductId()+" "+quantity.get(i));
		}
	}
	


}
