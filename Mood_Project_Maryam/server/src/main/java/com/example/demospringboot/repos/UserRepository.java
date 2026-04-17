package com.example.demospringboot.repos;
import com.example.demospringboot.models.user;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<user, Long> {
    user findByEmail(String email);
    
}
