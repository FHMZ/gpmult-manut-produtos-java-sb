package br.com.grupomult.gpmultmanutprodutosbe.util;

import br.com.grupomult.gpmultmanutprodutosbe.enums.CategoriesEnum;
import br.com.grupomult.gpmultmanutprodutosbe.enums.CurrencyEnum;
import br.com.grupomult.gpmultmanutprodutosbe.model.Product;
import br.com.grupomult.gpmultmanutprodutosbe.model.ProductDTO;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProductUtil {

    //final static DecimalFormat currencyFormatter = new DecimalFormat("###.###.###,###,00");
    //final static DecimalFormat decimalFormatter = new DecimalFormat("##.00");

    public static boolean isNull(Object value) {
        return value == null || "".equals(value) || "".trim().equals(value);
    }

    public static List<ProductDTO> toDTOList(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product item : productList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(item.getId());
            productDTO.setName(item.getName());
            if ("PERECÍVEL".equals(item.getCategory())) {
                productDTO.setCategoryCode(CategoriesEnum.PERECIVEL.getId().toString());
            } else {
                productDTO.setCategoryCode(CategoriesEnum.NAO_PERECIVEL.getId().toString());
            }
            productDTO.setValue(CurrencyEnum.REAL.format(item.getValue()));
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    public static Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        if ("1".equals(productDTO.getCategoryCode())) {
            product.setCategory(CategoriesEnum.PERECIVEL.getDescription());
        } else {
            product.setCategory(CategoriesEnum.NAO_PERECIVEL.getDescription());
        }
        final String stringNumber = CurrencyEnum.DECIMAL.format(product.getValue());
        final BigDecimal bigDecimalNumber = new BigDecimal(stringNumber);
        product.setValue(bigDecimalNumber);
        return product;
    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        if ("PERECÍVEL".equals(product.getCategory())) {
            productDTO.setCategoryCode(CategoriesEnum.PERECIVEL.getId().toString());
        } else {
            productDTO.setCategoryCode(CategoriesEnum.NAO_PERECIVEL.getId().toString());
        }
        productDTO.setValue(CurrencyEnum.REAL.format(product.getValue()));
        return productDTO;
    }

}
