package com.dvdlibrary;

import com.dvdlibrary.controller.DvdLibraryController;
import com.dvdlibrary.dao.DvdLibraryDao;
import com.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.dvdlibrary.exception.DvdLibraryDaoException;
import com.dvdlibrary.ui.DvdLibraryView;
import com.dvdlibrary.ui.UserIO;
import com.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {



            UserIO userIO=new UserIOConsoleImpl();
            DvdLibraryView dvdLibraryView=new DvdLibraryView(userIO);
            DvdLibraryDao dvdLibraryDao=new DvdLibraryDaoFileImpl();
            DvdLibraryController dvdLibraryController=new DvdLibraryController(dvdLibraryView,dvdLibraryDao);
            dvdLibraryController.run();


    }
}
