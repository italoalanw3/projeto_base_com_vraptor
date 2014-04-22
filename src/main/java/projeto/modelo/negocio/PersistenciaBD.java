/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.modelo.negocio;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class PersistenciaBD<T>{
	
	@Inject
	public PersistenciaBD(Session session) {
		this.session = session;
	}
	
	@Transient
	private Session session;
	@Transient
	private T entidade;
	@Transient
	private Class<T> classe;

	@Transient
	private Logger log;
	
	public void prepararPersistencia(T entidade, Class<T> classe){
		this.entidade = entidade;
		this.classe = classe;
		this.log = Logger.getLogger(classe);
	}	

	private boolean operacao(OPERACAO_PERSISTENCIA_BD op) {
		boolean resultado = true;
		try {
			session.getTransaction().begin();
			switch (op) {
			case INSERIR:
				log.debug("Begin inserir");
				session.persist(entidade);
				session.getTransaction().commit();
				log.debug("Commit inserir");
				break;
			case ALTERAR:
				log.debug("Begin alterar");
				session.saveOrUpdate(entidade);
				session.getTransaction().commit();
				log.debug("Commit alterar");
				break;
			case REMOVER:
				log.debug("Begin remover");
				session.delete(entidade);
				session.getTransaction().commit();
				log.debug("Commit remover");
			}
		} catch (HibernateException
				| javax.validation.ConstraintViolationException exc) {
			resultado = false;
			session.getTransaction().rollback();
			log.error("Rollback em operação: " + exc.getMessage()
					+ " Provavél causa do erro: " + exc.getCause());
		} finally {
			return resultado;
		}
	}
		
	public boolean salvar() {
		return operacao(OPERACAO_PERSISTENCIA_BD.INSERIR);
	}
	
	public boolean alterar() {
		return operacao(OPERACAO_PERSISTENCIA_BD.ALTERAR);
	}

	public boolean remover(long id) {
		try {
			if (id == 0){
				throw new PersistenciaBDExpection("[antes de deletar, o id deve ser preenchido]");
			}
			T entidade = carrega(id);
			if (entidade != null)
				return operacao(OPERACAO_PERSISTENCIA_BD.REMOVER);
			else
				return false;
		} catch (PersistenciaBDExpection re) {
			log.error("Falha lógica ao remover: " + re);
			return false;
		} catch(HibernateException he){
			log.error("Falha no hibernate: " + he);
			return false;
		}
		catch(Exception e){
			log.error("Falha geral: " + e);
			return false;
		}
	}

	public List<T> listarTodos() {
		List<T> lista = null;
		session.getTransaction().begin();
		lista = session.createCriteria(classe).list();
		session.getTransaction().commit();
		return lista;
	}

	public T carrega(long id) {
		T entidade = null;
		try {
			if(id == 0){
				throw new PersistenciaBDExpection("[para carregar, o id deve ser preenchido]");
			}
			session.getTransaction().begin();
			entidade = (T) session.get(classe, id);
			session.getTransaction().commit();
		} catch (PersistenciaBDExpection e) {
			session.getTransaction().rollback();
		}
		return entidade;
	}
	
	public Session getSession() {
		return session;
	}
	
	public Logger getLog() {
		return log;
	}
}
