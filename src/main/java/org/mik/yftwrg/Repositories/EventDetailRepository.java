package org.mik.yftwrg.Repositories;

import org.mik.yftwrg.Entity.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailRepository extends JpaRepository<EventDetail, Long> {
}
