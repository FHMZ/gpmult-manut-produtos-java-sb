package br.com.grupomult.gpmultmanutprodutosbe.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ErrorResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}