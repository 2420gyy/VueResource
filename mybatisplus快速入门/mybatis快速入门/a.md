idea快速展开和折叠全部方法的快捷键. 
ctrl+shift+ (+/-)展开全部折叠全部. ctrl+（+/-） 展开折叠当前方法.
向下复制一行：c + a + 箭头下

1.mybatis原理,创建过程

2.配置文件的引用
3.接口和实现类，注入
4.xml的各种传参和返回类型
    map,set,list
5.多表一对多，多对多
6.动态SQL
7.分页

1.更改数据库的自动填充 update_time 需要勾选自动填充时间戳 默认:CURRENT_TIMESTAMP

2.自动填充：继承implements MetaObjectHandler
实现-》//String fieldName, Object fieldVal, MetaObject metaObject
        this.setFieldValByName("createTime",new Date(),metaObject);