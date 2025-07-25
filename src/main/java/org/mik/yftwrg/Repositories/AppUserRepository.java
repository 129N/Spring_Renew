package org.mik.yftwrg.Repositories;

import org.mik.yftwrg.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
