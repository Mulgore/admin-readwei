package com.readwei.common;

import com.baomidou.kisso.common.util.EnvUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 
 * 自动生成映射工具类
 * 
 * @author hubin
 *
 */
public class AutoGeneratorHelper {

	/**
	 * <p>
	 * 测试 run 执行
	 * </p>
	 * <p>
	 * 更多使用查看
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		if (EnvUtil.isLinux()) {
			gc.setOutputDir(System.getProperty("user.home") + "/autoCode/admin/");
		} else {
			gc.setOutputDir("D:/autoCode/admin/");
		}
		gc.setFileOverride(true);
//		gc.setActiveRecord(true);// 开启 activeRecord 模式
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(false);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor("xingwu");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert());
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("aa11*bb22!");
		dsc.setUrl("jdbc:mysql://123.56.42.127:3306/readwei?characterEncoding=utf8");
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
//		strategy.setNaming(NamingStrategy.remove_prefix_and_camel);// 表名生成策略
		// 字段名生成策略
//		strategy.setFieldNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperServiceImplClass("com.readwei.service.support.BaseServiceImpl");
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
//		pc.setModuleName("test");
		pc.setParent("com.readwei");// 自定义包路径
		pc.setController("controller");// 这里是控制器包名，默认 web
		mpg.setPackageInfo(pc);
		// 执行生成
		mpg.execute();
	}

}
