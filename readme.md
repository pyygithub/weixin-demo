
流程：
第一次访问前端页面时，首先查询本地cookie中是否有openId，
如果有携带  该openId访问指定的页面即可。

如果没有就访问写好的服务器地址：authorize 来获取openId，
存入cookie

url：http://xxx.xxx/wechat/authorize?returnUrl=前端项目地址


 