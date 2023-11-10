package mvc.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import mvc.domain.Account;
import mvc.dto.AccountCommand;
import mvc.services.AccountServiceImpl;

@RestController
public class AccountController {
    @Autowired
    AccountServiceImpl accountService;

    @GetMapping("/accounts/{accountnumber}")
    public ResponseEntity<?> getAccount(@PathVariable Long accountnumber) {
        Account account = accountService.getAccount(accountnumber);
        if (account == null) {
            return new ResponseEntity<CustomErrorType>(
                    new CustomErrorType("Account with number= " + accountnumber + " is not available"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{accountnumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountnumber) {
        Account account = accountService.getAccount(accountnumber);
        if (account == null) {
            return new ResponseEntity<CustomErrorType>(
                    new CustomErrorType("Account with number= " + accountnumber + " is not available"),
                    HttpStatus.NOT_FOUND);
        }
        accountService.deleteAccount(accountnumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> handlePost(@Valid @RequestBody AccountCommand accountCommand) {

        Account account = accountService.handlePost(accountCommand);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("******************************************");
        System.out.println(ex.getBindingResult().getFieldErrors());
        Map<String, Object> fieldError = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            fieldError.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("data", null);
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("fieldError", fieldError);
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("error", exception.getMessage());
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("from", "from controller");
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
