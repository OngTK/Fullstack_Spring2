package example.day12_250930;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/axios")
@Log4j2
public class AxiosController {

    // [1]
    @GetMapping
    public int axios1 (){

        return 10;
    };

} // class end
