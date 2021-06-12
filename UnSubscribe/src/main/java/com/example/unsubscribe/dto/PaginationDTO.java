package com.example.unsubscribe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PaginationDTO<T> {
    private Boolean hasNext;
    private Boolean hasPrevious;
    private Integer pageCount;
    private Integer page;
    private Long count;
    private List<T> items;
}
