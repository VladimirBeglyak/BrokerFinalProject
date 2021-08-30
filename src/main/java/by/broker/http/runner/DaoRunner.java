package by.broker.http.runner;

import by.broker.http.dao.ClientDao;
import by.broker.http.dao.CurrenciDao;
import by.broker.http.dao.StockDao;
import by.broker.http.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
        CurrenciDao currenciDao = CurrenciDao.getInstance();

        ClientDao clientDao=ClientDao.getInstance();

        StockDao stockDao=StockDao.getInstance();

        /*Optional<Client> byId = clientDao.findById(3L);
        System.out.println(byId);*/

        /*Optional<Stock> byId = stockDao.findById(7L);
        System.out.println(byId);*/

        Optional<Client> byId1 = clientDao.findById(1L);
        System.out.println(byId1);





        /*Optional<Stock> byId = stockDao.findById(6);
        System.out.println(byId);*/

        /*Optional<Client> byId = clientDao.findById(2L);
        System.out.println(byId);*/


        /*Currenci save = currenciDao.save(Currenci.builder()
                .name("Euro European Union")
                .ticker("EUR")
                .build());
        System.out.println(save);*/

       /* List<Currenci> currencis = currenciDao.findAll();
        System.out.println(currencis);*/

       /* boolean delete = currenciDao.delete(2L);
        System.out.println(delete);*/



        /*Optional<Currenci> maybeCurrenci = currenciDao.findById(2L);
        System.out.println(maybeCurrenci);

        maybeCurrenci.ifPresent(currenci -> {
            currenci.setTicker("CAN");
            currenci.setName("Canadian Dollar");
            currenciDao.update(currenci);
        });*/

        /*Currenci savedCurrenci = currenciDao.save(Currenci.builder()
                .name("Russian Rubles")
                .ticker("RUB")
                .build());

        System.out.println(savedCurrenci);*/
    }
}
