/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebalanceadavetor;

/**
 *
 * @author Wali
 */
public class ListaOrdenada {

    private No inicio;
    private int numPos;
    

    public ListaOrdenada() {
        inicio = null;
    }

    public int Size() {
        return numPos;
    }

    public boolean isEmpty() {
        return (inicio == null);
    }

    public void incluir(int e) {
        No novo = new No(e, null);
        if ((isEmpty()) || (e < inicio.getInfo())) {
            novo.setProximo(inicio);
            inicio = novo;
            numPos++;
            //System.out.println(inicio.getProximo());
        } else {
            No temp = inicio;

            while ((temp.getProximo() != null) && (e > temp.getProximo().getInfo())) {
                temp = temp.getProximo();
            }
            novo.setProximo(temp.getProximo());
            temp.setProximo(novo);
            numPos++;
                //System.out.println(temp.getProximo());

        }
    }

    public boolean Remover(int e) {
        No temp;
        if ((isEmpty()) || (e < inicio.getInfo())) {
            return false;
        } else if (e == inicio.getInfo()) {
            inicio = inicio.getProximo();
            numPos--;
            return true;
        } else {
            temp = inicio;
            while ((temp.getProximo() != null) && (e > temp.getProximo().getInfo())) {
                temp = temp.getProximo();
            }
            if ((temp.getProximo() != null) && (e == temp.getProximo().getInfo())) {
                No aux = temp.getProximo();
                temp.setProximo(aux.getProximo());
                numPos--;
                return true;
            }

        }
        return false;
    }
    
    public int retornaElemento(int pos){//retorna o elemento da posição inserida
        int cont = 1;
        No temp = inicio;
        while(cont < pos){
            temp = temp.getProximo();
            cont++;
        }
        return temp.getInfo();
    }
    
    public int removeERetorna(){//remove e retorna os elementos da fila em ordem
        int valor = inicio.getInfo();
        inicio = inicio.getProximo();
        return valor;
    }
    
    
    
    public void imprimir(){
        No temp = inicio;
        while(temp!=null){
            System.out.println(temp.getInfo());
            temp=temp.getProximo();
        }
    }

}
