/**
 * 
 */
package com.vodafone.crud.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodafone.crud.Exception.VodafoneDataBaseException;
import com.vodafone.crud.model.Customer;

/**
 * @author Geeth
 */
public class DataDaoImpl implements DataDao {
	static final Logger logger = Logger.getLogger(DataDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer addEntity(Customer customer) throws Exception {
		try {
			logger.debug("Session save operation started...");
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(customer);
			tx.commit();
			session.close();
			logger.debug("Session save operation finished...");
		} catch (Exception exe) {
			logger.error(exe.getStackTrace());
			throw new VodafoneDataBaseException("Error in creating Customer", "DB");
		}
		return customer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer updateEntity(Customer customer) throws Exception {
		Customer customerPersitence = null;
		try {
			logger.debug("Session update operation started...");
			if (customer.getId() != 0) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				customerPersitence = (Customer) session.load(Customer.class, new Long(customer.getId()));
				customerPersitence.setName(customer.getName());
				customerPersitence.setAddress(customer.getAddress());
				customerPersitence.setPhoneNumber(customer.getPhoneNumber());

				session.update(customerPersitence);
				tx.commit();
				session.close();
				logger.debug("Session updte operation finished...");
			}
		} catch (Exception exe) {
			logger.error(exe.getStackTrace());
			throw new VodafoneDataBaseException("Error in updating Customer", "DB");
		}
		return customerPersitence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer getEntityById(long id) throws Exception {
		Customer customer = null;
		try {
			logger.debug("Session load operation started...");
			session = sessionFactory.openSession();
			customer = (Customer) session.load(Customer.class, new Long(id));
			tx = session.getTransaction();
			session.beginTransaction();
			tx.commit();
			logger.debug("Session load operation finished...");
		} catch (Exception exe) {
			logger.error(exe.getStackTrace());
			throw new VodafoneDataBaseException("Error in Fetching Customer " + id, "DB");
		}
		return customer;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getEntityList() throws Exception {
		List<Customer> customerList = null;
		try {
			logger.debug("Session create criteria operation started...");
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			customerList = session.createCriteria(Customer.class).list();
			tx.commit();
			session.close();
			logger.debug("Session creat criteria operation finished...");
		} catch (Exception exe) {
			logger.error(exe.getStackTrace());
			throw new VodafoneDataBaseException("Error in Fetching Customers", "DB");
		}

		return customerList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteEntity(long id) throws Exception {
		boolean retVal = false;
		try {
			logger.debug("Session delete operation started...");
			session = sessionFactory.openSession();
			Object obj = session.get(Customer.class, id);
			tx = session.getTransaction();
			session.beginTransaction();
			if (obj != null) {
				session.delete(obj);
				retVal = true;
			} else {
				logger.warn("Dose not exit requested customer " + id);
			}
			tx.commit();
		logger.debug("Session delete operation finished...");
		} catch (Exception exe) {
			logger.error(exe.getStackTrace());
			throw new VodafoneDataBaseException("Error in Deleting Customers " + id, "DB");
		}
		return retVal;
	}

}
