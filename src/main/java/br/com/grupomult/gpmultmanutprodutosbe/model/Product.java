package br.com.grupomult.gpmultmanutprodutosbe.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity 
@Table (name = "Product")
public class Product implements Serializable {

	private static final long serialVersionUID = 8621858893196804955L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id;
	
    private String name;

    private String category;
    
    @Column(name="value", precision = 15, scale = 2)
    private BigDecimal value;
	
}
