package vallegrande.edu.pe.sistematransporte.model;

public class Vehiculo {

    private String tipo, marca, modelo;

    public Vehiculo(String t, String m, String mo){
        tipo = t;
        marca = m;
        modelo = mo;
    }

    public String getTipo(){ return tipo; }
    public String getMarca(){ return marca; }
    public String getModelo(){ return modelo; }

    public void setMarca(String marca){ this.marca = marca; }
    public void setModelo(String modelo){ this.modelo = modelo; }
}