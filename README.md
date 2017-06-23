## 实现spring boot+spring security鉴权的一个实用DEMO

因为网上查到的大部分案例都过于简单，所以结合实际开发写下此demo

### 特色：
1. 以配置文件的形式管理权限
2. 实现登陆成功处理类
3. 自定义授权认证流程
4. 由Service类获取用户信息（网上大部分教程都是在内存中创建临时用户）

### 如何跑起来？
1. clone或下载源码，
2. 使用maven下载相关依赖
3. 编译后直接运行App.class