package com.fresh.coding.schoolmanagementapi.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Paginate<T> implements Serializable {
    private T items;
    private PageInfo pageInfo;
}
