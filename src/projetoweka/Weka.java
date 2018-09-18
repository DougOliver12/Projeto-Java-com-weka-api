/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoweka;

import java.util.Random;

import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.DenseInstance;

/**
 *
 * @author al550495579
 *
 */
public class Weka {

    private final String caminhoDados;
    private Instances dados;

    public Weka(String caminhoDados) {
        this.caminhoDados = caminhoDados;
    }

    public void leDados() throws Exception {
        DataSource fonte = new DataSource(caminhoDados);
        dados = fonte.getDataSet();
        if (dados.classIndex() == -1) {
            dados.setClassIndex(dados.numAttributes() - 1);
        } else {
        }
    }

    public void imprimeDados() {
        for (int i = 0; i < dados.numInstances(); i++) {
            Instance atual = dados.instance(i);
            System.out.println((i + 1) + ": " + atual + "\n");
        }
    }

    public void arvoreDeDecisaoJ48() throws Exception {
        J48 tree = new J48();
        tree.buildClassifier(dados);
        System.out.println(tree);
        System.out.println("Avaliacao inicial: \n");
        Evaluation avaliacao;
        avaliacao = new Evaluation(dados);
        avaliacao.evaluateModel(tree, dados);
        System.out.println("--> Instancias corretas: " + avaliacao.correct() + "\n");
        System.out.println("Avaliacao cruzada: \n");
        Evaluation avalCruzada;
        avalCruzada = new Evaluation(dados);
        avalCruzada.crossValidateModel(tree, dados, 10, new Random(1));
        System.out.println("--> Instancias corretas CV: " + avalCruzada.correct() + "\n");
        System.out.println("Chamada de linha de código: \n");
        String[] options = new String[2];
        options[0] = "-t";
        options[1] = caminhoDados;
        System.out.println(Evaluation.evaluateModel(new J48(), options));
    }
    
    public void InstanceBased() throws Exception {
       Instance newInst = new DenseInstance(11){};
        System.out.println("Avaliacao inicial: Instance Based \n");
        IBk k3 = new IBk(4);
        k3.buildClassifier(dados);
        FrmRadio frame = new FrmRadio ();  
  
       frame.setVisible(true);  
        
        newInst.setDataset(dados);
        newInst.setValue(0, 10);
        newInst.setValue(1, 12);
        newInst.setValue(2, 14);
        newInst.setValue(3, 13);
        newInst.setValue(4, 13);
        newInst.setValue(5, 12);
        newInst.setValue(6, 11);
        newInst.setValue(7, 10);
        newInst.setValue(8, 15);
        newInst.setValue(9, 11);           
        //newInst.setValue(4, "Iris-versicolor");
        double pred = k3.classifyInstance(newInst);
        System.out.println("Predição: " + pred);
        Attribute a = dados.attribute(4);
        String predClass = a.value((int) pred);
        System.out.println("Predição: " + predClass);
    }

    
}
