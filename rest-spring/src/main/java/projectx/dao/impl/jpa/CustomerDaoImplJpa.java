package projectx.dao.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import projectx.dao.CustomerDao;
import projectx.entity.Customer;

@Repository
@Transactional
public class CustomerDaoImplJpa implements CustomerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Customer> findAll() {
		String strJpa = "SELECT c from Customer c ORDER BY c.registerTime DESC";
		List<Customer> customers = em.createQuery(strJpa).getResultList();
		return customers;
	}
	
	@Override
	public Customer findOne(Long id) {
		Customer cust = em.find(Customer.class, id);
		return cust;
	}
	
	@Override
	public Customer save(Customer customer) {
		em.persist(customer);
		return customer;
	}
	
	@Override
	public int delete(Long id) {
		Customer cust = findOne(id);
		try {
			em.remove(cust);
		} catch(Exception ex) {
			// handle exception
		}
		return 1;
	}
	
	
	public void update(Customer customer) {
		// TODO implement this
	}
}
