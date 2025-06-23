package ma.enset.ebankbackend.dtos;

import lombok.Data;


@Data
public class CustomerDTO {
    private Long idCustomer;
    private String nameCustomer;
    private String emailCustomer;
}
