package com.example.shiro.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Description TODO
 * @Author Ricky
 * @Date 2022/3/24 0:12
 */

@Data
@Builder
public class UserDto {

    Long id;

    String userName;

    String password;
}
