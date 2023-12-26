package com.pjatk.MPRSpring1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends CrudRepository<Cat,Long> {

        public Cat findByName(String name);

}
