package lk.ijse.library.bo;

import lk.ijse.library.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        BOOK,CUSTOMER,DASHBOARD,FINES,HOME,LOGIN,PAYMENT,RECIEVED,RESERVATION
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case BOOK:
                return new BookBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case FINES:
                return new FinesBOImpl();
            case HOME:
                return new HomeBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case RECIEVED:
                return new ReceivedBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
