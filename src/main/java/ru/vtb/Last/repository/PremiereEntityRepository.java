package ru.vtb.Last.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vtb.Last.entity.PremiereEntity;

public interface PremiereEntityRepository extends JpaRepository<PremiereEntity, Long> {

    @Query("from PremiereEntity p where p.name = :name")
    PremiereEntity findByName(@Param("name") String name);

}
