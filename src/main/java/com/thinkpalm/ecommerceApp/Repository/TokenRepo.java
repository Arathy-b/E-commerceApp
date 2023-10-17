package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token,Integer> {
    @Query(value = """
            select t.* from token t inner join customer c on t.customer_id=c.id where c.id =:customerId and (t.expired=false or t.revoked=false)
            """,nativeQuery = true)
    List<Token> findAllValidTokenByUser(Integer customerId);

    Optional<Token> findByToken(String token);

}
