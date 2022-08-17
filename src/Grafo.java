import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// import org.w3c.dom.css.Counter;


public class Grafo {
    private double[][] matriz;
    private int tamanho;
    private Vertice[] vertices;
    private Boolean[][] matrizFechamento;

    public Grafo(int tamanho) {
        this.tamanho = tamanho;
        this.matriz = new double[tamanho][tamanho];
        this.vertices = new Vertice[tamanho];
        this.matrizFechamento = new Boolean[tamanho][tamanho];

        for(int i = 0; i < tamanho; i++) {
            vertices[i] = new Vertice();
            for(int x = 0; x < tamanho; x++) {
                matriz[i][x] = Integer.MAX_VALUE;
                matrizFechamento[i][x] = false;
            }
        }
    }

    public void criaAdj(int origem, int destino, double custo) {
        matriz[origem][destino] = custo;
    }

    public void removeAdj(int origem, int destino) {
        matriz[origem][destino] = Integer.MAX_VALUE;
    }

    public void imprimeAdj() {
        for(int i = 0; i < tamanho; i++) {
            if(i == tamanho - 1) {
                System.out.println(i+1);
                break;
            }else if(i == 0) {
                System.out.print("  ");
            }
            System.out.print(i+1 + "|");
        }


        for(int i = 0; i < tamanho; i++) {
            for(int x = 0; x < tamanho; x++) {
                if(x == 0) {
                    System.out.print(i+1 + " ");
                }else if(x == tamanho - 1) {
                    System.out.print(matriz[i][x]);
                    break;
                }
                System.out.print(matriz[i][x] + "|");
            }
            System.out.println("");
        }
    }

    public void setaInfo(int i, String info) {
        vertices[i].info = info;
    }

    public int[] adjacentes(int i) {
        int[] adj = new int[tamanho];

        for(int x = 0; x < tamanho; x++){
            adj[x] = -1;
        }
        for(int x = 0; x < tamanho; x++) {
            if(matriz[i][x] != Integer.MAX_VALUE) {
                adj[x] = x;
            }
        }
        return adj;
    }

    public double peso(int i, int j) {
        return matriz[i][j];
    }

    public void fechamento(){
        int i, j, k;

        // inicialização da matriz de fechamento
        for (i = 0; i < tamanho; i++ ){
            for(j = 0; j < tamanho; j++){
                if(matriz[i][j] != Integer.MAX_VALUE){
                    matrizFechamento[i][j] = true;
                }
            }
        }

        // algoritmo de Warshall
        for(k = 0; k < tamanho; k++){
            for(i = 0; i < tamanho; i++){
                if (matrizFechamento[i][k]){
                    for(j = 0; j < tamanho; j++){
                        matrizFechamento[i][j] = matrizFechamento[i][j]  ||  matrizFechamento[k][j];
                    }
                }
            }
        }

        //imprime
        for(int x = 0; x < tamanho; x++){
            System.out.println("");
            for(int y = 0; y < tamanho; y++){
                System.out.print(" "+matrizFechamento[x][y]);
            }
        }
    }

    public void Dijkstra(int x, int y){
        List<Integer> caminho = new ArrayList<Integer>();
        double[] distancia = new double[tamanho];
        int[] pai = new int[tamanho];
        boolean[] aberto = new boolean[tamanho];

        for(int i = 0; i < tamanho; i++){
            if(i == x){
                distancia[i] = 0;
                pai[i] = -1;
                aberto[i] = true;
            }else{
                distancia[i] = Integer.MAX_VALUE;
                pai[i] = -1;
                aberto[i] = true;
            }
        }

        while(true){
            double menorDist = Integer.MAX_VALUE;
            int menorIndex = -1;
            for(int i = 0; i < tamanho; i++){
                if(aberto[i] && distancia[i] < menorDist){
                    menorDist = distancia[i];
                    menorIndex = i;
                }
            }

            if(menorIndex == -1){
                break;
            }

            aberto[menorIndex] = false;
            int[] adj = new int[tamanho];
            adj = adjacentes(menorIndex);

            for(int i : adj){
                if(i != -1 && distancia[menorIndex] + peso(menorIndex, i) < distancia[i]){
                    distancia[i] = distancia[menorIndex] + peso(menorIndex, i);
                    pai[i] = menorIndex;
                }
            }
        }
        int p = y;
        while(p!=-1){
            caminho.add(p);
            p = pai[p];
        }
        Collections.sort(caminho);
        System.out.println("menor distancia entre "+x+" e "+y+" é: "+distancia[y]);
        System.out.println("Caminho entre  "+x+" e "+y+" é: "+caminho);
    }


    public void prim() { // passa por todos os vertices e pegar o caminho com menor peso
        String[] listaArestas = new String[tamanho]; // lista que salva o caminho
        String s = ""; // adiciona na lista de arestas
        int countAdds = 0; // temos um contador iniciado em 0
        double menorCusto = Integer.MAX_VALUE; // armazena o custo
        Random r = new Random(); // sorteia um vertice aleatorio
        int v1 = -1;
        int v2 = -1;
        int[] listaVertices = new int[tamanho];
        boolean verificador = false; // verificador começa falso

        for (int i = 0; i < tamanho; i++) { // inicializamos as variaveis
            listaVertices[i] = -1;
            listaArestas[i] = "";
        }
        while (countAdds < tamanho) { // enquanto for menor que o tamanho
            if (countAdds == 0) { // se o contador for igual a 0

                v1 = r.nextInt(tamanho); // pega um numero aleatorio
                listaVertices[countAdds] = v1; //adiciona na lista
                countAdds++; //incrementa
                for (int j = 0; j < tamanho - 1; j++) { // passa por todos os vertices
                    if (matriz[v1][j] < menorCusto) {// verifica se a adjacencia da matriz é menor que o menor custo
                        menorCusto = matriz[v1][j]; // pega o menor custo
                        v2 = j;
                        s = Integer.toString(v1) + "-" + Integer.toString(v2);

                    }
                }
                // substitui os valores
                listaArestas[countAdds] = s;
                listaVertices[countAdds] = v2;
                countAdds++;
                menorCusto = Integer.MAX_VALUE;


            } else {
                menorCusto = Integer.MAX_VALUE; // inicializamos a variavel de novo
                for (int i : listaVertices) { // passamos por toda a lista

                    if (i == -1) { //se o i for iual a -1
                        break; // paramos porque nao tem mais nenhum item na lista
                    }

                    for (int j = 0; j < tamanho; j++) { // percorremos todos os vertices
                        verificador = false; // verificador inicia como false
                        if (matriz[i][j] < menorCusto) { // verifica se a adjacencia é menor que o menor custo
                            for (int x : listaVertices) { // passa por todos os elementos da lista
                                if (x != -1) {
                                    if (j == x) {
                                        verificador = true; // deixa true caso ja tenha o item na lista
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            if (!verificador) { // substituimos os valores pra depois armazenar
                                menorCusto = matriz[i][j];
                                v2 = j;
                                v1 = i;
                                s = Integer.toString(v1) + "-" + Integer.toString(v2);
                            }
                        }
                    }
                }

                listaVertices[countAdds] = v2;
                listaArestas[countAdds] = s;
                countAdds++;
            }

        }
        for (int i : listaVertices) { // printamos o resultados bonitinho
            System.out.print(i + " ");

        }
        System.out.print(" | ");
        for (String i : listaArestas) {
            System.out.print(i + " ");
        }

    }

    public static void main(String[] args) {
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


        g.prim();
    }
}