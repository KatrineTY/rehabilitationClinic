package com.javaschool.services.interfaces;

import com.javaschool.entities.Role;

import java.util.List;

public interface RoleService {

    /**
     * Retrieve roles
     *
     * @return list of role names
     */
    List<Role> getRoles();

    /**
     * Retrieve role by id
     *
     * @param id - the specified id
     * @return the specified role
     */
    Role getRole(int id);

}
