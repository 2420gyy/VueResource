1. springboot默认不支持jsp模板视图 
加入依赖-配置视图解析器-工作环境$MODULE_WORKING_DIR$
controller层使用@controller注解
service采用@service注解
dao层使用@repository注解
2. idea包结构.和/
3.${pageContext.request.contextPath} 用于解决使用相对路径时出现的问题，它的作用是取出所部署项目的名字。 
在JavaWeb项目中， Jsp 页面的form表单的action属性也常常会使用 ${pageContext.request.contextPath} 来表示请求路径。