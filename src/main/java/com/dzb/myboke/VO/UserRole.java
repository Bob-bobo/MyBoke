package com.dzb.myboke.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 23:45
 */
@Data
@NoArgsConstructor
public class UserRole {

    /**
     * 角色用户表Id
     */
    private Integer id;
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 角色Id
     */
    private Integer roleId;

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
