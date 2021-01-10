package com.lti.repository;

import java.util.List;

import com.lti.dto.Cart;
import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

public interface EcommerceRepository {
	public Admin addAnAdmin(Admin admin);
	
	public Customer addACustomer(Customer customer);
	public Customer updateACustomer(Customer customer);
	public Customer findCustomerById(long customerId);
	public List<Customer> viewAllCustomers();
	public boolean authenticateWithEmailAndPassword(String email, String password);
	public boolean updatePasswordwithEmail(String email,String pwd);

	
	public Product addOrUpdateProduct(Product product);
    public Product findProductById(long productId);
    public List<Product> viewAllProducts();
    public Product updateStockOfProduct(long ProductId,int stock);
    public void removeProduct(long ProductId);
    
    
	public Retailer addOrUpdateRetailer(Retailer retailer);
	public Retailer findRetailerById(long retailerId);
	public List<Retailer> viewAllRetailers();  
	public double revenueGeneratedByRetailer(long retailerId);
	public List<Product> displayRetailerProducts(long retailerId);
	public Product addProductByRetailer(Retailer retailer,Product product);
	
	public Order addorUpdateOrder(Order order);
	public Order findOrderById(long orderId );
	public List<Order> viewAllOrders();
	
	public OrderItem addOrUpdateOrderItem(OrderItem orderItem);
	public OrderItem findOrderItemById(long orderItemId);
	public List<OrderItem> viewAllOrderItems();	
	
	

	public List<Product> viewAllProductByCategoryName(String category);
	public List<Product> viewProductsByProductName(String productName);
	public List<Product> filterByProductName(String productName);
	public List<Product> filterByPriceAndCategoryName(double minPrice, double maxPrice , String categoryName);
	public List<Product> filterByBrand(String brand);
	public List<Product> filterByProductPrice(double minPrice, double maxPrice);
	//public List<Order> viewCustomerOrderHistory(long customerId);

	public Cart getCart();
	public void setCart(Cart cart);
	public void createCart(long customerId);
	public void addToCart(long productId);
	public int searchProductinCart(Product product);
	public void increaseProductQuantityinCart(Product product,int quantity);
	public double calculateProductPriceWithQuantityinCart(Product product,int quantity);
	public double calculateTotalPrice(List<Product> products,List<Integer> quantity);
	public void addIntoOrderAndOrderItemByCart(Cart cart);
//	public void addIntoOrderItemByCart(Cart cart);

