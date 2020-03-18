package com.sdb.test;

import com.sdb.Address;
import com.sdb.Data;
import com.sdb.Person;
import com.sdb.Search;
import junit.framework.TestCase;

/**
 * SearchTest.java
 * Brooke Lampe
 * 2020/03/18
 * This class provides unit testing for the Search.java class methods
 */

public class SearchTest extends TestCase {

    public void testFindPersonByCodeWhenExists() {
        // Generate test data
        Address address1 = new Address("10100 Black Rapids RD", "Lincoln", "NE", "68527", "USA");
        Person person1 = new Person("1111", null, "Brooke", "Lampe", address1, "brooke.lampe@huskers.unl.edu");
        Person person2 = new Person("1112", null, "Ryan", "Lampe", address1, "brooke.lampe@huskers.unl.edu");
        Person person3 = new Person("1113", null, "Christian", "Lampe", address1, "brooke.lampe@huskers.unl.edu");

        // Clear data from personList, if any, and add test data
        Data.getPersonList().clear();
        Data.getPersonList().add(person1);
        Data.getPersonList().add(person2);
        Data.getPersonList().add(person3);

        // Define expected and actual values
        Person expectedPerson = person1;
        Person actualPerson = Search.findPersonByCode("1111");

        // Perform the assertion test
        assertEquals(expectedPerson, actualPerson);
    }

    public void testFindPersonByCodeWhenNotExists() {
        // Generate test data
        Address address1 = new Address("10100 Black Rapids RD", "Lincoln", "NE", "68527", "USA");
        Person person1 = new Person("1111", null, "Brooke", "Lampe", address1, "brooke.lampe@huskers.unl.edu");
        Person person2 = new Person("1112", null, "Ryan", "Lampe", address1, "brooke.lampe@huskers.unl.edu");
        Person person3 = new Person("1113", null, "Christian", "Lampe", address1, "brooke.lampe@huskers.unl.edu");

        // Clear data from personList, if any, and add test data
        Data.getPersonList().clear();
        Data.getPersonList().add(person1);
        Data.getPersonList().add(person2);
        Data.getPersonList().add(person3);

        // Define expected and actual values
        Person expectedPerson = null;
        Person actualPerson = Search.findPersonByCode("1114");

        // Perform the assertion test
        assertEquals(expectedPerson, actualPerson);
    }

    public void testFindPersonByCodeWhenMultiple() {
        // Generate test data
        Address address1 = new Address("10100 Black Rapids RD", "Lincoln", "NE", "68527", "USA");
        Person person1 = new Person("1111", null, "Brooke", "Lampe", address1, "brooke.lampe@huskers.unl.edu");
        Person person2 = new Person("1113", null, "Ryan", "Lampe", address1, "brooke.lampe@huskers.unl.edu");
        Person person3 = new Person("1113", null, "Christian", "Lampe", address1, "brooke.lampe@huskers.unl.edu");

        // Clear data from personList, if any, and add test data
        Data.getPersonList().clear();
        Data.getPersonList().add(person1);
        Data.getPersonList().add(person2);
        Data.getPersonList().add(person3);

        // Define expected and actual values
        Person expectedPerson = person2;
        Person actualPerson = Search.findPersonByCode("1113");

        // Perform the assertion test
        assertEquals(expectedPerson, actualPerson);
    }

    public void testFindPersonByCodeWhenEmpty() {
        // Clear data from personList, if any
        Data.getPersonList().clear();

        // Define expected and actual values
        Person expectedPerson = null;
        Person actualPerson = Search.findPersonByCode("1113");

        // Perform the assertion test
        assertEquals(expectedPerson, actualPerson);
    }

    // If this project were intended for real use, the following methods would also be tested
    // TODO: Create test cases for the following methods
    public void testFindBrokerByIdentifier() {
    }

    public void testFindPersonById() {
    }

    public void testFindPersonIdByPersonCode() {
    }

    public void testFindAssetByCode() {
    }

    public void testFindAssetById() {
    }

    public void testFindAssetIdByAssetCode() {
    }

    public void testFindEquityById() {
    }

    public void testFindStateById() {
    }

    public void testFindPortfolioByCode() {
    }

    public void testFindStateIdByState() {
    }

    public void testFindCountryById() {
    }

    public void testFindCountryIdByCountry() {
    }

    public void testFindEmailById() {
    }
}