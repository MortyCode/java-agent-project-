package top.rcode;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import top.rcode.transformer.BaseTransformer;
import top.rcode.visitor.PreClassVisitor;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：河神
 * @date ：Created in 2022/3/17 09:25
 */
public class AgentDynamic {


    public static void  agentmain(String agentOps, Instrumentation inst)  {
        System.out.println("====agentmain 方法开始");

        Class<?> redefineClass = null;
        Class<?>[] allLoadedClasses = inst.getAllLoadedClasses();

        List<Class<?>> data = new ArrayList<>();
        for (Class<?> clazz : allLoadedClasses) {
            if ("top.rcode.controller.HelloController".equals(clazz.getName())
                    ||
                "top.rcode.manger.DataManger".equals(clazz.getName())){
                redefineClass = clazz;
                data.add(redefineClass);
            }
        }
        if (data.size()==0){
            System.out.println("未找到redefineClass");
            return;
        }
        try {
            inst.addTransformer(new BaseTransformer(),true);
            Class<?>[] objects = data.toArray(new Class<?>[2]);
            inst.retransformClasses(objects);
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
        System.out.println("====agentmain 方法结束");
    }


    public void replase(String agentOps, Instrumentation inst){
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
    }

}
