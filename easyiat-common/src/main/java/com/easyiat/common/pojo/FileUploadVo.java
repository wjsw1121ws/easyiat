package com.easyiat.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Data
public class FileUploadVo implements Serializable {
    private static final long serialVersionUID = -6510845588980603662L;

    private String name;
    private String filename;
    private String filepath;

    public static final String FILE_UPLOAD = "fileUpload";
    public static final String FILE_NAME = "filename";
}
