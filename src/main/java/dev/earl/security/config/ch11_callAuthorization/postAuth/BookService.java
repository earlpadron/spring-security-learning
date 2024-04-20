package dev.earl.security.config.ch11_callAuthorization.postAuth;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    //data source
    private Map<String, Employee> records =
            Map.of("emma",
                    new Employee("Emma Thompson",
                            List.of("Karamazov Brothers"),
                            List.of("accountant", "reader")),
                    "natalie",
                    new Employee("Natalie Parker",
                            List.of("Beautiful Paris"),
                            List.of("researcher"))
            );


    /**
     * . Observe that the expression we use with the
     * @PostAuthorize annotation refers to the value returned by the method
     * returnObject. The postauthorization expression can use the value returned
     * by the method, which is available after the method executes.

     */
    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name){
        return records.get(name);
    }

}
