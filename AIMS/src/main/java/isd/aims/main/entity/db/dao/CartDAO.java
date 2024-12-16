package isd.aims.main.entity.db.dao;

import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.db.DAO;

import java.util.List;

public class CartDAO implements DAO<Cart> {
    @Override
    public List<Cart> getAll() {
        return List.of();
    }

    @Override
    public Cart getById(int id) {
        return null;
    }

    @Override
    public Cart add(Cart cart) {
        return null;
    }

    @Override
    public boolean update(Cart cart) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
