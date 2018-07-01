package main;

/**
 * Created by MiaoZhuang on 2016/6/1.
 */
public class Scientist implements Person {

    @Override
    public void say() {
        System.out.println("我是科学家");
    }

    public void walk() {
        System.out.print("科学家在走路");
    }
}
