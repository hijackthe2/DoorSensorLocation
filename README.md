# Door Sensor Location
use door sensor for location

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
