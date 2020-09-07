package br.com.grupomult.gpmultmanutprodutosbe.enums;

public enum CategoriesEnum {

	PERECIVEL(1, "PERECÍVEL"),
	NAO_PERECIVEL(2, "NÃO PERECÍVEL");

	Integer id;
	String description;

	CategoriesEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
