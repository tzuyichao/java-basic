package scheduler.main;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import scheduler.annotation.Job;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ScanTest {
    public static void main(String[] args) throws Exception {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        ClassLoader cl = ClassPathScanningCandidateComponentProvider.class.getClassLoader();
        provider.addIncludeFilter(new AnnotationTypeFilter(
                ((Class<? extends Annotation>) ClassUtils.forName("scheduler.annotation.Job", cl)), false));

        Set<BeanDefinition> candidates = provider.findCandidateComponents("scheduler.annotated.job");
        candidates.forEach(beanDefinition -> {
            System.out.println(beanDefinition);
            try {
                Class beanClz = ClassUtils.forName(beanDefinition.getBeanClassName(), cl);
                Job jobAnnotation = (Job) beanClz.getAnnotation(Job.class);
                System.out.println(jobAnnotation.name());
                System.out.println(jobAnnotation.group());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
