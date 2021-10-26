package com.dvdlibrary.dao;


import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryDaoException;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    public static  String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();

    public DvdLibraryDaoFileImpl() {
        ROSTER_FILE = "roster.txt";
    }

    public DvdLibraryDaoFileImpl(String rosterTextFile) {
        ROSTER_FILE = rosterTextFile;
    }

    private Dvd unmarshallDvd(String dvdAsText){

        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdId = dvdTokens[0];

        Dvd dvdFromFile = new Dvd(dvdId);

        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(dvdTokens[2]);
        dvdFromFile.setMpaaRating(Float.valueOf(dvdTokens[3]));
        dvdFromFile.setDirectorName(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserNote(dvdTokens[6]);


        return dvdFromFile;
    }

    private String marshallDvd(Dvd dvd){

        String dvdAsText = dvd.getId() + DELIMITER;

        dvdAsText += dvd.getTitle() + DELIMITER;

        dvdAsText += dvd.getReleaseDate() + DELIMITER;

        dvdAsText += dvd.getMpaaRating() + DELIMITER;

        dvdAsText += dvd.getDirectorName() + DELIMITER;

        dvdAsText += dvd.getStudio() + DELIMITER;

        dvdAsText += dvd.getUserNote() + DELIMITER;


        return dvdAsText;
    }


    /**
     * Writes all dvds in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     *
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    private void writeRoster() throws DvdLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
            String dvdAsText;
            List<Dvd> dvdList = this.getAllDvds();
            for (Dvd currentDvd : dvdList) {
                dvdAsText = marshallDvd(currentDvd);
                out.println(dvdAsText);
                out.flush();
            }
            // Clean up
            out.close();
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save dvd data.", e);
        }


    }


    private void loadRoster() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException ("-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        Dvd currentDvd;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getId(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException {
        loadRoster();
        Dvd prevDvd = dvds.put(dvdId, dvd);
        writeRoster();
        return prevDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadRoster();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryDaoException {
        loadRoster();
        return dvds.get(dvdId);
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryDaoException {
        loadRoster();
        Dvd removedDvd = dvds.remove(dvdId);
        if(removedDvd==null){
            throw new DvdLibraryDaoException("Dvd record not found");
        }else {
            writeRoster();
        }
        return removedDvd;

    }

    @Override
    public Dvd editDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException {
        loadRoster();
        dvds.put(dvdId,dvd);
        writeRoster();
        return dvd;
    }

    @Override
    public Dvd getDvdByTitle(String title) throws DvdLibraryDaoException {
        loadRoster();
        Iterator<Dvd> iterator=dvds.values().iterator();
        Dvd dvd=null;
        while (iterator.hasNext()){
            dvd=iterator.next();
            if(dvd.getTitle().equalsIgnoreCase(title)){
                return dvd;
            }
        }
        return dvd;
    }

}
