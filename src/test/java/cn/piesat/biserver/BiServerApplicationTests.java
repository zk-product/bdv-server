package cn.piesat.biserver;

import cn.piesat.biserver.common.ApplicationContextProvider;
import cn.piesat.biserver.common.PathConfig;
import cn.piesat.biserver.common.configuration.AssemblyIndexConfig;
import cn.piesat.biserver.common.configuration.HttpclientParamsConfig;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiServerApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {

    }


    @Autowired
    private HttpclientParamsConfig httpclientConfig;

    @Test
    public void test3() {
        httpclientConfig.getMaxTotalConnect();
    }

    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "H:\\b北斗导航应用事业部\\y运维\\运维工作排期.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, new NoModleDataListener()).sheet().doRead();

    }

    @Autowired
    private ApplicationContextProvider provider;
    @Test
    public void test4() {
        try {
            Class<?> aClass = Class.forName("cn.piesat.biserver.dao.AssemblyMapper");
            Object assemblyMapper = provider.getBean(aClass);
            Method deleteAssemblyById = aClass.getMethod("deleteAssemblyById",String.class);
            Object invoke = deleteAssemblyById.invoke(assemblyMapper, "1");
            System.out.println(invoke);
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    @Autowired
    private AssemblyIndexConfig config;
    @Test
    public void testConfig() {
        Map<String, Map<String, String>> maps = config.getMaps();
        System.out.println(maps.get("01001").get("className"));
    }

}