package projeto.modelo.negocio.entidade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.Session;
import org.hibernate.validator.constraints.NotEmpty;

import projeto.modelo.negocio.IPersistenciaBD;
import projeto.modelo.negocio.IPreparaClassePersistente;
import projeto.modelo.negocio.PersistenciaBD;

@Entity
@Table(name = "cliente")
public class ClienteJuridico extends Cliente implements Serializable, IPersistenciaBD<ClienteJuridico>, IPreparaClassePersistente {

	private static final long serialVersionUID = 1L;
	private PersistenciaBD<ClienteJuridico> persistencia;
	
	@NotEmpty(message="{javax.validation.constraints.NotNull.message}")
    @Size(min=4, max=20, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "cnpj")
	private String cnpj;
	
	@NotEmpty(message="{javax.validation.constraints.NotNull.message}")
    @Size(min=4, max=200, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	public ClienteJuridico() {
		// TODO Auto-generated constructor stub
	}
	
	@Inject
	public ClienteJuridico(Session session){
		//this.session = session;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remover() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClienteJuridico carrega() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean salvar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClienteJuridico> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void prepararPersistencia() {
		this.persistencia.prepararPersistencia(this, ClienteJuridico.class); 		
	}

}
