package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void deleterById(Integer id);
    void findById(Integer id);
    List<Seller> findAll();
}
