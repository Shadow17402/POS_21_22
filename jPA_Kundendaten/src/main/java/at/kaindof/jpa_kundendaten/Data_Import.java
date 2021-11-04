package at.kaindof.jpa_kundendaten;

import at.kaindof.jpa_kundendaten.beans.Country;
import at.kaindof.jpa_kundendaten.beans.Customer;
import at.kaindof.jpa_kundendaten.beans.XMLCustomer;
import at.kaindof.jpa_kundendaten.beans.XMLCustomerList;
import at.kaindof.jpa_kundendaten.xml.XMLAccess;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data_Import {

    //JSON
    //{"country":"United States",
    // "country_code":"US",
    // "city":"San Antonio",
    // "postal_code":"78205",
    // "streetname":"Anthes",
    // "streetnumber":"41131",
    // "firstname":"Francois",
    // "lastname":"Craythorn",
    // "gender":"M",
    // "active":true,
    // "email":"fcraythorn0@google.it",
    // "since":"13-Jan-2018"}

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static final Path JSON_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "res" + File.separator + "customers.json");
    private static final Path XML_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "res" + File.separator + "customers.xml");

    public static void importData(Scanner scanner){
        int input;
        while(true) {
            try {
                System.out.println("1 | Import JSON");
                System.out.println("2 | Import XML");
                System.out.print("How do you want to import: ");
                input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        importJSON();
                        break;
                    case 2:
                        importXML();
                        break;
                    default:
                        System.out.println("wrong input\n");
                }
            } catch (NumberFormatException exception) {
                System.out.println("wrong input\n");
            }
            Query countCountries = em.createNamedQuery("Country.countAll");
            Long amountOfCountries = (Long) countCountries.getSingleResult();
            System.out.println("Countries imported: " + amountOfCountries);

            Query countAddresses = em.createNamedQuery("Address.countAll");
            Long amountOfAddresses = (Long) countAddresses.getSingleResult();
            System.out.println("Addresses imported: " + amountOfAddresses);

            Query countCustomers = em.createNamedQuery("Customer.countAll");
            Long amountOfCustomers = (Long) countCustomers.getSingleResult();
            System.out.println("Customers imported: " + amountOfCustomers);
            return;
        }
    }

    public static void dataMenu(Scanner scanner) {
        int input = 0;
        while (true) {
            System.out.println("\n1 | Find Country By Name");
            System.out.println("2 | Find all countries");
            System.out.println("3 | Count all countries");
            System.out.println("4 | Count all addresses");
            System.out.println("5 | Get all years of customers");
            System.out.println("6 | Count all customers");
            System.out.println("7 | Find customers from country");
            System.out.println("anything else | Exit");
            System.out.print("Input: ");
            try {
                input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        System.out.print("Name: ");
                        String countryName = scanner.nextLine();
                        TypedQuery<Country> typedQuery = em.createNamedQuery("Country.findByName", Country.class);
                        typedQuery.setParameter("name", countryName);
                        try {
                            Country country = typedQuery.getSingleResult();
                            System.out.println("\nCountry:");
                            System.out.println(country);
                        } catch (NoResultException ex) {
                            System.out.println("No result!");
                        }
                        break;

                    case 2:
                        TypedQuery<Country> typedQuery2 = em.createNamedQuery("Country.findAll", Country.class);
                        List<Country> countries = typedQuery2.getResultList();
                        System.out.println("\nCountries:");
                        countries.forEach(System.out::println);
                        break;

                    case 3:
                        Query countCountries = em.createNamedQuery("Country.countAll");
                        Long amountOfCountries = (Long) countCountries.getSingleResult();
                        System.out.println("\nNumber of countries: " + amountOfCountries);
                        break;

                    case 4:
                        Query countAddresses = em.createNamedQuery("Address.countAll");
                        Long amountOfAddresses = (Long) countAddresses.getSingleResult();
                        System.out.println("\nNumber of addresses: " + amountOfAddresses);
                        break;

                    case 5:
                        TypedQuery<Number> customerTypedQuery = em.createNamedQuery("Customer.findYears", Number.class);
                        List<Number> years = customerTypedQuery.getResultList();
                        System.out.println("\nYears:");
                        years.stream().forEach(number -> System.out.println(number.intValue()));
                        break;

                    case 6:
                        Query countCustomers = em.createNamedQuery("Customer.countAll");
                        Long amountOfCustomers = (Long) countCustomers.getSingleResult();
                        System.out.println("\nNumber of Customers: " + amountOfCustomers);
                        break;

                    case 7:
                        System.out.print("Name: ");
                        String countryName2 = scanner.nextLine();
                        TypedQuery<Customer> customerTypedQuery1 = em.createNamedQuery("Customer.findFromCountry", Customer.class);
                        customerTypedQuery1.setParameter("name", countryName2);
                        try {
                            List<Customer> customers = customerTypedQuery1.getResultList();
                            if(customers.size() == 0) {
                                System.out.println("No result!");
                            } else {
                                System.out.println("\nCustomers:");
                                customers.forEach(System.out::println);
                            }
                        } catch (NoResultException ex) {
                            System.out.println("No result!");
                        }
                        break;

                    default:
                        return;
                }
            } catch (NumberFormatException ex) {
                System.out.println("wrong Input!");
            }
        }
    }

    public static void main(String[] args) {
        open();
        Scanner scanner = new Scanner(System.in);

        importData(scanner);
        dataMenu(scanner);

        close();
    }

    public static void open(){
        emf = Persistence.createEntityManagerFactory("PU_JPA_Kunden");
        em = emf.createEntityManager();
    }

    public static void close(){
        em.close();
        emf.close();
    }

    public static void importJSON() {
        List<Customer> jsonCustomers;
        try {
            jsonCustomers = new ObjectMapper().readValue(new File(JSON_PATH.toString()), new TypeReference<List<Customer>>() { });
            em.getTransaction().begin();
            jsonCustomers.stream().forEach(customer -> {
                em.persist(customer);
            });
            em.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void importXML() {
        XMLCustomerList xmlCustomerList = JAXB.unmarshal(XML_PATH.toString(), XMLCustomerList.class);
        List<XMLCustomer> xmlCustomers = xmlCustomerList.getXmlCustomerList();

        List<Customer> customerList = new ArrayList<>();
        xmlCustomers.stream().forEach(xmlCustomer -> {
            customerList.add(XMLAccess.convertToCustomer(xmlCustomer));
        });

        em.getTransaction().begin();
        customerList.stream().forEach(customer -> {
            em.persist(customer);
        });
        em.getTransaction().commit();
    }

}
