package com.tcs.denmail.online.repository;

import java.util.Optional;
import com.tcs.denmail.online.domain.entity.VersionEntity;
import com.tcs.denmail.online.domain.repository.VersionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class VersionRepositoryTest {

    @Autowired
    private VersionRepository versionRepository;

    @Test
    @Transactional
    @Commit
    public void test001() {
        Optional<VersionEntity> find = versionRepository.findById(2);

        find.ifPresentOrElse(versionEntity -> {
            versionEntity.setName("v44");
            // versionEntity.setVersion(0);
            VersionEntity saved = versionRepository.saveAndFlush(versionEntity);
            System.out.println(saved);
        }, () -> {
            System.out.println("Not found!!!!");
        });
    }

    @Test
    @Transactional
    @Commit
    public void test002() {

        VersionEntity entity = new VersionEntity();
        entity.setId(2);
        entity.setName("v23");
        entity.setVersion(4);
        versionRepository.save(entity);

    }
}
