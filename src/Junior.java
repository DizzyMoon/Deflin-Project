import java.util.Date;

public class Junior extends Membership {

    public Junior (String name, Date dateOfBirth, Date regDate, String phone, String email){
        super (name, dateOfBirth, regDate, phone, email/*, backstrokeResults, breaststrokeResults, butterflyResults, crawlResults*/}

    public Senior transitToSenior(Junior majorOfAge) {
        String newCard1 = majorOfAge.getName;
        Date newCard2 = majorOfAge.getDateOfBirth;
        Date newCard3 = majorOfAge.getRegDate;
        String newCard4 = majorOfAge.getphone;
        String newCard5 = majorOfAge.getEmail;
        return Senior(newCard1, newCard2, newCard3, newCard4, newCard5);
    }
}

