package com.dvdlibrary;

import com.dvdlibrary.controller.DvdLibraryController;
import com.dvdlibrary.dao.DvdLibraryAuditDao;
import com.dvdlibrary.dao.DvdLibraryAuditDaoFileImpl;
import com.dvdlibrary.dao.DvdLibraryDao;
import com.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.dvdlibrary.service.DvdLibraryService;
import com.dvdlibrary.service.DvdLibraryServiceImpl;
import com.dvdlibrary.ui.DvdLibraryView;
import com.dvdlibrary.ui.UserIO;
import com.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {



            UserIO userIO=new UserIOConsoleImpl();
            DvdLibraryView dvdLibraryView=new DvdLibraryView(userIO);
            DvdLibraryDao dvdLibraryDao=new DvdLibraryDaoFileImpl();
            DvdLibraryAuditDao dvdLibraryAuditDao=new DvdLibraryAuditDaoFileImpl();

            DvdLibraryService dvdLibraryService=new DvdLibraryServiceImpl(dvdLibraryDao,dvdLibraryAuditDao);
            DvdLibraryController dvdLibraryController=new DvdLibraryController(dvdLibraryView,dvdLibraryService);
            dvdLibraryController.run();


    }
}
