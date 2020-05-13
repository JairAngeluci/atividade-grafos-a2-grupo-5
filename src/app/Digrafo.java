/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author leona
 */
public class Digrafo {

    private LinkedList<Node> verticesAdj;
    HashMap<String, Integer> index;

    public Digrafo() {
        verticesAdj = new LinkedList<>();
        index = new HashMap<>();
    }

    public int degree() {
        return verticesAdj.size();
    }

    public int size() {
        int count = 0;
        for (Node no : verticesAdj) {
            Node aux = no.getNext();
            while (aux != null) {
                count++;
                aux = aux.getNext();
            }
        }
        return count;
    }

    private Node findVertice(String name) {
        for (Node no : verticesAdj) {
            if (no.getName().equals(name)) {
                return no;
            }
        }
        return null;
    }

    public boolean containsVertice(String name) {
        if (findVertice(name) != null) {
            return true;
        }
        return false;
    }

    public boolean insertNewVertice(String name) {
        if (containsVertice(name)) {
            return false;
        }
        verticesAdj.add(new Node(name));
        index.put(name, index.size());
        return true;
    }

    public boolean insertVerticeAjdTo(String from, String to) {
        Node origin = findVertice(from);
        if (origin == null) {
            return false;
        }
        Node aux = origin;
        while (aux.getNext() != null) { // localiza o final da lista
            if (aux.getName().equalsIgnoreCase(to)) { // proibido inserir uma aresta que já existe no grafo
                return false;
            }
            aux = aux.getNext();
        }
        insertNewVertice(to); // insere na lista de nós caso não tenha inserido antes
        aux.addNext(new Node(to));
        return true;
    }

    public boolean deleteEdge(String from, String to) {
        Node origin = findVertice(from);
        Node destiny = findVertice(to);
        if (origin == null || destiny == null) {
            return false;
        }
        Node atual = origin;
        Node anterior = origin;
        while (atual != null) { // localiza o final da lista
            if (atual.getName().equalsIgnoreCase(to)) { // proibido inserir uma aresta que já existe no grafo
                anterior.addNext(atual.getNext());
                return true;
            }
            anterior = atual;
            atual = atual.getNext();
        }
        return false;
    }

    public int degreeOf(String vertice) {
        Node node = findVertice(vertice);
        if (node == null) {
            return 0;
        }
        Node aux = node;
        int count = 0;
        while (aux.getNext() != null) {
            count++;
            aux = aux.getNext();
        }
        return count;
    }

    public int getQtdVerticesImpares() {
        int grau, contImpar = 0;
        for (int i = 0; i < verticesAdj.size(); i++) {
            grau = degreeOf(verticesAdj.get(i).getName());
            if (grau % 2 != 0) {
                contImpar++;
            }
        }
        return contImpar;
    }

    public String show() {
        String out = "";

        for (Node node : verticesAdj) {
            Node aux = node;

            while (aux != null) {
                out += aux.getName() + "->";
                aux = aux.getNext();
            }
            out += "/\n";
        }
        return out;
    }

    public ArrayList<Node> getAdjacentes(Node from) {
        ArrayList<Node> adj = new ArrayList<>();
        Node aux = from;
        while (aux != null) {
            aux = aux.getNext();
            adj.add(aux);
        }
        return adj;
    }
    
    public LinkedList<Node> getVertices(){
        return verticesAdj;
    }
}
