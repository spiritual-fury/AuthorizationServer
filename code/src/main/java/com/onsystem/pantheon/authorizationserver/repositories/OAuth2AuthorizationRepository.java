package com.onsystem.pantheon.authorizationserver.repositories;


import com.onsystem.pantheon.authorizationserver.entities.OAuth2Authorization;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "auth.mock", havingValue = "false")
public interface OAuth2AuthorizationRepository extends JpaRepository<OAuth2Authorization, Integer>, JpaSpecificationExecutor<OAuth2Authorization> {


}
