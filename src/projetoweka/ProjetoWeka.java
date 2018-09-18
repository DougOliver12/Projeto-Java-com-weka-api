/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoweka;

public class ProjetoWeka {

    /**
     * @param args the command line arguments
     */
    public static String caminhoDados;
   

    public static void main(String[] args) throws
            Exception {
              
        caminhoDados = "C:\\Users\\Terrible Mind\\Desktop\\ProjetoWeka\\novo.arff";
        
        Weka exemplo = new Weka(caminhoDados);
        exemplo.leDados();
        exemplo.imprimeDados();
        exemplo.arvoreDeDecisaoJ48();
        exemplo.InstanceBased();
       
    }

}
