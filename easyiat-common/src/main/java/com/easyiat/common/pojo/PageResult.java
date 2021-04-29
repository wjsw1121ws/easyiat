package com.easyiat.common.pojo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -6275609896230768749L;
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private Integer pages;
    private List<T> rowList;

    public PageResult(PageInfo<T> pageInfo){
        this.pageNum=pageInfo.getPageNum();
        this.pageSize=pageInfo.getPageSize();
        this.total=pageInfo.getTotal();
        this.pages=pageInfo.getPages();
        this.rowList= pageInfo.getList();
    }

}
