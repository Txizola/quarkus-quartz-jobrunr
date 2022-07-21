/*package jobrunrscheduler;

import io.quarkus.runtime.Startup;
import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;


@Startup
@ApplicationScoped
public class ConfigJobRunr {

    ConfigJobRunr(){
        JobRunr.configure()
                .useStorageProvider(new MongoDBStorageProvider("localhost", 27017))
                .useBackgroundJobServer()
                .initialize();
    }

    @Produces
    public JobScheduler assignJobScheduler(){
        return new JobScheduler(new MongoDBStorageProvider("localhost", 27017));
    }
}*/
