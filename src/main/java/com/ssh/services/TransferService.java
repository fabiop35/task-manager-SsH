package com.ssh.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.exceptions.AccountNotFoundException;
import com.ssh.model.Account;
import com.ssh.repositories.AccountRepository;

@Service
public class TransferService {

    static final Logger logger = Logger.getLogger(TransferService.class.getName());
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {

        logger.info(">>> INIT TRANSACTION <<<");
        logger.info(">findAccountById(idSender)<");
        Account sender = accountRepository.findById(idSender)
                .orElseThrow(() -> new AccountNotFoundException());
        logger.info(">findAccountById(idReceive)r<");
        Account receiver = accountRepository.findById(idReceiver)
                .orElseThrow(() -> new AccountNotFoundException());
        logger.info(">getAmount()-sender<");
        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        logger.info(">-senderNewAmount: "+senderNewAmount+" <");
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);
        logger.info(">-receiverNewAmount: "+receiverNewAmount+" <");
        accountRepository.changeAmount(idSender, senderNewAmount);
        logger.info(">changeAmount()-receiver<");
        accountRepository.changeAmount(idReceiver, receiverNewAmount);
        //logger.info(">>> END <<<");
        //throw new RuntimeException("Oh no! Something went wrong!");
        logger.info(">>> END <<<");
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
