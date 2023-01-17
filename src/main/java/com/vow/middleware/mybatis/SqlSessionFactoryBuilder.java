package com.vow.middleware.mybatis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(Reader reader) {
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(new InputSource(reader));
            Configuration configuration = parseConfiguration(document.getRootElement());
            return new DefaultSqlSessionFactory(configuration);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DefaultSqlSessionFactory build(InputStream inputStream) {
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(inputStream);
            Configuration configuration = parseConfiguration(document.getRootElement());
            return new DefaultSqlSessionFactory(configuration);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Configuration parseConfiguration(Element root) {
        Configuration configuration = new Configuration();
        configuration.setDataSource(parseDataSource(root.element("environments").element("environment").element("dataSource")));
        configuration.setConnection(parseConnection(configuration.dataSource));
        configuration.setMapperElement(parseMapperElement(root.element("mappers")));
        return configuration;
    }

    public Map<String, String> parseDataSource(Element element) {
        Map<String, String> datasource = new HashMap<>();
        List<Element> properties = element.elements("property");
        for (Element e : properties) {
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            datasource.put(name, value);
        }
        return datasource;
    }

    private Connection parseConnection(Map<String, String> dataSource) {
        String driver = dataSource.get("driver");
        try {
            Class.forName(driver);
            return DriverManager.getConnection(dataSource.get("url"), dataSource.get("username"), dataSource.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, XNode> parseMapperElement(Element mappers) {
        Map<String, XNode> map = new HashMap<>();

        List<Element> mapperList = mappers.elements("mapper");
        for (Element e : mapperList) {
            String resource = e.attributeValue("resource");

            SAXReader saxReader = new SAXReader();
            try {
                Document document = saxReader.read(new InputSource(Resources.getResourceAsReader(resource)));
                Element root = document.getRootElement();
                // 命名空间
                String namespace = root.attributeValue("namespace");

                // select
                List<Element> selectNodes = root.elements("select");
                for (Element node : selectNodes) {
                    String id = node.attributeValue("id");
                    String parameterType = node.attributeValue("parameterType");
                    String resultType = node.attributeValue("resultType");
                    String sql = node.getText();

                    // 匹配
                    Map<Integer, String> parameter = new HashMap<>();
                    Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                    Matcher matcher = pattern.matcher(sql);
                    for (int i = 1; matcher.find(); i++) {
                        String g1 = matcher.group(1);
                        String g2 = matcher.group(2);
                        parameter.put(i, g2);
                        sql = sql.replace(g1, "?");
                    }

                    XNode xNode = new XNode();
                    xNode.setNamespace(namespace);
                    xNode.setId(id);
                    xNode.setParameterType(parameterType);
                    xNode.setResultType(resultType);
                    xNode.setSql(sql);
                    xNode.setParameter(parameter);

                    map.put(namespace + "." + id, xNode);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
}
