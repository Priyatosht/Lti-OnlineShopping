package com.lti.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.Cart;
import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

@Component
public class EcommerceRepositoryImpl implements EcommerceRepository {

    
	Cart cart;
	
	@PersistenceContext
	EntityManager em;
	
	
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Transactional
	public Admin addAnAdmin(Admin admin) {
		Admin admin1 = em.merge(admin);
		return admin1;
	}

	@Transactional
	public Customer addACustomer(Customer customer) {
		Customer customer2 = em.merge(customer);
		return customer2;
	}

	@Transactional
	public Customer updateACustomer(Customer customer) {
		Customer customer2 = em.merge(customer);
		return customer2;
	}

	public Customer findCustomerById(long customerId) {
		return em.find(Customer.class, customerId);
	}

	public List<Customer> viewAllCustomers() {
		String jpql = "from Customer c";
		Query query = em.createQuery(jpql);
		List<Customer> customers = query.getResultList();
		return customers;
	}

	public boolean authenticateWithEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		String jpql = "Select c from Customer c where c.emailId=:emailId and c.password=:pwd";
		try {
			Query query = em.createQuery(jpql);
			query.setParameter("emailId", email);
			query.setParameter("pwd", password);
			Customer customer = (Customer) query.getSingleResult();
		} catch (Exception NoResultException) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean updatePasswordwithEmail(String email, String pwd) {
		// String jpql="update Customer c set c.password=:pwd where c.emailId=:emailId";
		String jpql = "Select c from Customer c where c.emailId=:emailId";
		try {
			Query query = em.createQuery(jpql);
			query.setParameter("emailId", email);
			// query.setParameter("pwd", pwd);
			Customer customer = (Customer) query.getSingleResult();
			customer.setPassword(pwd);
			em.merge(customer);
		} catch (Exception NoResultException) {
			return false;
		}
		return true;
	}

	@Transactional
	public Product addOrUpdateProduct(Product product) {
		Product product2 = em.merge(product);
		return product2;
	}

	public Product findProductById(long productId) {
		return em.find(Product.class, productId);
	}

	public List<Product> viewAllProducts() {
		String jpql = "from Product p";
		Query query = em.createQuery(jpql);
		List<Product> products = query.getResultList();
		return products;
	}

	@Transactional
	public Product updateStockOfProduct(long ProductId, int stock) {
		Product product = em.find(Product.class, ProductId);
		product.setStock(stock);
		product = em.merge(product);
		return product;
	}

	@Transactional
	public void removeProduct(long productId) {
		em.remove(em.find(Product.class, productId));
	}

	@Transactional
	public Retailer addOrUpdateRetailer(Retailer retailer) {
		Retailer retailer2 = em.merge(retailer);
		return retailer2;
	}

	public Retailer findRetailerById(long retailerId) {
		return em.find(Retailer.class, retailerId);
	}

	public List<Retailer> viewAllRetailers() {
		String jpql = "from Retailer r";
		Query query = em.createQuery(jpql);
		List<Retailer> retailers = query.getResultList();
		return retailers;
	}

	
	public double revenueGeneratedByRetailer(long retailerId) {
		String jpql="Select p.productPrice,oi.quantity from  Product p join OrderItem oi on p.productId=oi.product.productId where p.retailer.retailerId=:retailerId";
		Query query = em.createQuery(jpql);
		query.setParameter("retailerId",retailerId);
		List<Object[]> objects=query.getResultList();
		double revenue=0;
		for(Object[] o:objects) {
		  revenue=revenue+((Double)o[0])*((Integer)o[1]);

		}
		return revenue;
	}

	public List<Product> displayRetailerProducts(long retailerId) {
		String jpql = "Select p from Product p where p.retailer.retailerId=:retailerId";
		Query query = em.createQuery(jpql);
		query.setParameter("retailerId", retailerId);
		List<Product> products = query.getResultList();
		return products;
	}

	@Transactional
	public Product addProductByRetailer(Retailer retailer,Product product) {
		List<Product> products=retailer.getProduct();
		if(products==null) {
			products=new ArrayList<Product>();
		}
		products.add(product);
		product.setRetailer(retailer);
		retailer.setProduct(products);
		Product product2= em.merge(product);
		return product2;
	}

	
	@Transactional
	public Order addorUpdateOrder(Order order) {
		Order order2 = em.merge(order);
		return order2;
	}

	public Order findOrderById(long orderId) {
		Order order = em.find(Order.class, orderId);
		return order;
	}

	public List<Order> viewAllOrders() {
		String jpql = "from Order o";
		Query query = em.createQuery(jpql);
		List<Order> orders = query.getResultList();
		return orders;
	}

	@Transactional
	public OrderItem addOrUpdateOrderItem(OrderItem orderItem) {
		OrderItem orderItem2 = em.merge(orderItem);
		return orderItem2;
	}

	public OrderItem findOrderItemById(long orderItemId) {
		return em.find(OrderItem.class, orderItemId);
	}

	public List<OrderItem> viewAllOrderItems() {
		String jpql = "from OrderItem oi";
		Query query = em.createQuery(jpql);
		List<OrderItem> orderItems = query.getResultList();
		return orderItems;
	}


	// -------------------------------------------------------------

	public List<Product> viewAllProductByCategoryName(String category) {
		String jpql = "select p from Product p where p.categoryName=:category";
		Query query = em.createQuery(jpql);
		query.setParameter("category", category);
		List<Product> products = query.getResultList();
		return products;
	}

	public List<Product> viewProductsByProductName(String productName) {
		String jpql = "select p from Product p where p.productName=:prodname";
		Query query = em.createQuery(jpql);
		query.setParameter("prodname", productName);
		List<Product> prod =  query.getResultList();
		return prod;

	}
	
	public List<Product> filterByProductName(String productName){
		String jpql = "select p from Product p where p.productName=:prodname";
		Query query = em.createQuery(jpql);
		query.setParameter("prodname", productName);
		List<Product> prod =  query.getResultList();
		return prod;
	}
	
	public List<Product> filterByPriceAndCategoryName(double minPrice, double maxPrice ,String categoryName){
		
		String jpql = "select p from Product p where p.categoryName =: categorynm AND p.productPrice> :minPrice AND p.productPrice< :maxPrice ";
		Query query=em.createQuery(jpql);
		query.setParameter("minPrice", minPrice);
		query.setParameter("maxPrice", maxPrice);
		query.setParameter("categorynm", categoryName);
		List<Product> prod = query.getResultList();
		return prod;
	}
	
	public List<Product> filterByBrand(String brand){
		String jpql = "select p from Product p where p.brand=:brandName";
		Query query = em.createQuery(jpql);
		query.setParameter("brandName", brand);
		List<Product> prod =  query.getResultList();
		return prod;
	}
	
	public List<Product> filterByProductPrice(double minPrice, double maxPrice){
		String jpql = "select p from Product p where p.productPrice between :minPrice AND :maxPrice ";
		Query query=em.createQuery(jpql);
		query.setParameter("minPrice", minPrice);
		query.setParameter("maxPrice", maxPrice);
		List<Product> prod = query.getResultList();
		return prod;
	}
	
//	public List<Order> viewCustomerOrderHistory(long customerId){
//		String jpql="select o from Order o where o.customerId =: custId ";
//		Query query = em.createQuery(jpql);
//		query.setParameter("custId", customerId);
//		List<Order> orders = query.getResultList();
//		return orders;
//	}

	
	public void createCart(long customerId) {
	   cart=new Cart();
       cart.setCustomerId(customerId);	
       cart.setProducts(new ArrayList<Product>());
       cart.setQuantity(new ArrayList<Integer>());
	}
	
	public int searchProductinCart(Product product) {
		List<Product> products=cart.getProducts();
		int i=-1;
		for(Product p:products)
		{   i=i+1;
			if(p.getProductId()==product.getProductId()) {
				break;
			}			
		}
		return i;
	}
	
	public void increaseProductQuantityinCart(Product product,int quantity) {
		int i=searchProductinCart(product);
		List<Integer> quantity1= cart.getQuantity();
		quantity1.set(i, quantity);
		cart.setQuantity(quantity1);
	}
	
	public void addToCart(long productId) {
		Product product=em.find(Product.class, productId);
		cart.getProducts().add(product);
		cart.getQuantity().add(new Integer(1));		
	}
	public double calculateProductPriceWithQuantityinCart(Product product,int quantity) {
		double price=product.getProductPrice()*quantity;
		return price;
	}
	public double calculateTotalPrice(List<Product> products,List<Integer> quantity) {
		double totalPrice=0;
		int i=0;
		for(Product p:products) {
			totalPrice=totalPrice+calculateProductPriceWithQuantityinCart(p,(int)quantity.get(i));
//			(p.getProductPrice()*quantity.get(i));
			i=i+1;
		}
		return totalPrice;
	}

	@Transactional
	public void addIntoOrderAndOrderItemByCart(Cart cart) {
		Order order=new Order();
		order.setCustomer(findCustomerById(cart.getCustomerId()));
		List<OrderItem> oil=new ArrayList<OrderItem>();
		List<Integer> quantity=cart.getQuantity();
		int i=-1;
        for(Product p:cart.getProducts()) {
        	i=i+1;
        	OrderItem oi=new OrderItem();
        	oi.setProduct(p);
        	oi.setQuantity(quantity.get(i));
        	oi.setOrder(order);
        	oil.add(oi);
        }
        order.setOrderItem(oil);
        order.setOrderDate(LocalDate.now());
		em.merge(order);
	}


}
