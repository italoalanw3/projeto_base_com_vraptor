package projeto.modelo.negocio.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class Cliente{
	
	@NotEmpty(message="{javax.validation.constraints.NotNull.message}")
    @Size(min=4, max=100, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "nome")
	private String nome;
	
	@NotEmpty(message="{javax.validation.constraints.NotNull.message}")
    @Size(min=8, max=200, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "email")
	private String email;
	
	@NotEmpty(message="{javax.validation.constraints.NotNull.message}")
    @Size(min=8, max=100, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "logradouro")
	private String logradouro;
		
    @Size(min=1, max=10, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "numero")
	private String numero;
		
    @Size(min=2, max=150, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "bairro")
	private String bairro;
		
    @Size(min=4, max=100, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "cidade")
	private String cidade;
		
    @Size(min=4, max=10, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "cep")
	private String cep;
		
    @Size(min=2, max=10, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "uf")
	private String uf;		
		
    @Size(min=2, max=100, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "complemento")
	private String complemento;
		
    @Size(min=2, max=100, message="{javax.validation.constraints.Size.message}") 
	@Column(name = "observacoes")
	private String observacoes;
		    
	@Column(name = "situacao")
	private boolean situacao;
		    
	@Column(name = "limite_credito")	
	private BigDecimal limiteCredito;
	
	@Column(name = "limite_desconto")
	private BigDecimal limiteDesconto;	

}
