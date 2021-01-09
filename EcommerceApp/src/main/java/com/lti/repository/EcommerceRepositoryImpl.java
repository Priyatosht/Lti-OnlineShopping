package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.Customer;
import com.lti.entity.Order;
import com.lti.entity.OrderItem;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
@Component
public class EcommerceRepositoryImpl implements EcommerceRepository {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Admin addAnAdmin(Admin admin) {
		Admin admin1=em.merge(admin);
		return admin1;
	}
	
	@Transactional
	public Customer addACustomer(Customer customer) {
		Customer customer2=em.merge(customer);
		return customer2;
	}

	@Transactional
	public Customer updateACustomer(Customer customer) {
		Customer customer2=em.merge(customer);
		return customer2;
	}
	
	public Customer findCustomerById(long customerId) {
	 return em.find(Customer.class,customerId);	
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
	public boolean updatePasswordwithEmail(String email,String pwd) {
	 //String jpql="update Customer c set c.password=:pwd where c.emailId=:emailId";
		String jpql = "Select c from Customer c where c.emailId=:emailId";
		try {
	 Query query=em.createQuery(jpql);
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
		Product product2=em.merge(product);
		return product2;
	}
	
    public Product findProductById(long productId) {
    	return em.find(Product.class, productId);
    }

	public List<Product> viewAllProducts(){
		String jpql = "from Product p";
		Query query = em.createQuery(jpql);
		List<Product> products = query.getResultList();
		return products;	}
	
	@Transactional
    public Product updateStockOfProduct(long ProductId,int stock) {
    	Product product=em.find(Product.class, ProductId);
    	product.setStock(stock);
    	product= em.merge(product);
    	return product;
    }
	
	@Transactional
	public void removeProduct(long productId) {
		em.remove(em.find(Product.class, productId));
	}


	@Transactional
	public Retailer addOrUpdateRetailer(Retailer retailer) {
		Retailer retailer2=em.merge(retailer);
		return retailer2;
	}
	
	public Retailer findRetailerById(long retailerId ) {
		return em.find(Retailer.class, retailerId);
	}
	
	public List<Retailer> viewAllRetailers(){
		String jpql = "from Retailer r";
		Query query = em.createQuery(jpql);
		List<Retailer> retailers = query.getResultList();
		return retailers;	
	}
	
	public int revenueGeneratedByRetailer(long retailerId) {
		String jpql="Select p.productPrice,oi.quantity from  Product p join OrderItem oi on p.productId=oi.product.productId where p.retailer.retailerId=:retailerId";
		Query query = em.createQuery(jpql);
		query.setParameter("retailerId",retailerId);
		List<Object[]> objects=query.getResultList();
		int revenue=0;
		for(Object[] o:objects) {
		  revenue=revenue+((Integer)o[0])*((Integer)o[1]);
		}
        return revenue;
	}
	
	public List<Product> displayRetailerProducts(long retailerId){
		String jpql="Select p from Product p where p.retailer.retailerId=:retailerId";
		Query query = em.createQuery(jpql);
		query.setParameter("retailerId",retailerId);
		List<Product> products=query.getResultList();
		return products;
	}
	
	@Transactional
	public Order addorUpdateOrder(Order order) {
		Order order2=em.merge(order);
		return order2;
	}
	
	public Order findOrderById(long orderId ) {
		Order order=em.find(Order.class, orderId);
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
		OrderItem orderItem2=em.merge(orderItem);
		return orderItem2;
	}
	
	public OrderItem findOrderItemById(long orderItemId) {
		return em.find(OrderItem.class, orderItemId);
	}
	
	public List<OrderItem> viewAllOrderItems(){
		String jpql = "from OrderItem oi";
		Query query = em.createQuery(jpql);
		List<OrderItem> orderItems = query.getResultList();
		return orderItems;
	}
}
