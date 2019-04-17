package ru.spaceouter.infoscan.coins;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author danil
 * @date 14.04.19
 */

@RestController
@RequestMapping("/coins")
public class UserCoinController {

    private final CoinStatusService coinStatusService;

    @Autowired
    public UserCoinController(CoinStatusService coinStatusService){
        this.coinStatusService = coinStatusService;
    }

    @GetMapping
    public UserCoinStatusDTO getCoinStatus(@RequestParam Map<String, String> params) {

        return coinStatusService.getCoinStatus(
                Integer.parseInt(params.getOrDefault("user", String.valueOf(-1))));
    }

}
