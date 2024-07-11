package lk.ijse.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fines {
   private String finesId;
   private String reservationId;
   private String dayLate;
   private double amount;
   private String bookId;
   private String paymentId;

}
