package br.com.grupomult.gpmultmanutprodutosbe.service;

import br.com.grupomult.gpmultmanutprodutosbe.model.Product;
import br.com.grupomult.gpmultmanutprodutosbe.model.ProductDTO;
import br.com.grupomult.gpmultmanutprodutosbe.repository.ProductRepository;
import br.com.grupomult.gpmultmanutprodutosbe.response.ApiErrorResponse;
import br.com.grupomult.gpmultmanutprodutosbe.response.ErrorResponse;
import br.com.grupomult.gpmultmanutprodutosbe.util.ProductUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepo;

    private ApiErrorResponse response;
    private ObjectMapper mapper;

    public ProductService() {
        this.response = new ApiErrorResponse();
        this.mapper = new ObjectMapper();
    }

    public ResponseEntity<Object> save(ProductDTO productDTO) throws ParseException {
        logger.info("#### Save Method");
        if (this.isInputsNull(productDTO)) {
            logger.error("#### Possui erros no insert");
            return this.response();
        }
        logger.info("#### Inserting Produto Código: {}", productDTO.getId());
        logger.info("#### Inserting Produto Nome: {}", productDTO.getName());
        logger.info("#### Inserting Produto Categoria: {}", productDTO.getCategoryCode());
        logger.info("#### Inserting Produto Valor: {}", productDTO.getValue());
        final Product product = ProductUtil.toProduct(productDTO);
        productRepo.save(product);
        return this.response(productDTO);
    }

    public ResponseEntity<Object> delete(Long id) {
        final Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            logger.error("Erro no método deleteProdutos ID: {}, Produtos: {}", id, product);
            return this.response();
        }
        logger.info("#### Inserting Produto Código: {}", product.get().getId());
        logger.info("#### Inserting Produto Nome: {}", product.get().getName());
        logger.info("#### Inserting Produto Categoria: {}", product.get().getCategory());
        logger.info("#### Inserting Produto Valor: {}", product.get().getValue());

        productRepo.deleteById(id);
        final ProductDTO productDTO = ProductUtil.toProductDTO(product.get());
        return this.response(productDTO);
    }

    public ResponseEntity<Object> findAll() {
        logger.info("#### Busca todas os produtos cadastradas");
        final List<Product> productList = this.productRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (!Optional.of(productList).isPresent()) {
            logger.error("#### Possui erros na busca");
            return this.response();
        }
        logger.error("#### Busca realizada com sucesso");

        final List<ProductDTO> productDTOList = ProductUtil.toDTOList(productList);
        return this.response(productDTOList);
    }

    public ResponseEntity<Object> findById(Long id) {
        logger.info("#### Busca por código id");
        final Optional<Product> product = this.productRepo.findById(id);
        if (!product.isPresent()) {
            logger.error("#### Possui erros na busca");
            return this.response();
        }
        logger.error("#### Busca realizada com sucesso");

        final ProductDTO productDTO = ProductUtil.toProductDTO(product.get());
        return this.response(productDTO);
    }

    /**
     * build response messages success
     *
     * @return ApiErrorResponse
     */
    private ResponseEntity<Object> response(ProductDTO productDTO) {
        this.response.setCode("200");
        this.response.setMessage("OK");
        return ResponseEntity.status(200).body(productDTO);
    }

    /**
     * build response messages success
     *
     * @return ApiErrorResponse
     */
    private ResponseEntity<Object> response(List<ProductDTO> productDTOList) {
        this.response.setCode("200");
        this.response.setMessage("OK");
        return ResponseEntity.status(200).body(productDTOList);
    }

    /**
     * build response messages errors
     *
     * @return ApiErrorResponse
     */
    private ResponseEntity<Object> response() {
        this.response.setCode("1");
        this.response.setMessage("Verifique os erros presentes");
        return ResponseEntity.status(500).body(this.response);
    }

    /**
     * verify null fields
     *
     * @return boolean
     */
    private boolean isInputsNull(ProductDTO product) {
        boolean hasError = false;
        if (ProductUtil.isNull(product.getName())) {
            this.addErrorsMessage(1, "Nome do Produto esta vazio.");
            hasError = true;
        }
        if (ProductUtil.isNull(product.getCategoryCode())) {
            this.addErrorsMessage(2, "Categoria do Produto esta vazia.");
            hasError = true;
        }
        if (ProductUtil.isNull(product.getValue())) {
            this.addErrorsMessage(3, "Valor do Produto esta vazio.");
            hasError = true;
        }
        return hasError;
    }

    /**
     * add error message response
     *
     * @param code
     * @param message
     * @return void
     */
    private void addErrorsMessage(final Integer code, final String message) {
        response.getErrors().add(new ErrorResponse(String.valueOf(code), message));
    }

}
