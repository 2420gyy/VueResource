###1. Swagger 是一套基于 OpenAPI 规范（OpenAPI Specification，OAS）构建的开源工具
###2. Swagger 为我们提供了一套通过代码和注解自动生成文档的方法，这一点对于保证API 文档的及时性将有很大的帮助
###3. 组成:
   Swagger Editor：基于浏览器的编辑器，我们可以使用它编写我们 OpenAPI 规范。
   Swagger UI：它会将我们编写的 OpenAPI 规范呈现为交互式的 API 文档，后文我将使用浏览器来查看并且操作我们的 Rest API。
   Swagger Codegen：它可以通过为 OpenAPI（以前称为 Swagger）规范定义的任何 API 生成服务器存根和客户端 SDK 来简化构建过程。
 @Api：用在controller类，描述API接口
 @ApiOperation：描述接口方法
 @ApiModel：描述对象
 @ApiModelProperty：描述对象属性
 @ApiImplicitParams：描述接口参数
 @ApiResponses：描述接口响应
 @ApiIgnore：忽略接口方法
 
 ###4.