package br.com.grupomult.gpmultmanutprodutosbe.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiErrorResponse {

	@JsonProperty("code")
	private String code;

	@JsonProperty("message")
	private String message;

	@JsonProperty("errors")
	private List<ErrorResponse> errors;
	
    public ApiErrorResponse() {
    	super();
		this.errors = new ArrayList<>();
    }

	public ApiErrorResponse(String code, String message, List<ErrorResponse> errors) {
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
}