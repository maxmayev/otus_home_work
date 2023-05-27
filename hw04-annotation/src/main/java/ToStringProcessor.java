import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;


@SupportedAnnotationTypes(
        "ToString")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class ToStringProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {

            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            if (annotatedElements.isEmpty()) {
                continue;
            }

            TypeElement element = ((TypeElement) annotatedElements.iterator().next().getEnclosingElement());

            try {
                writeToStringText(element);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return true;
    }

    private void writeToStringText(TypeElement element) throws IOException {

        JavaFileObject file = processingEnv.getElementUtils().getFileObjectOf(element);

        try (PrintWriter out = new PrintWriter(file.openWriter())) {

            String textOfToString = "    @Override\n" +
                    "    public String toString() {\n" +
                    "        return this.toString();\n" +
                    "    }";

            int index = file.getKind().extension.lastIndexOf("}");
            out.write(textOfToString,index,textOfToString.length());

        }
    }
}
