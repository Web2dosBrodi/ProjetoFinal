/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import redis.clients.jedis.Jedis;

/**
 *
 * @author White
 */
public class Cache {

    Jedis jedis = null;

    public Cache() {
        //Connecting to Redis server on localhost 
        jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not 
        System.out.println("Server is running: " + jedis.ping());

//    Jedis jedis = new Jedis("hostname.redislabs.com", 6379);
//    jedis.auth("password");
//    System.out.println("Connected to Redis");
    }

    public String procuraDado(String dado) {
        String result = jedis.get("lista:eventos");
        if (dado.equals(result)) {
            return result;
        } else {
            return null;
        }
    }

    public String procuraUltimoDado(String dado) {
        String result = jedis.get("lista:evento:ultima"); //chave = idUser+lista
        if (dado.equals(result)) {
            return result;
        } else {
            return null;
        }
    }

    public void gravaDados(String json) {
        jedis.set("lista:eventos", json);
    }

    public void gravaUltimaPesquisa(String json) {
        jedis.set("lista:evento:ultimo", json);
    }
}
