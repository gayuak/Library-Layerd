package lk.ijse.library.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookTm {
    private String Code;
            private String name;
            private double Unit_Price;
            private int Qty_On_Hand;

}
