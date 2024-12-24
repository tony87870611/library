package vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVo {

    /**
     * 用戶編號
     */
    private String userId;
    /**
     * 帳戶名
     */
    private String accountName;
    /**
     * 密碼
     */
    private String password;
    /**
     * 帳號狀態
     */
    private Integer status;
    /**
     * 租借的書名
     */
    private List<String> bookName;
    /**
     * 最後創建時間
     */
    private String createTime;
    /**
     * 最後更新時間
     */
    private String updateTime;
}
