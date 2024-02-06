package com.gulimall.common.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

public class CodeGeneration {
    public static void main(String[] args) {
        CodeGeneration.Generation("gulimall-wms",
                "com.atguigu.gulimall.ware",
                "C:\\Users\\Administrator\\IdeaProjects\\gulimall\\gulimall-modules\\gulimall-product\\test",
                "wms_purchase_inflow");
    }
    /**
     * 根据表名生成相应结构代码
     * @param databaseName 数据库名
     * @param outputModulePath 输出模块根目录路径
     * @param packName  包名
     * @param tableName 表名
     */
    public static void Generation(String databaseName,String packName,String outputModulePath, String... tableName){
        FastAutoGenerator.create("jdbc:mysql://localhost:3307/"+databaseName+"?&useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai","root","wjj520138")
                .globalConfig(builder -> {
                    builder.author("junjie")
                            //启用swagger
                            .enableSwagger()
                            //指定输出目录
                            .outputDir(outputModulePath+"/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.entity("entity")//实体类包名
                            .parent(packName)//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                            .controller("controller")//控制层包名
                            .mapper("mapper")//mapper层包名
                            //.other("dto")//生成dto目录 可不用
                            .service("service")//service层包名
                            .serviceImpl("service.impl")//service实现类包名
                            //自定义mapper.xml文件输出目录
                            .pathInfo(Collections.singletonMap(OutputFile.xml,outputModulePath+"/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    //设置要生成的表名
                    builder.addInclude(tableName)
                            .addTablePrefix("wms_")//设置表前缀过滤
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .naming(NamingStrategy.underline_to_camel)//数据表映射实体命名策略：默认下划线转驼峰underline_to_camel
                            .columnNaming(NamingStrategy.underline_to_camel)//表字段映射实体属性命名规则：默认null，不指定按照naming执行
                            .idType(IdType.AUTO)//添加全局主键类型
                            .formatFileName("%s")//格式化实体名称，%s取消首字母I,
                            .mapperBuilder()
                            .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                            .enableBaseColumnList()//启用xml文件中的BaseColumnList
                            .formatMapperFileName("%sMapper")//格式化Dao类名称
                            .formatXmlFileName("%sMapper")//格式化xml文件名称
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
                            .controllerBuilder()
                            .superClass("BaseController")
                            .enableRestStyle();
                })
//                .injectionConfig(consumer -> {
//            Map<String, String> customFile = new HashMap<>();
//            // 配置DTO（需要的话）但是需要有能配置Dto的模板引擎，比如freemarker，但是这里我们用的VelocityEngine，因此不多作介绍
//            customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
//            consumer.customFile(customFile);
//           })
                .execute();
    }
}
