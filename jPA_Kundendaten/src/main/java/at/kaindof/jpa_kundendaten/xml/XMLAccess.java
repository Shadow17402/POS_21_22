package at.kaindof.jpa_kundendaten.xml;

import at.kaindof.jpa_kundendaten.beans.Address;
import at.kaindof.jpa_kundendaten.beans.Country;
import at.kaindof.jpa_kundendaten.beans.Customer;
import at.kaindof.jpa_kundendaten.beans.XMLCustomer;

import java.util.HashSet;
import java.util.Set;

public class XMLAccess {
    private static Set<Country> countrySet = new HashSet<>();
    private static Set<Address> addressSet = new HashSet<>();

    public static Customer convertToCustomer(XMLCustomer xmlCustomer) {

        Country country = new Country(xmlCustomer.getCountry(), xmlCustomer.getCountry_code());
        countrySet.add(country);
        Country realCountry = countrySet.stream().filter(country1 -> country1.equals(country)).findFirst().get();

        Address address = new Address(
                xmlCustomer.getStreetname(),
                xmlCustomer.getStreetnumber(),
                xmlCustomer.getPostal_code(),
                xmlCustomer.getCity(),
                realCountry);
        addressSet.add(address);

        realCountry.getAddresses().add(addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get());

        Customer customer = new Customer(
                xmlCustomer.getFirstname(),
                xmlCustomer.getLastname(),
                xmlCustomer.getGender().charAt(0),
                xmlCustomer.isActive(),
                xmlCustomer.getEmail(),
                xmlCustomer.getSince(),
                addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get());

        addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get().getCustomers().add(customer);

        return customer;
    }
}