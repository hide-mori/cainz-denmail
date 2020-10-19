package com.tcs.denmail.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TcsBaseRepository<T, ID> extends JpaRepository<T, ID> {

}
