package la.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JpaTest {

	public static void main(String[] args) {
		System.out.println("Start...");
		// DB start
		try (DatabaseManager dbManager = DatabaseManager.INSTANCE) {
			// Spring Data Jpa initialize
			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		} catch (Throwable t) {
			t.printStackTrace();
		}

		System.out.println("Done.");
	}
}