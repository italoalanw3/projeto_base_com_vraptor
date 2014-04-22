package projeto.modelo.negocio;

public interface IPreparaClassePersistente {
	/**
	 * Este m�todo deve ser chamado no construtor da classe que � persistente
	 * 
	 * A implementa��o desse m�todo � sempre chamando o m�todo que segue
	 * PersistenciaBD.prepararPersistencia(T entidade, Class<T> classe);
	 * 
	 * Exemplo: Em Usuario.class a implementa��o do m�todo ficar� assim: 
	 * this.persistencia(this, Usuario.class);
	 * 
	 * E o construtor de Usuario.class deve chamar prepararPersistencia();
	 * */
	public void prepararPersistencia();
}
