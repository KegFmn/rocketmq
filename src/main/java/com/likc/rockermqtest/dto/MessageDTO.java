package com.likc.rockermqtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author likc
 * @date 2022/7/14
 * @description
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO implements Serializable {

    Integer id;

    String msg;
}
