package Application;

import UI.UserInterface;
import event.EventList;
import event.Swimmeet;
import members.*;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Achievement.*;

import filehandling.FileHandler;

public class Controller {
    Login login = new Login();
    AchievementList achievementList = new AchievementList();
    private boolean running = true;
    private FileHandler fileHandler = new FileHandler();
    Scanner sc = new Scanner(System.in);
    UserInterface ui = new UserInterface();
    MemberManager memberManager = new MemberManager();
    EventList el = new EventList();
    Creator cr = new Creator();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy");

    public Controller() throws FileNotFoundException {
    }

    public void login() throws FileNotFoundException {
        if (fileHandler.hasSavedData()) {
            memberManager.loadMembersFromCSV(); //Loads members from /src/data/members.csv
            achievementList.setList(cr.loadAchievements());
            sortTempAchievementList(cr.loadAchievements());
        }

        boolean badLogin = true;


        while (badLogin) {
            switch (login.loginScreen()) {
                case -1 -> ui.badLogin();
                case 1 -> {
                    run();
                    badLogin = false;
                }
                case 2 -> {
                    træner();
                    badLogin = false;
                }
                case 3 -> {
                    kasserer();
                    badLogin = false;
                }
                case 4 -> {
                    formand();
                    badLogin = false;
                }
            }
        }
    }

    public void run() throws FileNotFoundException {

        while (running) {
            ui.startupMenu();
            String input = sc.nextLine();
            switch (input) {
                case "1" -> formand();
                case "2" -> kasserer();
                case "3" -> træner();
                case "4" -> login.changePassword("admin");
                case "5" -> login();
                case "6" -> exit();
                default -> ui.badInput();
            }
        }
    }

    public void formand() throws FileNotFoundException {
        while (running) {

            ui.formandUI();
            String input = sc.nextLine();
            switch (input) {
                case "1" -> chairmanNewMember();
                case "2" -> removeMember();
                case "3" -> {
                    try {
                        ui.typeMemberIDForNameChange();
                        String memberID = sc.next();
                        findMember(memberID).toggleStatus();
                        String activeMember = findMember(memberID).getActive();
                        ui.statusAltered(activeMember);
                    } catch (NullPointerException e) {
                        ui.badInput();
                    }
                }
                case "4" -> {
                    try {
                        ui.typeMemberIDForNameChange();
                        String memberID = sc.next();
                        findMember(memberID).toggleCompetitive();
                        String competionSwimmer = findMember(memberID).getCompetitive();
                        ui.typeAltered(competionSwimmer);
                    } catch (NullPointerException e) {
                        ui.badInput();
                    }
                }
                case "5" -> {
                    ui.typeMemberIDForNameChange();
                    String memberID = sc.next();                                  // nextLine crasher i runtime 1
                    ui.nameChange();

                    String newName = sc.useDelimiter("\n").next();
                    findMember(memberID).setName(newName);
                    fileHandler.saveMembersToCSV(memberManager.getMemberList());
                }
                case "6" -> ui.printMemberListTable(memberManager.getMemberList());
                case "7" -> {
                    fileHandler.saveMembersToCSV(memberManager.getMemberList());
                    login();
                }
                case "8" -> login.changePassword("formand");
                case "9" -> exit();
                default -> ui.badInput();
            }
        }
    }

    public void kasserer() throws FileNotFoundException {
        while (running) {

            ui.kassererUI();
            String input = sc.nextLine();
            switch (input) {
                case "1" -> subscriptionIncome();
                case "2" -> {
                    sortByArrears();
                    ui.printArrearsListTable(memberManager.getMemberList());
                }
                case "3" -> {
                    try {
                        ui.typeMemberIDForNameChange();
                        String memberID = sc.next();
                        findMember(memberID).toggleArrears();
                        String subscriptionPayment = findMember(memberID).getArrears();
                        ui.statusAltered(subscriptionPayment);
                        fileHandler.saveMembersToCSV(memberManager.getMemberList());
                    } catch (NullPointerException e) {
                        ui.badInput();
                    }
                }
                case "4" -> login();
                case "5" -> login.changePassword("kasserer");
                case "6" -> exit();
                default -> ui.badInput();
            }
        }
    }

    public void træner() throws FileNotFoundException {
        while (running) {
            ui.traenerUI();
            String input = sc.nextLine();
            switch (input) {
                case "1" -> coachCreateNewAchievement();
                case "2" -> coachNewEvent();
                case "3" -> ui.printEventListTable(el.getList());
                case "4" -> {
                    ui.top5AgeUI();
                    int ageChoice = sc.nextInt();
                    sc.nextLine(); //Scannerbug fix
                    if (ageChoice == 1) {
                        top5Gender(memberManager.sortSenior());
                        memberManager.getSeniorList().removeAll(memberManager.getSeniorList());
                    } else if (ageChoice == 2) {
                        top5Gender(memberManager.sortJunior());
                    }
                }
                case "5" -> coachAssignAthleteToComp();
                case "6" -> login();
                case "7" -> login.changePassword("træner");
                case "8" -> exit();
                default -> ui.badInput();
            }
        }
    }

    public void coachAssignAthleteToComp() throws FileNotFoundException {
        ui.printEventListTable(el.getList());
        ui.chooseComp();
        int selection = sc.nextInt();
        Swimmeet chosen = el.getList().get(selection - 1);
        ui.chooseDiscipline();
        selection = sc.nextInt();
        String discipline;
        switch (selection) {
            case 1 -> discipline = "Backcrawl";
            case 2 -> discipline = "Brystsvømning";
            case 3 -> discipline = "Butterfly";
            default -> discipline = "Crawl";
        }
        //getTop5 (chosen.getGenderCategory(), chosen.getLeague())
        ui.inputDistance();
        int distance = sc.nextInt();
        ui.addProspect();
        ui.inputSwimmerID();
        String competitor = sc.next();
        Member swimmer = findMember(competitor);
        chosen.assignCompetitor(swimmer, chosen.chosenDiscipline(selection));
        ui.competitorReady(discipline, distance);
    }

    public void coachCreateNewAchievement() throws FileNotFoundException {

        Discipline discipline = Discipline.BUTTERFLY;
        boolean competition = false;

        ui.writeDateForAchievement();

        LocalDate newDate = null;

        boolean pass = true;
        while (pass) {
            try {
                newDate = truncateToDate(transformToDate(sc.useDelimiter("\n").next()));
                pass = false;
            } catch (NumberFormatException | DateTimeException e) {
                ui.badInput();
            }
        }

        pass = true;

        while (pass) {
            ui.writeDiscipline();
            String disciplineString = sc.next();
            switch (disciplineString) {
                case "butterfly" -> {
                    discipline = Discipline.BUTTERFLY;
                    pass = false;
                }
                case "crawl" -> {
                    discipline = Discipline.CRAWL;
                    pass = false;
                }
                case "ryg" -> {
                    discipline = Discipline.BACKSTROKE;
                    pass = false;
                }
                case "bryst" -> {
                    discipline = Discipline.BREASTSTROKE;
                    pass = false;
                }
                default -> ui.badInput();
            }
        }


        ui.inputSwimmerID();
        String memberID = sc.next();

        ui.inputDistance();
        int distance = sc.nextInt();

        ui.inputTime();
        String timeString = sc.next();
        int minutes = Integer.parseInt(timeString.substring(0, timeString.indexOf(":")));
        int seconds = Integer.parseInt(timeString.substring(timeString.indexOf(":") + 1));
        int year = newDate.getYear();
        int month = newDate.getMonthValue();
        int day = newDate.getDayOfMonth();
        int hours = 0;

        LocalDateTime time = LocalDateTime.of(year, month, day, hours, minutes, seconds);

        String event;
        int placement;
        String medal = "";

        ui.achievementFromEvent();
        String addEvent = sc.next();

        if (addEvent.equalsIgnoreCase("ja")) {
            competition = true;
        }
        if (competition) {
            ui.writeEvent();
            event = sc.useDelimiter("\n").next();

            ui.writePlacement();
            placement = sc.nextInt();

            ui.addCommendation();
            String choice = sc.useDelimiter("\n").next();
            if (choice.equalsIgnoreCase("ja")) {
                ui.commDescr();
                int medalChoice = sc.nextInt();
                switch (medalChoice) {
                    case 1 -> medal = "Guld";
                    case 2 -> medal = "Sølv";
                    case 3 -> medal = "Bronze";
                    default -> {
                        ui.specialCommDescr();
                        medal = sc.useDelimiter("\n").next();
                    }
                }
            }
        } else {
            event = "træning";
            placement = -1;
            medal = "ingen";
        }
            Achievement achievement = new Achievement(memberID, discipline, time, distance, placement, medal, event);
            achievementList.getAchievements().add(achievement);
            fileHandler.saveAchievementsToCSV(achievementList);
        }

    public void top5Gender(ArrayList<Member> member) throws FileNotFoundException {
        boolean running = true;
        while (running) {
            ui.top5GenderUI();
            int genderChoice = sc.nextInt();
            sc.nextLine(); //Scannerbug fix
            if (genderChoice == 1) {
                memberManager.mensList(member);
                top5Style(memberManager.getMen());
                memberManager.getMen().removeAll(memberManager.getMen());
                running = false;
            } else if (genderChoice == 2) {
                memberManager.womensList(member);
                top5Style(memberManager.getWomen());
                memberManager.getWomen().removeAll(memberManager.getWomen());
                running = false;
            } else {
                ui.badInput();
            }
        }
    }

    public void top5Style(ArrayList<Member> member) {
        boolean running = true;
        while (running) {
            try {
                ui.top5StyleUI();
                int styleChoice = sc.nextInt();
                sc.nextLine(); //Scannerbug fix
                ui.printTop5(sortBy(styleChoice, member));
                emptyTop3();
                running = false;
            } catch (IndexOutOfBoundsException e) {
                ui.noResult();
                running = false;
            }
            sortByName();
        }
    }

    public void exit() {
        running = false;
    }

    public void removeMember() throws FileNotFoundException {
        ui.typeMemberIDForRemove();
        String UID = sc.nextLine();
        memberManager.removeMember(UID);
    }

    public LocalDate truncateToDate(LocalDateTime input) {
        int d = input.getDayOfMonth();
        int m = input.getMonthValue();
        int y = input.getYear();
        LocalDate newDate = LocalDate.of(y, m, d);
        return newDate;
    }

    public LocalDateTime transformToDate(String dateFormat) {
        int first = dateFormat.indexOf(".");
        int second = dateFormat.lastIndexOf(".");
        int date = Integer.valueOf(dateFormat.substring(0, first));
        int month = Integer.valueOf(dateFormat.substring(first + 1, second));
        int year = Integer.valueOf(dateFormat.substring(second + 1));
        LocalDateTime newDate = LocalDateTime.of(year, month, date, 0, 0);
        return newDate;
    }

    public void chairmanNewMember() throws FileNotFoundException {
        boolean competition = false;
        boolean arrears = true;
        boolean pass = true;
        String gender = "";
        String phoneNumber = "";
        LocalDate newDate = LocalDate.now();
        String email = "";
        ui.memberName();
        String name = sc.nextLine();
        ui.gender();
        while (pass) {
            gender = sc.next();
            if (gender.equalsIgnoreCase("H") || gender.equalsIgnoreCase("D")) {
                pass = false;
            } else {
                ui.badInput();
            }
        }
        pass = true;
        ui.dateOfBirth();
        while (pass) {
            try {
                newDate = truncateToDate(transformToDate(sc.useDelimiter("\n").next()));
                pass = false;
            } catch (NumberFormatException | DateTimeException e) {
                ui.badInput();
            }
        }
        pass = true;
        ui.competitive();
        while (pass) {
            String competitive = sc.next();
            if (competitive.equalsIgnoreCase("ja")) {
                competition = true;
                pass = false;
            } else if (competitive.equalsIgnoreCase("nej")) {
                competition = false;
                pass = false;
            } else {
                ui.badInput();
            }
        }
        pass = true;
        ui.phoneNumber();
        while (pass) {
            phoneNumber = sc.next();
            if (phoneNumber.length() > 12 || phoneNumber.length() < 8) {
                ui.badInput();
            } else {
                pass = false;
            }
        }
        pass = true;
        ui.email();
        while (pass) {
            email = sc.next();
            int atFound = email.indexOf("@");
            int domainDot = email.lastIndexOf(".");
            String domain = email.substring(domainDot + 1);
            if (atFound == -1) {
                ui.badInput();
            } else {
                if (domainDot > atFound + 1) {
                    if ((domain.length() > 1) && (domain.length() < 5)) {
                        pass = false;
                    } else {
                        ui.badInput();
                    }
                }
            }
        }
        String tempID = " ";

        memberManager.createNewMember(name, tempID, gender, newDate, phoneNumber, email, competition, arrears, true);
        sortByName();
    }

    public int subscription(int i) {
        int subscription = 0;
        int subscriptionJunior = 1000;
        int subscriptionSenior = 1600;
        int subscriptionRetired = 1200;
        int subscriptionPassive = 500;


        if (memberManager.getList().get(i) instanceof Junior) {
            if (memberManager.getList().get(i).getActiveBool()) {
                subscription += subscriptionJunior;
            } else {
                subscription += subscriptionPassive;
            }

        } else if (memberManager.getList().get(i) instanceof Senior) {
            LocalDate now = LocalDate.now();
            Period p = Period.between(memberManager.getList().get(i).getBirth(), now);
            if (memberManager.getList().get(i).getActiveBool()) {
                subscription += subscriptionSenior;
            } else if (p.getYears() >= 60) {
                subscription += subscriptionRetired;
            } else if (!memberManager.getList().get(i).getActiveBool()) {
                subscription += subscriptionPassive;
            }
        }
        return subscription;
    }

    public void subscriptionIncome() {
        int income = 0;
        for (int i = 0; i < memberManager.getMemberList().getList().size(); i++) {
            income += subscription(i);
        }
        System.out.println("| Årlig indkomst fra medlemskontigenter: |");
        System.out.println("| \t" + income + " kroner\t |");
    }

    public void coachNewEvent() {
        ui.promptEventName();
        String eventName = sc.useDelimiter("\n").next();
        ui.promptSubdivisionCode();
        boolean SDCvalid = false;
        String category = "";
        String categoryIN = "";
        boolean league = false;
        String leagueIN = "";

        while (!SDCvalid) {
            String input = sc.next();
            if (input.length() == 2) {
                categoryIN = input.substring(0, 1);
                leagueIN = input.substring(1, 2);
                if (categoryIN.equalsIgnoreCase("h") || categoryIN.equalsIgnoreCase("d")) {
                    if (leagueIN.equalsIgnoreCase("j")) {
                        category = categoryIN;
                        league = true;
                        SDCvalid = true;
                    } else if (leagueIN.equalsIgnoreCase("s")) {
                        category = categoryIN;
                        league = false;
                        SDCvalid = true;
                    } else {
                        ui.badInput();
                    }
                }
            } else {
                ui.badInput();
            }
        }
        ui.promptEventDate();
        LocalDate tempTime = truncateToDate(transformToDate(sc.useDelimiter("\n").next()));
        ui.promptEventTime();
        int year = tempTime.getYear();
        int month = tempTime.getMonthValue();
        int day = tempTime.getDayOfMonth();

        String timeString = sc.next();
        int colonIndex = timeString.indexOf(":");
        int hours = Integer.parseInt(timeString.substring(0, colonIndex));
        int minutes = Integer.parseInt(timeString.substring(colonIndex + 1, timeString.length()));

        LocalDateTime eventTime = LocalDateTime.of(year, month, day, hours, minutes);

        el.getList().add(cr.createNewEvent(eventName, category, league, eventTime));
    }

    public void sortByName() {
        Collections.sort(memberManager.getList(), (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }

    public ArrayList<Member> sortBy(int sort, ArrayList<Member> memberIn) {

        ArrayList<Member> member = memberIn;

        switch (sort) {
            case 1 -> {
                //Frasortering af medlemmer uden resultater indenfor kategorien
                for (int i = 0; i < member.size(); i++) {
                    if (member.get(i).getBackstrokeResults().size() == 0) {
                        member.remove(member.get(i));
                        i--;
                    }
                }
                //Sortering af bedste tider indenfor kategori for hver svømmer
                for (int i = 0; i < member.size(); i++) {
                    Collections.sort((List<Achievement>) member.get(i).getBackstrokeResults(), Comparator.comparingInt(o -> o.getTime().getSecond()));
                }
                //Indv. oprettelse af top3 for hver svømmer
                top3backstroke(member);
                //Medlemsliste sorteres efter hvem der har den hurtigste tid på indv. top3
                Collections.sort((List<Member>) member, (o1, o2) -> o1.getTempTop3().get(0).compareTo(o2.getTempTop3().get(0)));
            }
            case 2 -> {
                //Frasortering af medlemmer uden resultater indenfor kategorien
                for (int i = 0; i < member.size(); i++) {
                    if (member.get(i).getBreaststrokeResults().size() == 0) {
                        member.remove(member.get(i));
                        i--;
                    }
                }
                //Sortering af bedste tider indenfor kategori for hver svømmer
                for (int i = 0; i < member.size(); i++) {
                    Collections.sort((List<Achievement>) member.get(i).getBreaststrokeResults(), Comparator.comparingInt(o -> o.getTime().getSecond()));
                }
                //Indv. oprettelse af top3 for hver svømmer
                top3breaststroke(member);
                //Medlemsliste sorteres efter hvem der har den hurtigste tid på indv. top3
                Collections.sort((List<Member>) member, (o1, o2) -> o1.getTempTop3().get(0).compareTo(o2.getTempTop3().get(0)));
            }
            case 3 -> {
                //Frasortering af medlemmer uden resultater indenfor kategorien
                for (int i = 0; i < member.size(); i++) {
                    if (member.get(i).getButterflyResults().size() == 0) {
                        member.remove(member.get(i));
                        i--;
                    }
                }
                //Sortering af bedste tider indenfor kategori for hver svømmer
                for (int i = 0; i < member.size(); i++) {
                    Collections.sort((List<Achievement>) member.get(i).getButterflyResults(), Comparator.comparingInt(o -> o.getTime().getSecond()));
                }
                //Indv. oprettelse af top3 for hver svømmer
                top3butterfly(member);
                //Medlemsliste sorteres efter hvem der har den hurtigste tid på indv. top3
                Collections.sort((List<Member>) member, (o1, o2) -> o1.getTempTop3().get(0).compareTo(o2.getTempTop3().get(0)));
            }
            case 4 -> {
                //Frasortering af medlemmer uden resultater indenfor kategorien
                for (int i = 0; i < member.size(); i++) {
                    if (member.get(i).getCrawlResults().size() == 0) {
                        member.remove(member.get(i));
                        i--;
                    }
                }
                //Sortering af bedste tider indenfor kategori for hver svømmer
                for (int i = 0; i < member.size(); i++) {
                    Collections.sort((List<Achievement>) member.get(i).getCrawlResults(), Comparator.comparingInt(o -> o.getTime().getSecond()));
                }
                //Indv. oprettelse af top3 for hver svømmer
                top3crawl(member);
                //Medlemsliste sorteres efter hvem der har den hurtigste tid på indv. top3
                Collections.sort((List<Member>) member, (o1, o2) -> o1.getTempTop3().get(0).compareTo(o2.getTempTop3().get(0)));
            }
        }
        return member;
    }

    public void sortByArrears() {
        Collections.sort((List<Member>) memberManager.getList(), ((o1, o2) -> o2.getArrears().compareTo(o1.getArrears())));
    }

    public Member findMember(String userID) throws FileNotFoundException {
        String memberID = userID;
        boolean found = false;
        int searchedElements = 0;

        for (Member member : memberManager.getList()) {
            if (member.getMemberID().equals(memberID)) {
                return member;
            }
            searchedElements++;
            if (searchedElements == memberManager.getList().size() && !found) {
                ui.elementDoesNotExist();
            }
        }

        return null;
    }

    public void top3crawl(ArrayList<Member> member) {
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getCrawlResults().size() < 3) {
                for (int o = 0; o < member.get(i).getCrawlResults().size(); o++) {
                    member.get(i).setTempTop3(member.get(i).getCrawlResults().get(o));
                }
            } else {
                for (int o = 0; o < 3; o++) {
                    member.get(i).setTempTop3(member.get(i).getCrawlResults().get(o));
                }
            }

        }
    }

    public void top3butterfly(ArrayList<Member> member) {
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getButterflyResults().size() < 3) {
                for (int o = 0; o < member.get(i).getButterflyResults().size(); o++)
                    member.get(i).setTempTop3(member.get(i).getButterflyResults().get(o));
            } else {
                for (int o = 0; o < 3; o++)
                    member.get(i).setTempTop3(member.get(i).getButterflyResults().get(o));
            }
        }
    }

    public void top3backstroke(ArrayList<Member> member) {
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getBackstrokeResults().size() < 3) {
                for (int o = 0; o < member.get(i).getBackstrokeResults().size(); o++) {
                    member.get(i).setTempTop3(member.get(i).getBackstrokeResults().get(o));
                }
            } else {
                for (int o = 0; o < 3; o++) {
                    member.get(i).setTempTop3(member.get(i).getBackstrokeResults().get(o));
                }
            }
        }
    }

    public void top3breaststroke(ArrayList<Member> member) {
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getBreaststrokeResults().size() < 3) {
                for (int o = 0; o < member.get(i).getBreaststrokeResults().size(); o++)
                    member.get(i).setTempTop3(member.get(i).getBreaststrokeResults().get(o));
            } else {
                for (int o = 0; o < 3; o++)
                    member.get(i).setTempTop3(member.get(i).getBreaststrokeResults().get(o));
            }
        }
    }

    public void sortTempAchievementList(ArrayList<Achievement> achievements) {
        for (int i = 0; i < achievements.size(); i++) {
            for (int o = 0; o < memberManager.getList().size(); o++) {
                if (achievements.get(i).getMemberID().equalsIgnoreCase(memberManager.getList().get(o).getMemberID())) {
                    switch (achievements.get(i).getDiscipline()) {
                        case BACKSTROKE -> memberManager.getList().get(o).setBackstrokeResults(achievements.get(i));
                        case CRAWL -> memberManager.getList().get(o).setCrawlResults(achievements.get(i));
                        case BREASTSTROKE -> memberManager.getList().get(o).setBreastStroke(achievements.get(i));
                        case BUTTERFLY -> memberManager.getList().get(o).setButterflyResults(achievements.get(i));
                    }
                }
            }
        }
    }

    public void emptyTop3() {
        for (int i = 0; i < memberManager.getList().size(); i++) {
            memberManager.getList().get(i).getTempTop3().removeAll(memberManager.getList().get(i).getTempTop3());
        }
    }
}



