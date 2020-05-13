/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leona
 */
public class Grafo {

    protected int matriz[][];
    HashMap<String, Integer> vertices;
    
    public Grafo(int tamGrafo) {
        matriz = new int[tamGrafo][tamGrafo];
        vertices = new HashMap<>();
    }

    public int getDegree() {
        return vertices.size();
    }

    public void insertVertice(String name) {
        vertices.put(name, vertices.size());
    }

    public void insertEdge(String from, String to) {
        int source = vertices.get(from);
        int destiny = vertices.get(to);

        matriz[source][destiny] = 1;
        matriz[destiny][source] = 1;

    }

    public void deleteEdge(String from, String to) {
        int source = vertices.get(from);
        int destiny = vertices.get(to);

        matriz[source][destiny] = 0;
        matriz[destiny][source] = 0;
    }

    public int size() {
        int count = 0;

        for (int[] linha : matriz) {
            for (int valor : linha) {
                count += valor;
            }
        }

        return count / 2;
    }

    public String show() {
        String out = "";
        for (int[] linha : matriz) {
            for (int valor : linha) {
                out += valor + " ";
            }
            out += "\n";
        }
        return out;
    }

    public int getQtdVerticesImpares(){
        int cont = 0, contImpar = 0;
        for (int i = 0; i < matriz.length; i++) {
            cont = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    cont++;
                }
            }
            if (cont % 2 != 0) {
                contImpar++;
            }
        }
        return contImpar;
    }
    
    public ArrayList<String> getVertices() {
        ArrayList<String> v = new ArrayList<>();
        for (Map.Entry<String, Integer> verticeEntry : vertices.entrySet()) {
            v.add(verticeEntry.getKey());
        }
        return v;
    }

    public ArrayList<String> getAdjacentes(String from) {
        ArrayList<String> adj = new ArrayList<>();
        int linha = vertices.get(from);
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[linha][i] == 1) {
                for (Map.Entry<String, Integer> verticeEntry : vertices.entrySet()) {
                    if (verticeEntry.getValue() == i) {
                        adj.add(verticeEntry.getKey());
                    }
                }
            }
        }
        return adj;
    }

    public int getQtdAdjacentes(String from) {
        int cont = 0;
        int linha = vertices.get(from);
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[linha][i] == 1) {
                for (Map.Entry<String, Integer> verticeEntry : vertices.entrySet()) {
                    if (verticeEntry.getValue() == i) {
                        cont++;
                    }
                }
            }
        }
        return cont;
    }
}
