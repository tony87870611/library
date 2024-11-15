圖書借書還書系統side project

希望能讓您更理解我的寫程式思路或架構
全專案採用SpringMVC+MyBatis的SpringBoot專案
其中有使用事務樂觀索及分布式鎖
在Docker上部屬Redis在本地的Port:6379

dubbodemo專案下
library-client為客戶端
library-api為街口端
library為後端服務

接口有提供
1.創建用戶
2.編輯用戶
3.條件查詢用戶
4.查詢用戶細節(利用userId)

5.創建書籍
6.編輯書籍
7.條件查詢書籍

8.借書 新增一筆借書流水
9.還書 新增一筆還書流水
