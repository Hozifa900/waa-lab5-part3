package mvc.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mvc.domain.Account;
import mvc.dto.AccountCommand;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {

    public Account findByAccountnumber(Long accountnumber);

    public void deleteByAccount(Long accountnumber);

    public Account save(Account account);

}
