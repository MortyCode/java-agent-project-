package top.rcode.visitor;

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
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        if ("top/rcode/controller/HelloController".equals(name)) {
            classWriter.visitField(Opcodes.ACC_PUBLIC|Opcodes.ACC_STATIC|Opcodes.ACC_FINAL,
                    "time", "Ljava/lang/Long;", null, "初始值");
        }
    }

    public byte[] toByteArray() {
        return classWriter.toByteArray();
    }
}
