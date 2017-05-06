package com.reawei.common;

/**
 * Created by xingwu on 2017/5/5.
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.baomidou.framework.exception.SpringWindException;
import com.baomidou.framework.velocity.RunEnvironment;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;
import org.springframework.util.StringValueResolver;

import com.baomidou.framework.common.SwConstants;

/**
 * <p>
 * velocity 模式加载 properties
 * </p>
 * <p>
 * 支持 properties 文件使用 velocity 标签控制，注入 VelocityContext 可定义标签内容。
 * </p>
 * @author hubin
 * @Date 2016-01-27
 */
public class VelocityPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Logger logger = LoggerFactory.getLogger(VelocityPropertyPlaceholderConfigurer.class);
    private String charset = SwConstants.UTF_8;

    private static VelocityContext velocityContext = null;

    private Resource[] locations;

    private Resource[] releaseLocations;

    private RunEnvironment runEnvironment;
    private String mode;

    /* 处理未找到占位内容 */
    private String placeholderValue = "";

    @Override
    public void setLocation(Resource location) {
        this.locations = new Resource[] { location };
    }

    @Override
    public void setLocations(Resource... locations) {
        this.locations = locations;
    }

    public void setMode(String mode) {
        this.mode = mode;
        System.setProperty("spring_runmode", mode);
    }

    public void setReleaseLocation(Resource location) {
        this.releaseLocations = new Resource[] { location };
    }

    public void setReleaseLocations(Resource... locations) {
        this.releaseLocations = locations;
    }


    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(props);
        doProcessProperties(beanFactoryToProcess, valueResolver);
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final PropertyPlaceholderHelper helper;

        private final PlaceholderResolver resolver;

        public PlaceholderResolvingStringValueResolver(Properties props) {
            this.helper = new PropertyPlaceholderHelper(placeholderPrefix, placeholderSuffix, valueSeparator,
                    ignoreUnresolvablePlaceholders);
            this.resolver = new PropertyPlaceholderConfigurerResolver(props);
        }

        @Override
        public String resolveStringValue(String strVal) throws BeansException {
            String value = this.helper.replacePlaceholders(strVal, this.resolver);
            if (value.contains(placeholderPrefix) && value.equals(strVal)) {
                return placeholderValue;
            }
            return (value.equals(nullValue) ? null : value);
        }
    }

    private class PropertyPlaceholderConfigurerResolver implements PlaceholderResolver {

        private final Properties props;

        private PropertyPlaceholderConfigurerResolver(Properties props) {
            this.props = props;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            return VelocityPropertyPlaceholderConfigurer.this.resolvePlaceholder(placeholderName, props,
                    SYSTEM_PROPERTIES_MODE_FALLBACK);
        }
    }

    public void fillMergeProperties(Properties prop, InputStream input) {
        try {
            StringWriter writer = new StringWriter();
            BufferedReader br = new BufferedReader(new InputStreamReader(input, getCharset()));
            if (velocityContext == null) {
				/*
				 * 设置环境变量判断逻辑
				 */
                Map<Object, Object> context = new HashMap<Object, Object>();
                context.put("env", this.getRunEnvironment());
                context.putAll(System.getProperties());
                velocityContext = new VelocityContext(context);
            }
            Velocity.evaluate(velocityContext, writer, "VelocityPropertyPlaceholderConfigurer", br);
            prop.load(new StringReader(writer.toString()));
        } catch (Exception e) {
            throw new SpringWindException(e);
        }
    }

    @Override
    protected void loadProperties(Properties props) throws IOException {
        if ((this.locations != null || this.releaseLocations != null) && props != null) {
            Resource[] locs = this.locations;
            if ("online".equals(mode) || "release".equals(mode)) {
                locs = this.releaseLocations;
            }
            for (Resource location : locs) {
                if (logger.isInfoEnabled()) {
                    logger.info("Loading properties file from " + location);
                }

                this.fillMergeProperties(props, location.getInputStream());
            }
        }
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is adirectory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does notexist");
        }
        return new FileInputStream(file);
    }

    public RunEnvironment getRunEnvironment() {
        if (runEnvironment == null) {
            return new RunEnvironment();
        }
        return runEnvironment;
    }

    public void setRunEnvironment(RunEnvironment runEnvironment) {
        this.runEnvironment = runEnvironment;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getPlaceholderValue() {
        return placeholderValue;
    }

    public void setPlaceholderValue(String placeholderValue) {
        this.placeholderValue = placeholderValue;
    }


}