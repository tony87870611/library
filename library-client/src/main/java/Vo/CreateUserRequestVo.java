package Vo;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserRequestVo {

    @NotEmpty
    private String accountName;

    @NotEmpty
    private String password;
}
