package Vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateUserRequestVo {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String accountName;

    @NotEmpty
    private String password;
}
