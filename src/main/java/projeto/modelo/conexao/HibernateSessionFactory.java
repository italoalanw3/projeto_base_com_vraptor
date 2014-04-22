package projeto.modelo.conexao;

import java.awt.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import projeto.modelo.negocio.PersistenciaBD;
import projeto.modelo.negocio.entidade.GrupoUsuario;
import projeto.modelo.negocio.entidade.Usuario;

@SuppressWarnings("deprecation")
@ApplicationScoped
public class HibernateSessionFactory {

	private static SessionFactory factory;

	public HibernateSessionFactory() {
	}

	@Produces
	public SessionFactory sessionFactory() {
		return factory;
	}

	@PostConstruct
	public void criar(@Observes ServletContext context) {
		Logger log = Logger.getLogger(HibernateSessionFactory.class);
		log.info("Criando SessionFactory [Verificando tabelas e atualizando banco de dados]");
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		log.info("Nova SessionFactory disponivel " + factory);
		Usuario usuario;
		PersistenciaBD<Usuario> persistencia = new PersistenciaBD<Usuario>(factory.openSession()); 
		usuario = new Usuario(persistencia);
		GrupoUsuario grupoUsuario;
		PersistenciaBD<GrupoUsuario> persistencia2 = new PersistenciaBD<GrupoUsuario>(factory.openSession());
		grupoUsuario = new GrupoUsuario(persistencia2);
		
		log.info("Verificando se existe grupo de usuário disponivel");
		if(grupoUsuario.listarTodos().isEmpty()){
			log.info("Nenhum grupo de usuário disponivel");
			log.info("Preparando-se para criar grupo: [Administrador]");
			grupoUsuario.setNome("Administrador");
			grupoUsuario.salvar();
			log.info("Cadastrado grupo: [Administrador]");
			
			java.util.List<GrupoUsuario> grupos = new ArrayList<>();
			grupos.add(grupoUsuario);
			
			log.info("Preparando-se para criar usuário: [administrador]");
			usuario.setEmail("admin");
			usuario.setNome("administrador");
			usuario.setSenha("123");
			usuario.setGrupoUsuario(grupos);
			log.info("Adicionando usuário ["+usuario.getNome()+" ao grupo: "+grupoUsuario.getNome());
			usuario.salvar();
			log.info("Cadastrado usuário ["+usuario.getNome()+" ao grupo: "+grupoUsuario.getNome());
		} else {
			log.info(grupoUsuario.listarTodos().size()+" grupo(s) de usuário existente(s)");
		}
	}

}
