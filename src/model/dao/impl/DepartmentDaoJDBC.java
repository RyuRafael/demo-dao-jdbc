package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection conn = null;

    public DepartmentDaoJDBC(Connection connection) {
        this.conn = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement("INSERT INTO department(Name) VALUES (?)");
            ps.setString(1, obj.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void update(Department obj) {
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement("UPDATE department set Name = ? WHERE Id = ?");
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();

        } catch (
                SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement("DELETE FROM department where Id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (
                SQLException e) {
            throw new

                    DbException(e.getMessage());
        } finally {
            DB.

                    closeStatement(ps);
        }

    }

    // Buscar department por id
    @Override
    public Department findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                Department dp = objToDepartment(rs);
                return dp;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Department> findAll() {

        List<Department> departments = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM department");
            rs = ps.executeQuery();

            while (rs.next()) {
                Department department = objToDepartment(rs);
                departments.add(department);
            }

            return departments;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    private Department objToDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setName(rs.getString("Name"));
        department.setId(rs.getInt("Id"));

        return department;
    }
}
