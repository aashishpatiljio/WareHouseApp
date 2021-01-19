/*
package in.nareshit.aashish.batch.uom;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UomBatchRunner implements CommandLineRunner {

	@Autowired
	private Job jobUom;

	@Autowired
	private JobLauncher launcher;

	@Override
	public void run(String... args) throws Exception {
		launcher.run(jobUom, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());

	}

}
*/