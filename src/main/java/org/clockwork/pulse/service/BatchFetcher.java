package org.clockwork.pulse.service;

import java.util.List;

public interface BatchFetcher {

  List<String> publishNextBatchOfJobs();

}
