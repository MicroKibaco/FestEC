package com.asiainfo.latte_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private final Filer FILER;
    private String mPackageName;

    public EntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }


    @Override
    public Void visitString(String s, Void v) {
        this.mPackageName = s; // 解析得到包名
        return v;
    }

    @Override
    public Void visitType(TypeMirror t, Void v) {
        generateJavaCode(t);
        return v;
    }

    /**
     * 生成Java类
     */
    private void generateJavaCode(TypeMirror t) {
        // 创建类的描述
        final TypeSpec targetActivity = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(t))
                .build();

        // 创建java类文件
        JavaFile javaFile = JavaFile
                .builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();

        try {
            // 写入编译文档文件
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
