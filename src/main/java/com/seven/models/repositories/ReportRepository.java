package com.seven.models.repositories;

import com.seven.models.domainobjects.Report;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ruslankatsyuryna on 6/24/17.
 */
public interface ReportRepository extends CrudRepository<Report, Long> {
}
