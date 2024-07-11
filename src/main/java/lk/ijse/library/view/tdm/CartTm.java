package lk.ijse.library.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CartTm {

    private String orderid;
    private String customerid;
    private String bookid;
    private int qty;
    private String returndate;
    private JFXButton btnRemove;

}
