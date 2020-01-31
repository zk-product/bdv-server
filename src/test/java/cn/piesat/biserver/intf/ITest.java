package cn.piesat.biserver.intf;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zk
 * @date 2020/1/3 10:17
 */
public interface ITest {
    void methodOne();
    void methodTwo();
}
interface ITest2 extends ITest {
    void methodThree();
}
class A implements ITest {

    @Override
    public void methodOne() {
        System.out.println("A methodOne()");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void methodTwo() {
        System.out.println("A methodTwo()");
        Class<? extends A> aClass = this.getClass();
        try {
            Method methodThree = aClass.getMethod("methodThree");
            methodThree.setAccessible(false);
            methodThree.invoke(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /*public void methodThree(){
        System.out.println("A methodThree()");
    }*/
}
class B extends A implements ITest2 {

    @Override
    public void methodThree() {
        System.out.println("B methodThree()");
    }
}
class Test {
    public static void main(String[] args) {
        B b = new B();
        b.methodOne();
        b.methodTwo();
    }
}
