package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Producto;
import com.example.demo.repository.IProductoRepository;
import com.example.demo.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	@Transactional
	public Producto register(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}
	
}
