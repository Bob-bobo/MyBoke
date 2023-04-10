package com.dzb.myboke.Service;

import com.dzb.myboke.Utils.DataMap;
import com.dzb.myboke.VO.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 23:22
 */

public interface UserService {

    /**
     * 通过手机号查询用户
     * @param phone
     * @return
     */
    User findUserByPhone(String phone);

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    boolean usernameIsExist(String username);

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Transactional
    DataMap insert(User user);

    /**
     * 通过手机号查询用户是否存在
     * @param phone
     * @return
     */
    boolean userIsExist(String phone);
}
