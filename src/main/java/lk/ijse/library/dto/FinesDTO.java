package lk.ijse.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FinesDTO {
   private String finesId;
   private String reservationId;
   private String dayLate;
   private double amount;
   private String bookId;
   private String paymentId;

}
