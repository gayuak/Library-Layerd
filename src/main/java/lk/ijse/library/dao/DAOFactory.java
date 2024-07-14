package lk.ijse.library.dao;

import lk.ijse.library.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDAOFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        Book,Catagory,Customer,Employee,Fines,Reservation,ReservationDetail,User
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case Catagory:
                return new CatagoryDAOImpl();
            case User:
                return new UserDAOImpl();
            case Book:
                return new BookDAOImpl();
            case Customer:
                return new CustomerDAOImpl();
            case Employee:
                return new EmployeeDAOImpl();
            case Fines:
                return new FinesDAOImpl();
            case Reservation:
                return new ReservationDAOImpl();
            case ReservationDetail:
                return new ReservationDetailDAOImpl();
            default:
                return null;
        }
    }
}
