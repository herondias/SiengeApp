package me.herondias.Sienge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.herondias.Sienge.model.TipoVeiculo;

@Repository
public interface TipoVeiculoRepository extends JpaRepository<TipoVeiculo, Long>{
	public List<TipoVeiculo> findAllByOrderByDescricaoAsc();
	public List<TipoVeiculo> findByDescricao(String descricao);
}
