package pers.sunny.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-09-26-8:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code;
    private String msg;
}
