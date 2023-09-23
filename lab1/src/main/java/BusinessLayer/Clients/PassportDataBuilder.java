package BusinessLayer.Clients;

import lombok.NonNull;

public class PassportDataBuilder {
    private String passportNumber;
    private String name;
    private String surname;

    @NonNull
    public PassportDataBuilder setPassportNumber(String newPassportNumber){
        passportNumber = newPassportNumber;
        return  this;

    }
    @NonNull
    public PassportDataBuilder setName(String newName){
        name = newName;
        return this;
    }
    @NonNull
    public PassportDataBuilder setSurname(String newSurname){
        surname = newSurname;
        return this;
    }

    public PassportData create(){
      return new PassportData(passportNumber, name, surname);
    }

}
