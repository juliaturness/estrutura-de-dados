package esd;

public class Par<K, V>{
    private K chave;
    public V valor;
    private TabHash<K, V> novo_atributo;
    public Par(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }
    public K obtemChave(){
        return chave;
    }

//    public obtem_par(chave){
//
//    }

    public V obtemValor(){
        return valor;
    }

    public boolean temChave(K chave){

        return false;
    }



}
