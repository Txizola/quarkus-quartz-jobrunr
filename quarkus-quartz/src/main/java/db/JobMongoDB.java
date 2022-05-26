package db;

import com.novemberain.quartz.mongodb.*;
import org.quartz.*;
import org.quartz.spi.OperableTrigger;


public class JobMongoDB {
    private String mongoUri= "mongodb:///localhost:27017";
    private String username = "";
    private String password = "";

    MongoDBJobStore dynamicMongoDBJobStore = new MongoDBJobStore(mongoUri, username, password);


    public void removeTrigger(Trigger trigger) throws JobPersistenceException {
        dynamicMongoDBJobStore.removeTrigger(trigger.getKey());
    }
    public void removeJob(JobDetail job) throws JobPersistenceException {
        dynamicMongoDBJobStore.removeJob(job.getKey());
    }
    public void saveJobAndTrigger(JobDetail jobDetail, Trigger trigger) throws JobPersistenceException {
        OperableTrigger operableTrigger = (OperableTrigger) trigger;
        dynamicMongoDBJobStore.storeJobAndTrigger(jobDetail, operableTrigger);
    }

    public void saveJobDetails(JobDetail jobDetail) throws JobPersistenceException {
        dynamicMongoDBJobStore.storeJob(jobDetail, true);
    }

    public void saveTrigger(Trigger trigger) throws JobPersistenceException {
        OperableTrigger operableTrigger = (OperableTrigger) trigger;
        dynamicMongoDBJobStore.storeTrigger(operableTrigger, false);
    }
}