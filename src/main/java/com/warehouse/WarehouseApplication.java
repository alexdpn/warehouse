package com.warehouse;

import javax.ws.rs.core.UriBuilder;

import com.warehouse.config.SwaggerConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.warehouse.config.AppBinder;
import com.warehouse.resource.ProductResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class WarehouseApplication {

    public  static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(WRITE_DATES_AS_TIMESTAMPS);

        URI uri = UriBuilder.fromUri("http://localhost").port(8081).build();
        ResourceConfig resourceConfig = new ResourceConfig(ProductResource.class);
        resourceConfig.register(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS))
                        .register(new AppBinder())
                        .register(ApiListingResource.class)
                        .register(SwaggerSerializers.class);

        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
        swaggerConfiguration.configureSwagger();

        Server server = JettyHttpContainerFactory.createServer(uri, resourceConfig);    }
}
