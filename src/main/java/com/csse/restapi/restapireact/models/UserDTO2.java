package com.csse.restapi.restapireact.models;


import com.csse.restapi.restapireact.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO2 implements Serializable {

    private Long id;
    private String email;
    private String fullName;
    private String old;
    private String neww;
    private String renew;

}
