package com.sanvalero.toastsapi.service;

import java.util.List;
import java.util.Vector;

import com.sanvalero.toastsapi.exception.NotFoundException;
import com.sanvalero.toastsapi.model.ProductType;
import com.sanvalero.toastsapi.repository.ProductTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository ptr;

    @Override
    public List<ProductType> findByProductName(String name) {
        return ptr.findByProductName(name);
    }

    @Override
    public ProductType findByType(String type) {
        return ptr.findByType(type);
    }

    @Override
    public ProductType findByProductNameAndType(String name, String type) {
        return ptr.findByProductNameAndType(name, type);
    }

    @Override
    public List<ProductType> findAll() {
        return ptr.findAll();
    }

    @Override
    public ProductType findById(int id) throws NotFoundException {
        return ptr.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<ProductType> findByIds(Vector<Integer> ids) {
        return (List<ProductType>) ptr.findAllById(ids);
    }

    @Override
    public ProductType addType(ProductType type) {
        return ptr.save(type);
    }

    @Override
    public ProductType updateType(ProductType type) {
        return ptr.save(type);
    }

    @Override
    public void deleteType(ProductType type) {
        ptr.delete(type);
    }

    @Override
    public void deleteAll() {
        ptr.deleteAll();
    }

}
