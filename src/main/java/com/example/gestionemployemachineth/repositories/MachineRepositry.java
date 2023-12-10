package com.example.gestionemployemachineth.repositories;

import com.example.gestionemployemachineth.entities.Machine;
import org.springframework.data.repository.CrudRepository;

public interface MachineRepositry extends CrudRepository<Machine, Long> {
}
