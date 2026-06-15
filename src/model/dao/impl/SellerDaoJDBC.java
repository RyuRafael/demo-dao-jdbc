package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn = null;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleterById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE seller.Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                Department dp = objToDepartment(rs);
                Seller seller = objToSeller(rs, dp);

                return seller;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    // Instancia e retorna um obj Seller com dados da tabela
    private Seller objToSeller(ResultSet rs, Department dp) throws SQLException {
        Seller seller = new Seller();

        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(dp);

        return seller;
    }

    // Instancia e retorna um obj Department com dados da tabela
    private Department objToDepartment(ResultSet rs) throws SQLException {

        Department dp = new Department();
        dp.setId(rs.getInt("DepartmentId"));
        dp.setName(rs.getString("DepName"));

        return dp;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }


}
