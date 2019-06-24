package com.notifications.java.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.experimental.UtilityClass;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@UtilityClass
public class TemplateUtils {

    public static String getContentFromTemplate(Map<String,Object> model, String templateFilename) throws IOException, TemplateException {
        final StringBuffer content = new StringBuffer();
        final Configuration freemarkerConfig = new Configuration(Configuration.VERSION_2_3_28);
        final ClassTemplateLoader loader = new ClassTemplateLoader(new TemplateUtils().getClass(), "/templates"); //TODO check working .getClass() in static context
        freemarkerConfig.setTemplateLoader(loader);
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(freemarkerConfig.getTemplate(templateFilename), model));

        return content.toString();
    }

}
