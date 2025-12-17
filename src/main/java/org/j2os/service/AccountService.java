package org.j2os.service;

import lombok.RequiredArgsConstructor;
import org.j2os.entity.Account;
import org.j2os.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void save(Account account){
        accountRepository.save(account);
    }

    @Transactional
    public void twoSave(Account account){
        accountRepository.save(account);
    }

    public Map<String , Object> fullFetch(Account account){
        var genericDTO = new HashMap<String, Object>();
        genericDTO.put("findByAccountBalance", accountRepository.findByAccountBalance(account.getAccountBalance()));
        genericDTO.put("findByAccountBalanceLessThan", accountRepository.findByAccountBalanceLessThan(account.getAccountBalance()));
        genericDTO.put("findByAccountBalanceOrAccountOwnerName", accountRepository.findAccountsByAccountBalanceOrAccountOwnerName(account.getAccountBalance(), account.getAccountOwnerName()));

        return genericDTO;
    }

    public List<Account> finfByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public List<Account> getAccounts(Long balance){
        return accountRepository.getAccounts(balance);
    }
}