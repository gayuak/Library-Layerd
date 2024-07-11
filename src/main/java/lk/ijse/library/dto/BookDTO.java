package lk.ijse.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO {
        private String bookId;
        private String bookName;
        private String catagoryId;
        private double price;
        private int qty;
}
