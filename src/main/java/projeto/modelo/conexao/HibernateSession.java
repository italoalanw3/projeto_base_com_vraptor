package projeto.modelo.conexao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateSession {

	@Inject
	private SessionFactory factory;
	Logger log;

	@Produces
	@RequestScoped
	public Session getSession() {
		log = Logger.getLogger(HibernateSession.class);
		log.debug("CLINICA: Abrindo session do hibernate");
		return factory.openSession();
	}

	public void close(@Disposes Session session) {
		if (session.isOpen()) {
			session.close();
			log.debug("CLINICA: Fechando session do hibernate");
		}
	}
}
