package com.asiainfo.latte_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private final Filer FILER;
    private String mPackageName;

    public PayEntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }

    @Override
    public Void visitString(String s, Void v) {
        this.mPackageName = s;
        return v;
    }

    @Override
    public Void visitType(TypeMirror t, Void v) {
        generateJavaCode(t);
        return v;
    }


    private void generateJavaCode(TypeMirror t) {

        final TypeSpec targetActivity = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(t))
                .build();

        JavaFile javaFile = JavaFile
                .builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信支付入口文件")
                .build();

        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
