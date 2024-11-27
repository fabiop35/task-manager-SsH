package com.ssh;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssh.exceptions.AccountNotFoundException;
import com.ssh.model.Account;
import com.ssh.repositories.AccountRepository;
import com.ssh.services.TransferService;


@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTests {

    Logger log = Logger.getLogger(TransferServiceUnitTests.class.getName());

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    @DisplayName("Test the amount is transferred from one account to another if no exception occurs.")
    public void moneyTransferHappyFlow() {
        log.info(">>> moneyTrasnferHappyFlow() <<<");
        //AccountRepository accountRepository = mock(AccountRepository.class);
        //TransferService transferService = new TransferService(accountRepository);

        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));
        log.log(Level.INFO, "serder.id: {0}", sender.getId());

        Account destination = new Account();
        destination.setId(2);
        destination.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));

        given(accountRepository.findById(destination.getId()))
                .willReturn(Optional.of(destination));

        transferService.transferMoney(sender.getId(), destination.getId(), new BigDecimal(100));

        verify(accountRepository).changeAmount(1, new BigDecimal(900));
        verify(accountRepository).changeAmount(2, new BigDecimal(1100));

    }

    @Test
    @DisplayName("Test that the method throws an AccountNotFountExceptio.")
    public void moneyTransferDestinationAccountNotFountFlow() {
        log.info(">>> moneyTransferDestinationAccountNotFountFlow() <<<");

        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));
        log.log(Level.INFO, "serder.id: {0}", sender.getId());

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));

        given(accountRepository.findById(2L))
                .willReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> transferService.transferMoney(1, 2, new BigDecimal(1000)));
        
        verify(accountRepository, never()).changeAmount(anyLong(), any());
    }
}
