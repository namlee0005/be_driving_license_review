package com.nam_dev.driving_license_review.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private Integer pageIndex;
    private Integer length;
    private Integer totalPage;
    private T data;
}
