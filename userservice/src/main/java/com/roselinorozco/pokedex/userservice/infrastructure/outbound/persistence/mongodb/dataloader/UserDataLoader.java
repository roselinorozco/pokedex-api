package com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.dataloader;

import com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.entity.UserEntity;
import com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.repository.UserRepositoryMongoDB;
import com.roselinorozco.pokedex.userservice.infrastructure.service.adapter.BCryptPasswordManagerAdapter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataLoader {

    private final UserRepositoryMongoDB userRepositoryMongoDB;
    private final BCryptPasswordManagerAdapter bCryptPasswordManagerAdapter;

    public UserDataLoader(final UserRepositoryMongoDB userRepositoryMongoDB,
                          final BCryptPasswordManagerAdapter bCryptPasswordManagerAdapter) {
        this.userRepositoryMongoDB = userRepositoryMongoDB;
        this.bCryptPasswordManagerAdapter = bCryptPasswordManagerAdapter;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadUserData() {

        if (this.userRepositoryMongoDB.count() == 0) {
            this.userRepositoryMongoDB.saveAll(List.of(
                    new UserEntity("admin@example.com",
                            this.bCryptPasswordManagerAdapter.hashPassword("testpass#123KA")
                    )
            ));
            System.out.println("Initial User data loaded.");
        }
    }

}
