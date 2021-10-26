package com.dvdlibrary.dao;

import com.dvdlibrary.dto.Dvd;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryDaoFileImplTest {
    DvdLibraryDao testDao;

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        try {
            String testFile = "testroster.txt";
            // Use the FileWriter to quickly blank the file
            new FileWriter(testFile);
            testDao = new DvdLibraryDaoFileImpl(testFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGetDvd() throws Exception {
        // Create our method test inputs
        String dvdId = "0001";
        Dvd dvd = new Dvd(dvdId);
        dvd.setTitle("title1");
        dvd.setReleaseDate("1990");
        dvd.setMpaaRating(1.8f);
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");


        //  Add the Dvd to the DAO
        testDao.addDvd(dvdId,dvd);
        // Get the Dvd from the DAO
        Dvd retrievedDvd = testDao.getDvd(dvdId);

        // Check the data is equal
        assertEquals(dvd.getId(), retrievedDvd.getId(), "Checking Dvd id.");
        assertEquals(dvd.getTitle(), retrievedDvd.getTitle(), "Checking Dvd title.");
        assertEquals(dvd.getReleaseDate(), retrievedDvd.getReleaseDate(), "Checking Dvd releaseDate.");
        assertEquals(dvd.getMpaaRating(), retrievedDvd.getMpaaRating(), "Checking Dvd mpaaRating.");
        assertEquals(dvd.getDirectorName(), retrievedDvd.getDirectorName(), "Checking Dvd DirectorName.");
        assertEquals(dvd.getStudio(), retrievedDvd.getStudio(), "Checking Dvd Studio name.");
        assertEquals(dvd.getUserNote(), retrievedDvd.getUserNote(), "Checking Dvd User note.");

    }

    @Test
    public void testAddGetAllDvd() throws Exception {
        // Create our first Dvd
        String dvdId = "0001";
        Dvd dvd = new Dvd(dvdId);
        dvd.setTitle("title1");
        dvd.setReleaseDate("1990");
        dvd.setMpaaRating(1.8f);
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");

        // Create our second Dvd
        String dvdId2 = "0002";
        Dvd dvd2 = new Dvd(dvdId2);
        dvd2.setTitle("title1");
        dvd2.setReleaseDate("1990");
        dvd2.setMpaaRating(1.8f);
        dvd2.setDirectorName("name");
        dvd2.setStudio("studio");
        dvd2.setUserNote("note1");

        // Add both our students to the DAO
        testDao.addDvd(dvdId,dvd);
        testDao.addDvd(dvdId2,dvd2);

        // Retrieve the list of all students within the DAO
        List<Dvd> allDvds = testDao.getAllDvds();

        // First check the general contents of the list
        assertNotNull(allDvds, "The list of dvds must not null");
        assertEquals(2, allDvds.size(),"List of dvds should have 2 dvd.");

        // Then the specifics
        assertTrue(testDao.getAllDvds().contains(dvd),
                "The list of dvds should include dvd1.");
        assertTrue(testDao.getAllDvds().contains(dvd2),
                "The list of students should include dvd2.");

    }

}