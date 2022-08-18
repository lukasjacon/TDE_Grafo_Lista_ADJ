import java.util.Random;

public class Grafo {
    //Variaveis da classe
    public double[][] matriz;
    public int tamanho;
    public Vertice[] vertices;

    public Grafo(int tamanho) {
        this.tamanho = tamanho;
        this.matriz = new double[tamanho][tamanho];
        this.vertices = new Vertice[tamanho];

        for(int i = 0; i < tamanho; i++) {
            vertices[i] = new Vertice();
            for(int x = 0; x < tamanho; x++) {
                //Trocamos para int ao inves de double
                matriz[i][x] = Integer.MAX_VALUE;
            }
        }
    }

    //Cria adjacencia
    public void criaAdj(int origem, int destino, double custo) {
        matriz[origem][destino] = custo;
    }

    //Remove adjacencia
    public void removeAdj(int origem, int destino) {
        matriz[origem][destino] = Integer.MAX_VALUE;
    }

    //Mostra as adjacencia
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
                    System.out.print(matriz[i][x] != Integer.MAX_VALUE ? matriz[i][x] : "0");
                    break;
                }
                System.out.print((matriz[i][x] != Integer.MAX_VALUE ? matriz[i][x] : "0") + "|");
            }
            System.out.println("");
        }
    }

    //Atribui o info
    public void setaInfo(int i, String info) {
        vertices[i].info = info;
    }

    //Retorna a adjacencia
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

    //Resolvemos deixar o peso em forma de metodo
    public double peso(int i, int j) {
        return matriz[i][j];
    }

    // Passa por todos os vertices e pega o caminho com menor peso
    public void print() {
        String[] listaArestas = new String[tamanho]; 
        String s = ""; 
        int countAdds = 0; 
        double menorCusto = Integer.MAX_VALUE; 
        Random r = new Random(); 
        int v1 = -1;
        int v2 = -1;
        int[] listaVertices = new int[tamanho];
        boolean verificador = false;

        for (int i = 0; i < tamanho; i++) { 
            listaVertices[i] = -1;
            listaArestas[i] = "";
        }

        //Arruma os vetores para poder fazer o print
        while (countAdds < tamanho) {
            if (countAdds == 0) { 

                v1 = r.nextInt(tamanho); 
                listaVertices[countAdds] = v1; 
                countAdds++; 
                for (int j = 0; j < tamanho - 1; j++) { 
                    if (matriz[v1][j] < menorCusto) {
                        menorCusto = matriz[v1][j]; 
                        v2 = j;
                        s = Integer.toString(v1) + "-" + Integer.toString(v2);

                    }
                }
                
                listaArestas[countAdds] = s;
                listaVertices[countAdds] = v2;
                countAdds++;
                menorCusto = Integer.MAX_VALUE;


            } else {
                menorCusto = Integer.MAX_VALUE; 
                for (int i : listaVertices) { 

                    if (i == -1) { 
                        break; 
                    }

                    for (int j = 0; j < tamanho; j++) { 
                        verificador = false; 
                        if (matriz[i][j] < menorCusto) { 
                            for (int x : listaVertices) { 
                                if (x != -1) {
                                    if (j == x) {
                                        verificador = true; 
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            if (!verificador) { 
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

        //Mostra os vertices
        for (int i : listaVertices) {
            System.out.print(i + " ");

        }

        //Mostra as arestas que ligam os vertices
        System.out.print(" | ");
        for (String i : listaArestas) {
            System.out.print(i + " ");
        }

    }

}