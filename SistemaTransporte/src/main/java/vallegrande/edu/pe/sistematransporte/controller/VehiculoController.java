package vallegrande.edu.pe.sistematransporte.controller;

import vallegrande.edu.pe.sistematransporte.model.Vehiculo;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class VehiculoController {

    ArrayList<Vehiculo> lista = new ArrayList<>();

    public void agregar(Vehiculo v){
        lista.add(v);
    }

    public void eliminar(int i){
        if(i >= 0 && i < lista.size()){
            lista.remove(i);
        }
    }

    public void editar(int i, String marca, String modelo){
        if(i >= 0 && i < lista.size()){
            lista.get(i).setMarca(marca);
            lista.get(i).setModelo(modelo);
        }
    }

    public ArrayList<Vehiculo> getLista(){
        return lista;
    }

    // 🔍 BUSCADOR (extra)
    public ArrayList<Vehiculo> buscar(String marca){
        return lista.stream()
                .filter(v -> v.getMarca().toLowerCase().contains(marca.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // 📊 TOTAL (extra)
    public int total(){
        return lista.size();
    }
}