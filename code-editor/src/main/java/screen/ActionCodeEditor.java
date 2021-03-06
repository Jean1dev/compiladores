/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import analisador.ExecutorSemantico;
import analisador.ExecutorSintatico;
import main.analisador.ExecutorLexico;
import main.arquivo.ArquivoUtils;
import main.resultado.ResultadoExecucao;
import main.token.CaracterAnalisadoInfo;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author jeanfernandes
 */
public class ActionCodeEditor {

    private ResultadoExecucao resultadoExecucao;

    private static final String DEFAULT_FILE_NAME = "arquivo.txt";

    public void salvar(String conteudo) {
        ArquivoUtils.gravarArquivo(conteudo);
    }

    public void compile() {
        resultadoExecucao = new ExecutorLexico(DEFAULT_FILE_NAME).analisarLexicamente();
        new ExecutorSintatico(resultadoExecucao).doSintatico();
        new ExecutorSemantico(resultadoExecucao).executar();
    }

    public String getStatusCompilacao() {
        return resultadoExecucao.getStatusAnalise().toString();
    }

    public List<String> getMensagensValidacao() {
        return resultadoExecucao.getMensagensValidacao();
    }

    public Stack<CaracterAnalisadoInfo> getPilha() {
        return resultadoExecucao.getStack();
    }
}
