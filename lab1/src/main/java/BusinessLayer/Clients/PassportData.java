package BusinessLayer.Clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NonNull
public class PassportData {
    private String regex = new String ("^(\\d{10})");
    private String name;
    private String surname;
    private String passportNumber;
    public PassportData(String newPassportNumber, String newName, String NewSurname)
    {
        passportNumber = newPassportNumber;
        name = newName;
        surname = NewSurname;
    }



}
