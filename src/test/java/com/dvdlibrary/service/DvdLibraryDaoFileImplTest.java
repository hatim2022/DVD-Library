package com.dvdlibrary.service;

import com.dvdlibrary.dao.DvdLibraryDao;
import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryDuplicateIdException;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryDaoFileImplTest {
    private DvdLibraryService testService;
    private DvdLibraryDao testDao;

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    public DvdLibraryDaoFileImplTest() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("Context.xml");
        testService =
                ctx.getBean("serviceLayer", DvdLibraryServiceImpl.class);
        testDao=ctx.getBean("daoStub",DaoStubImpl.class);
    }

    @BeforeEach
    public void setUp() {

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


        testDao.addDvd(dvdTitle,dvd);

        List<Dvd> allDvds = testDao.getAllDvds();

        assertNotNull(allDvds, "The list of dvds must not null");
        assertEquals(1, allDvds.size(),"List of dvds should have 2 dvd.");

        assertTrue(testDao.getAllDvds().contains(dvd),
                "The list of dvds should include dvd1.");

    }

    @Test
    @Deprecated
    public void testRemoveDvd() throws Exception{
        // Create our first Dvd
        String dvdTitle = "title1";
        Dvd dvd = new Dvd(dvdTitle);

        dvd.setReleaseDate("1990");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");


        testDao.addDvd(dvd.getTitle(),dvd);
        Dvd removedDvd = testDao.removeDvd(dvd.getTitle());

        assertEquals(removedDvd, dvd);

        List<Dvd> allDvds = testDao.getAllDvds();

        assertNotNull( allDvds, "All dvd list should be not null.");
        assertEquals( 1, allDvds.size(), "dvds list should only have 1 dvd.");


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


        Assertions.assertThrows(DvdLibraryDuplicateIdException.class, () -> testService.createDvd(dvd.getTitle(),dvd));


    }



}