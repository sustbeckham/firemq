package com.mozilla.fire.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 将组件做到可配置化
 *
 * Created by mozilla on 2017/12/11.
 */
public class BeanConfig {

    private static final String FILE_NAME = "bean-config.properties";

    private static final String CLASS_PREFIX = "Default";

    public static void main(String[] args) {
        System.out.println(getInstance(Cloneable.class));
    }

    /**
     * 根据指定Class返回实际要使用的策略类
     *
     * @param clazz
     * @return
     */
    public static <T> T getInstance(Class<T> clazz){

        // 1. 防御性校验
        if(clazz == null){
            return null;
        }

        // 2. 尝试加载bean-config.properties文件
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File(System.getProperty("user.dir"), FILE_NAME)));
        }catch (Exception e){
            // logger
        }

        // 3. 尝试在properties中加载指定key对应的实现
        String name = clazz.getCanonicalName();
        if(p.size() > 0){
            String strategyClass = (String)p.get(name);
            if(strategyClass != null && !strategyClass.equals("")){
                try {
                    return (T)Class.forName(strategyClass).newInstance();
                }catch (Exception e){
                    // logger
                }
            }
        }

        // 4. 配置错误或者是发生异常。按照接口约定名称去查找对应实现
        //    如接口名为com.mozilla.fire.HelloService
        //    则默认回去加载com.mozilla.fire.impl.DefaultHelloService
        String defaultStrategyClass = null;
        int lastIndex = name.lastIndexOf(".");
        if(lastIndex == -1){
            defaultStrategyClass = "impl." + CLASS_PREFIX + name;
        }else{
            defaultStrategyClass = name.substring(0, lastIndex) + ".impl." + CLASS_PREFIX + name.substring(lastIndex + 1);
        }

        try {
            return (T)Class.forName(defaultStrategyClass).newInstance();
        }catch (Exception e){
            throw new IllegalArgumentException("未能找到匹配的Bean实现. name=" + name, e);
        }
    }
}













