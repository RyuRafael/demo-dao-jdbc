package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Scanner sc = new Scanner(System.in);

        // Buscar todos os departamentos
        System.out.println("=== department findAll ===");
        List<Department> allDep = departmentDao.findAll();
        allDep.forEach(System.out::println);

        // Buscar por id
        System.out.println("\n=== department findById ===");
        System.out.println("Enter id for find department: ");
        Integer idFind = sc.nextInt();
        Department department = departmentDao.findById(idFind);
        System.out.println(department);

        // Inserir departmaent
        System.out.println("\n=== department insert ===");
        Department newDepartment = new Department(null, "Department");
        departmentDao.insert(newDepartment);
        System.out.println("Successfully inserted department");

        // Atualizar departmet
        System.out.println("\n=== department update ===");
        System.out.println("Enter id for update department: ");
        Integer idUpdate = sc.nextInt();
        System.out.println("Enter name for update: ");
        String name = sc.next();
        Department depUpdate = new Department(idUpdate, name);
        departmentDao.update(depUpdate);
        System.out.println("Successfully updated department");

        // Deletar department
        System.out.println("\n=== department delete ===");
        System.out.println("Enter id for delete department: ");
        Integer idDelete = sc.nextInt();
        departmentDao.deleteById(idDelete);
        System.out.println("Successfully deleted department");

    }
}
