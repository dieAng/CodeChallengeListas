package com.example.codechange.models.dao;

import com.example.codechange.models.suma.Suma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ISumaDao extends CrudRepository<Suma, String> {
}
