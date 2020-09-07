package br.com.grupomult.gpmultmanutprodutosbe.model;

import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.Serializable;

@Data
@EnableJpaRepositories
public class ProductDTO implements Serializable{

	private static final long serialVersionUID = 5403569274077200104L;

	private long id;	
	
    private String name;
    
    private String categoryCode;

    private String value;
}
