package com.easyiat.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class PageInfoVo {
    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1,message = "当前页不能小于1")
    private Integer pageNum;

    /**
     * 每页条数
     */
    @NotNull(message = "分页条数不能为空")
    @Min(value = 1, message = "分页条数不能小于1")
    private Integer pageSize;

}
