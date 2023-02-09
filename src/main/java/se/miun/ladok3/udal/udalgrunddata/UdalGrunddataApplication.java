package se.miun.ladok3.udal.udalgrunddata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import se.miun.ladok3.udal.AtomFeedsConfiguration;
import se.miun.ladok3.udal.HibernateConfiguration;
import se.miun.ladok3.udal.service.worker.SynkroniseraGrunddataWorker;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"se.miun.ladok3.udal.udalgrunddata", "se.miun.ladok3.udal.service", "se.miun.ladok3.udal.adapter.service"})
@Import({HibernateConfiguration.class, AtomFeedsConfiguration.class})
public class UdalGrunddataApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UdalGrunddataApplication.class, args);
		SynkroniseraGrunddataWorker synkroniseraGrunddataWorker = context.getBean(SynkroniseraGrunddataWorker.class);
		int counter = synkroniseraGrunddataWorker.synkroniseraGrunddata();
		System.exit(SpringApplication.exit(context));
	}

}
