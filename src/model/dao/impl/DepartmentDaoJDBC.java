package model.dao.impl;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection conn = null;

    public DepartmentDaoJDBC(Connection connection) {
        this.conn = connection;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    // Buscar department por id
    @Override
    public Department findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("select * from Department where Id = ?");


            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                Department dp = objToDepartment(rs);
                return dp;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    private Department objToDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setName(rs.getString("Name"));
        department.setId(rs.getInt("DepartmentId"));

        return department;
    }


    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
