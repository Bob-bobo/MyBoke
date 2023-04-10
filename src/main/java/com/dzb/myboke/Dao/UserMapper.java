package com.dzb.myboke.Dao;

import com.dzb.myboke.VO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 23:25
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user_tab where phone=#{phone}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "role", property = "role", javaType = List.class,
                    many = @Many(select = "com.dzb.myboke.Dao.UserMapper.getRoleNameByPhone"))
    })
    User getUserAndRoleByPhone(@Param("phone") String phone);

    @Select("select r.role from user_tab u left join user_role_tab ur on u.id = ur.user_id left join role_tab r on" +
            " ur.role_id = r.id where phone = #{phone}")
    User getRoleNameByPhone(@Param("phone") String phone);

    @Select("select * from user_tab u where u.phone = #{phone}")
    User getUserByPhone(@Param("phone") String phone);

    @Insert("insert into user_tab(phone, username, password) values(#{phone}, #{username}, #{password})")
    void save(User user);

    @Select("select id from user_tab where phone = #{phone}")
    int findIdByPhone(@Param("phone") String phone);

    @Insert("insert into user_role_tab(user_id,role_id) values(#{user_id}, #{role_id})")
    void insertRole(int userId, int domesticUser);
}
