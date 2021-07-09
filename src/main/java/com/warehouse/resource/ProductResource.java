package com.warehouse.resource;

import com.warehouse.service.ProductService;
import com.warehouse.model.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("api/v1/products")
@Api(value="/api/v1/products")
public class ProductResource {

    private final ProductService productService;

    @Inject
    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Get all products by type or category",
            produces = APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The list of products"),
            @ApiResponse(code = 404, message = "Invalid type or category supplied ")
            })
    public Response getProductsByTypeOrCategory(
            @ApiParam(
                    value = "The type of product",
                    allowableValues = "FRUIT, VEGETABLE, BEAN, RICE, POULTRY, BEEF, PORK, LAMB"
            )
            @QueryParam("type") Type type,

            @ApiParam(
                    value = "The wider category of the product",
                    allowableValues = "FLESH, FOOD, GRAIN"
            )
            @QueryParam("category") String category) {
        return productService.getProducts(type, category);
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Get product by id",
            produces = APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The product with the specified id"),
            @ApiResponse(code = 404, message = "Invalid id supplied ")
    })
    public Response getFleshProductsById(@ApiParam(value = "The id of the product") @PathParam("id") int id) {
        return productService.getProductById(id);
    }

    @GET
    @Path("/weights")
    @Produces(APPLICATION_JSON)
    @ApiOperation(
            value = "Get the total weight for a type of products",
            produces = APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The type and its total weight"),
            @ApiResponse(code = 404, message = "Invalid type supplied")
    })
    public Response getWeights(
            @ApiParam(
                    value = "The type of product",
                    allowableValues = "FRUIT, VEGETABLE, BEAN, RICE, POULTRY, BEEF, PORK, LAMB"
            )
            @QueryParam("type") Type type) {
        return productService.calculateWeights(type);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content meaning successful deletion"),
            @ApiResponse(code = 404, message = "Invalid id supplied ")
    })
    public Response deleteProductbyId(@ApiParam(value = "The id of the product") @PathParam("id") int id) {
        return productService.deleteProductById(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update was successful"),
            @ApiResponse(code = 201, message = "A new product was added to the backed in case the supplied id was invalid")
    })
    public Response updateProductById(@ApiParam(value = "The id of the product") @PathParam("id") int id, Object object) {
        return productService.putProduct(id, object);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Add a new product to the list")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The product was added successfully"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Response postProduct(@ApiParam(value = "The product", required = true) Object object, @Context UriInfo uriInfo) {
          return productService.insertProduct(object, uriInfo);
    }
}
