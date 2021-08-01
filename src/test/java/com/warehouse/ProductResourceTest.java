package com.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.warehouse.config.AppBinder;
import com.warehouse.model.Product;
import com.warehouse.model.Type;
import com.warehouse.model.WeightValue;
import com.warehouse.model.flesh.Lamb;
import com.warehouse.model.food.Vegetable;
import com.warehouse.repository.ProductRepository;
import com.warehouse.resource.ProductResource;
import com.warehouse.service.ProductService;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.junit.Assert.assertEquals;

public class ProductResourceTest {

    private static URI uri;
    private static Server server;
    private static Client client;
    private static WebTarget target;
    private static ProductRepository productRepository;
    private static ProductService productService;

    @BeforeClass
    public static void startHttpServer(){
        uri = UriBuilder.fromUri("http://localhost/").port(8000).build();

        ResourceConfig config = new ResourceConfig(ProductResource.class);
        config.register(new AppBinder());

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(WRITE_DATES_AS_TIMESTAMPS);
        config.register(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));

        server = JettyHttpContainerFactory.createServer(uri, config);

        productRepository = new ProductRepository();
        productRepository.init();

        productService = new ProductService(productRepository);

        client = ClientBuilder.newBuilder()
                .register(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS))
                .build();

        target = client.target(uri).path("api/v1");
    }

    @Test
    public void testGet(){
        List<Product> list = productRepository.getList();
        list.forEach(product -> assertEquals(product, target.path("products")
                .path(String.valueOf(list.indexOf(product)))
                .request(MediaType.APPLICATION_JSON)
                .get(product.getType().getCorrespondingClass())));
    }

    @Test
    public void testPut(){
        Product product = new Lamb(50.00, LocalDate.of(2022, 12, 31), new BigDecimal(500));

        int status = target.path("products").path("1")
                .request().put(Entity.entity(product, MediaType.APPLICATION_JSON))
                .getStatus();

        assertEquals(200, status);
    }

    @Test
    public void testDelete() {
        int status = target.path("products").path("3").request().delete().getStatus();

        assertEquals(204, status);
    }

    @Test
    public void testPost(){
        Product product = new Vegetable(50.00, LocalDate.of(2022, 12, 31), new BigDecimal(500), "green");

        int status = target.path("products")
                .request().post(Entity.entity(product, MediaType.APPLICATION_JSON))
                .getStatus();

        assertEquals(201, status);
    }

    @Test
    public void testWeightCalculator() {
        WeightValue lambTotalWeight = (WeightValue) productService.calculateWeights(Type.LAMB).getEntity();

        WeightValue weightValueFromServer = target.path("products").path("weights").queryParam("type", "LAMB")
                .request(MediaType.APPLICATION_JSON)
                .get(WeightValue.class);

        assertEquals(lambTotalWeight, weightValueFromServer);
    }

    @AfterClass
    public static void stopServer(){
        try {
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
