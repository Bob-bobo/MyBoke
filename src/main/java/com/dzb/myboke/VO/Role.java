package com.dzb.myboke.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 23:08
 */
@Data
@NoArgsConstructor
public class Role {
    /**
     * 角色Id
     */
    private Integer id;
    /**
     * 角色而
     */
    private String role;
    /**
     * 描述
     */
    private String describe;

    public Role(Integer id, String role, String describe) {
        this.id = id;
        this.role = role;
        this.describe = describe;
    }
}
