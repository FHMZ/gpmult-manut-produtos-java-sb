package br.com.grupomult.gpmultmanutprodutosbe.enums;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public enum CurrencyEnum {
	
	REAL("R$", "#,##0.00");
	
	private String code;
	private NumberFormat numberFormat;

	private CurrencyEnum(String code, String pattern) {
		this.code = code;
		this.numberFormat = new DecimalFormat(pattern);
	}
	
	public String format(Number number) {
		return this.numberFormat.format(number);
	}

	public String getCode() {
		return code;
	}
	
}
