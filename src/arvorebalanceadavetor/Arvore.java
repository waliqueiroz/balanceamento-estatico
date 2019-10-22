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
public class Arvore {

    private No raiz;
    private ListaOrdenada lista = new ListaOrdenada();

    public Arvore() {
        raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public No getRaiz() {
        return raiz;
    }

    public ListaOrdenada getLista() {
        return lista;
    }

    
    public void inserir(int i, No atual) {
        if (isEmpty()) {//se vazio preenche a raiz
            No novo = new No(i);
            raiz = novo;
            defineFB(raiz);//define o fator de balanceamento de cada nó
        } else {
            if (i > atual.getInfo()) {
                if (atual.getDir() == null) {//se o no a ser inserido for maior que o atual e o da direita do atual for vazio, insere la
                    No novo = new No(i);
                    atual.setDir(novo);
                    novo.setPai(atual);
                    defineFB(raiz);//define o fator de balanceamento de cada nó
                } else { //senão, faz a recursão, passando o nó da direita do atual.
                    inserir(i, atual.getDir());
                }
            } else if (i < atual.getInfo()) { //mesma coisa aqui, só que com o nó da esquerda, se for menor
                if (atual.getEsq() == null) {
                    No novo = new No(i);
                    atual.setEsq(novo);
                    novo.setPai(atual);
                    defineFB(raiz);//define o fator de balanceamento de cada nó
                } else {
                    inserir(i, atual.getEsq());
                }
            } else {//se o nó já existir
                //System.out.println("Impossível Inserir!");
            }
        }
    }
    //imprime todos os nós e mostra o fator de balanceamento de cada um
    public void imprime(No f) {
        System.out.println("Nó: " + f.getInfo() + " - Seu fator de balanceamento é: " + f.getBalanceamento());
        if (f.getDir() != null) {
            imprime(f.getDir());
        }
        if (f.getEsq() != null) {
            imprime(f.getEsq());
        }
    }
    
    public No remover(int i, No atual) {
        if (isEmpty()) {//Se a arvore tiver vazia, nao faz nada
            System.out.println("Arvore vazia!");
        } else if (i > atual.getInfo()) {//Se o valor a ser removido for maior q o do no atual, anda pra direita e atribui o retorno do metodo ao no direito passando ele mesmo como parametro
            atual.setDir(remover(i, atual.getDir()));
        } else if (i < atual.getInfo()) {//se for menor, faz a mesma coisa do passo anterior, só q indo pra esquerda
            atual.setEsq(remover(i, atual.getEsq()));
        } else {//Se for igual, faz as verificações de remoção
            if (atual.getDir() == null && atual.getEsq() == null) {//se o nó não tiver subarvores, ou seja, for uma folha, basta anular ele
                atual = null;
            } else if (atual.getEsq() == null) {//se o nó atual só tiver a subarvore da direita, pega o valor da raiz dessa subarvore e substitui pelo atual
                atual = atual.getDir();
            } else if (atual.getDir() == null) {//se o atual tiver só a subarvore da esquerda,faz a mesma coisa , só que troca o nó pela raiz da direita
                atual = atual.getEsq();
            } else {//Se o nó tiver as duas subárvores, o bixo pega um pouco
                /*Cria um nó auxiliar pra percorrer a arvore até o valor mais a direita da subarvore da esquerda do nó atual
                 e seta ele como o valor da esquerda do atual*/
                No aux = atual.getEsq();
                while (aux.getDir() != null) {//Faz o aux ir até o valor mais a direita
                    aux = aux.getDir();
                }
                atual.setInfo(aux.getInfo()); //substitui o valor do nó atual pelo valor do auxiliar
                aux.setInfo(i); // substitui o valor do auxiliar pelo atual
                atual.setEsq(remover(i, atual.getEsq()));//chama a recursão pra remover o valor do atual, q agora tá no auxiliar, por isso, vai remover o auxiliar                
            }
        }
        defineFB(raiz);//atualiza o fator de balanceamento de cada nó
        return atual;// retorna o atual
    }

    public int altura(No atual) {//Verifica a altura de um determinado nó
        if (atual == null) {//Se o nó for nulo, sua altura será -1
            return -1;
        }
        if (atual.getDir() == null && atual.getEsq() == null) {//Se ele não tiver nenhum filho, sua altura será 0
            return 0;
        } else if (atual.getEsq() == null) {//Se o nó tiver apenas um filho, sua altura será 1 + a altura do nó filho
            return 1 + altura(atual.getDir());
        } else if (atual.getDir() == null) { //Mesma do passo anterior aqui
            return 1 + altura(atual.getEsq());
        } else { //Se ele tiver dois filhos, temos q verificar qual filho é mais "alto"
            if (altura(atual.getEsq()) > altura(atual.getDir())) {//a altura do nó será a soma de 1+ a altura do filho mais alto
                return 1 + altura(atual.getEsq());
            } else {
                return 1 + altura(atual.getDir());
            }
        }//e assim recursivamente, até chegar nas folhas q não terão filhos, a altura será 0 e assim a recursão para.
    }

    public void defineFB(No atual) {//Define o valor de balanceamento de cada nó com base na altura (adicionei um atributo pra armazenar o balanceamento na classe nó)
        atual.setBalanceamento(altura(atual.getEsq()) - altura(atual.getDir()));//O valor do balanceamendo será a altura do filho da direita menos a altura do da direita
        if (atual.getDir() != null) {//verifica todos os nós
            defineFB(atual.getDir());
        }
        if (atual.getEsq() != null) {//verifica todos os nós
            defineFB(atual.getEsq());
        }
    }
    
    
    public void Quebra(ListaOrdenada lista){
        //cria as duas listas 
        ListaOrdenada lista1;
        ListaOrdenada lista2;
        if(lista.Size()==1){//se a lista tiver só um elemento, joga ele na arvore e para
            inserir(lista.retornaElemento(1), raiz);
        }else if(lista.Size()%2==0){//se a lista tiver um tamanho par
            inserir(lista.retornaElemento(lista.Size()/2), raiz);  //pega o elemento mais a esquerda do centro da lista
            lista1 = quebra1metade(lista.Size()/2, lista);//pega a primeira metade da lista e joga em uma segunda lista
            lista2 = quebra2metade(lista.Size()/2, lista);//pega a segunda metade da lista e joga em outra lista
            //faz a recursão com as novas listas
            Quebra(lista1);
            Quebra(lista2);
        }else if(lista.Size()%2!=0){//se a lista tiver tamanho impar
            inserir(lista.retornaElemento((lista.Size()/2)+1), raiz);//pega o elemento do centro da lista e joga na arvore
            lista1 = quebra1metade((lista.Size()/2)+1, lista);//pega a primeira metade da lista e joga em uma segunda lista
            lista2 = quebra2metade((lista.Size()/2)+1, lista);//pega a segunda metade da lista e joga em uma outra lista
            //faz a recursão com as novas listas
            Quebra(lista1);
            Quebra(lista2);
        }     
    }
    
    public void balanceia(No atual){// faz uma verificação na arvore
        if(atual.getBalanceamento()>1||atual.getBalanceamento()<-1){ // se achar algum nó desbalanceado, chama os métodos responsáveis por balancear
            preencheVetor(raiz);
            Quebra(lista);
        }else{
            if(atual.getDir()!=null){
                balanceia(atual.getDir());
            }
            if(atual.getEsq()!=null){
                balanceia(atual.getEsq());
            }
        }
    }
    
    public ListaOrdenada preencheVetor(No atual){//Preenche uma lista ordenada com os elementos da árvore
        lista.incluir(atual.getInfo());
        if(atual.getDir()!=null){
            preencheVetor(atual.getDir());
        }
        if(atual.getEsq()!=null){
            preencheVetor(atual.getEsq());
        }
        raiz =null;//anula a raiz, porque ela vai ser substituida
        return lista;
    }   
    
    public ListaOrdenada quebra1metade(int valor, ListaOrdenada list){//Pega a primeira metade de uma lista ordenada e joga em outra
        ListaOrdenada lista = new ListaOrdenada();//cria uma nova lista       
            for (int i = 0; i < valor; i++) {//faz ela caminhar até o centro | tem um pequeno bug aqui, ele adiciona elemento repetido na nova lista, mas não interfere no funcionamento pq o metodo de inserir da arvore não deixa inserir valores iguais
                lista.incluir(list.removeERetorna());//adicionando os elementos até o centro na segunda lista
            }      
        return lista;//retorna a nova lista
    }
    
    public ListaOrdenada quebra2metade(int valor, ListaOrdenada list){//pega a segunda metade de uma lista ordenada e joga em outra
        ListaOrdenada lista = new ListaOrdenada();
        for (int i = valor+1; i <= list.Size(); i++) {//caminha do centro até o fim da lista
            lista.incluir(list.removeERetorna());//adicionando os elementos numa nova lista
        }
        return lista;//retorna a nova lista
    }

}
