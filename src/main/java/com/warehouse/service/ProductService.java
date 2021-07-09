package com.warehouse.service;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.exception.ExceptionMessage;
import com.warehouse.exception.ProductException;
import com.warehouse.model.Product;
import com.warehouse.model.Type;
import com.warehouse.model.WeightValue;
import com.warehouse.model.flesh.Flesh;
import com.warehouse.model.food.Food;
import com.warehouse.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.warehouse.model.Type.BEAN;
import static com.warehouse.model.Type.BEEF;
import static com.warehouse.model.Type.FRUIT;
import static com.warehouse.model.Type.LAMB;
import static com.warehouse.model.Type.PORK;
import static com.warehouse.model.Type.POULTRY;
import static com.warehouse.model.Type.RICE;
import static com.warehouse.model.Type.VEGETABLE;

public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private static Map<String, List<Type>> categories = new HashMap<>();

    static {
        categories.put("FLESH", Arrays.asList(POULTRY, BEEF, PORK, LAMB));
        categories.put("FOOD", Arrays.asList(FRUIT, VEGETABLE, BEAN, RICE));
        categories.put("GRAIN", Arrays.asList(BEAN, RICE));
    }

    @Inject
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Response getProducts(Type type, String category) {
        List<Product> list = productRepository.getList();

        if(type != null) {
            list =  list.stream()
                    .filter((product) -> product.getType().equals(type))
                    .collect(Collectors.toList());
        }

        if(category != null) {
            list =  list.stream()
                    .filter((product) -> categories.get(category).contains(product.getType()))
                    .collect(Collectors.toList());
        }

        logger.info("Retrieving list of products");

        return Response
                .ok(list)
                .cacheControl(buildCacheControl())
                .build();
    }

    public Response getProductById(int id) throws ProductException {
        if(id >= productRepository.getList().size())
            throw new ProductException(new ExceptionMessage(id,"Product with id " + id + " was not found"));

        Product product = productRepository.getList().get(id);

        logger.info("Retrieving product with id " + id);

        return Response
                .ok(product)
                .cacheControl(buildCacheControl())
                .build();
    }

    public Response deleteProductById(int id) throws ProductException {
        if(id >= productRepository.getList().size())
            throw new ProductException(new ExceptionMessage(id,"Product with id " + id + " was not found"));

        logger.info("Deleting product with id " + id);

        productRepository.deleteProductById(id);

        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    public Response insertProduct(Object object, UriInfo uriInfo) {
        Product product = mapToProduct(object);

        logger.info("Creating new product");

        productRepository.insertProduct(product);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(productRepository.getList().indexOf(product)))
                .build();

        return Response
                .status(Response.Status.CREATED)
                .location(uri)
                .build();
    }

    public Response putProduct(int id, Object object) {
        Product product = mapToProduct(object);

        if(id < productRepository.getList().size()) {
            logger.info("Updating product with id " + id);

            productRepository.replaceProduct(id, product);

            return Response
                    .status(Response.Status.OK)
                    .build();
        } else {
            logger.info("Creating new product");

            productRepository.insertProduct(product);

            return Response
                    .status(Response.Status.CREATED)
                    .build();
        }
    }

    public Response calculateWeights(Type type) {
        List<Product> products = productRepository.getList();

        logger.info("Caculating total weight for the type " + type);

        double weights = products.stream().filter(product -> product.getType().equals(type)).mapToDouble(product -> {
            if(categories.get("FLESH").contains(type))
                return ((Flesh) product).getWeight();
            else
                return ((Food) product).getWeight();

        }).sum();

        WeightValue weightValue = new WeightValue(type, weights);

        return Response.ok(weightValue)
                        .cacheControl(buildCacheControl())
                        .build();
    }

    @SuppressWarnings("unchecked")
    private Product mapToProduct(Object object) {
        Type type = Type.valueOf((String)((LinkedHashMap<String, Object>)object).get("type"));

        ObjectMapper objectMapper = new ObjectMapper();

        return (Product) Arrays.stream(Type.values())
                .filter(t -> t.equals(type))
                .map((Function<Type, Object>) type1 -> {
                    try {
                        return objectMapper.readValue(objectMapper.writeValueAsBytes(object),
                                type.getCorrespondingClass());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList()).get(0);
    }

    private CacheControl buildCacheControl(){
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(300);

        return cacheControl;
    }
}
