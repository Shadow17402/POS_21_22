package at.kaindorf.jpa_intro;

import at.kaindorf.jpa_intro.pojos.Address;
import at.kaindorf.jpa_intro.pojos.SchoolClass;
import at.kaindorf.jpa_intro.pojos.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA_Intro");
        EntityManager em = emf.createEntityManager();

        Student student = new Student("5DHIF",1l,"Nico","Baumann", LocalDate.now());
        Student student2 = new Student("5DHIF",2l,"Adrian","Berner", LocalDate.now());
        Address addr1 = new Address("Kaindorf","Grazerstr","220");
        Address addr2 = new Address("Kaindorf","Grazerstr","220");

        student.setAddress(addr1);
        student2.setAddress(addr2);

        addr1.setStudent(student);
        addr2.setStudent(student2);

        // em.persist(student);
        // em.persist(student2);

        SchoolClass sc = new SchoolClass("5DHIF");
        sc.addStudent(student);
        // student.setSchoolClass(sc);
        sc.addStudent(student2);
        em.persist(sc);

        // em.detach(student); aus dem Context
        // em.remove(student); aus dem Context und der DB

        em.getTransaction().begin();
        em.getTransaction().commit();

        //JPQL-Queries
        TypedQuery<Student> typedQuery = em.createQuery("SELECT s FROM student s",Student.class);
        List<Student> students = typedQuery.getResultList();
        System.out.println(students);

        TypedQuery<Address> typedQuery2 = em.createNamedQuery("Address.GetALl", Address.class);
        typedQuery2.setParameter("street","%str%");
        List<Address> addresses = typedQuery2.getResultList();
        addresses.stream().forEach(System.out::println);

        TypedQuery<Address> typedQuery3 = em.createNamedQuery("Address.GetByClassname", Address.class);
        typedQuery3.setParameter("classname","5DHIF");
        List<Address> addressesByCLass = typedQuery3.getResultList();
        addressesByCLass.stream().forEach(System.out::println);

        Query query = em.createNamedQuery("Student.CountByClassname");
        Long number = (Long) query.getSingleResult();
        System.out.println(number);

        em.close();
        emf.close();
    }
}

