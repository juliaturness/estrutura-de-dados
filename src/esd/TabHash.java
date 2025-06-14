package esd;

import esd.unidade1.ListaSequencial;

public class TabHash <K, V>{

    private ListaSequencial<Par<K, V>> tab;


    public void obtem(K chave){

    }
    public void adiciona (K chave, V valor){

    }

    public ListaSequencial valores (){
        ListaSequencial<K> valor = new ListaSequencial<>();

        return valor;
    }

    public ListaSequencial chaves (){
        ListaSequencial<K> chave = new ListaSequencial<>();
        return chave;
    }

    public void remove (K chave){

    }
    public void contem(K chave ){

    }
    public void esta_vazia(){

    }

    public V obten_ou_default(K chave, V defval){
        return null;
    }

    public ListaSequencial items (){
        return null;
    }

}
