package com.jajm.inquilino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.repository.helper.imovel.ImoveisQueries;

@Repository
public interface Imoveis extends JpaRepository<Imovel, Long>, ImoveisQueries {

}
