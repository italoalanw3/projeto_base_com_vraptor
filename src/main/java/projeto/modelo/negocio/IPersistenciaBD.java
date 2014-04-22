package projeto.modelo.negocio;

import java.util.List;

public interface IPersistenciaBD<T> {
	
	public long getId();
	
	public void setId(long id);
	
	public boolean salvar();

	public boolean alterar();

	public boolean remover();

	public List<T> listarTodos();

	public T carrega();

}
