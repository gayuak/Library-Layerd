package lk.ijse.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
    private String reservationId;
    private String userId;
    private Date reseravtionDate;
    private String bookId;
    private Date returnDate;
    private String CId;

}
