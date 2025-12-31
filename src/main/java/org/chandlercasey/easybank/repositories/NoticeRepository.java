package org.chandlercasey.easybank.repositories;


import org.chandlercasey.easybank.entities.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query(
            value = """
    SELECT *
    FROM notice_details
    WHERE CURDATE() BETWEEN notic_beg_dt AND notic_end_dt
  """,
            nativeQuery = true
    )    List<Notice> findAllActiveNotices();

}