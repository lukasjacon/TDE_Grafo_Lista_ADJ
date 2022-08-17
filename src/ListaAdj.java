public class ListaAdj {
    //Guarda o destino, peso
    public int destino;
    public double peso;
    public ListaAdj proximo;

    public ListaAdj(int destino, double peso,ListaAdj proximo) {
        this.destino = destino;
        this.peso = peso;
        this.proximo = proximo;
    }
}