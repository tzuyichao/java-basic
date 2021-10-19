## 7entity的說明

1. headers設定
   
    這個API如果Header沒有設定"text/plain, application/json, application/*+json, */*"
    就會收到回傳的時候使用text/plain，但是是gbk編碼。

    如果使用feign的Headers annotation會發現沒有生效，因此改用PostMapping那邊的headers設定。

2. MessageConverter的目的

    因為此API回傳時<code>Content-Type</code>是text/plain。原本的Decoder會找不到對應的converter。
    因此解法之一使用Jackson是自訂一個MessageConverter繼承自<code>MappingJackson2HttpMessageConverter</code>，
    然後設定支援text/plain。
   
    接著就是要在spring context註冊一個feign <code>Decoder</code>，這個<code>Decoder</code>會產生我們自訂的
    <code>HttpMessageConverters</code>物件。
