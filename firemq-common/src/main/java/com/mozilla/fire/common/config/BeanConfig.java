package com.mozilla.fire.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * ��������������û�
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
     * ����ָ��Class����ʵ��Ҫʹ�õĲ�����
     *
     * @param clazz
     * @return
     */
    public static <T> T getInstance(Class<T> clazz){

        // 1. ������У��
        if(clazz == null){
            return null;
        }

        // 2. ���Լ���bean-config.properties�ļ�
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File(System.getProperty("user.dir"), FILE_NAME)));
        }catch (Exception e){
            // logger
        }

        // 3. ������properties�м���ָ��key��Ӧ��ʵ��
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

        // 4. ���ô�������Ƿ����쳣�����սӿ�Լ������ȥ���Ҷ�Ӧʵ��
        //    ��ӿ���Ϊcom.mozilla.fire.HelloService
        //    ��Ĭ�ϻ�ȥ����com.mozilla.fire.impl.DefaultHelloService
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
            throw new IllegalArgumentException("δ���ҵ�ƥ���Beanʵ��. name=" + name, e);
        }
    }
}













