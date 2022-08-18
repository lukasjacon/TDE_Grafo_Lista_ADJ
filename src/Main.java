public class Main {
    public static void main(String[] args) {
        //No main cria a adjacencia
        Grafo g = new Grafo(7);
        // 0
        g.criaAdj(0,1,5);
        g.criaAdj(0,2,7);
        // 1
        g.criaAdj(1,0,5);
        g.criaAdj(1,2,9);
        g.criaAdj(1,4,15);
        g.criaAdj(1,5,6);
        // 2
        g.criaAdj(2,0,7);
        g.criaAdj(2,1,9);
        g.criaAdj(2,3,8);
        g.criaAdj(2,4,7);
        // 3
        g.criaAdj(3,2,8);
        g.criaAdj(3,4,5);
        // 4
        g.criaAdj(4,2,7);
        g.criaAdj(4,3,5);
        g.criaAdj(4,5,8);
        g.criaAdj(4,6,9);
        g.criaAdj(4,1,15);
        // 5
        g.criaAdj(5,1,6);
        g.criaAdj(5,4,8);
        g.criaAdj(5,6,11);
        // 6
        g.criaAdj(6,4,9);
        g.criaAdj(6,5,11);

        //Mostra na tela o nosso grafo.
        g.print();

        /*
        System.out.println("\n------------------------------");
        g.imprimeAdj();
        */
    }
}
