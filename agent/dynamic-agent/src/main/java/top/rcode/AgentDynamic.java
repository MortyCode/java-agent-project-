package top.rcode;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ：河神
 * @date ：Created in 2022/3/17 09:25
 */
public class AgentDynamic {


    public static void  agentmain(String agentOps, Instrumentation inst){
        System.out.println("====agentmain 方法开始");

        String[] split = agentOps.split(",");

        String className = split[0];
        String classFile = split[1];

        System.out.println("替换类为:   "+className);

        Class<?> redefineClass = null;
        Class<?>[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class<?> clazz : allLoadedClasses) {
            if (className.equals(clazz.getName())){
                redefineClass = clazz;
            }
        }

        if (redefineClass==null){
            return;
        }

        //热替换
        try {
            byte[] classBytes = Files.readAllBytes(Paths.get(classFile));
            ClassDefinition classDefinition = new ClassDefinition(redefineClass, classBytes);
            inst.redefineClasses(classDefinition);
        } catch (ClassNotFoundException | UnmodifiableClassException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("====agentmain 方法结束");
    }

}
