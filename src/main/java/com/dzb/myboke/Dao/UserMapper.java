package com.dzb.myboke.Dao;

import com.dzb.myboke.VO.Role;
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
            @Result(column = "role", property = "roles", javaType = List.class,
                    many = @Many(select = "com.dzb.myboke.Dao.UserMapper.getRoleNameByPhone"))
    })
    User getUserAndRoleByPhone(@Param("phone") String phone);

    @Select("select r.* from user_tab u left join user_role_tab ur on u.id = ur.user_id left join role_tab r on" +
            " ur.role_id = r.id where phone = #{phone}")
    Role getRoleNameByPhone(@Param("phone") String phone);

    @Select("select r.* from user_tab u left join user_role_tab ur on u.id = ur.user_id left join role_tab r on" +
            " ur.role_id = r.id where email = #{email}")
    Role getRoleNameByEmail(@Param("email") String email);

    @Select("select r.* from user_tab u left join user_role_tab ur on u.id = ur.user_id left join role_tab r on" +
            " ur.role_id = r.id where username = #{username}")
    Role getRoleNameByUsername(@Param("username") String username);

    @Select("select * from user_tab where phone=#{phone}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phone", property = "roles", javaType = List.class,
                    many = @Many(select = "com.dzb.myboke.Dao.UserMapper.getRoleNameByPhone"))
    })
    User getUserByPhone(@Param("phone") String phone);

    @Select("select * from user_tab where email=#{email}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "roles", javaType = List.class,
                    many = @Many(select = "com.dzb.myboke.Dao.UserMapper.getRoleNameByEmail"))
    })
    User getUserByEmail(@Param("email") String email);

    @Select("select * from user_tab where username=#{username}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "username", property = "roles", javaType = List.class,
                    many = @Many(select = "com.dzb.myboke.Dao.UserMapper.getRoleNameByUsername"))
    })
    User getUserByUsername(@Param("username") String username);

    @Insert("insert into user_tab(phone, username, email, password) values(#{phone}, #{username}, #{email}, #{password})")
    void saveUser(User user);

    @Select("select id from user_tab where phone = #{phone}")
    int findIdByPhone(@Param("phone") String phone);

    @Insert("insert into user_role_tab(user_id, role_id) values(#{arg0}, #{arg1})")
    void insertRole(@Param(("user_id")) int user_id, @Param("role_id") int domesticUser);

    @Update("update user_tab set recent_login = #{arg1} where phone = #{arg0}")
    void updateRecentLogin(@Param("phone") String phone, @Param("recent_login") long recent_login);

    @Update("update user_tab set password = #{arg0} where phone = #{arg1}")
    void updatePassword(@Param("password") String newPassword, @Param("phone") String phone);
}
