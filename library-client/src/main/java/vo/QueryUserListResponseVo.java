package vo;

import lombok.Data;

import java.util.List;

@Data
public class QueryUserListResponseVo {

    private List<UserVo> users;
    /**
     * 響應代碼
     */
    private String code;
    /**
     * 響應信息
     */
    private String message;
}
