package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        // Buscar vendedores  por Id
        System.out.println("=== Test seller findById ===");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);

        // Buscar todos os vendedores de um department
        System.out.println("\n=== Test seller findByDepartmentSeller ===");
        Department department = new Department(2, null);
        List<Seller> sellers = sellerDao.findByDepartmentSeller(department);
        sellers.forEach(System.out::println);

        // Buscar todos os vendedores
        System.out.println("\n=== Test seller findAll ===");
        sellerDao.findAll().forEach(System.out::println);

        // Inserir dados
        System.out.println("\n=== Test insert seller ===");
        Seller seller1 = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4800.00, department);
        sellerDao.insert(seller1);

        // Atualizar dados
        System.out.println("\n=== Test update seller ===");

        seller = sellerDao.findById(1);
        seller.setName("Rafael");
        sellerDao.update(seller);
        System.out.println("Update complete.");

        

    }
}
