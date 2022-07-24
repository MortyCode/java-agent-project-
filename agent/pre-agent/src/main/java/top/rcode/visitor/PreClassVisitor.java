package top.rcode.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author ：河神
 * @date ：Created in 2022/4/12 23:44
 */
public class PreClassVisitor extends ClassVisitor {

    private ClassWriter classWriter;


    public PreClassVisitor( ClassWriter classWriter) {
        super(Opcodes.ASM6,classWriter);
        this.classWriter = classWriter;
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if ("hello".equals(name)) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

            PreMethordVisitor preMethordVisitor = new PreMethordVisitor(methodVisitor);
            preMethordVisitor.visitMaxs(1,1);
            return preMethordVisitor;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }


    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if ("Lorg/springframework/web/bind/annotation/RestController;".equals(descriptor)){
            classWriter.visitField(Opcodes.ACC_PUBLIC|Opcodes.ACC_STATIC|Opcodes.ACC_FINAL,
                    "timer", "Ljava/lang/Long;", null, "0");
        }

        return super.visitAnnotation(descriptor, visible);
    }

    public byte[] toByteArray() {
        return classWriter.toByteArray();
    }
}
