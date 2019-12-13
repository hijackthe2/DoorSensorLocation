# Door Sensor Location
use door sensor for location

## 整体框架
pojo: 专指只有 setter / getter / toString 的简单类，包括 DO/DTO/BO/VO 等

manager: 通用业务处理层，它有如下特征：

    1） 对第三方平台封装的层，预处理返回结果及转化异常信息；
    
    2） 对 Service 层通用能力的下沉，如缓存方案、中间件通用处理；
    
    3） 与 DAO 层交互，对多个 DAO 的组合复用

service: 相对具体的业务逻辑服务层

web: 主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等

搭建：maven

框架：spring boot

数据库：spring data jpa

    表关系：一对多、多对一
           
           不推荐使用类似注解：@OneToMany、@ManyToOne
            
           减少数据库对外键约束的使用，外键约束判断应在业务逻辑层中实现
    
    查询：分页
    
策略模式：

    自定义注解:
    
        为实现同一个接口的类定义一个id，使用getBeansWithAnnotation获取使用了该注解的类的实例。
    
        以注解的值作为键，bean实例作为对象，生成map。注解的值默认为类名。
    
    
    使用@Autowired注解Collection:
    
        Autowired当使用在Collection里时，会将所申明类的所有实现类都放在指定的Collection里。
    
        如果Autowired和map使用的话，它默认将bean名称作为key，bean实例作为value。
    
        如果该实现类被@Component注解时，注解中的value值将作为key，默认情况下为类名。
        
微信签名算法

传递给前端的数据类型应为一个对象，即DTO

跨域