package scheduler.main;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ScanTest {
    public static void main(String[] args) throws Exception {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        ClassLoader cl = ClassPathScanningCandidateComponentProvider.class.getClassLoader();
        provider.addIncludeFilter(new AnnotationTypeFilter(
                ((Class<? extends Annotation>) ClassUtils.forName("scheduler.annotation.Job", cl)), false));

        Set<BeanDefinition> candiates = provider.findCandidateComponents("scheduler.annotated.job");
        candiates.forEach(beanDefinition -> System.out.println(beanDefinition));
    }
}
