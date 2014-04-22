package projeto.modelo.negocio.entidade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.validator.constraints.NotEmpty;

import projeto.modelo.negocio.IPersistenciaBD;
import projeto.modelo.negocio.IPreparaClassePersistente;
import projeto.modelo.negocio.PersistenciaBD;
import projeto.modelo.servico.SegurancaUtilMD5;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, IPersistenciaBD<Usuario>, IPreparaClassePersistente {

	private static final long serialVersionUID = 1L;

	@Transient
	private PersistenciaBD<Usuario> persistencia;

	public Usuario() {
	}

	/** Construtor */
	@Inject
	public Usuario(PersistenciaBD<Usuario> persistencia) {
		this.persistencia = persistencia;
		this.persistencia.prepararPersistencia(this, Usuario.class);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@NotEmpty(message = "{javax.validation.constraints.NotNull.message}")
	@Size(min = 8, max = 50, message = "{javax.validation.constraints.Size.message}")
	@Column(name = "nome")
	private String nome;
	@NotEmpty(message = "{javax.validation.constraints.NotNull.message}")
	@Size(min = 5, max = 50, message = "{javax.validation.constraints.Size.message}")
	@Column(name = "email", unique = true)
	private String email;
	@NotEmpty(message = "{javax.validation.constraints.NotNull.message}")
	@Size(min = 3, max = 50, message = "{javax.validation.constraints.Size.message}")
	@Column(name = "senha")
	private String senha;

	@ManyToMany
	@JoinTable(name = "grupos_usuarios", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_grupo_usuario"))
	private List<GrupoUsuario> grupos;

	@Transient
	private boolean autenticado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		senha = SegurancaUtilMD5.md5(senha);
		this.senha = senha;
	}

	public List<GrupoUsuario> getGrupos() {
		return grupos;
	}

	public void setGrupoUsuario(List<GrupoUsuario> grupos) {
		this.grupos = grupos;
	}

	public PersistenciaBD<Usuario> getPersistencia() {
		return persistencia;
	}

	/** Consultas */
	public boolean verificaExiste(String valor) {
		boolean isExiste = false;
		try {
			persistencia.getSession().getTransaction().begin();

			persistencia.getLog().debug(
					"Verificando se usuário existe pelo e-mail");
			// Retorna true se estiver vazio
			isExiste = !getPersistencia().getSession()
					.createCriteria(Usuario.class)
					.add(Restrictions.eq("email", valor)).list().isEmpty();
			persistencia.getSession().getTransaction().commit();
		} catch (HibernateException e) {
			persistencia.getSession().getTransaction().rollback();
		}
		return isExiste;
	}

	public Usuario validarUsuario(Usuario usuario) {
		try {
			Usuario usuarioValido;
			persistencia.getSession().getTransaction().begin();
			persistencia.getLog().debug("Validando usuário");
			usuarioValido = (Usuario) persistencia
					.getSession()
					.createCriteria(Usuario.class)
					.add(Restrictions.eq("email", usuario.getEmail()))
					.add(Restrictions.and(Restrictions.eq("senha",
							usuario.getSenha()))).uniqueResult();
			if (usuarioValido != null) {
				persistencia.getLog().debug("usuário validado");
			} else {
				senha = ""; // Reseta a senha para não voltar valor
							// descriptografado
			}
			persistencia.getSession().getTransaction().commit();
			return usuarioValido;
		} catch (HibernateException e) {
			persistencia.getSession().getTransaction().rollback();
			persistencia.getLog().debug("Rollback ao validar usuário");
			return null;
		}
	}

	public boolean getAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
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
		return persistencia.salvar();
	}

	@Override
	public boolean alterar() {
		return persistencia.alterar();
	}

	@Override
	public boolean remover() {
		return this.persistencia.remover(this.getId());
	}

	@Override
	public List<Usuario> listarTodos() {
		return this.persistencia.listarTodos();
	}

	@Override
	public Usuario carrega() {
		return this.persistencia.carrega(getId());
	}

	@Override
	public void prepararPersistencia() {
		this.persistencia.prepararPersistencia(this, Usuario.class);
	}

}
