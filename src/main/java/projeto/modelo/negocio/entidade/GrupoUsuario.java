package projeto.modelo.negocio.entidade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import projeto.modelo.negocio.IPersistenciaBD;
import projeto.modelo.negocio.IPreparaClassePersistente;
import projeto.modelo.negocio.PersistenciaBD;

@Entity
@Table(name = "grupo_usuario")
public class GrupoUsuario implements Serializable, IPersistenciaBD<GrupoUsuario>, IPreparaClassePersistente {

	private static final long serialVersionUID = 1L;
	
	@Transient
	private PersistenciaBD<GrupoUsuario> persistencia;


	public GrupoUsuario() {
	}
	
	/** Construtor */
	@Inject
	public GrupoUsuario(PersistenciaBD<GrupoUsuario> persistencia) {
		this.persistencia = persistencia;
		prepararPersistencia();
	}	
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "nome", unique = true)
	private String nome;
	
	@ManyToMany(mappedBy = "grupos")	
	private List<Usuario> usuarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuario(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;		
	}

	@Override
	public boolean salvar() {
		return this.persistencia.salvar();
	}

	@Override
	public boolean alterar() {
		return this.persistencia.alterar();
	}

	@Override
	public boolean remover() {
		return this.persistencia.remover(getId());
	}

	@Override
	public List<GrupoUsuario> listarTodos() {
		return this.persistencia.listarTodos();
	}

	@Override
	public GrupoUsuario carrega() {
		return this.persistencia.carrega(getId());
	}
	
	@Override
	public void prepararPersistencia() {
		this.persistencia.prepararPersistencia(this, GrupoUsuario.class); 		
	}
}
