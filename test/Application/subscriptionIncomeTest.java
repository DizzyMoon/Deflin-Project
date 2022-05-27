
package Application;

import members.Junior;
import members.Member;
import members.Senior;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class subscriptionIncomeTest {

    //Arrange
    ArrayList<Member> memberArrayList = new ArrayList<Member>(); //Dummylist of members
    int i = memberArrayList.size() - 1; //Length of dummylist (-1 to avoid off by one)


    //Act

    @org.junit.jupiter.api.Test
    int subscription(int i) {


        int subscription = 0;
        int subscriptionJunior = 1000;
        int subscriptionSenior = 1600;
        int subscriptionRetired = 1200;
        int subscriptionPassive = 500;


        if (memberArrayList.get(i) instanceof Junior) {
            if (memberArrayList.get(i).getActiveBool()) {
                subscription += subscriptionJunior;
            } else {
                subscription += subscriptionPassive;
            }

        } else if (memberArrayList.get(i) instanceof Senior) {
            LocalDate now = LocalDate.now();
            Period p = Period.between(memberArrayList.get(i).getBirth(), now);
            if (memberArrayList.get(i).getActiveBool()) {
                subscription += subscriptionSenior;
            } else if (p.getYears() >= 60) {
                subscription += subscriptionRetired;
            } else if (!memberArrayList.get(i).getActiveBool()) {
                subscription += subscriptionPassive;
            }
        }
        return subscription;
    }

    @org.junit.jupiter.api.Test
    @Test
    public void subscriptionIncome() {

        //Fill dummylist
        memberArrayList.add(new Junior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, false, true));   // 1
        memberArrayList.add(new Junior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, false, false));  // 2
        memberArrayList.add(new Junior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, true, true));    // 3
        memberArrayList.add(new Junior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, true, false));   // 4
        memberArrayList.add(new Senior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, false, true));   // 5
        memberArrayList.add(new Senior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, true, false));   // 6
        memberArrayList.add(new Senior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, false, true));   // 7
        memberArrayList.add(new Senior("hej", "5252", "H", LocalDate.of(2005, 02, 13), "34234234", "sdfsdfsdfs", true, true, true));    // 8


        int income = 0;
        for (int i = 0; i < memberArrayList.size(); i++) {
            income += subscription(i);
        }

        //Assert
        int expected = 8300;
        int actual = income;


        assertEquals(expected, actual);
    }
}
