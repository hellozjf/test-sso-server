package com.hellozjf.test.testssoserver.repository;

import com.hellozjf.test.testssoserver.dataobject.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jingfeng Zhou
 */
public interface ManufacturerRepository extends JpaRepository<Manufacturer, String> {
    Manufacturer findByZrrdah(String zrrdah);
    Manufacturer findByToken(String token);
}
