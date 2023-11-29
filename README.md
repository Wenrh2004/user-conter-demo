# Spring Boot 项目实战 —— 用户中心

> # 项目流程
>
> 1. 需求分析
> 2. 功能设计
> 3. 技术选型
> 4. 项目框架构建
> 5. 业务逻辑实现
> 6. 功能验证(单元测试)
> 7. 代码评审
> 8. 部署验证
> 9. 正式发布

## 需求分析

- 用户登陆注册
- 用户管理
- 用户权限

## 功能设计

- 用户信息的增删改查 ==P0==
    - 新增用户
    - 删除用户
    - 查询用户信息
    - 更改用户信息
- 用户权限校验 ==P1==
    - 0 - admin
    - 1 - user
- 用户登陆注册 ==P2==

## 技术选型

- 前端

- 后端

    - Java

    - Spring

      > 依赖注入框架

    - SpringMVC

      > Web 框架，提供接口访问能力、Restful API接口等能力

    - Spring Boot

      > Spring 项目快速启动/快速集成框架

    - Mybaties

      > 持久化框架 *对`JDBC`的封装*

    - Mybaties-plus

      > `Mybties`框架的加强

    - Mysql

- 部署



## Spring 依赖项

- Spring Web

  > Web 服务框架
  > 基于这个依赖我们可以快速搭建 Web 服务

- Spring Boot DevTools

  > Spring 官方提供的一个开发依赖
  >
  > 实现了Spring项目的热加载

- Spring Configuration Processor

  > Spring 官方提供的配置读取依赖
  >
  > 读取 Ymal、XML 这种配置文件

- MySQL Driver

  > MySQL DataBase 的驱动依赖

- Lombok

  > 基于注释生成方法

## 引入 Mybyties-plus
1. 导入依赖
  ```xml
<!--pom.xml-->
  <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter-test</artifactId>
    <version>3.5.4.1</version>
  </dependency>
  ```
2. 创建数据库
```sql
# Create a new Database for demo
CREATE DATABASE IF NOT EXISTS user_center_demo;

# Use the database
USE user_center_demo;

# Mybyties-plus test
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

DELETE FROM `user`;

INSERT INTO `user` (id, name, age, email) VALUES
                                              (1, 'Jone', 18, 'test1@baomidou.com'),
                                              (2, 'Jack', 20, 'test2@baomidou.com'),
                                              (3, 'Tom', 28, 'test3@baomidou.com'),
                                              (4, 'Sandy', 21, 'test4@baomidou.com'),
                                              (5, 'Billie', 24, 'test5@baomidou.com');
```
3. 创建 Model.User
```java
@Data
@TableName(value = "user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```
4. 创建 Mapper.UserMapper
```java
public interface UserMapper extends BaseMapper<User> {

}
```
5. 在 UserConterDemoApplication 中引入 `@MapperScan("com.qitsoftwarestudio.userconterdemo.mapper")`
6. 创建 test 文件
```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }

}
```
> 输出示例
> ```shell
> ----- selectAll method test ------
>2023-11-29T20:10:07.741+08:00  INFO 32573 --- [user-center-demo] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
>2023-11-29T20:10:07.982+08:00  INFO 32573 --- [user-center-demo] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@1a01ffff
>2023-11-29T20:10:07.983+08:00  INFO 32573 --- [user-center-demo] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
>User(id=1, name=Jone, age=18, email=test1@baomidou.com)
>User(id=2, name=Jack, age=20, email=test2@baomidou.com)
>User(id=3, name=Tom, age=28, email=test3@baomidou.com)
>User(id=4, name=Sandy, age=21, email=test4@baomidou.com)
>User(id=5, name=Billie, age=24, email=test5@baomidou.com)
>
> ```
## 用户注册业务逻辑
用户账号、密码、校验密码
1. 创建 `user` 表的 ORM 模型 --> `model.user`
2. 创建 `user register` 的 request 模型
3. 实现用户注册流程
   > - 用户注册的抽象方法 --> `service.UserService` interface(对于方法的规范)
   > - 用户注册方法实现 --> `service.impl.userServiceImpl` class(对于方法的实现)
4. 创建统一相应方法

11.29 小组任务
> 1. 实现用户注册业务逻辑中的 1. 2. 3.
> 2. 思考 4. 的实现
> 3. 创建仓库，代码统一提交至仓库管理