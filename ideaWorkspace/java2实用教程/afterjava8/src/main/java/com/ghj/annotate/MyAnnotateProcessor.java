package com.ghj.annotate;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author guohuanjun1
 * @date 2023/8/4 17:14
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.ghj.annotate.ParamNotNull")
public class MyAnnotateProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if(element.getKind() == ElementKind.PARAMETER){
                    ParamNotNull paramNotNull = element.getAnnotation(ParamNotNull.class);
                    if(paramNotNull != null){
                        System.out.println(paramNotNull.message());
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
