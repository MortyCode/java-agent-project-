package top.rcode.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author ：河神
 * @date ：Created in 2022/3/20 22:26
 */
public class BaseTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader,
                            //要转换的类的定义加载程序，
                            String className,
                            //Java虚拟机规范中定义的完全限定类和接口名称的内部形式的类名称。
                            // 例如，“java/util/List”。
                            Class<?> classBeingRedefined,
                            //如果这是由重定义或重传触发的，则被重定义或重传的类；如果这是类加载，则为null
                            ProtectionDomain protectionDomain,
                            //正在定义或重新定义的类的保护域
                            byte[] classfileBuffer
                            //类文件格式的输入字节缓冲区——不得修改
    ) throws IllegalClassFormatException {


        



        return new byte[0];
    }
}
