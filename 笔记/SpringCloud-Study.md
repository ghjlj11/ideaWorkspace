# SpringCloud-Study



## SpringCloud与SpringBoot的关系 

- SpringBoot是用来构建微服务的， SpringCloud是用来协调微服务的。SpringBoot把程序打成一个个的jar包在SpringCloud上运行。
- SpringCloud是关注于全局的微服务协调整理治理框架， 把SpringBoot的一个个单体服务整合到一起管理， 为各个服务之间提供： 配置管理， 服务发现， 断路器， 路由， 微代理， 事件总线， 全局锁， 决策竞选， 分布式会话等等集成服务。
- SpringBoot可以离开SpringCloud独立使用，但是SpringCloud离不开SpringBoot。
- SpringBoot关注各个单体开发 ， SpringCloud专注于全局的服务治理框架。
