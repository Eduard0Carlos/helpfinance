package com.helpfinance.api.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpfinance.api.controllers.base.ApiController;
import com.helpfinance.core.data.IHttpResult;
import com.helpfinance.domain.entities.*;
import com.helpfinance.domain.enums.DayOfWeek;
import com.helpfinance.domain.enums.MovimentationCategory;
import com.helpfinance.domain.enums.MovimentationType;
import com.helpfinance.domain.enums.PaymentNetwork;
import com.helpfinance.domain.enums.PaymentType;
import com.helpfinance.infrastructure.repositories.AddressRepository;
import com.helpfinance.infrastructure.repositories.BankRepository;
import com.helpfinance.infrastructure.repositories.InvestmentRepository;
import com.helpfinance.infrastructure.repositories.JobRepository;
import com.helpfinance.infrastructure.repositories.MovimentationRepository;
import com.helpfinance.infrastructure.repositories.PaymentCardRepository;
import com.helpfinance.infrastructure.repositories.RecurrencyRepository;
import com.helpfinance.infrastructure.repositories.UserRepository;
import com.helpfinance.infrastructure.unitOfWork.UnitOfWork;

@RestController
@RequestMapping("api/v1/qa")
public class QAController extends ApiController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecurrencyRepository recurrencyRepository;
    @Autowired
    private MovimentationRepository movimentationRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private InvestmentRepository investmentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Autowired
    private UnitOfWork unitOfWork;

    @PostMapping("database/add/{quantity}")
    IHttpResult post(@PathVariable int quantity) {
        InsertData(quantity);

        unitOfWork.commit();

        return getResult(quantity + " users, banks, jobs, investments, address, paymentCards and movimentations has been added to database");
    }

    private void InsertData(int amount) {
        for (int i = 1; i <= amount; i++) {
            var user = new User("user" + i, Timestamp.valueOf(LocalDateTime.now()), "user" + i + "@gmail.com",
                    "123456");

            userRepository.insert(user);

            InsertBank(user.getId());
            InsertJob(user.getId());
            InsertInvestment(user.getId());
            InsertAddress(user.getId());
            InsertPaymentCard(user.getId());
            InsertMovimentation(user.getId());
        }
    }

    private void InsertRecurrency(UUID movimentationId) {
        var recurrency = new Recurrency(movimentationId, Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), DayOfWeek.FRIDAY, true, false);
        recurrencyRepository.insert(recurrency);
    }

    private void InsertPaymentCard(UUID userId) {
        var paymentCard = new PaymentCard(userId, "5452569214121234", "Carlos Vieira",
                Timestamp.valueOf(LocalDateTime.now()), "852", PaymentNetwork.MASTERCARD, PaymentType.CREDIT);
        paymentCardRepository.insert(paymentCard);
    }

    private void InsertMovimentation(UUID userId) {
        var movimentation = new Movimentation(userId, "Mercado", 200, MovimentationCategory.FOOD,
                MovimentationType.OUTGOING);
        movimentationRepository.insert(movimentation);

        InsertRecurrency(movimentation.getId());
    }

    private void InsertJob(UUID userId) {
        var job = new Job(userId, "TSystems", "Software Analyst", 99999);
        jobRepository.insert(job);

    }

    private void InsertInvestment(UUID userId) {
        var investment = new Investment(userId, "MXRF11", 200);
        investmentRepository.insert(investment);
    }

    private void InsertAddress(UUID userId) {
        var address = new Address(userId, "605900", "86", "Osaimon", "Foleza", "CE", "Brasil");
        addressRepository.insert(address);
    }

    private void InsertBank(UUID userId) {
        var bank = new Bank(userId, "Itau", 12453, 22, "integrationToken");
        bankRepository.insert(bank);
    }
}
