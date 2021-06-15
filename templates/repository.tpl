package {{packageName}}repository;

import {{packageName}}entity.{{Entity}};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface {{Entity}}Repository extends 
        JpaRepository<{{Entity}}, Integer>{
    
}