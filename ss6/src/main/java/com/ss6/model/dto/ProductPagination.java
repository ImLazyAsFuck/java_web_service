package com.ss6.model.dto;

import com.ss6.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPagination{
    private List<Product> products;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}
