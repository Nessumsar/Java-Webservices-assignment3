package com.ecutb.web.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = -5884745313195801967L;

    @Id
    private String username;
    private String password;
    private List<String> acl;
}
