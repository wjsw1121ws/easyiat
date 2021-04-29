package com.easyiat.framework.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
public class PageReq implements Serializable {

    private static final long serialVersionUID = 549010689129965788L;

    @NotBlank(message = "当前页不能为空")
    @Size(min = 1,message = "当前页不能小于1")
    private int pageNum;

    @NotBlank(message = "分页条数不能为空")
    @Size(min = 1,message = "分页条数不能小于1")
    private int pageSize;

}
