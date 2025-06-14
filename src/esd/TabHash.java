package esd;

import esd.unidade1.ListaSequencial;

public class TabHash <K, V>{

    private ListaSequencial<Par<K, V>> tab = new ListaSequencial<>();
    int len;
    final int defcap =31;

    public TabHash(){
        for (int i=0; i < defcap; i++){
            tab.adiciona(null);
        }
    }


    public void obtem(K chave){

    }
    public void adiciona (K chave, V valor){
     int hash = chave.hashCode() % tab.comprimento();

        Par par = tab.obtem(hash);
        if(par == null){
            tab.substitui(hash, new Par<>(chave, valor));
            len++;
        } else if (par.temChave(chave)){
            par.valor = valor;
        } else {
            throw new IndexOutOfBoundsException("posicação ocupada");
        }
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
    public boolean esta_vazia(){
        return tab.esta_vazia();
    }

    public V obten_ou_default(K chave, V defval){
        return null;
    }

    public ListaSequencial items (){
        return null;
    }

}
