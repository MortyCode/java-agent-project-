package top.rcode;

import java.lang.instrument.Instrumentation;

/**
 * @author ：河神
 * @date ：Created in 2021/4/9 4:47 下午
 */
public class AgentPre {

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     */
    public static void  premain(String agentOps, Instrumentation inst){
        System.out.println("====premain 方法执行");

        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class allLoadedClass : allLoadedClasses) {
            System.out.println(allLoadedClass.getName());
        }

        System.out.println("====premain 方法执行");
    }
}
