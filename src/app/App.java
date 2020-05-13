/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ArrayList<String> a = new ArrayList<>();
        Grafo grafo = new Grafo(12);
        //Digrafo digrafo = new Digrafo();
        try {
            FileInputStream arquivo = new FileInputStream("grafo.txt");
            InputStreamReader input = new InputStreamReader(arquivo);
            BufferedReader br = new BufferedReader(input);

            String linha;
            int contLinhas = 1;
            int numVertices = 0;

            do {
                linha = br.readLine();
                if (linha != null) {
                    System.out.println(linha);
                    //pega o numero de vertices
                    if (contLinhas == 2) {
                        numVertices = Integer.parseInt(linha);
                    }
                }
                contLinhas++;
            } while (contLinhas <= 2);

            //criar os objetos dos vertices
            for (int i = 0; i < numVertices; i++) {
                linha = br.readLine();
                if (linha != null) {
                    System.out.println(linha);
                    grafo.insertVertice(linha);
                }
            }

            //inserir as pontes
            String[] temp = new String[2];
            do {
                linha = br.readLine();
                if (linha != null) {
                    System.out.println(linha);
                    temp = linha.split(",");
                    grafo.insertEdge(temp[0], temp[1]);
                }
                contLinhas++;
            } while (linha != null);

            arquivo.close();
            br.close();

            System.out.println(grafo.show());
            ArrayList<String> circuito = new ArrayList<>();
            circuito = getEulerPath(grafo);
            System.out.println("Circuito encontrado: " + circuito);
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }

    public static int DFS(Grafo grafo, ArrayList visited, String from) {
        int cont = 1;
        visited.add(from);
        ArrayList<String> adj = grafo.getAdjacentes(from);
        for (String to : adj) {
            if (!visited.contains(to)) {
                cont = cont + DFS(grafo, visited, to);
            }
        }
        return cont;
    }

    public static boolean isBridge(Grafo grafo, String from, String to) {
        if (grafo.getQtdAdjacentes(from) == 1) {
            return false;
        }
        int bridgeCount = DFS(grafo, new ArrayList(), to);
        grafo.deleteEdge(from, to);
        int nonBridgeCount = DFS(grafo, new ArrayList(), to);
        grafo.insertEdge(from, to);
        return nonBridgeCount < bridgeCount;
    }

    public static void getEulerPath(Grafo grafo, ArrayList circuito, String from) {
        ArrayList<String> adj = grafo.getAdjacentes(from);
        for (String to : adj) {
            if (!isBridge(grafo, from, to)) {
                circuito.add(to);
                grafo.deleteEdge(from, to);
                getEulerPath(grafo, circuito, to);
                break;
            }
        }
    }

    public static ArrayList getEulerPath(Grafo grafo) {
        ArrayList<String> circuito = new ArrayList();
        int oddCount = grafo.getQtdVerticesImpares();
        if (oddCount == 0) {
            circuito.add(grafo.getVertices().get(0));
            getEulerPath(grafo, circuito, circuito.get(0));
        } else {
            circuito.add("O grafo fornecido não é Euleriano");
        }
        return circuito;
    }

    public static int DFS(Digrafo digrafo, ArrayList visited, Node from) {
        int cont = 1;
        visited.add(from);
        ArrayList<Node> nodes = digrafo.getAdjacentes(from);
        for (Node to : nodes) {
            if (!visited.contains(to)) {
                cont = cont + DFS(digrafo, visited, to);
            }
        }
        return cont;
    }

    public static boolean isBridge(Digrafo digrafo, Node from, Node to) {
        if (digrafo.degreeOf(from.getName()) == 1) {
            return false;
        }
        int bridgeCount = DFS(digrafo, new ArrayList(), to);
        digrafo.deleteEdge(from.getName(), to.getName());
        int nonBridgeCount = DFS(digrafo, new ArrayList(), to);
        digrafo.insertVerticeAjdTo(from.getName(), to.getName());
        return nonBridgeCount < bridgeCount;
    }

    public static void getEulerPath(Digrafo digrafo, ArrayList circuito, Node from) {
        ArrayList<Node> nodes = digrafo.getAdjacentes(from);
        for (Node to : nodes) {
            if (!isBridge(digrafo, from, to)) {
                circuito.add(to);
                digrafo.deleteEdge(from.getName(), to.getName());
                getEulerPath(digrafo, circuito, to);
                break;
            }
        }
    }

    public static ArrayList getEulerPath(Digrafo digrafo) {
        ArrayList<Node> circuito = new ArrayList();
        int oddCount = digrafo.getQtdVerticesImpares();
        if (oddCount == 0) {
            circuito.add(digrafo.getVertices().get(0));
            getEulerPath(digrafo, circuito, circuito.get(0));
        } else {
            circuito.add(new Node("O grafo fornecido não é Euleriano"));
        }
        return circuito;
    }
}
