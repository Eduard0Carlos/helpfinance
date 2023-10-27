import dao.*;
import entities.*;
import enums.*;
import factories.daoFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        //InsertData(5);

        var userDAO = daoFactory.tryGet(UserDAO.class);

        var allUsers = userDAO.getAll();

        if (allUsers == null)
            return;

        for (var user : allUsers)
            System.out.println(user.toString());

        var getUser = userDAO.get(allUsers.stream().findFirst().orElse(null).getId());

        System.out.println("\r\nUSER by Id:" + getUser);
    }

    private static void InsertData(int amount) {
        for (int i = 1; i <= amount; i++) {
            var userDAO = daoFactory.tryGet(UserDAO.class);
            var user = new User("user" + i, Timestamp.valueOf(LocalDateTime.now()), "user" + i + "@gmail.com", "123456");

            userDAO.insert(user);

            InsertBank(user.getId());
            InsertJob(user.getId());
            InsertInvestment(user.getId());
            InsertAddress(user.getId());
            InsertPaymentCard(user.getId());
            InsertMovimentation(user.getId());
        }
    }

    private static void InsertRecurrency(UUID movimentationId) {
        var recurrencyDAO = daoFactory.tryGet(RecurrencyDAO.class);
        var recurrency = new Recurrency(movimentationId, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), DayOfWeek.FRIDAY, true, false);
        recurrencyDAO.insert(recurrency);
    }

    private static void InsertPaymentCard(UUID userId) {
        var paymentCardDAO = daoFactory.tryGet(PaymentCardDAO.class);
        var paymentCard = new PaymentCard(userId, "5452569214121234", "Carlos Vieira", Timestamp.valueOf(LocalDateTime.now()), "852", PaymentNetwork.MASTERCARD, PaymentType.CREDIT);
        paymentCardDAO.insert(paymentCard);
    }

    private static void InsertMovimentation(UUID userId) {
        var movimentationDAO = daoFactory.tryGet(MovimentationDAO.class);
        var movimentation = new Movimentation(userId, "Mercado", 200, MovimentationCategory.FOOD, MovimentationType.OUTGOING);
        movimentationDAO.insert(movimentation);

        InsertRecurrency(movimentation.getId());
    }

    private static void InsertJob(UUID userId) {
        var jobDAO = daoFactory.tryGet(JobDAO.class);
        var job = new Job(userId, "TSystems", "Software Analyst", 99999);
        jobDAO.insert(job);

    }

    private static void InsertInvestment(UUID userId) {
        var investmentDAO = daoFactory.tryGet(InvestmentDAO.class);
        var investment = new Investment(userId, "MXRF11", 200);
        investmentDAO.insert(investment);
    }

    private static void InsertAddress(UUID userId) {
        var addressDAO = daoFactory.tryGet(AddressDAO.class);
        var address = new Address(userId, "605900", "86", "Osaimon", "Foleza", "CE", "Brasil");
        addressDAO.insert(address);
    }

    private static void InsertBank(UUID userId) {
        var bankDAO = daoFactory.tryGet(BankDAO.class);
        var bank = new Bank(userId, "Itau", 12453, 22, "integrationToken");
        bankDAO.insert(bank);
    }
}