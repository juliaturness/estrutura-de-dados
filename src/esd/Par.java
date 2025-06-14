package esd;

import com.sun.source.tree.BreakTree;

public class Par<K, V>{
    private K chave;
    private V valor;
    private TabHash<K, V> novo_atributo;
    public Par(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }
    public K obtemChave(){
        return chave;
    }

    public V obtemValor(){
        return valor;
    }



}
