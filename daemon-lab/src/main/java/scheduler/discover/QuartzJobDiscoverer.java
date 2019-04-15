package scheduler.discover;

import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuartzJobDiscoverer implements JobDiscoverer {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJobDiscoverer.class);
    private String scanPackage;

    public QuartzJobDiscoverer(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    @Override
    public Class[] find() {
        List<Class> result = new ArrayList<>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        ClassLoader cl = ClassPathScanningCandidateComponentProvider.class.getClassLoader();
        try {
            provider.addIncludeFilter(new AnnotationTypeFilter(
                    ((Class<? extends Annotation>) ClassUtils.forName("scheduler.annotation.JobExec", cl)), false));
        } catch(ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        Set<BeanDefinition> candidates = provider.findCandidateComponents(scanPackage);
        candidates.stream().forEach(beanDefinition -> {
            try {
                Class beanClz = ClassUtils.forName(beanDefinition.getBeanClassName(), cl);
                if(Job.class.isAssignableFrom(beanClz)) {
                    result.add(beanClz);
                } else {
                    logger.error(String.format("%s is not implements Quartz Job interface", beanDefinition.getBeanClassName()));
                }
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage());
            }
        });

        return result.toArray(new Class[0]);
    }
}
