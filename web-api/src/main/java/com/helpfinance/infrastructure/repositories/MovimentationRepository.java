package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IMovimentationRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Movimentation;

@Repository
public class MovimentationRepository extends EntityRepository<Movimentation> implements IMovimentationRepository {
  
    public MovimentationRepository() { super("Movimentations"); }
}