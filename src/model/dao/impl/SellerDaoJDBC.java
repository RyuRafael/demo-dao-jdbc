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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Buscar vendedor por id
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

                Department dept = objToDepartment(rs);
                Seller seller = objToSeller(rs, dept);

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
    private Seller objToSeller(ResultSet rs, Department dept) throws SQLException {
        Seller seller = new Seller();

        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(dept);

        return seller;
    }

    // Instancia e retorna um obj Department com dados da tabela
    private Department objToDepartment(ResultSet rs) throws SQLException {

        Department dp = new Department();
        dp.setId(rs.getInt("DepartmentId"));
        dp.setName(rs.getString("Name"));

        return dp;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

    // Retorna um lista de todos os vendedores do departamento informado
    @Override
    public List<Seller> findByDepartmentSeller(Department dept) {

        List<Seller> sellers = new ArrayList<>();
        Map<Integer, Department> map = new HashMap<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT seller.*, department.Name DeptName FROM seller "
                    + "JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");

            ps.setInt(1, dept.getId());
            rs = ps.executeQuery();

            while (rs.next()) {

                // Instancia um obj Departmente apontando para um departamento com o id armazenado no map;
                Department department = map.get(rs.getInt("DepartmentId"));

                // Verifica de o department esta null;
                if (department == null) {
                    department = objToDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), department);
                }

                Seller obj = objToSeller(rs, department);
                sellers.add(obj);
            }

            return sellers;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
