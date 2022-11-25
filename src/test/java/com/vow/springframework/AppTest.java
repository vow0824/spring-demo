package com.vow.springframework;

import cn.hutool.core.io.IoUtil;
import com.vow.springframework.bean.UserDao;
import com.vow.springframework.bean.UserService;
import com.vow.springframework.beans.PropertyValue;
import com.vow.springframework.beans.PropertyValues;
import com.vow.springframework.beans.factory.BeanFactory;
import com.vow.springframework.beans.factory.config.BeanDefinition;
import com.vow.springframework.beans.factory.config.BeanReference;
import com.vow.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.vow.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.vow.springframework.core.io.DefaultResourceLoader;
import com.vow.springframework.core.io.Resource;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:32
 */
public class AppTest {


    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.UserDao注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3.UserService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "103"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4.userService 注入bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        // 5.获取userService单例对象
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/vow0824/spring-demo/blob/main/src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }

    @Test
    public void test_xml() {
        // 1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.读取xml文件注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3.获取bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }



    /*@Test
    public void test_BeanFactory() {

        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "vow");
        userService.queryUserInfo();

        // 4.第二次获取bean
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object object = enhancer.create(new Class[]{String.class}, new Object[]{"vow"});
        System.out.println(object);
    }

    @Test
    public void test_newInstance() throws InstantiationException, IllegalAccessException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_parameterTypes() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        Constructor constructor = null;
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (declaredConstructor.getParameterTypes().length == 1) {
                constructor = declaredConstructor;
                break;
            }
        }
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("vow");
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("vow");
        System.out.println(userService);
    }*/
}
