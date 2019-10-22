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
public class ArvoreBalanceadaVetor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arvore a = new Arvore();
        
        a.inserir(50, a.getRaiz());
        a.inserir(30, a.getRaiz());
        a.inserir(60, a.getRaiz());
        a.inserir(40, a.getRaiz());
        a.inserir(20, a.getRaiz());
        a.inserir(10, a.getRaiz());
        a.inserir(25, a.getRaiz());

        
        System.out.println("Antes de balancear: ");
        a.imprime(a.getRaiz());//imprime a arvore sem balancear                
        
        System.out.println("Depois de balancear: ");
        a.balanceia(a.getRaiz());//balanceia a arvore usando a lista ordenada
        a.imprime(a.getRaiz());//imprime a arvore ordenada
    }
    
}
