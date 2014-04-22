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
public class ClienteFisico extends Cliente implements Serializable, IPersistenciaBD<ClienteFisico>, IPreparaClassePersistente{	
	
	private static final long serialVersionUID = 1L;
	private PersistenciaBD<ClienteFisico> persistencia;
		
    @Size(min=3, max=200, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "apelido")
	private String apelido;
	
	@NotEmpty(message="{javax.validation.constraints.NotNull.message}")
    @Size(min=3, max=200, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "cpf")
	private String cpf;
		
    @Size(min=2, max=20, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "rg")
	private String rg;
		
    @Size(min=8, max=20, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "emissor")
	private String emissor;
		
    @Size(min=8, max=20, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "telefone")
	private String telefone;
	
	public ClienteFisico() {
		// TODO Auto-generated constructor stub
	}
	
	@Inject
	public ClienteFisico(Session session){
	//	this.session = session;		
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
	public ClienteFisico carrega() {
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
	public List<ClienteFisico> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void prepararPersistencia() {
		this.persistencia.prepararPersistencia(this, ClienteFisico.class); 		
	}			

}