package volodymyr;

import java.util.Arrays;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Application {

	public static void main(String[] args) {

		// create configuration object with credentionals to DataBase
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		// create factory and begin Transaction
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		// Create Carts
		Cart cart1 = new Cart(1, "Cart1");
		session.persist(cart1);

		Cart cart2 = new Cart(2, "Cart2");
		session.persist(cart2);

		Cart cart3 = new Cart(3, "Cart3");
		session.persist(cart3);

		Cart cart4 = new Cart(4, "Cart4");
		session.persist(cart4);

		// Create Items

		Item item1 = new Item(1);
		session.persist(item1);

		Item item2 = new Item(2);
		session.persist(item2);

		Item item3 = new Item(3);
		session.persist(item3);

		Item item4 = new Item(4);
		session.persist(item4);

		cart1.setItems(new HashSet<>(Arrays.asList(item3, item2, item1, item4)));
		session.persist(cart1);

		cart2.setItems(new HashSet<>(Arrays.asList(item4, item1, item2, item3)));
		session.persist(cart2);

		cart3.setItems(new HashSet<>(Arrays.asList(item2, item1, item4, item2)));
		session.persist(cart3);

		cart4.setItems(new HashSet<>(Arrays.asList(item2, item1, item4, item2)));
		session.persist(cart4);

		transaction.commit();

		session.close();

	}

}
