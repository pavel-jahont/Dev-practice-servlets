package com.gmail.jahont.pavel.app.service;

import java.io.Serializable;
import java.util.List;

import com.gmail.jahont.pavel.app.service.model.UserDTO;

public interface UserService {

    UserDTO add(UserDTO userDTO);

    List<UserDTO> findAll();

    void delete(Serializable id);

}
