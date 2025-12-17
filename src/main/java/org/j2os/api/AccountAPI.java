package org.j2os.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.j2os.entity.Account;
import org.j2os.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
public class AccountAPI {
    private final AccountService accountService;

    @PostMapping("/save.do")
    public Account save(@RequestBody Account account){
        log.info("save");
        accountService.save(account);
        return account;
    }

    @GetMapping("/fullFetch.do")
    public Map<String, Object> fullFetch(@Valid Account account){
        return accountService.fullFetch(account);
    }

    @GetMapping("/findByEmail/{email}")
    public List<Account> findByEmail(@PathVariable String email){
        return accountService.finfByEmail(email);
    }

    @GetMapping("/getAccounts.do")
    public List<Account> getAccounts(Long balance){
        return accountService.getAccounts(balance);
    }
}
