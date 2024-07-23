package dao.generic;

import java.util.HashMap;
import java.util.Map;

public class SingletonMap {

    private static SingletonMap singletonMap;

    /**
     * Contém todos os registros da aplicação.
     * Simula o banco de dados.
     */
    protected Map<Class<?>, Map<?, ?>> map;

    private SingletonMap() {
        map = new HashMap<>();
    }

    /**
     * Método que garante o retorno de apenas uma instância desse objeto.
     * 
     * @return SingletonMap
     */
    public static SingletonMap getInstance() {
        if (singletonMap == null) {
            singletonMap = new SingletonMap();
        }
        return singletonMap;
    }

    /**
     * Obtém o mapa correspondente a uma classe específica.
     * 
     * @param clazz
     * @return Map<?, ?>
     */
    public Map<?, ?> getMap(Class<?> clazz) {
        return map.get(clazz);
    }

    /**
     * Adiciona um novo mapa para uma classe específica.
     * 
     * @param clazz
     * @param map
     */
    public void addMap(Class<?> clazz, Map<?, ?> map) {
        this.map.put(clazz, map);
    }
}
