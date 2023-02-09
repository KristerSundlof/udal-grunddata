/*
package se.miun.ladok3.udal.udalgrunddata;

import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import se.miun.ladok3.udal.service.worker.SynkroniseraGrunddataWorker;


@Component
public class SynkroniseraGrunddataOnStartup implements ApplicationListener<ApplicationReadyEvent> {
    private static Log log = LogFactory.getLog(SynkroniseraGrunddataWorker.class);
    private ApplicationContext applicationContext;
    @Autowired
    private SynkroniseraGrunddataWorker synkroniseraGrunddataWorker;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Hämtar grunddata");
        synkroniseraGrunddataWorker.synkroniseraAllGrunddata();
    }
}
*/
