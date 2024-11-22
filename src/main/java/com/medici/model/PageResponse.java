package com.medici.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T>{

    private int page;
    private int rows;
    private long total;
    private List<T> payload;

}
