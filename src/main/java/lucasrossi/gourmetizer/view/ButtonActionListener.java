package lucasrossi.gourmetizer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lucasrossi.gourmetizer.model.Prato;
import lucasrossi.gourmetizer.service.PratoService;

/**
 * JFrame: tratamento do botão "Já pensei!", que originalmente era "Ok"
 * 
 * @author Lucas Rossi
 */
public class ButtonActionListener implements ActionListener {

    private PratoService pratoService = new PratoService();
    private Prato pratoSelecionado = null;

    /**
     * Pergunta para o jogador se o prato que ele pensou "é um" adjetivo da lista de
     * pratos, se sim, pergunta se é o prato. Se não, continua percorrendo os
     * adjetivos da lista de pratos, até esvaziar, então pergunta o nome do prato
     * (text input do usuário), e seu adjetivo. A última pergunta antes de perguntar
     * o nome do prato sempre é o Bolo de Chocolate.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for (Prato prato : pratoService.obterPratos()) {
            this.pratoSelecionado = prato;

            Boolean existeAdjetivo = exibePergunta(prato.getAdjetivo());

            if (existeAdjetivo == true) {
                checkPergunta(exibePergunta(prato.getNome()));
                return;
            }

        }

        exibePerguntaBoloChocolate();

    }

    /**
     * Sempre é a última pergunta. Se não for o bolo, o usuário poderá escrever o
     * nome do prato.
     */
    private void exibePerguntaBoloChocolate() {
        Prato boloDeChocolate = new Prato("Bolo de chocolate");

        this.pratoSelecionado = boloDeChocolate;

        checkPergunta(exibePergunta(boloDeChocolate.getNome()));
    }

    /**
     * Checa se acertou o prato. Se sim -> exibe mensagem de sucesso. Se não -> novo
     * prato é criado.
     * 
     * @param simOuNao
     */
    private void checkPergunta(Boolean simOuNao) {
        if (simOuNao == true) {
            exibeMensagemSucesso();
        } else if (simOuNao == false) {
            criaNovoPrato();
        }
    }

    /**
     * Pergunta se é algum prato da lista
     * 
     * @param pratoInfo
     * @return
     */
    private Boolean exibePergunta(String pratoInfo) {
        Object[] options = { "Sim", "Não" };
        // return SimOuNao.getByCodigo(
        if (JOptionPane.showOptionDialog(null, "O prato que você pensou é ".concat(pratoInfo).concat("?"), "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 1) {
            return false;
        } else {
            return true;
        }
    }

    private void exibeMensagemSucesso() {
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
    }

    /**
     * Cria novo prato com as infos inputadas pelo usuário (nome do prato e ajetivo)
     */
    private void criaNovoPrato() {
        // String nome = JOptionPane.showInputDialog("Em qual prato você pensou?");
        String nome = JOptionPane.showInputDialog(null, "Em qual prato vocë pensou?", "Desisto",
                JOptionPane.QUESTION_MESSAGE);

        if (nome == null || nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Eita! Prato não informado. =(", "Confirm", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String adjetivo = JOptionPane.showInputDialog(
                nome.concat(" é _______, mas ").concat(this.pratoSelecionado.getNome()).concat(" não"));

        if (adjetivo == null || adjetivo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Adjetivo do prato não informado", "Confirm",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        PratoService.salvar(new Prato(nome, adjetivo));

    }

}