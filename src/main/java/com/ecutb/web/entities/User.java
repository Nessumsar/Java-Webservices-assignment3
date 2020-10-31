package com.ecutb.web.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class User {

    @Id
    private String username;
    private String password;
    private List<String> acl;
}
