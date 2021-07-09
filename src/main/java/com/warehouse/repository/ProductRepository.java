package com.warehouse.repository;

import com.warehouse.model.Product;
import com.warehouse.model.flesh.Lamb;
import com.warehouse.model.flesh.Pork;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductRepository {
    private List<Product> list;

    @PostConstruct
    public void init() {
        this.list = new CopyOnWriteArrayList<>(Arrays.asList(
                new Lamb(20.00, LocalDate.of(2022, 12, 31), new BigDecimal(400)),
                new Lamb(30.00, LocalDate.of(2022, 12, 31), new BigDecimal(500)),
                new Pork(22.00, LocalDate.of(2022, 12, 31), new BigDecimal(450)),
                new Pork(30.00, LocalDate.of(2022, 12, 31), new BigDecimal(600)))
        );
    }

    public List<Product> getList() {
        return new ArrayList<>(list);
    }

    public void deleteProductById(int id) {
        this.list.remove(id);
    }

    public void insertProduct(Product product) {
        list.add(product);
    }

    public void replaceProduct(int id, Product product) {
        list.set(id, product);
    }
}
