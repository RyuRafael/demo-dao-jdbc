package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;


public class Main {
    private static Object SellerDaoJDBC;

    public static void main(String[] args) {

        // Buscar vendedores  por Id
       // System.out.println("=== Seller findById ===");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(1);

       // System.out.println(seller);

        Department department = new Department(2, null);
        List<Seller> sellers = sellerDao.findByDepartmentSeller(department);

        sellers.forEach(System.out::println);
    }
}
