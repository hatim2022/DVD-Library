package com.dvdlibrary.dao;

import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryDuplicateIdException;
import com.dvdlibrary.service.DvdLibraryService;
import com.dvdlibrary.service.DvdLibraryServiceImpl;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryDaoFileImplTest {
    DvdLibraryDao testDao;
    DvdLibraryService testService;
    DvdLibraryAuditDao dvdLibraryAuditDao;

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
            dvdLibraryAuditDao=new DvdLibraryAuditDaoFileImpl();
            testService=new DvdLibraryServiceImpl(testDao,dvdLibraryAuditDao);

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
        dvd.setMpaaRating("R");
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");


        //  Add the Dvd to the DAO
        testDao.addDvd(dvdId,dvd);
        // Get the Dvd from the DAO
        Dvd retrievedDvd = testDao.getDvd(dvdId);

        // Check the data is equal

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
        String dvdTitle = "title1";
        Dvd dvd = new Dvd(dvdTitle);

        dvd.setReleaseDate("1990");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");

        // Create our second Dvd
        String dvdTitle2 = "title2";
        Dvd dvd2 = new Dvd(dvdTitle2);

        dvd2.setReleaseDate("1990");
        dvd2.setMpaaRating("R");
        dvd2.setDirectorName("name");
        dvd2.setStudio("studio");
        dvd2.setUserNote("note1");

        testDao.addDvd(dvdTitle,dvd);
        testDao.addDvd(dvdTitle2,dvd2);

        List<Dvd> allDvds = testDao.getAllDvds();

        assertNotNull(allDvds, "The list of dvds must not null");
        assertEquals(2, allDvds.size(),"List of dvds should have 2 dvd.");

        assertTrue(testDao.getAllDvds().contains(dvd),
                "The list of dvds should include dvd1.");
        assertTrue(testDao.getAllDvds().contains(dvd2),
                "The list of students should include dvd2.");

    }

    @Test
    public void testRemoveDvd() throws Exception{
        // Create our first Dvd
        String dvdTitle = "title1";
        Dvd dvd = new Dvd(dvdTitle);

        dvd.setReleaseDate("1990");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");

        // Create our second Dvd
        String dvdTitle2 = "title2";
        Dvd dvd2 = new Dvd(dvdTitle2);

        dvd2.setReleaseDate("2005");
        dvd2.setMpaaRating("R");
        dvd2.setDirectorName("name2");
        dvd2.setStudio("studio2");
        dvd2.setUserNote("note2");


        testDao.addDvd(dvd.getTitle(),dvd);
        testDao.addDvd(dvd2.getTitle(),dvd2);

        Dvd removedDvd = testDao.removeDvd(dvd.getTitle());

        assertEquals(removedDvd, dvd);

        List<Dvd> allDvds = testDao.getAllDvds();

        assertNotNull( allDvds, "All dvd list should be not null.");
        assertEquals( 1, allDvds.size(), "dvds list should only have 1 dvd.");

        assertFalse( allDvds.contains(dvd));
        assertTrue( allDvds.contains(dvd2));

        removedDvd = testDao.removeDvd(dvd2.getTitle());

        assertEquals( removedDvd,dvd2);

        allDvds = testDao.getAllDvds();

        assertTrue( allDvds.isEmpty(), "The retrieved list of dvds should be empty.");

        Dvd retrievedStudent = testDao.getDvd(dvd.getTitle());
        assertNull(retrievedStudent, "Ada was removed, should be null.");

        retrievedStudent = testDao.getDvd(dvd2.getTitle());
        assertNull(retrievedStudent, "Charles was removed, should be null.");

    }

    @Test
    public void testCreateDuplicateIdDvd() throws Exception {
        // Create our first Dvd
        String dvdId = "0001";
        Dvd dvd = new Dvd(dvdId);
        dvd.setTitle("title1");
        dvd.setReleaseDate("1990");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");

        // Create our second Dvd
        String dvdId2 = "0001";
        Dvd dvd2 = new Dvd(dvdId2);
        dvd2.setTitle("title2");
        dvd2.setReleaseDate("2005");
        dvd2.setMpaaRating("R");
        dvd2.setDirectorName("name2");
        dvd2.setStudio("studio2");
        dvd2.setUserNote("note2");

        testService.createDvd(dvd.getTitle(),dvd);


        Assertions.assertThrows(DvdLibraryDuplicateIdException.class, () -> testService.createDvd(dvd2.getTitle(),dvd2));


    }



}