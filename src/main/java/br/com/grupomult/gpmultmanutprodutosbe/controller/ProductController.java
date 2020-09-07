package br.com.grupomult.gpmultmanutprodutosbe.controller;

import br.com.grupomult.gpmultmanutprodutosbe.model.ProductDTO;
import br.com.grupomult.gpmultmanutprodutosbe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.text.ParseException;

/**
 * @author Fernando Zandonadi
 * @Email ferzan17@gmail.com
 * @Doc variaveis de ambiente adicionadas para melhor pratica
 **/
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "${controller.url}")
public class ProductController implements Serializable {

    /**
     * Se forem realizadas mudanças muito grandes nesta classe,
     * deve-se tambem alterar este valor
     **/
    private static long serialVersionUID = 1L;

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping()
    public ResponseEntity<Object> insert(@RequestBody ProductDTO productDTO) throws ParseException {
        return this.productService.save(productDTO);
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody ProductDTO productDTO) throws ParseException {
        return this.productService.save(productDTO);
    }

    @DeleteMapping(value = "${controller.urn.deleteById}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return this.productService.delete(id);
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        return this.productService.findAll();
    }

    @GetMapping(value = "${controller.urn.findAllById}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return this.productService.findAllById(id);
    }

    @GetMapping(value = "${controller.urn.findAllByCategoryOrName}")
    public ResponseEntity<Object> findByCategoryOrName(@PathVariable("param") String param) {
        return this.productService.findByCategoryOrName(param);
    }
}