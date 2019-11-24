package com.dongfang.xml;

import com.dongfang.xml.bean.Student;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestXmlParser {
    @Test
    public void testXml1() throws DocumentException {
        // 1、导入dom解析包，dom4j-1.6
        // 2、创建一个SAXReader，一个xml文件阅读器
        SAXReader saxReader = new SAXReader();
        // 3 、使用reader读取文件即可
        Document document = saxReader.read(new File("D:\\ubuntu\\learn\\JavaWeb\\MavenProject\\maven03\\JavaWeb\\src\\main\\resources\\xml\\students.xml"));

        System.out.println("document = " + document);

        // 4、获取到document后，可以进行增删改查


        // 解析
        short nodeType = document.getNodeType();
        System.out.println("nodeType = " + nodeType);

        // 先要获取根节点，使用根节点往下找
        // getNodeType()获取当前节点类型
        // getName()获取节点名字
        Element rootElement = document.getRootElement();
        short rootElementNodeType = rootElement.getNodeType();
        System.out.println("rootElementNodeType = " + rootElementNodeType);
        System.out.println("rootElement.getName() = " + rootElement.getName());
        System.out.println("rootElement.getText() = " + rootElement.getText());

        // 5、使用根节点往下查找
        // 获取当前节点下的所有子节点
        List<Element> students = rootElement.elements();
        for (Element student : students) {
            System.out.println("student.getName() = " + student.getName());
            System.out.println("-------------");
            // elementText(name)获取当前节点下名为name子元素的文本值
            String name = student.elementText("name");
            System.out.println("name = " + name);

            List<Element> stuChild = student.elements();
            for (Element childEle : stuChild) {
                System.out.println("childEle.getName() = " + childEle.getName());
                System.out.println("childEle.getText() = " + childEle.getText());
            }
        }

        // students代表所有student的集合
        for (Element student : students) {
            //每个student代表一个student标签
            String dd = student.attributeValue("dd");
            System.out.println("dd = " + dd);

            String id = student.attributeValue("id");
            System.out.println("id = " + id);

        }
    }

    @Test
    public void testXml3() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("D:\\ubuntu\\learn\\JavaWeb\\MavenProject\\maven03\\JavaWeb\\src\\main\\resources\\xml\\students.xml"));

        Element rootElement = document.getRootElement();

        List<Element> students = rootElement.elements();
        List<Student> list = new ArrayList<>();
        for (Element student : students) {
            String id = student.attributeValue("id");
            String name = student.elementText("name");
            String age = student.elementText("age");
            list.add(new Student(name, Integer.parseInt(age), id));
        }
        System.out.println("list = " + list);

    }

    private String path = "D:\\ubuntu\\learn\\JavaWeb\\MavenProject\\maven03\\JavaWeb\\src\\main\\resources\\xml\\";
    @Test
    public void testXml4() throws DocumentException, IOException {
        SAXReader reader = new SAXReader();

        Document document = reader.read(new File("D:\\ubuntu\\learn\\JavaWeb\\MavenProject\\maven03\\JavaWeb\\src\\main\\resources\\xml\\students.xml"));

        Element rootEle = document.getRootElement();
        Element firstStudent = rootEle.element("student");
        Element name = firstStudent.element("name");
        System.out.println("name.getText() = " + name.getText());
        name.setText("张三33");
        name.addAttribute("firstName", "张");

        //修改的东西保存起来
//        OutputFormat将输出的数据格式化
        OutputFormat compactFormat = OutputFormat.createCompactFormat();
        XMLWriter writer = new XMLWriter(new FileOutputStream(path + "compactStu.xml"), compactFormat);
        writer.write(document);
        writer.close();

        // xmlWriter用来写xml的document的对象
        OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path + "prettyStu.xml"), prettyPrint);
        xmlWriter.write(document);
        xmlWriter.close();

    }


    @Test
    public void testXml5() {

    }
}
