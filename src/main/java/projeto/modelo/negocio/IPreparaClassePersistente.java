package projeto.modelo.negocio;

public interface IPreparaClassePersistente {
	/**
	 * Este método deve ser chamado no construtor da classe que é persistente
	 * 
	 * A implementação desse método é sempre chamando o método que segue
	 * PersistenciaBD.prepararPersistencia(T entidade, Class<T> classe);
	 * 
	 * Exemplo: Em Usuario.class a implementação do método ficará assim: 
	 * this.persistencia(this, Usuario.class);
	 * 
	 * E o construtor de Usuario.class deve chamar prepararPersistencia();
	 * */
	public void prepararPersistencia();
}
