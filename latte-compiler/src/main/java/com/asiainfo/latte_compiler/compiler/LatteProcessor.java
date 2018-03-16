package com.asiainfo.latte_compiler.compiler;

import com.google.auto.service.AutoService;

import com.asiainfo.latte_annotations.annotations.AppRegisterGenerator;
import com.asiainfo.latte_annotations.annotations.EntryGenerator;
import com.asiainfo.latte_annotations.annotations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by MicroKibaco on 16/03/2018.
 */
@SuppressWarnings("unused")
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set,
                           RoundEnvironment roundEnv) {
        generateEntryCode(roundEnv);
        generatePayEntryCode(roundEnv);
        generateAppRegisterCode(roundEnv);
        return true;
    }

    /**
     * 注解的集合
     *
     * @return Annotations List
     */

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        Set<Class<? extends Annotation>> supportedAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supportedAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {

        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> mirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror mirror : mirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues =
                        mirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {

                    entry.getValue().accept(visitor, null);

                }
            }
        }
    }

    private void generateAppRegisterCode(RoundEnvironment roundEnv) {
        final AppRegisterVisitor appRegisterVisitor =
                new AppRegisterVisitor(processingEnv.getFiler());
        scan(roundEnv, AppRegisterGenerator.class, appRegisterVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment roundEnv) {
        final PayEntryVisitor payEntryVisitor =
                new PayEntryVisitor(processingEnv.getFiler());
        scan(roundEnv, PayEntryGenerator.class, payEntryVisitor);
    }

    private void generateEntryCode(RoundEnvironment roundEnv) {
        final EntryVisitor entryVisitor =
                new EntryVisitor(processingEnv.getFiler());
        scan(roundEnv, EntryGenerator.class, entryVisitor);
    }
}
